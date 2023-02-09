INSERT INTO pessoa (id, cpf, nome)
VALUES
  	(1, '56637927082', 'Pedro'), -- id 1
  	(2, '56637927082', 'Joao'); -- id 2

INSERT INTO produto (id, descricao, valor_unitario)
VALUES
  	(1, 'Produto 1', 50.0), -- id 1
  	(2, 'Produto 2', 20.0), -- id 2
  	(3, 'Produto 3', 30.0), -- id 3
  	(4, 'Produto 4', 2.0), -- id 3
  	(5, 'Produto 5', 100.0), -- id 3
  	(6, 'Produto 6', 35.0); -- id 3

INSERT INTO pedido (id, pessoa_id)
VALUES
	(1, 1), -- Pedido de id 1 da Pessoa 1
	(2, 2); -- Pedido de id 2 da Pessoa 2

INSERT INTO pedido_item (id, produto_id, quantidade, pedido_id)
VALUES
	(1, 1, 2, 1), -- Item de id 1 relacionado ao Pedido de id 1
	(2, 2, 1, 2), -- Item de id 2 relacionado ao Pedido de id 2
	(3, 3, 1, 2); -- Item de id 3 relacionado ao Pedido de id 2
