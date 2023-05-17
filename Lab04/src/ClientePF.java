import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private Date dataNascimento;
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private int idade;

    //Métodos
    public int calculaIdade() { 
    /* Função que calcula a idade de um cliente. */
        Date dataHoje = new Date();
        long diferencaTempo = dataHoje.getTime() - dataNascimento.getTime();
        long idade = TimeUnit.MILLISECONDS.toDays(diferencaTempo) / 365;
        return (int)idade;
    }

    public double fatorIdade() {
    /* Função que calcula o fator de idade para o cálculo do preço do seguro. */
        if (idade < 30)
            return CalcSeguro.FATOR_18_30.getFator();
        else if (idade < 60)
            return CalcSeguro.FATOR_30_60.getFator();
        return CalcSeguro.FATOR_60_90.getFator();
    } 

    @Override
    public double calculaScore() {
    /* Função que faz o cálculo do preço de um seguro para uma pessoa física. */
        double fator = fatorIdade();
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        double valorFinal = valorBase * fator * getQtdeVeiculos();
        return valorFinal;
    }

    //Construtor
    public ClientePF(String nome, String endereco, String educacao, String genero, String classeEconomica, Date dataLicenca,
        Date dataNascimento, String cpf) { 
        super (nome, endereco);
        this.classeEconomica = classeEconomica;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade();
        }
    
    @Override
    public String toString() {
        return super.toString() + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento + "\nEducação: " + educacao +
        "\nGênero: " + genero + "\nClasse Econômica: " + classeEconomica + "\nData Licença: " + dataLicenca;
    }

    //Getters e Setters
    public String getCpf() {
        return cpf;
    }

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

    public Date getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }    
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
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
