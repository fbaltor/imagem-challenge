package br.com.imagemfilmes.desafio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.imagemfilmes.desafio.entity.Pedido;
import br.com.imagemfilmes.desafio.entity.PedidoItem;
import br.com.imagemfilmes.desafio.entity.Pessoa;
import br.com.imagemfilmes.desafio.entity.Produto;

public class PedidoDAO extends DAO {
    
    public PedidoDAO(final Connection connection) {
        super(connection);
    }

    public List<Pedido> findAll() throws SQLException {

        String query  = 
            """
            SELECT
                pedido_item.id as id,
                pedido.id AS pedido_id,
                pedido_item.produto_id AS registro,
                pedido_item.quantidade AS quantidade,
                produto.descricao AS descricao,
                produto.valor_unitario AS valor_unitario,
                pedido.pessoa_id AS pessoa_id,
                pessoa.nome AS pessoa_nome,
                pessoa.cpf AS pessoa_cpf
            FROM pedido_item
            JOIN pedido
                ON pedido_item.pedido_id = pedido.id
            JOIN produto
                ON pedido_item.produto_id = produto.id
            JOIN pessoa
                ON pedido.pessoa_id = pessoa.id
            ORDER BY pedido.id"""
        ;

        try (PreparedStatement psmt = this.getConnection().prepareStatement(query)) {
            try (ResultSet rs = psmt.executeQuery()) {
                Map<Long, Pedido> pedidosMap = new HashMap<>();

                while (rs.next()) {
                    final long pedidoId = rs.getLong("pedido_id");

                    Pedido pedido;
                    if (!pedidosMap.containsKey(pedidoId)) {
                        final Pessoa pessoa = new Pessoa()
                                .setId(rs.getInt("pessoa_id"))
                                .setCpf(rs.getString("pessoa_cpf"))
                                .setNome(rs.getString("pessoa_nome"));

                        pedido = new Pedido()
                                .setId(pedidoId)
                                .setPessoa(pessoa)
                                .setItens(new ArrayList<PedidoItem>());
                    } else {
                        pedido = pedidosMap.get(pedidoId);
                    }

                    final Produto produto = new Produto()
                            .setRegistro(rs.getInt("registro"))
                            .setDescricao(rs.getString("descricao"))
                            .setValorUnitario(rs.getBigDecimal("valor_unitario"));
                
                    final PedidoItem pedidoItem = new PedidoItem()
                            .setId(rs.getLong("id"))
                            .setProduto(produto)
                            .setQuantidade(rs.getInt("quantidade"));

                    List<PedidoItem> itens = pedido.getItens();
                    itens.add(pedidoItem);
                    pedido.setItens(itens);

                    pedidosMap.put(pedidoId, pedido);
                }

                return new ArrayList<Pedido>(pedidosMap.values());
            }
        }
    }

}
