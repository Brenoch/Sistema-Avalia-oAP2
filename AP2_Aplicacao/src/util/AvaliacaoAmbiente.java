package util;

public class AvaliacaoAmbiente extends Avaliacao {
    private float notaAmbiente;
    private Restaurante restaurante;
    private Cliente cliente;


    public AvaliacaoAmbiente(float notaAmbiente) {
        super();
        this.notaAmbiente = notaAmbiente;

    }

    public AvaliacaoAmbiente(int idAvaliacao, float notaAmbiente, Restaurante restaurante, Cliente cliente) {
        super();
        setIdAvaliacao(idAvaliacao);
        this.notaAmbiente = notaAmbiente;
        this.restaurante = restaurante;
        this.cliente = cliente;
    }

    public float getNotaAmbiente() {
        return notaAmbiente;
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
        System.out.println("Comentário sobre o ambiente com nota: " + notaAmbiente);
        if (restaurante != null) {
            System.out.println("No restaurante: " + restaurante.getNome());
        }
        if (cliente != null) {
            System.out.println("Feito por: " + cliente.getNome());
        }
    }
}