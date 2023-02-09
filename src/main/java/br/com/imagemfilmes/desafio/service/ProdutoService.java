package br.com.imagemfilmes.desafio.service;

import br.com.imagemfilmes.desafio.dao.ProdutoDAO;
import br.com.imagemfilmes.desafio.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    DataSource dataSource;

    public List<Produto> getProdutos() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            return new ProdutoDAO(connection).findAll();
        }
    }

    public List<Produto> getProdutosOrdenadoPorPreco() throws Exception {
        var produtos = this.getProdutos();
        produtos.sort((p1, p2) -> p1.getValorUnitario().compareTo(p2.getValorUnitario()));
        return produtos;
    }

}
