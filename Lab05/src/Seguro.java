import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;
    private static ArrayList<Integer> listaIds = new ArrayList<Integer>();
    public static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    //Getters e setters
    public int getId() {
        return id;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public double getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public Cliente getCliente() {
        return null;
    }
    //Construtor
    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        this.id = gerarId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.valorMensal = 0;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
    }

    //Métodos
    public boolean cadastrarCondutor(Scanner input) throws Exception {
        System.out.println("Nome: ");
        String nome = input.nextLine();
        boolean validacao = true;
        validacao = Validacao.validarNome(nome);
        if (!validacao) {
            System.out.println("Nome inválido, tente novamente.");
            return false;
        }
        System.out.println("CPF: ");
        String cpf = input.nextLine();
        String dado = cpf.replaceAll("[^\\d]", "");
        validacao = Validacao.validarCpf(dado);
        if (!validacao) {
            System.out.println("CPF inválido, tente novamente.");
            return false;
        }
        System.out.println("Telefone: ");
        String telefone = input.nextLine();
        System.out.println("Endereço: ");
        String endereco = input.nextLine();
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Data de Nascimento (dd/MM/aaaa): ");
        String dataPura = input.nextLine();
        Date data = formataData.parse(dataPura);
        Condutor condutor = new Condutor(cpf, nome, telefone, endereco, email, data);
        return listaCondutores.add(condutor);
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
    
    public int quantidadeSinistrosCondutores() {
        int quantidade = 0;
        for (Condutor c : listaCondutores) {
            if (c.getListaSinistros() == null) {
                return 0;
            }
            quantidade += c.getListaSinistros().size();
        }   
        return quantidade;
    }

    public abstract double calcularValor();

    public abstract String getDado();

    public void listarSinistros() {
        System.out.println("=====Sinistros do Seguro " + id + "=====\n");
        for (Sinistro s : listaSinistros) {
            System.out.println(s);
        }
    }

    public Boolean gerarSinistro(Scanner input, Seguro seguro) throws Exception {
        System.out.println("Data do ocorrido (dd/MM/aaaa): ");
        String dataPura = input.nextLine();
        Date data = formataData.parse(dataPura);
        System.out.println("Endereço onde ocorreu: ");
        String endereco = input.nextLine();
        System.out.println("CPF do condutor: \n");
        String cpf = input.nextLine();
        String dado = cpf.replaceAll("[^\\d]", "");
        boolean verificacao = true;
        verificacao = Validacao.validarCpf(dado);
        if (!verificacao) {
            System.out.println("CPF inválido, tente novamente.");
            return false;
        }
        boolean encontrou = false;
        Condutor condutor = new Condutor(cpf, cpf, cpf, cpf, cpf, data);
        for (Condutor c : listaCondutores) {
            if (c.getCpf().equals(cpf)) {
                condutor = c;
                encontrou = true;
            }
        }
        if (encontrou) {
            Sinistro sinistro = new Sinistro(data, endereco, condutor, seguro);
            condutor.getListaSinistros().add(sinistro);
            return seguro.getListaSinistros().add(sinistro);
        }
        System.out.println("Condutor não encontrado.\n");
        return false;
    }
    public boolean gerarSinistro(Sinistro sinistro, Condutor condutor) {
        boolean gerou = true;
        if (!listaSinistros.contains(sinistro)) {
            listaSinistros.add(sinistro);
            gerou = true;
        }
        if (!condutor.getListaSinistros().contains(sinistro)) {
            condutor.getListaSinistros().add(sinistro);
            gerou = true;
        }
        if (!gerou) {
            System.out.println("Sinistro ja existente.");
        }
        return gerou;
    }

    public boolean removerSinistro(int id) {
        for (Sinistro s : listaSinistros) {
            if (s.getId() == id) {
                listaSinistros.remove(s);
                for (Condutor c : listaCondutores) {
                    if (s.getCondutor().equals(c)) {
                        c.getListaSinistros().remove(s);
                    }
                }
                System.out.println("Sinistro removido com sucesso");
                return true;
            }
        }
        return false;
    }

    public Boolean autorizarCondutor(Condutor condutor) {
        if (!listaCondutores.contains(condutor)) {
            listaCondutores.add(condutor);
            System.out.println("Condutor autorizado com sucesso.\n");
            return true;
        }
        System.out.println("Condutor já autorizado.\n");
        return false;
    }

    public Boolean desautorizarCondutor(Condutor condutor) {
        if (listaCondutores.contains(condutor)) {
            listaCondutores.remove(condutor);
            System.out.println("Condutor desautorizado.\n");
            return true;
        }
        System.out.println("Condutor não encontrado/autorizado.\n");
        return false;
    }

    public void listarCondutores() {
        System.out.println("=====Condutores do cliente=====\n");
        for (Condutor c : listaCondutores) {
            System.out.println(c);
        } 
    }
    @Override
    public String toString() {
        return "Seguro - " + id + ": \nData de início: " + dataInicio + "\nData do fim: " + dataFim + "\nSeguradora: " + seguradora
                + "\nLista de sinistros: " + listaSinistros + "\nLista de condutores: " + listaCondutores + "\nValor mensal: "
                + valorMensal + "\n";
    }
}