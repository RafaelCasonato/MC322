import java.util.Random;
import java.util.Date;
import java.util.ArrayList;

public class Sinistro {
    private Date data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    private final int id;
    private static ArrayList<Integer> listaIds;

    //Métodos 
    public String toString() {
        return "Sinistro:\nData: " + data + "\nEndereço: " + endereco + "\nSeguradora: " + seguradora.getNome() + "\nVeículo: "
            + veiculo.getModelo() + "-" + veiculo.getPlaca() + "\nCliente: " + cliente.getNome() + "\n";
    }
    
    public int gerarId() {
        Random numero = new Random();
        int id;
        id = numero.nextInt(0, 10000);
        if (listaIds.size() == 0) {
            listaIds.add(id);
            return id;
        }
        while (listaIds.contains(id))
            id = numero.nextInt(0, 10000);
        listaIds.add(id);
        return id;
    }
    
    //Construtor
    public Sinistro(Date data, String endereco, Seguradora seguradora, Cliente cliente, Veiculo veiculo) {
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
        listaIds = new ArrayList<Integer>();
        this.id = gerarId();
    }

    //Getters e Setters
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Integer> getListaIds() {
        return listaIds;
    }
}