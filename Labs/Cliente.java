public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    //Métodos
    public boolean verificadores_cpf(String cpf) {
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
        for (int i = 0; i < cpf.length() - 1; i++) {
            soma += (Character.getNumericValue(cpf.charAt(i)) * j);
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
        if (novo_cpf.length() > 11)
            return false;
        char c = novo_cpf.charAt(0);
        for (int i = 1; i < novo_cpf.length() - 1; i++) {
            //char c = novo_cpf.charAt(i);
            //if (i + 1 <= novo_cpf.length()) {
            if (c != novo_cpf.charAt(i + 1))
                return verificadores_cpf(novo_cpf);
            //}
        }
        return false;
    }
        
    public String toString() {
        return "Cliente:\nNome: " + nome + "\nCpf: " + cpf + "\nData de nascimento: " + dataNascimento + "\nIdade: " + idade
            + "\nEndereco: " + endereco + "\n";
    }

    //Construtor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }    
}