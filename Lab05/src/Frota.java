import java.util.ArrayList;
import java.util.Scanner;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos;
    
    //Métodos
    public String toString() {
        return "Frota - " + code + ": " + "\nVeículos: " + listaVeiculos + "\n";
    }

    public void listarVeiculos() {
        System.out.println("=====Veículos de " + code + "=====\n");
        for (Veiculo v : listaVeiculos) {
            System.out.println(v);
        }
    }
    public boolean adicionaVeiculo(String placa, Scanner input) {
    /* Função que recebe os dados de um veículo e, caso ainda não exista, o adiciona na lista de veículos do cliente. */
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().equals(placa)) {
                return false;
            }
        }
        System.out.println("Modelo: ");
        String modelo = input.nextLine();
        System.out.println("Marca: ");
        String marca = input.nextLine();
        System.out.println("Ano de Fabricação: ");
        int ano = input.nextInt();
        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
        listaVeiculos.add(veiculo);
        return true;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if(!listaVeiculos.contains(veiculo)) {
            listaVeiculos.add(veiculo);
            return true;
        }
        System.out.println("Veiculo ja existente.");
        return false;
    }

    public boolean removerVeiculo(String placa) {
    /* Função que remove um veículo dos vinculados a um cliente a partir de sua placa. */
        if (listaVeiculos == null || listaVeiculos.size() == 0) {
            System.out.println("Nenhum veículo encontrado.\n");    
            return false;
        }
            for (Veiculo v : listaVeiculos) {
            if ((v.getPlaca()).equals(placa)) {
                System.out.println("Veículo removido com sucesso.\n");
                return listaVeiculos.remove(v);
            }
        }
        System.out.println("Nenhum veículo encontrado.\n");
        return false;
    }

    //Construtor
    public Frota(String code) {
        this.code = code;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    //Getters e setters
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
}