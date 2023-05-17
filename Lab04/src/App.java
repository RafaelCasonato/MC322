import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {
    public static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    private static void exibirMenuExterno() {
        MenuOperacoes menuOperacoes[] = MenuOperacoes.values();
        System.out.println("Menu principal");
        for (MenuOperacoes op : menuOperacoes) {
            System.out.println(op.ordinal() + " - " + op.getDescricao());
        }
    }
    
    private static void exibirSubmenu(MenuOperacoes op) {
        SubmenuOperacoes[] submenu = op.getSubmenu();
        System.out.println(op.getDescricao());
        for (int i=0; i < submenu.length; i++) {
            System.out.println(i + " - " + submenu[i].getDescricao());
        }
    }

    private static MenuOperacoes lerOpcaoMenuExterno() {
        Scanner input = new Scanner(System.in);
        int opUsuario;
        MenuOperacoes opUsuarioConst;
        do {
            System.out.println("Digite uma opção: ");
            opUsuario = input.nextInt();
        } while (opUsuario < 0 || opUsuario > MenuOperacoes.values().length - 1);
        opUsuarioConst = MenuOperacoes.values()[opUsuario];
        input.close();
        return opUsuarioConst;
    }

    private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes op) {
        Scanner input = new Scanner(System.in);
        int opUsuario;
        SubmenuOperacoes opUsuarioConst;
        do {
            System.out.println("Digite uma opção: ");
            opUsuario = input.nextInt();
        } while (opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
        opUsuarioConst = op.getSubmenu()[opUsuario];
        input.close();
        return opUsuarioConst;
    }

    private static void executarOpcaoMenuExterno(MenuOperacoes op) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Qual seguradora deseja acessar?");
        String seguradora = input.nextLine();
        for (Seguradora s : Seguradora.getListaSeguradoras()) {
            if (s.getNome().equals(seguradora)) {
                switch(op) {
                    case CADASTROS:
                    case LISTAR:
                    case EXCLUIR:
                        executarSubmenu(op, s);
                        break;
                    case GERAR_SINISTRO:
                        boolean gerou;
                        System.out.println("Nome completo: ");
                        String nome = input.nextLine();
                        for (Cliente c : s.getListaClientes()) {
                            if (c.getNome().equals(nome)) {
                                System.out.println("Data do ocorrido (dd/MM/aaaa): ");
                                String dataPura = input.nextLine();
                                Date data = formataData.parse(dataPura);
                                System.out.println("Endereço onde ocorreu: ");
                                String endereco = input.nextLine();
                                System.out.println("Informações sobre o veículo: \n");
                                System.out.println("Placa: ");
                                String placa = input.nextLine();
                                for (Veiculo v : c.getListaVeiculos()) {
                                    if (v.getPlaca().equals(placa)) {
                                        Sinistro sinistro = new Sinistro(data, endereco, s, c, v);
                                        gerou = s.gerarSinistro(sinistro);
                                        break;
                                    }
                                }
                                System.out.println("Modelo: ");
                                String modelo = input.nextLine();
                                System.out.println("Marca: ");
                                String marca = input.nextLine();
                                System.out.println("Ano de Fabricação: ");
                                int ano = input.nextInt();
                                Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
                                Sinistro sinistro2 = new Sinistro(data, endereco, s, c, veiculo);
                                boolean adicionou = c.getListaVeiculos().add(veiculo);
                                gerou = s.gerarSinistro(sinistro2);
                                if (gerou && adicionou)
                                    break;
                            }
                            System.out.println("Cliente não encontrado, tente novamente.");
                            break;
                        }
                    case TRANSFERIR_SEGURO:
                        System.out.println("Nome do cliente do qual deseja transferir: ");
                        String nome2 = input.nextLine();
                        System.out.println("Nome do cliente que vai receber a transferência: ");
                        String nome3 = input.nextLine();
                        boolean transferiu;
                        transferiu = s.transferirSeguro(nome2, nome3);
                        if (transferiu)
                            break;
                    case CALCULAR_RECEITA:
                        double receita = s.calcularReceita();
                        System.out.println("Receita atual de " + s.getNome() + "R$" + receita);
                    case SAIR:
                        break;
                }
            }
        }
        System.out.println("Seguradora não encontrada, tente cadastrá-la.");
        executarSubmenu(MenuOperacoes.CADASTROS, null);
    }

    private static void executarOpcaoSubmenu(SubmenuOperacoes opSubmenu, Seguradora s) throws Exception {
        Scanner input = new Scanner(System.in);
        switch(opSubmenu) {
            case CADASTRAR_CLIENTE:
                System.out.println("Nome completo: ");
                String name = input.nextLine();
                boolean cadastro;
                cadastro = s.cadastrarCliente(name, input);
                if (cadastro)
                    break;    
                System.out.println("Algo deu errado, tente novamente");
                break;
            case CADASTRAR_VEICULO:
                System.out.println("Nome completo do cliente: ");
                String nomeCliente = input.nextLine();
                boolean adicionou = false;
                for (Cliente c : s.getListaClientes()) {
                    if (c.getNome().equals(nomeCliente)) {
                        System.out.println("Informações sobre o veículo: \n");
                        System.out.println("Placa: ");
                        String placa = input.nextLine();
                        adicionou = c.adicionaVeiculo(placa, input);
                    }
                }
                if (adicionou)
                    break;
                System.out.println("Veiculo ja cadastrado ou cliente não encontrado");
                break;
            case CADASTRAR_SEGURADORA:
                if (s != null) {
                    System.out.println("Seguradora já cadastrada.");
                    break;
                }
                System.out.println("Nome: ");
                String nomeSeg = input.nextLine();
                System.out.println("Telefone: ");
                String telefone = input.nextLine();
                System.out.println("Email: ");
                String email = input.nextLine();
                System.out.println("Endereço: ");
                String enderecoSeg = input.nextLine();
                Seguradora seg = new Seguradora(nomeSeg, telefone, email, enderecoSeg);
                s.adicionaSeguradora(seg);
                break;
            case LISTAR_CLIENTES:
                s.listarClientes();
                break;
            case LISTAR_SINISTROS:
                s.listarSinistros();
                break;
            case LISTAR_VEICULOS:
                System.out.println("Nome do cliente no qual deseja visualizar os veículos: ");
                String nome = input.nextLine();
                for (Cliente c : s.getListaClientes()) {
                    if (c.getNome().equals(nome)) {
                        c.listarVeiculos();
                    }
                }
                break;
            case EXCLUIR_CLIENTE:
                System.out.println("CPF/CNPJ: ");
                String dado = input.nextLine();
                boolean removeu;
                removeu = s.removerCliente(dado);
                if (removeu)
                    break;
                System.out.println("Algo deu errado, tente novamente.");
                break;
            case EXCLUIR_VEICULO:
                System.out.println("Placa: ");
                String placa = input.nextLine();
                boolean removeu2 = false;
                for (Cliente c : s.getListaClientes()) {
                    for (Veiculo v : c.getListaVeiculos()) {
                        if (v.getPlaca().equals(placa))
                            removeu2 = c.removerVeiculo(placa);
                    }
                }
                if (removeu2)
                    break;
                System.out.println("Algo deu errado, tente novamente");
                break;
            case EXCLUIR_SINISTRO:
                System.out.println("Id do sinistro: ");
                int id = input.nextInt();
                boolean remove;
                remove = s.removerSinistro(id);
                if (remove)
                    break;
                System.out.println("Algo deu errado, tente novamente");
            case VOLTAR:
                break;
        }
    }
    
    private static void executarSubmenu(MenuOperacoes op, Seguradora s) throws Exception {
        SubmenuOperacoes opSubmenu;
        do {
            exibirSubmenu(op);
            opSubmenu = lerOpcaoSubmenu(op);
            executarOpcaoSubmenu(opSubmenu, s);
        } while (opSubmenu != SubmenuOperacoes.VOLTAR);
        System.out.println("Saiu do sistema");
    }

    public static void main(String[] args) throws Exception {
        Seguradora seguradora = new Seguradora("Porto Seguro", "(19) 3234-2940", "porto.seguro.seguros@gmail.com",
				"Av. Francisco Glicério, 1424");
		System.out.println(" Nova seguradora criada ");
		System.out.println("===================");
		Seguradora.getListaSeguradoras().add(seguradora);
		System.out.println(seguradora);
		System.out.println("===================");
		Date data = new Date();
		try {
			data = formataData.parse("06/08/1970");
		} catch (Exception e) {
			System.out.println("Data inserida inválida!");
		}
        System.out.println(" Validação CPF ");
        System.out.println("===================");
        System.out.println("Cpf válido? " + Validacao.validarCpf("11924569809"));
        Date data2 = formataData.parse("10/05/2023");
		Cliente clientePF = new ClientePF("Homero", "Rua Barão de Atibaia", "Educado", "M", "Média-Alta", data,
				data2, "119.245.698-09");
		seguradora.getListaClientes().add(clientePF);
		System.out.println(" ClientePF criado ");
		System.out.println("===================");
		System.out.println(clientePF);
		System.out.println("===================");

        System.out.println(" Validação CNPJ ");
        System.out.println("===================");
        System.out.println("CNPJ válido? " + Validacao.validarCnpj("61198164000160"));
		Cliente clientePJ = new ClientePJ("Clínica Casonato", "Rua Barão de Atibaia, 2121", "61198164000160", data2, 100);
		seguradora.getListaClientes().add(clientePJ);
		System.out.println(" ClientePJ criado ");
		System.out.println("===================");
		System.out.println(clientePJ);
		System.out.println("===================");
		Veiculo hondaFit = new Veiculo("FOV1C28", "HONDA", "FIT", 2018);
		clientePF.getListaVeiculos().add(hondaFit);
		System.out.println(" Novo veículo no ClientePF ");
		Veiculo hondaCRV = new Veiculo("FNC4596", "HONDA", "CRV", 2009);
		clientePJ.getListaVeiculos().add(hondaCRV);
		System.out.println(" Novo veícullo no ClientePJ ");
		System.out.println("===================");
		clientePF.listarVeiculos();
		System.out.println("===================");

        System.out.println(" Preço do Seguro ");
		System.out.println("===================");
		System.out.println(seguradora.calcularPrecoSeguroCliente("Clínica Casonato"));
		System.out.println("===================");
		clientePF.setValorSeguro(seguradora.calcularPrecoSeguroCliente("Clínica Casonato"));

		System.out.println(" Preço do Seguro ");
		System.out.println("===================");
		System.out.println(seguradora.calcularPrecoSeguroCliente("Homero"));
		clientePJ.setValorSeguro(seguradora.calcularPrecoSeguroCliente("Homero"));
		System.out.println("===================");

		System.out.println(" Calculo da receita da Seguradora");
		System.out.println("===================");
		System.out.println(seguradora.calcularReceita());
		System.out.println("===================");

		seguradora.gerarSinistro(new Sinistro(data, "Rua do Abacate Cozido", seguradora,
				clientePF, hondaFit));
		System.out.println(" Sinistro gerado para " + clientePF.getNome());
		System.out.println("===================");

		seguradora.gerarSinistro(new Sinistro(data2, "Rua do After", seguradora,
				clientePJ, clientePJ.getVeiculo("FNC4596")));
		System.out.println(" Sinistro gerado para " + clientePJ.getNome());
		System.out.println("===================");

		System.out.println(" Listar Clientes ");
		System.out.println("===================");
		seguradora.listarClientes();
		System.out.println("===================");

		System.out.println(" Visualizar sinistro ");
		System.out.println("===================");
		seguradora.visualizarSinistro(clientePF);
		System.out.println("===================");

		System.out.println(" Listar Sinistros ");
		System.out.println("===================");
		seguradora.listarSinistros();
		System.out.println("===================");

        MenuOperacoes op;
        do {
            exibirMenuExterno();
            op = lerOpcaoMenuExterno();
            executarOpcaoMenuExterno(op);
        } while (op != MenuOperacoes.SAIR);
        System.out.println("Saiu do sistema.");
    }
}