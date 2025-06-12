package util;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements ClienteInterface {
    private int idcliente;
    private String cpf;
    private String nome;
    private String email;
    private String senha;

    private List<Classificacao> classificacoes;



    public Cliente(int idcliente, String cpf, String nome, String email, String senha) {
        this.idcliente = idcliente;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.classificacoes = new ArrayList<>();
    }

    public Cliente(String cpf, String nome, String email, String senha) {
        this(0, cpf, nome, email, senha);
        this.classificacoes = new ArrayList<>();
    }

    public int getIdcliente() { return idcliente; }
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public void setIdcliente(int idcliente) { this.idcliente = idcliente; }


    @Override
    public String toString() {
        return "Cliente{" + "idcliente=" + idcliente + ", cpf='" + cpf + '\'' + ", nome='" + nome + '\'' + ", email='" + email + '\'' + ", senha='[PROTECTED]'" + ", numeroClassificacoes=" + (classificacoes != null ? classificacoes.size() : 0) + '}';
    }
}