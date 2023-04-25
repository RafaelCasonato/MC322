import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;

    //Métodos    
    public String toString() {
        return "Cliente:\nNome: " + nome + "\nEndereco: " + endereco + "\nVeículos: " + listaVeiculos;
    }

    public Boolean adicionaVeiculo(Veiculo veiculo) {
        try {
            if (listaVeiculos.contains(veiculo) == false)
                System.out.println("Veículo adicionado com sucesso.\n");    
            return listaVeiculos.add(veiculo);
        } catch (Exception e) {
            return false;
        }
    }

    //Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
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
    
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
}