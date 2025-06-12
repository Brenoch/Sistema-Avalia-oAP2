package util;

public class AvaliacaoLocalizacao extends Avaliacao {
    private float notaLocalizacao;
    private Restaurante restaurante;
    private Cliente cliente;

    public AvaliacaoLocalizacao(float notaLocalizacao) {
        super();
        this.notaLocalizacao = notaLocalizacao;
        this.restaurante = null;
        this.cliente = null;
    }


    public AvaliacaoLocalizacao(int idAvaliacao, float notaLocalizacao, Restaurante restaurante, Cliente cliente) {
        super();
        setIdAvaliacao(idAvaliacao);
        this.notaLocalizacao = notaLocalizacao;
        this.restaurante = restaurante;
        this.cliente = cliente;
    }



    public float getNotaLocalizacao() {
        return notaLocalizacao;
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
        String restauranteNome = (restaurante != null) ? restaurante.getNome() : "Desconhecido";
        String clienteNome = (cliente != null) ? cliente.getNome() : "Desconhecido";
        System.out.println("Comentário sobre a localização com nota: " + notaLocalizacao +
                " (Restaurante: " + restauranteNome + ", Cliente: " + clienteNome + ")");
    }
}