import java.util.Date;

public class ClientePF extends Cliente {
    private final String cpf;
    private Date dataNascimento;
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;

    //Métodos
    public boolean verificadoresCpf(String cpf) {
    /* Validação do primeiro dígito após o "-". */
        int soma = 0, j = 10;
        for (int i = 0; i < cpf.length() - 2; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += (numero * j);
            j--;
        }
        int resto = (soma * 10) % 11;
        if (resto == 10)
            resto = 0;
        if (resto != Character.getNumericValue(cpf.charAt(9)))
            return false;
    /* Validação do segundo dígito após o "-". */
        soma = 0;
        j = 11;
        for (int k = 0; k < cpf.length() - 1; k++) {
            soma += (Character.getNumericValue(cpf.charAt(k)) * j);
            j--;
        }
        resto = (soma * 10) % 11;
        if (resto == 10)
            resto = 0;
        if (resto != Character.getNumericValue(cpf.charAt(10)))
            return false;
        return true;
    }

    public boolean validarCpf(String cpf) {
        String novo_cpf = cpf.replaceAll("[^\\d]", "");
        if (novo_cpf.length() != 11)
            return false;
        char c = novo_cpf.charAt(0);
        for (int i = 1; i < novo_cpf.length() - 1; i++) {
            if (c != novo_cpf.charAt(i + 1))
                return verificadoresCpf(novo_cpf);
        }
        return false;
    }

    //Construtor
    public ClientePF(String nome, String endereco, String educacao, String genero, String classeEconomica, Date dataLicenca,
        Date dataNascimento, String cpf) {
        super (nome, endereco);
            try {
            if (!(validarCpf(cpf)))
                throw new Exception("CPF inválido, tente novamente.\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("CPF válido.\n");
            this.classeEconomica = classeEconomica;
            this.genero = genero;
            this.dataLicenca = dataLicenca;
            this.educacao = educacao;
            this.cpf = cpf;
            this.dataNascimento = dataNascimento;
        }
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
}   
