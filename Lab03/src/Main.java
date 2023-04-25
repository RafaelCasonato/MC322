import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    //Métodos 
    public static void criarCliente(Scanner input, Seguradora seguradora) throws Exception {
        System.out.println("\nNome: ");
        String nome = input.nextLine();
        System.out.println("\nEndereço: ");
        String endereco = input.nextLine();
        System.out.println("\nCPF/CNPJ (com símbolos): ");
        String dado = input.nextLine();
        dado = dado.replaceAll("[^\\d]", "");
        
        if (dado.length() == 11) {
            System.out.println("\nEducacação: ");
            String educacao = input.nextLine();
            System.out.println("\nGênero: ");
            String genero = input.nextLine();
            System.out.println("\nClasse Econômica: ");
            String classeEconomica = input.nextLine();
            System.out.println("\nData Licença (dd/MM/aaaa): ");
            String puraDataLicenca = input.nextLine();
            Date dataLicenca = formataData.parse(puraDataLicenca);
            System.out.println("\nData Nascimento (dd/MM/aaaa): ");
            String puraDataNascimento = input.nextLine();
            Date dataNascimento = formataData.parse(puraDataNascimento);
            ClientePF clientePF = new ClientePF(nome, endereco, educacao, genero, classeEconomica, dataLicenca, dataNascimento, dado);
            seguradora.cadastrarCliente(clientePF);
        }
        else if (dado.length() == 13) {
            System.out.println("\nData Fundação (dd/MM/aaaa): ");
            String puraDataFundacao = input.nextLine();
            Date dataFundacao = formataData.parse(puraDataFundacao);
            ClientePJ clientePJ = new ClientePJ(nome, endereco, dado, dataFundacao);
            seguradora.cadastrarCliente(clientePJ);
        }

        System.out.println("Deseja adicionar um veículo?\n");
        String resposta = input.nextLine();
        if (resposta.equals("Sim")) {
            System.out.println("Marca: ");
            String marca = input.nextLine();
            System.out.println("\nModelo: ");
            String modelo = input.nextLine();
            System.out.println("\nPlaca: ");
            String placa = input.nextLine();
            System.out.println("\nAno de Fabricação: ");
            int anoFabricacao = input.nextInt();
            Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
            int tamanho = (seguradora.getListaClientes()).size();
            ((seguradora.getListaClientes()).get(tamanho - 1)).adicionaVeiculo(veiculo);
        } 
        if (resposta.equals("Não"))
            return;
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = 0;
        Seguradora seguradora = new Seguradora("Porto Seguro", "0800 777 3334", "portoseguro@seguradora.com.br",
            "Rua Porto Seguro 1212");
        System.out.println("Quantos clientes deseja cadastrar? ");
        n = input.nextInt();
        for (int i = 0; i < n; i++) {
            criarCliente(input, seguradora);
        }

        System.out.println("\n############ TESTES ###############");
        Date data = formataData.parse("23/04/2023");
        ClientePF cliente1 = new ClientePF("Rafael", "Rua UNICAMP", "Ensino médio completo", "Masculino", "Média-Alta", data, data, "467.758.728-01");
        ClientePJ cliente2 = new ClientePJ("Conpec", "Rua UNICAMP2", "66070178000117", data);
        seguradora.cadastrarCliente(cliente2);
        seguradora.cadastrarCliente(cliente1);
        Veiculo veiculo1 = new Veiculo("FOV1C28", "Honda", "FIT", 2018);
        Veiculo veiculo2 = new Veiculo("FNC2716", "Honda", "CRV", 2013);
        cliente1.adicionaVeiculo(veiculo1);
        cliente2.adicionaVeiculo(veiculo2);
        Sinistro sinistro = new Sinistro("07/10/2003", "Rua Minha Casa 02", seguradora, cliente1, veiculo1);
        seguradora.gerarSinistro(sinistro);
        System.out.println("\n############ LISTAR SINISTROS ############");
        seguradora.listarSinistros();
        System.out.println("############ VISUALIZAR SINISTRO ############");
        seguradora.visualizarSinistro(cliente1);
        System.out.println("############ LISTAR CLIENTES ############");
        seguradora.listarClientes();
        seguradora.removerCliente("467.758.728-01");
        System.out.println("############ MÉTODOS TO STRING ############");
        System.out.println(veiculo1);
        System.out.println(cliente2);
        System.out.println(seguradora);
        System.out.println(sinistro);
        System.out.println(cliente1);
    } 
}

