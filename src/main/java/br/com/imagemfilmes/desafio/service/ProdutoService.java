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

}
