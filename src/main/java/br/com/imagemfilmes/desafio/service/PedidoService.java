package br.com.imagemfilmes.desafio.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.imagemfilmes.desafio.dao.PedidoDAO;
import br.com.imagemfilmes.desafio.entity.Pedido;
import br.com.imagemfilmes.desafio.entity.PedidoItem;

@Service
public class PedidoService {

    @Autowired
    DataSource dataSource;

    public List<Pedido> getPedidos() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            return new PedidoDAO(connection).findAll();
        }
    }

    public List<Pedido> getPedidosPorCliente(long idCliente) throws Exception {
        return this.getPedidos().stream().filter(p -> p.getPessoa().getId() == idCliente).collect(Collectors.toList());
    }

    public List<Pedido> getPedidosOrdenadoPorValor() throws Exception {
        Function<PedidoItem, BigDecimal> totalMapper = (item) -> {
            BigDecimal valorUnitario = item.getProduto().getValorUnitario();
            BigDecimal quantidade = BigDecimal.valueOf(item.getQuantidade());
            return quantidade.multiply(valorUnitario);
        };

        var pedidos = this.getPedidos();

        pedidos.sort((p1, p2) -> {
            BigDecimal total1 = p1.getItens().stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal total2 = p2.getItens().stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);
            return total1.compareTo(total2);
        });

        return pedidos;
    }
}
