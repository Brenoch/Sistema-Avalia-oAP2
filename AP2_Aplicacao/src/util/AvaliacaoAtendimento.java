package util;

public class AvaliacaoAtendimento extends Avaliacao {
    private float notaAtendimento;
    private Restaurante restaurante;
    private Cliente cliente;

    public AvaliacaoAtendimento(float notaAtendimento) {
        super();
        this.notaAtendimento = notaAtendimento;
    }

    public AvaliacaoAtendimento(int idAvaliacao, float notaAtendimento, Restaurante restaurante, Cliente cliente) {
        super();
        setIdAvaliacao(idAvaliacao);
        this.notaAtendimento = notaAtendimento;
        this.restaurante = restaurante;
        this.cliente = cliente;
    }

    public float getNotaAtendimento() {
        return notaAtendimento;
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

    @Override
    protected void comentar() {
        System.out.println("Comentário sobre o atendimento com nota: " + notaAtendimento);
        if (restaurante != null) {
            System.out.println("No restaurante: " + restaurante.getNome());
        }
        if (cliente != null) {
            System.out.println("Feito por: " + cliente.getNome());
        }
    }
}