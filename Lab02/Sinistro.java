import java.util.Random;

public class Sinistro {
    private String data;
    private String endereco;
    private int id;
    
    //Construtor
    public Sinistro(String data, String endereco) {
        Random numero = new Random(); //
        this.data = data;
        this.endereco = endereco;
        this.id = numero.nextInt(0, 10000);
    }

    //Getters e Setters
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}