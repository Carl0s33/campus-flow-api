-- =========================================================================
-- MIGRATION V3: Inserção de Todas as Disciplinas da Matriz Curricular TADS
-- =========================================================================

INSERT INTO disciplines (id, name, code, teacher, color, absences, workload, period, n1, n2) VALUES

-- 1º PERÍODO
('tec-0772', 'Empreendedorismo', 'TEC.0772', NULL, '#FB923C', 0, 30, 1, NULL, NULL),
('tec-0002', 'Língua Inglesa', 'TEC.0002', NULL, '#60A5FA', 0, 60, 1, NULL, NULL),
('tec-0006', 'Matemática Discreta', 'TEC.0006', NULL, '#FBBF24', 0, 60, 1, NULL, NULL),
('tec-1005', 'Programação de Computadores', 'TEC.1005', NULL, '#34D399', 0, 90, 1, NULL, NULL),
('tec-0031', 'Seminário de Integração Acadêmica', 'TEC.0031', NULL, '#F472B6', 0, 4, 1, NULL, NULL),
('tec-0013', 'Web Design', 'TEC.0013', NULL, '#A78BFA', 0, 60, 1, NULL, NULL),

-- 2º PERÍODO
('tec-1007', 'Algoritmos', 'TEC.1007', NULL, '#38BDF8', 0, 60, 2, NULL, NULL),
('tec-0004', 'Epistemologia da Ciência', 'TEC.0004', NULL, '#FACC15', 0, 30, 2, NULL, NULL),
('tec-1006', 'Inglês para Informática', 'TEC.1006', NULL, '#818CF8', 0, 60, 2, NULL, NULL),
('tec-0001', 'Língua Portuguesa', 'TEC.0001', NULL, '#E879F9', 0, 60, 2, NULL, NULL),
('tec-1008', 'Programação Orientada a Objetos', 'TEC.1008', NULL, '#4ADE80', 0, 90, 2, NULL, NULL),

-- 3º PERÍODO
('tec-0017', 'Arquitetura de Computadores', 'TEC.0017', NULL, '#2DD4BF', 0, 60, 3, NULL, NULL),
('tec-0012', 'Computador e Sociedade', 'TEC.0012', NULL, '#FB923C', 0, 30, 3, NULL, NULL),
('tec-0018', 'Interface Humano-Computador', 'TEC.0018', NULL, '#F472B6', 0, 60, 3, NULL, NULL),
('tec-0005', 'Metodologia do Trabalho Científico', 'TEC.0005', NULL, '#FBBF24', 0, 30, 3, NULL, NULL),
('tec-0016', 'Redes de Computadores', 'TEC.0016', NULL, '#60A5FA', 0, 60, 3, NULL, NULL),
('tec-0035', 'Seminário de Iniciação à Pesquisa e à Extensão', 'TEC.0035', NULL, '#C084FC', 0, 30, 3, NULL, NULL),
('tec-0710', 'Sistemas Digitais', 'TEC.0710', NULL, '#34D399', 0, 60, 3, NULL, NULL),

-- 4º PERÍODO
('tec-0021', 'Análise e Projeto Orientado a Objetos', 'TEC.0021', NULL, '#A78BFA', 0, 60, 4, NULL, NULL),
('tec-1009', 'Bancos de Dados', 'TEC.1009', NULL, '#38BDF8', 0, 60, 4, NULL, NULL),
('tec-0037', 'Desenvolvimento de Projetos I', 'TEC.0037', NULL, '#FB923C', 0, 125, 4, NULL, NULL),
('tec-0019', 'Desenvolvimento de Sistemas Web', 'TEC.0019', NULL, '#4ADE80', 0, 90, 4, NULL, NULL),
('tec-0020', 'Estrutura de Dados Lineares', 'TEC.0020', NULL, '#FBBF24', 0, 60, 4, NULL, NULL),
('tec-0032', 'Seminário de Orientação ao Projeto de Desenvolvimento de Sistema Web', 'TEC.0032', NULL, '#E879F9', 0, 30, 4, NULL, NULL),

