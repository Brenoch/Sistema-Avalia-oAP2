package util;

public class AvaliacaoComida extends Avaliacao {
    private float notaComida;
    private Restaurante restaurante;
    private Cliente cliente;

    public AvaliacaoComida(float notaComida) {
        super();
        this.notaComida = notaComida;
    }

    public AvaliacaoComida(int idAvaliacao, float notaComida, Restaurante restaurante, Cliente cliente) {
        super();
        setIdAvaliacao(idAvaliacao);
        this.notaComida = notaComida;
        this.restaurante = restaurante;
        this.cliente = cliente;
    }

    public float getNotaComida() {
        return notaComida;
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
        System.out.println("Comentário sobre a comida com nota: " + notaComida);
        if (restaurante != null) {
            System.out.println("No restaurante: " + restaurante.getNome());
        }
        if (cliente != null) {
            System.out.println("Feito por: " + cliente.getNome());
        }
    }
}