public class Validacao {
    public static boolean verificadoresCnpj(String cnpj) {
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

    public static boolean validarCnpj(String cnpj) {
        if (cnpj.length() > 14)
            return false;
        char c = cnpj.charAt(0);
        for (int i = 1; i < cnpj.length() - 1; i++) {
            if (c != cnpj.charAt(i + 1))
                return verificadoresCnpj(cnpj);
        }
        return false;
    }

    public static boolean verificadoresCpf(String cpf) {
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

    public static boolean validarCpf(String cpf) {
        if (cpf.length() != 11)
            return false;
        char c = cpf.charAt(0);
        for (int i = 1; i < cpf.length() - 1; i++) {
            if (c != cpf.charAt(i + 1))
                return verificadoresCpf(cpf);
        }
        return false;
    }

    public static boolean validarNome(String nome) {
        if (nome == null) 
            return false;
        for (int i = 0; i < nome.length(); i++) {
            if (!(Character.isLetter(nome.charAt(i)))) 
                return false;
        }
        return true;
    }
}