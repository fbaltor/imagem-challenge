INSERT INTO pessoa (cpf, nome)
VALUES
  	('56637927082', 'Pessoa 1'), -- id 1
  	('56637927082', 'Pessoa 2'); -- id 2

INSERT INTO produto (descricao, valor_unitario)
VALUES
  	('Produto 1', 10.0), -- id 1
  	('Produto 2', 20.0), -- id 2
  	('Produto 3', 20.0); -- id 3

INSERT INTO pedido (pessoa_id)
VALUES
	(1), -- Pedido de id 1 da Pessoa 1
	(2); -- Pedido de id 2 da Pessoa 2

INSERT INTO pedido_item (produto_id, quantidade, pedido_id)
VALUES
	(1, 2, 1), -- Item de id 1 relacionado ao Pedido de id 1
	(2, 1, 2), -- Item de id 2 relacionado ao Pedido de id 2
	(3, 1, 2); -- Item de id 3 relacionado ao Pedido de id 2
