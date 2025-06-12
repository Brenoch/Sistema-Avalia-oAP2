package util;

public interface ClienteInterface {
    int getIdcliente();
    String getCpf();
    String getNome();
    String getEmail();
    String getSenha();
    void setIdcliente(int idcliente);
    String toString();
}