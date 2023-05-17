import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private int qtdeVeiculos;
    private double valorSeguro;

    //Métodos    
    public String toString() {
        return "Cliente:\nNome: " + nome + "\nEndereco: " + endereco + "\nVeículos: " + listaVeiculos;
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

    public boolean removerVeiculo(String placa) {
    /* Função que remove um veículo dos vinculados a um cliente a partir de sua placa. */
        if (listaVeiculos.size() == 0) {
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

    public void listarVeiculos() {
    /* Função que imprime todos os veículos vinculados ao cliente. */
        for (Veiculo v : listaVeiculos) {
            System.out.println(v);
            System.out.println("\n===================");
        }
    }

    public Veiculo getVeiculo(String placa) {
    /* Função que busca um veículo pela placa na lista de veículos de um cliente. */
        Veiculo veiculo = null;
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().equals(placa))
                veiculo = v;
        }
        return veiculo;
    }

    public double calculaScore() {
        return 1;
    }

    //Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
        this.qtdeVeiculos = listaVeiculos.size();
        this.valorSeguro = 0;
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

    public int getQtdeVeiculos() {
        return qtdeVeiculos;
    }

    public void setQtdeVeiculos(int qtdeVeiculos) {
        this.qtdeVeiculos = qtdeVeiculos;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}