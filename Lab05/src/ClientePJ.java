import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private int idade;
    private ArrayList<Frota> listaFrotas;

    //Métodos
    public int calculaIdade() { 
    /* Função que calcula a idade de um cliente. */
        Date dataHoje = new Date();
        long diferencaTempo = dataHoje.getTime() - dataFundacao.getTime();
        long idade = TimeUnit.MILLISECONDS.toDays(diferencaTempo) / 365;
        return (int)idade;
    }

    public boolean cadastrarFrota(Scanner input) {
        Frota frota = new Frota(cnpj);
        System.out.println("Código da frota: ");
        String code = input.nextLine();
        System.out.println("Quantos veículos deseja adicionar na frota?");
        int quantidade = input.nextInt();
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Placa: ");
            String placa = input.nextLine();
            frota.adicionaVeiculo(placa, input);
        }
        frota.setCode(code);
        return listaFrotas.add(frota);
    }

    public boolean cadastrarFrota(Frota f) {
        if (!listaFrotas.contains(f)) {
            listaFrotas.add(f);
            return true;
        }
        System.out.println("Frota ja existente.");
        return false;
    }

    public boolean atualizarFrota(Frota f) {
        listaFrotas.remove(f);
        System.out.println("Frota removida com sucesso.\n");
        return true;
    }

    public boolean atualizarFrota(Frota f, boolean acao) {
        Scanner input = new Scanner(System.in);
        System.out.println("Placa do veículo: ");
        String placa = input.nextLine();
        boolean retorno = false;
        if (acao) {
            retorno = f.adicionaVeiculo(placa, input);
            input.close();
            return retorno;
        }
        if (!acao) {
            retorno = f.removerVeiculo(placa);
            input.close();
            return retorno;
        }
        input.close();
        return false;
    }

    public boolean atualizarFrota(String code, boolean acao) {
            for (Frota f : listaFrotas) {
                if (f.getCode().equals(code))
                    return atualizarFrota(f, acao);
            }
            return false;
    }

    public ArrayList<Veiculo> getVeiculosPorFrota(String code) {
        ArrayList<Veiculo> veiculosFrota = new ArrayList<Veiculo>();
        for (Frota f : listaFrotas) {
            if (f.getCode().equals(code)) {
                for (Veiculo v : f.getListaVeiculos())
                    veiculosFrota.add(v);
            }
        }
        return veiculosFrota;
    }

    @Override
    public boolean listaFrotas() {
        if (listaFrotas == null || listaFrotas.size() == 0) {
            System.out.println("Nenhuma frota registrada.");
            return false;
        }
        for (int i = 0; i < listaFrotas.size(); i++) {
            System.out.println(i + "-" + listaFrotas.get(i));
        }
        return true;
    }

    //Construtor
    public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, Date dataFundacao) {
        super (nome, telefone, endereco, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.listaFrotas = new ArrayList<Frota>();
        this.idade = calculaIdade();
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nCNPJ: " + cnpj + "\nData de Fundação: " + dataFundacao + "\nFrotas: " + listaFrotas;
    }
    public String getDado() {
        return cnpj;
    }
    public String getTipo() {
        return "pj";
    }
    //Getters e Setters
    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getNome() {
        return super.getNome();
    }

    public ArrayList<Frota> getlistaFrotas() {
        return listaFrotas;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
