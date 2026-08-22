-- Criação da tabela de Disciplinas
CREATE TABLE IF NOT EXISTS disciplines (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255),
    teacher VARCHAR(255),
    color VARCHAR(255),
    absences INTEGER DEFAULT 0,
    workload INTEGER DEFAULT 60,
    period INTEGER,
    n1 DOUBLE PRECISION,
    n2 DOUBLE PRECISION
);

-- Criação da tabela de Horários (Schedules)
CREATE TABLE IF NOT EXISTS schedules (
    id VARCHAR(255) PRIMARY KEY,
    discipline_id VARCHAR(255) NOT NULL,
    day_of_week INTEGER NOT NULL,
    start_time VARCHAR(255) NOT NULL,
    end_time VARCHAR(255) NOT NULL,
    room VARCHAR(255),
    CONSTRAINT fk_schedules_discipline FOREIGN KEY (discipline_id) REFERENCES disciplines(id) ON DELETE CASCADE
);

-- Criação da tabela de Tarefas (Tasks)
CREATE TABLE IF NOT EXISTS tasks (
    id VARCHAR(255) PRIMARY KEY,
    discipline_id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date VARCHAR(255) NOT NULL,
    type VARCHAR(255) DEFAULT 'atividade',
    priority VARCHAR(255) DEFAULT 'media',
    completed BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_tasks_discipline FOREIGN KEY (discipline_id) REFERENCES disciplines(id) ON DELETE CASCADE
);

-- Criação da tabela de Provas (Exams)
CREATE TABLE IF NOT EXISTS exams (
    id VARCHAR(255) PRIMARY KEY,
    discipline_id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    date VARCHAR(255) NOT NULL,
    time VARCHAR(255),
    topics VARCHAR(255),
    location VARCHAR(255),
    CONSTRAINT fk_exams_discipline FOREIGN KEY (discipline_id) REFERENCES disciplines(id) ON DELETE CASCADE
);
