import java.util.Date;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;

    //Métodos
    @Override
    public double calculaScore() {
    /* Função que calcula o preço do seguro para uma pessoa jurídica. */
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        double valorFinal = valorBase * (1 + qtdeFuncionarios / 100) * getQtdeVeiculos();
        return valorFinal;
    }
    
    //Construtor
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int qtdeFuncionarios) {
        super (nome, endereco);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nCNPJ: " + cnpj + "\nData de Fundação: " + dataFundacao + "\n";
    }

    //Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    @Override 
    public int getQtdeVeiculos() {
        return super.getQtdeVeiculos();
    }

    public String getNome() {
        return super.getNome();
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return super.getListaVeiculos();
    }
}
