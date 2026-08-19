-- Inserção das Disciplinas Iniciais (6º Período)
INSERT INTO disciplines (id, name, code, teacher, color, absences, workload, period, n1, n2) VALUES
('d1', 'Estrutura de Dados Não-Lineares', 'TEC.0027', 'Leandro Luttiane', '#FBBF24', 0, 60, 6, NULL, NULL),
('d2', 'Teste de Software', 'TEC.0030', 'Mauricio Rabello', '#34D399', 1, 60, 6, NULL, NULL),
('d3', 'Desenvolvimento de Sistemas Corporativos', 'TEC.0028', 'Eliezio Soares', '#60A5FA', 0, 80, 6, NULL, NULL),
('d4', 'Seminário de Orientação ao Projeto', 'TEC.0034', 'Eliezio Soares', '#F472B6', 0, 40, 6, NULL, NULL),
('d5', 'Sistemas Operacionais', 'TEC.1010', 'Ronaldo Junior', '#A78BFA', 2, 60, 6, NULL, NULL),
('d6', 'Gerência de Projetos', 'TEC.0029', 'Mauricio Rabello', '#FB923C', 0, 60, 6, NULL, NULL)
ON CONFLICT (id) DO NOTHING;

-- Inserção dos Horários (Schedules)
INSERT INTO schedules (id, discipline_id, day_of_week, start_time, end_time, room) VALUES
('s1', 'd1', 1, '07:00', '08:30', 'Lab 04 - Bloco B'),
('s2', 'd1', 1, '08:50', '10:20', 'Lab 04 - Bloco B'),
('s3', 'd5', 1, '10:30', '12:00', 'Lab 01 - Bloco A'),
('s4', 'd6', 2, '07:00', '08:30', 'Sala 105'),
('s5', 'd5', 2, '08:50', '10:20', 'Lab 01 - Bloco A'),
('s6', 'd2', 2, '10:30', '12:00', 'Lab 03'),
('s7', 'd6', 3, '07:00', '08:30', 'Sala 105'),
('s8', 'd6', 3, '08:50', '10:20', 'Sala 105'),
('s9', 'd2', 3, '10:30', '12:00', 'Lab 03'),
('s10', 'd3', 4, '07:00', '08:30', 'Lab 02 - Bloco A'),
('s11', 'd3', 4, '08:50', '10:20', 'Lab 02 - Bloco A'),
('s12', 'd4', 5, '10:30', '12:00', 'Auditório 02')
ON CONFLICT (id) DO NOTHING;

-- Inserção das Tarefas (Tasks)
INSERT INTO tasks (id, discipline_id, title, description, due_date, type, priority, completed) VALUES
('t1', 'd1', 'Implementação de Árvores AVL', NULL, '2026-08-20T23:59:00Z', 'trabalho', 'media', FALSE),
('t2', 'd2', 'Plano de Testes Unitários (Jest)', NULL, '2026-08-15T18:00:00Z', 'trabalho', 'media', FALSE),
('t3', 'd3', 'Arquitetura Spring Boot & Microserviços', NULL, '2026-08-19T22:00:00Z', 'trabalho', 'media', FALSE),
('t4', 'd4', 'Proposta do Projeto de Sistemas', NULL, '2026-08-22T12:00:00Z', 'atividade', 'media', FALSE),
('t5', 'd5', 'Estudo Dirigido: Escalonamento de Processos', NULL, '2026-08-25T10:00:00Z', 'atividade', 'media', FALSE),
('t6', 'd6', 'Cronograma EAP e Matriz RACI', NULL, '2026-08-28T23:59:00Z', 'trabalho', 'media', FALSE)
ON CONFLICT (id) DO NOTHING;

-- Inserção das Provas (Exams)
INSERT INTO exams (id, discipline_id, title, date, time, topics, location) VALUES
('e1', 'd1', 'Prova 1 - Estruturas Não Lineares', '2026-08-23', '08:50', 'Árvores AVL e Grafos', NULL)
ON CONFLICT (id) DO NOTHING;
