import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Seguradora teste = new Seguradora("Rafael de Almeida Casonato",  "19949301045", "rafa.casonato@gmail.com", "Avenida Érico Veríssimo");
        Cliente cliente = new Cliente("Rafael de Almeida Casonato", "467.758.728-01", "31/12/1997", 25, "Avenida Érico Veríssimo");
        Veiculo veiculo = new Veiculo("HADA1291", "Buggati", "Chiron super sport");
        Sinistro sinistro = new Sinistro("22/03/2023", "Rua Roxo Moreira 1272");
        Boolean validacao = cliente.validarCpf(cliente.getCpf());
        System.out.print(cliente);
        System.out.print("Cpf válido? " + validacao);
        System.out.print("\n");
        System.out.print("Id: " + sinistro.getId());
    }
}