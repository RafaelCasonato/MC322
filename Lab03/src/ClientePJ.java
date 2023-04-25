import java.util.Date;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;

    //Métodos
    public boolean verificadoresCnpj(String cnpj) {
        /* Validação do primeiro dígito após o "-". */
        int soma = 0, j = 5;
        for (int i = 0; i < cnpj.length() - 10; i++) {
            int numero = Character.getNumericValue(cnpj.charAt(i));
            soma += (numero * j);
            j--;
        }
        j = 9;
        for (int k = 4; k < cnpj.length() - 2; k++) {
            int numero = Character.getNumericValue(cnpj.charAt(k));
            soma += (numero * j);
            j--;
        }
        int verificador = soma % 11;
        if (verificador < 2)
            verificador = 0;
        else 
            verificador = 11 - verificador;
        if (verificador != Character.getNumericValue(cnpj.charAt(12)))
            return false;
    /* Validação do segundo dígito após o "-". */
        soma = 0;
        j = 6;
        for (int i = 0; i < cnpj.length() - 9; i++) {
            soma += (Character.getNumericValue(cnpj.charAt(i)) * j);
            j--;
        }
        j = 9;
        for (int l = 5; l < cnpj.length() - 1; l++) {
            soma += (Character.getNumericValue(cnpj.charAt(l)) * j);
            j--;
        }
        int verificador2 = soma % 11;
        if (verificador2 < 2)
            verificador2 = 0;
        else 
            verificador2 = 11 - verificador2;
        if (verificador2 != Character.getNumericValue(cnpj.charAt(13)))
            return false;
        return true;
    } 

    public boolean validarCnpj(String cnpj) {
        String novoCnpj = cnpj.replaceAll("[^\\d]", "");
        if (novoCnpj.length() > 14)
            return false;
        char c = novoCnpj.charAt(0);
        for (int i = 1; i < novoCnpj.length() - 1; i++) {
            if (c != novoCnpj.charAt(i + 1))
                return verificadoresCnpj(novoCnpj);
        }
        return false;
    }

    //Construtor
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao) {
        super (nome, endereco);
        try {
            if (!(validarCnpj(cnpj)))
                throw new Exception("CNPJ inválido, tente novamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("CNPJ válido.\n");
            this.cnpj = cnpj;
            this.dataFundacao = dataFundacao;
        }
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
}
