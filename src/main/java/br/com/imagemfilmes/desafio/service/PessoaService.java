package br.com.imagemfilmes.desafio.service;

import br.com.imagemfilmes.desafio.dao.PessoaDAO;
import br.com.imagemfilmes.desafio.entity.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    DataSource dataSource;

    public List<Pessoa> getPessoas() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            return new PessoaDAO(connection).findAll();
        }
    }

    public List<Pessoa> getPessoasOrdenadoPorNome() throws Exception {
        var pessoas = this.getPessoas();
        pessoas.sort((p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()));
        return pessoas;
    }
}
