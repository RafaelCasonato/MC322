import java.util.ArrayList;


public abstract class Cliente {
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    //Métodos    
    public String toString() {
        return "Cliente:\nNome: " + nome + "\nEndereco: " + endereco + "\nTelefone: " + telefone + "\nEmail: " + email;
    }

    public boolean listaFrotas() {
        return true;
    }

    public void listaVeiculos() {

    }

    public ArrayList<Frota> getlistaFrotas() {
        return null;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return null;
    }

    //Construtor
    public Cliente(String nome, String telefone, String endereco, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDado() {
        return "";
    }

    public String getTipo() {
        return "";
    }
}