-- 5º PERÍODO
('tec-0025', 'Arquitetura de Software', 'TEC.0025', NULL, '#60A5FA', 0, 60, 5, NULL, NULL),
('tec-0038', 'Desenvolvimento de Projetos II', 'TEC.0038', NULL, '#FB923C', 0, 125, 5, NULL, NULL),
('tec-0023', 'Desenvolvimento de Sistemas Distribuídos', 'TEC.0023', NULL, '#A78BFA', 0, 90, 5, NULL, NULL),
('tec-0024', 'Processo de Software', 'TEC.0024', NULL, '#34D399', 0, 60, 5, NULL, NULL),
('tec-0026', 'Programação e Administração de Banco de Dados', 'TEC.0026', NULL, '#38BDF8', 0, 60, 5, NULL, NULL),
('tec-0033', 'Seminário de Orientação ao Projeto de Desenvolvimento de Sistema Distribuído', 'TEC.0033', NULL, '#F472B6', 0, 30, 5, NULL, NULL),

-- 6º PERÍODO
('tec-0039', 'Desenvolvimento de Projetos III', 'TEC.0039', NULL, '#FB923C', 0, 125, 6, NULL, NULL),

-- 7º PERÍODO
('tec-1011', 'Gestão de Tecnologia da Informação', 'TEC.1011', NULL, '#60A5FA', 0, 60, 7, NULL, NULL),
('tec-0036', 'Seminário de Orientação para Trabalho de Conclusão de Curso', 'TEC.0036', NULL, '#FBBF24', 0, 30, 7, NULL, NULL),

-- OPTATIVAS (period = 0)
('tec-0075', 'Aplicações com Interfaces Ricas', 'TEC.0075', NULL, '#A78BFA', 0, 60, 0, NULL, NULL),
('tec-0071', 'Cálculo Diferencial e Integral', 'TEC.0071', NULL, '#38BDF8', 0, 60, 0, NULL, NULL),
('tec-0077', 'Desenvolvimento de Jogos', 'TEC.0077', NULL, '#4ADE80', 0, 60, 0, NULL, NULL),
('tec-0076', 'Desenvolvimento para Dispositivos Móveis', 'TEC.0076', NULL, '#F472B6', 0, 60, 0, NULL, NULL),
('tec-0073', 'Engenharia de Requisitos', 'TEC.0073', NULL, '#34D399', 0, 60, 0, NULL, NULL),
('tec-0324', 'Informática', 'TEC.0324', NULL, '#60A5FA', 0, 30, 0, NULL, NULL),
('tec-0371', 'LIBRAS', 'TEC.0371', NULL, '#E879F9', 0, 30, 0, NULL, NULL),
('tec-0042', 'Métodos Quantitativos', 'TEC.0042', NULL, '#FBBF24', 0, 60, 0, NULL, NULL),
('tec-1004', 'Organização de Computadores', 'TEC.1004', NULL, '#2DD4BF', 0, 60, 0, NULL, NULL),
('tec-0080', 'Paradigmas de Linguagens de Programação', 'TEC.0080', NULL, '#A78BFA', 0, 60, 0, NULL, NULL),
('tec-0069', 'Psicologia nas Relações do Trabalho', 'TEC.0069', NULL, '#FB923C', 0, 60, 0, NULL, NULL),
('tec-0074', 'Qualidade de Software', 'TEC.0074', NULL, '#34D399', 0, 60, 0, NULL, NULL),
('tec-0372', 'Qualidade de Vida e Trabalho', 'TEC.0372', NULL, '#F472B6', 0, 30, 0, NULL, NULL),
('tec-0078', 'Segurança e Integridade de Dados', 'TEC.0078', NULL, '#38BDF8', 0, 60, 0, NULL, NULL)

ON CONFLICT (id) DO NOTHING;
