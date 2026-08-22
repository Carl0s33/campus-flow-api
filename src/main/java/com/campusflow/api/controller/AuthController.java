package com.campusflow.api.controller;

import com.campusflow.api.dto.LoginRequestDTO;
import com.campusflow.api.dto.LoginResponseDTO;
import com.campusflow.api.dto.RegisterRequestDTO;
import com.campusflow.api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${app.upload.dir:uploads/photos}")
    private String uploadDir;

    /** POST /api/auth/register — Cria novo usuário */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponseDTO register(@Valid @RequestBody RegisterRequestDTO dto) {
        return authService.register(dto);
    }

    /** POST /api/auth/login — Login por email OU matrícula + senha */
    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return authService.login(dto);
    }

    /** POST /api/auth/upload-photo/{userId} — Faz upload da foto de perfil */
    @PostMapping(value = "/upload-photo/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LoginResponseDTO uploadPhoto(
            @PathVariable String userId,
            @RequestParam("photo") MultipartFile photo) {
        return authService.uploadPhoto(userId, photo);
    }

    /** GET /api/auth/photos/{filename} — Serve a foto de perfil salva */
    @GetMapping("/photos/{filename}")
    public ResponseEntity<Resource> servePhoto(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = filename.endsWith(".png") ? "image/png" : "image/jpeg";
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
