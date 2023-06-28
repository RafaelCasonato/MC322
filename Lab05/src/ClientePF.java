import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private Date dataNascimento;
    private String educacao;
    private String genero;
    private int idade;
    private ArrayList<Veiculo> listaVeiculos;

    //Construtor
    public ClientePF(String nome, String telefone, String endereco, String email, String educacao, String genero, Date dataNascimento, String cpf) { 
        super (nome, telefone, endereco, email);
        this.genero = genero;
        this.educacao = educacao;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade();
        this.listaVeiculos = new ArrayList<Veiculo>();
        }
    
    //Métodos 
    public void listarVeiculos() {
        System.out.println("=====Veículos de " + (super.getNome()) + "=====\n");
        for (Veiculo v : listaVeiculos) {
            System.out.println(v);
        }
    }

    public boolean cadastrarVeiculo(String placa, Scanner input) {
    /* Função que recebe os dados de um veículo e o adiciona na lista de veículos do cliente. */
        System.out.println("Modelo: ");
        String modelo = input.nextLine();
        System.out.println("Marca: ");
        String marca = input.nextLine();
        System.out.println("Ano de Fabricação: ");
        int ano = input.nextInt();
        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
        return listaVeiculos.add(veiculo);
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if(!listaVeiculos.contains(veiculo)) {
            listaVeiculos.add(veiculo);
            return true;
        }
        System.out.println("Veiculo ja existente.");
        return false;
    }
    public double fatorIdade() {
    /* Função que calcula o fator de idade para o cálculo do preço do seguro. */
        if (idade < 30)
            return CalcSeguro.FATOR_menor30.getFator();
        else if (idade >= 30 || idade <=60)
            return CalcSeguro.FATOR_30_60.getFator();
        return CalcSeguro.FATOR_maior60.getFator();
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

    public int calculaIdade() { 
    /* Função que calcula a idade de um cliente. */
        Date dataHoje = new Date();
        long diferencaTempo = dataHoje.getTime() - dataNascimento.getTime();
        long idade = TimeUnit.MILLISECONDS.toDays(diferencaTempo) / 365;
        return (int)idade;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento + "\nEducação: " + educacao +
        "\nGênero: " + genero + "\nVeículos: " + listaVeiculos;
    }
    public String getDado() {
        return cpf;
    }
    public String getTipo() {
        return "pf";
    }
    public void listaVeiculos() {
        if (listaVeiculos == null || listaVeiculos.size() == 0)
            System.out.println("Nenhum veículo registrado");
        for (int i = 0; i < listaVeiculos.size(); i++) {
            System.out.println(i + "-" + listaVeiculos.get(i));
        }
    }
    //Getters e Setters
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEducacao() {
        return educacao;
    }
    public void seteducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return super.getNome();
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
}   
