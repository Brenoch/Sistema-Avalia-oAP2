package util;

public class Local {
    private int idLocal;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String cep;

    public Local(int idLocal, String cidade, String bairro, String rua, int numero, String cep) {
        this.idLocal = idLocal;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public Local(String cidade, String bairro, String rua, int numero, String cep) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }


    @Override
    public String toString() {
        return "Local{" +
                "idLocal=" + idLocal +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                '}';
    }
}