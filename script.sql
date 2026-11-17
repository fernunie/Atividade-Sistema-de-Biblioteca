EXERCÍCIO 2: Modelagem do Banco de Dados

-- 1. Tabela de Usuários
CREATE TABLE usuario (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('aluno', 'professor')) NOT NULL,
    limite_itens INT NOT NULL
);

-- 2. Tabela de Itens (Livros e Revistas unificados com a coluna tipo)
CREATE TABLE item (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('livro', 'revista')) NOT NULL,
    autor VARCHAR(100),
    edicao INT,
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

-- 3. Tabela de Empréstimos (depende de item e usuario)
CREATE TABLE emprestimo (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INT REFERENCES item(id) NOT NULL,
    usuario_id INT REFERENCES usuario(id) NOT NULL,
    data_retirada DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao DATE,
    valor_multa NUMERIC(10, 2) DEFAULT 0.00
);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
('Carlos Silva', 'aluno', 3),
('Prof. Dr. Ricardo', 'professor', 5);

INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
('L01', 'Clean Code', 'livro', 'Robert C. Martin', NULL, FALSE),
('L02', 'O Senhor dos Anéis', 'livro', 'J.R.R. Tolkien', NULL, TRUE),
('R01', 'Superinteressante', 'revista', NULL, 450, FALSE),
('R02', 'National Geographic', 'revista', NULL, 300, TRUE);

INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa) VALUES
(1, 1, '2026-11-01', '2026-11-15', NULL, 0.00),
(3, 2, '2026-10-01', '2026-10-08', '2026-10-10', 2.00);


-- EXERCÍCIO 3: Consultas SQL

SELECT codigo, titulo, tipo, disponivel 
FROM item;

SELECT e.id AS emprestimo_id, u.nome AS usuario, i.titulo AS item, e.data_retirada, e.data_devolucao_prevista
FROM emprestimo e
JOIN usuario u ON e.usuario_id = u.id
JOIN item i ON e.item_id = i.id
WHERE e.data_devolucao IS NULL;

SELECT u.nome AS usuario, SUM(e.valor_multa) AS total_multas
FROM emprestimo e
JOIN usuario u ON e.usuario_id = u.id
GROUP BY u.id, u.nome;

SELECT i.id, i.codigo, i.titulo, i.tipo
FROM item i
LEFT JOIN emprestimo e ON i.id = e.item_id
WHERE e.id IS NULL;