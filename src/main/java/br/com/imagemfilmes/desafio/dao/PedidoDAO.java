package br.com.imagemfilmes.desafio.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.imagemfilmes.desafio.entity.Pedido;

public class PedidoDAO extends DAO {
    
    public PedidoDAO(final Connection connection) {
        super(connection);
    }

    public List<Pedido> findAll() throws SQLException {

        String query  = 
            """
            SELECT
                pedido_item.id as id,
                pedido.id AS pedido,
                pedido_item.produto_id AS produto,
                pedido_item.quantidade AS quantidade,
                produto.descricao AS descricao,
                produto.valor_unitario AS valor_unitario,
                pedido.pessoa_id AS pessoa,
                pessoa.nome AS pessoa_nome,
                pessoa.cpf AS pessoa_cpf
            FROM pedido_item
            join pedido
                ON pedido_item.pedido_id = pedido.id
            JOIN produto
                ON pedido_item.produto_id = produto.id
            JOIN pessoa
                ON pedido.pessoa_id = pessoa.id
            ORDER BY pedido""";

        return List.of();
    }

}
