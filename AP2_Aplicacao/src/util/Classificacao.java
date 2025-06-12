package util;

import java.sql.Date;

public class Classificacao {
    private int idClassificacao;
    private Restaurante restaurante;
    private Cliente cliente;
    private float notaFinal;
    private Date dataClassificacao;

    private AvaliacaoComida avaliacaoComida;
    private AvaliacaoAmbiente avaliacaoAmbiente;
    private AvaliacaoAtendimento avaliacaoAtendimento;
    private AvaliacaoLocalizacao avaliacaoLocalizacao;

    public Classificacao(Restaurante restaurante, Cliente cliente, AvaliacaoComida avaliacaoComida, AvaliacaoAmbiente avaliacaoAmbiente, AvaliacaoAtendimento avaliacaoAtendimento, AvaliacaoLocalizacao avaliacaoLocalizacao) {
        this.restaurante = restaurante;
        this.cliente = cliente;
        this.avaliacaoComida = avaliacaoComida;
        this.avaliacaoAmbiente = avaliacaoAmbiente;
        this.avaliacaoAtendimento = avaliacaoAtendimento;
        this.avaliacaoLocalizacao = avaliacaoLocalizacao;
    }

    public Classificacao(int idClassificacao, Restaurante restaurante, Cliente cliente, float notaFinal, Date dataClassificacao) {
        this.idClassificacao = idClassificacao;
        this.restaurante = restaurante;
        this.cliente = cliente;
        this.notaFinal = notaFinal;
        this.dataClassificacao = dataClassificacao;
    }

    public Classificacao() {
    }

    public float calcularClassificacao() {
        float somaNotas = 0.0f;
        int numeroDeAvaliacoesValidas = 0;

        if (avaliacaoComida != null) {
            somaNotas += avaliacaoComida.getNotaComida();
            numeroDeAvaliacoesValidas++;
        }
        if (avaliacaoAmbiente != null) {
            somaNotas += avaliacaoAmbiente.getNotaAmbiente();
            numeroDeAvaliacoesValidas++;
        }
        if (avaliacaoAtendimento != null) {
            somaNotas += avaliacaoAtendimento.getNotaAtendimento();
            numeroDeAvaliacoesValidas++;
        }
        if (avaliacaoLocalizacao != null) {
            somaNotas += avaliacaoLocalizacao.getNotaLocalizacao();
            numeroDeAvaliacoesValidas++;
        }

        if (numeroDeAvaliacoesValidas > 0) {
            return somaNotas / numeroDeAvaliacoesValidas;
        } else {
            return 0.0f;
        }
    }

    public int getIdClassificacao() {
        return idClassificacao;
    }

    public void setIdClassificacao(int idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(float notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Date getDataClassificacao() {
        return dataClassificacao;
    }

    public void setDataClassificacao(Date dataClassificacao) {
        this.dataClassificacao = dataClassificacao;
    }

}