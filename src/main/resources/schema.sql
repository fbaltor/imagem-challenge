CREATE TABLE pessoa (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cpf VARCHAR(11) NOT NULL,
  nome VARCHAR(250) NOT NULL
);

CREATE TABLE produto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  descricao VARCHAR(50) NOT NULL,
  valor_unitario NUMERIC(10,2) NOT NULL
);

CREATE TABLE pedido (
  id INT AUTO_INCREMENT PRIMARY KEY,
  pessoa_id INT
);

CREATE TABLE pedido_item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  produto_id INT,
  quantidade INT,
  pedido_id INT
);

