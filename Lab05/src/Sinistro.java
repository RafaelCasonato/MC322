import java.util.Random;
import java.util.Date;
import java.util.ArrayList;

public class Sinistro {
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;
    private final int id;
    private static ArrayList<Integer> listaIds = new ArrayList<Integer>();

    //Métodos 
    public String toString() {
        return "Sinistro - :" + id + "\nData: " + data + "\nEndereço: " + endereco + "\nSeguro: "
            + seguro.getId() + "\nCondutor: " + condutor.getNome() + "\n";
    }
    
    public int gerarId() {
        Random numero = new Random();
        int id;
        id = numero.nextInt(0, 10000);
        if ((listaIds == null) || (listaIds.size() == 0)) {
            listaIds.add(id);
            return id;
        }
        while (listaIds.contains(id))
            id = numero.nextInt(0, 10000);
        listaIds.add(id);
        return id;
    }
    
    //Construtor
    public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
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

    public ArrayList<Integer> getListaIds() {
        return listaIds;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }
    
}