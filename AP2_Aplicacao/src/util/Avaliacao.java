package util;

public abstract class Avaliacao {
    private int idAvaliacao;

    public Avaliacao() {
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    protected abstract void comentar();
}