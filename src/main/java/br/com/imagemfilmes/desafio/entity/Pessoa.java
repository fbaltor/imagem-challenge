package br.com.imagemfilmes.desafio.entity;

public class Pessoa {

    private long id;
    private String cpf;
    private String nome;

    public long getId() {
        return id;
    }

    public Pessoa setId(long id) {
        this.id = id;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Pessoa setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
