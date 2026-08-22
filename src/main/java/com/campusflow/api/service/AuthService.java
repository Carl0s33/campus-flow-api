package com.campusflow.api.service;

import com.campusflow.api.domain.model.User;
import com.campusflow.api.domain.repository.UserRepository;
import com.campusflow.api.dto.LoginRequestDTO;
import com.campusflow.api.dto.LoginResponseDTO;
import com.campusflow.api.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Value("${app.upload.dir:uploads/photos}")
    private String uploadDir;

    @Value("${app.base-url:http://10.0.2.2:8080}")
    private String baseUrl;

    // ─── REGISTER ─────────────────────────────────────────────────────────────

    public LoginResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.existsByMatricula(dto.matricula())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Matrícula já cadastrada: " + dto.matricula());
        }
        if (userRepository.existsByEmail(dto.email().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email já cadastrado: " + dto.email());
        }

        User user = User.builder()
                .name(dto.name())
                .matricula(dto.matricula())
                .email(dto.email().toLowerCase())
                .passwordHash(hashPassword(dto.password()))
                .build();

        User saved = userRepository.save(user);
        return toDTO(saved);
    }

    // ─── LOGIN (email OU matrícula) ────────────────────────────────────────────

    public LoginResponseDTO login(LoginRequestDTO dto) {
        String identifier = dto.identifier().trim();

        // Decide se o identificador é email ou matrícula
        User user = identifier.contains("@")
                ? userRepository.findByEmail(identifier.toLowerCase())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED, "Email ou senha inválidos."))
                : userRepository.findByMatricula(identifier)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED, "Matrícula ou senha inválidos."));

        if (!user.getPasswordHash().equals(hashPassword(dto.password()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Credenciais inválidas.");
        }

        return toDTO(user);
    }

    // ─── UPLOAD DE FOTO ───────────────────────────────────────────────────────

    public LoginResponseDTO uploadPhoto(String userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arquivo vazio.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Apenas imagens são permitidas.");
        }

        try {
            // Cria o diretório de uploads se não existir
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            // Nome único para o arquivo
            String extension = getExtension(file.getOriginalFilename());
            String filename = "user_" + userId + "_" + UUID.randomUUID() + "." + extension;
            Path filePath = uploadPath.resolve(filename);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Salva a URL pública no banco
            String photoUrl = baseUrl + "/api/auth/photos/" + filename;
            user.setPhotoUrl(photoUrl);
            User saved = userRepository.save(user);

            return toDTO(saved);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao salvar foto: " + e.getMessage());
        }
    }

    // ─── HELPERS ──────────────────────────────────────────────────────────────

    private LoginResponseDTO toDTO(User user) {
        return new LoginResponseDTO(
                user.getId(),
                user.getName(),
                user.getMatricula(),
                user.getEmail(),
                user.getPhotoUrl()
        );
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            return HexFormat.of().formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao processar senha", e);
        }
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "jpg";
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }
}
