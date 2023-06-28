import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {
    public static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean validarDados(String dado, Scanner input) {
        String dadoPuro1 = dado.replaceAll("[^\\d]", "");
        boolean veri = true;
        veri = Validacao.validarCnpj(dadoPuro1);
        if (!veri) {
            veri = Validacao.validarCpf(dadoPuro1);
            if (!veri) {
                System.out.println("CPF/CNPJ inválido, tente novamente.");
                return false;
            }
        }
        return true;
    }

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
                        System.out.println("Informe o ID do seguro: ");
                        int id = input.nextInt();
                        for (Seguro seg : s.getListaSeguros()) {
                            if (seg.getId() == id) {
                                seg.gerarSinistro(input, seg);
                            }
                        }
                        System.out.println("Seguro não encontrado, tente novamente.\n");
                        break;
                    case CALCULAR_RECEITA:
                        double receita = s.calcularReceita();
                        System.out.println("Receita atual de " + s.getNome() + "R$" + receita);
                    case CALCULAR_SEGURO:
                        System.out.println("Entre com o ID do seguro: ");
                        int id2 = input.nextInt();
                        for (Seguro seg : s.getListaSeguros()) {
                            if (seg.getId() == id2) {
                                double valor = seg.calcularValor();
                                System.out.println("Valor atual do Seguro (" + id2 + "): R$" + valor + "\n");
                                break;
                            }
                        }
                        System.out.println("Seguro não encontrado, tente novamente.\n");
                        break;
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
                boolean validacao = true;
                System.out.println("Nome completo: ");
                String name = input.nextLine();
                validacao = Validacao.validarNome(name);
                if (validacao) {
                    boolean cadastro;
                    cadastro = s.cadastrarCliente(name, input);
                    if (cadastro)
                        break;    
                    System.out.println("Algo deu errado, tente novamente");
                    break;
                }
                System.out.println("Nome incorreto, tente novamente");
                break;
            case CADASTRAR_VEICULO:
                System.out.println("0 - PF\n1 - PJ");
                int numero = input.nextInt();
                System.out.println("CPF/CNPJ: ");
                String dado = input.nextLine();
                if (numero == 0) {
                    if (!validarDados(dado, input)) {
                        break;
                    }
                    System.out.println("Placa do carro: ");
                    String placa = input.nextLine();
                    boolean adicionou = true;
                    for (Cliente c : s.getListaClientes()) {
                        if (c.getDado().equals(dado)) {
                            adicionou = ((ClientePF) c).cadastrarVeiculo(placa, input);
                            System.out.println("Veículo cadastrado com sucesso.");
                            break;
                        }
                    }
                    if (!adicionou) {
                        System.out.println("Ocorreu um erro, tente novamente.");
                        break;
                    }
                }
                else if (numero == 1) {
                    if (!validarDados(dado, input)) {
                        break;
                    }
                    boolean adicionou = true;
                    for (Cliente c : s.getListaClientes()) {
                        if (c.getDado().equals(dado)) {
                            System.out.println("Qual o código da frota em que deseja adicionar? ");
                            String codigo = input.nextLine();
                            for (Frota f : ((ClientePJ) c).getlistaFrotas()) {
                                if (f.getCode().equals(codigo)) {
                                    System.out.println("Placa do carro: ");
                                    String placa = input.nextLine();
                                    adicionou = f.adicionaVeiculo(placa, input);
                                    if (adicionou)
                                        System.out.println("Veículo cadastrado com sucesso.");
                                    break;
                                }
                            }        
                        }
                    }
                    if (!adicionou) {
                        System.out.println("Ocorreu um erro, tente novamente.");
                        break;
                    }
                }
                break;
            case CADASTRAR_SEGURADORA:
                System.out.println("Nome: ");
                String nomeSeg = input.nextLine();
                boolean verificacao = true;
                verificacao = Validacao.validarNome(nomeSeg);
                if (verificacao) {
                    boolean cadastrou = true;
                    cadastrou = Seguradora.cadastraSeguradora(input, nomeSeg);
                    if (cadastrou) {
                        System.out.println("Seguradora cadastrada com sucesso.");
                        break;
                    }
                    break;
                }
                System.out.println("Nome inválido, tente novamente.");
                break;
            case CADASTRAR_CONDUTOR:
                System.out.println("Digite o ID do seguro em que deseja cadastrá-lo: ");
                int id3 = input.nextInt();
                for (Seguro seg : s.getListaSeguros()) {
                    if (seg.getId() == id3) {
                        boolean cadastrou = true;
                        cadastrou = seg.cadastrarCondutor(input);
                        if (cadastrou) {
                            System.out.println("Condutor cadastrado com sucesso.");
                            break;
                        }
                        System.out.println("Algo deu errado, tente novamente.");
                        break;
                    }
                }
                System.out.println("Seguro não encontrado, tente novamente.");
                break;
            case CADASTRAR_FROTA:
                System.out.println("Informe o CNPJ do cliente: ");
                String cnpj = input.nextLine();
                if (!validarDados(cnpj, input)) {
                    break;
                }
                for (Cliente c : s.getListaClientes()) {
                    if (c.getDado().equals(cnpj)) {
                        boolean criou = true;
                        criou = ((ClientePJ) c).cadastrarFrota(input);
                        if (criou) {
                            System.out.println("Frota cadastrada com sucesso.");
                            break;
                        }
                        System.out.println("Algo deu errado, tente novamente.");
                        break;
                    }
                }
                System.out.println("Cliente não encontrado.");
                break;
            case CADASTRAR_SEGURO:
                boolean cadastrado = true;
                cadastrado = s.gerarSeguro(input);
                if (cadastrado) {
                    break;
                }
                System.out.println("Algo deu errado, tente novamente.");
                break;
            case LISTAR_CLIENTES:
                s.listarClientes();
                break;
            case LISTAR_SINISTROS:
                System.out.println("Digite o ID do seguro: ");
                int i = input.nextInt();
                for (Seguro seg : s.getListaSeguros()) {
                    if (seg.getId() == i)
                        seg.listarSinistros();
                        break;
                }
                System.out.println("Seguro não encontrado, tente novamente.");
                break;
            case LISTAR_VEICULOS:
                System.out.println("CPF/CNPJ: ");
                String dado1 = input.nextLine();
                if (!validarDados(dado1, input)) {
                    break;
                }
                for (Cliente c : s.getListaClientes()) {
                    if (c.getDado().equals(dado1)) {
                        if (dado1.length() == 11) {
                            ((ClientePF) c).listarVeiculos();
                            break;
                        }
                        else if (dado1.length() == 14) {
                            System.out.println("Em qual frota deseja verificar? ");
                            ((ClientePJ) c).listaFrotas();
                            int posicao = input.nextInt();
                            Frota frota = ((ClientePJ) c).getlistaFrotas().get(posicao);
                            frota.listarVeiculos();
                            break;
                        }
                    }
                }
                System.out.println("Cliente não encontrado, tente novamente.");
                break;
            case LISTAR_CONDUTOR:
                System.out.println("CPF/CNPJ: ");
                String dado2 = input.nextLine();
                if (!validarDados(dado2, input)) {
                    break;
                }
                for (Seguro seg : s.getListaSeguros()) {
                    if (seg.getDado().equals(dado2)) {
                        seg.listarCondutores();
                        break;
                    }
                }
                System.out.println("Algo deu errado, tente novamente.");
                break;
            case LISTAR_FROTA:
                System.out.println("CNPJ: ");
                String cnpjC = input.nextLine();
                if (!validarDados(cnpjC, input)) {
                    break;
                }
                for (Cliente c : s.getListaClientes()) {
                    if (((ClientePJ) c).getDado().equals(cnpjC)) {
                        c.listaFrotas();
                        break;
                    }
                }
                System.out.println("Cliente não encontrado, tente novamente.");
                break;
            case LISTAR_SEGURADORAS:
                System.out.println("=====Seguradoras=====\n");
                for (Seguradora seg : Seguradora.getListaSeguradoras()) {
                    System.out.println(seg);
                }
                break;
            case LISTAR_SEGUROS:
                System.out.println("=====Seguros=====\n");
                for (Seguro seg : s.getListaSeguros()) {
                    System.out.println(seg);
                }
                break;
            case EXCLUIR_CLIENTE:
                System.out.println("CPF/CNPJ: ");
                String dado3 = input.nextLine();
                if (!validarDados(dado3, input))
                    break;
                boolean removeu;
                removeu = s.removerCliente(dado3);
                if (removeu)
                    break;
                System.out.println("Algo deu errado, tente novamente.");
                break;
            case EXCLUIR_VEICULO:
                System.out.println("CPF/CNPJ: ");
                String dado4 = input.nextLine();
                if (!validarDados(dado4, input))
                    break;
                System.out.println("Placa: ");
                String placa = input.nextLine();
                boolean removeu2 = false;
                for (Cliente c : s.getListaClientes()) {
                    if (c.getTipo().equals("pf")) {
                        if (c.getDado().equals(dado4))
                            removeu2 = ((ClientePF) c).removerVeiculo(placa);
                    }
                    if (c.getTipo().equals("pj")) {
                        if (c.getDado().equals(dado4)) {
                            for (Frota f : c.getlistaFrotas()) {
                                removeu2 = f.removerVeiculo(placa);
                            }
                        }
                    }
                }
                if (removeu2)
                    break;
                System.out.println("Algo deu errado, tente novamente");
                break;
            case EXCLUIR_SINISTRO:
                System.out.println("ID do seguro:");
                int dado5 = input.nextInt();
                for (Seguro seg : s.getListaSeguros()) {
                    if (seg.getId() == (dado5)) {
                        System.out.println("ID do sinistro: ");
                        int id = input.nextInt(); 
                        seg.removerSinistro(id);
                        break;
                    }
                }
                System.out.println("Seguro não encontrado");
            case EXCLUIR_CONDUTOR:
                System.out.println("ID do seguro: ");
                int id = input.nextInt();
                System.out.println("CPF/CNPJ: ");
                String dado6 = input.nextLine();
                boolean removeu4 = true;
                if (!validarDados(dado6, input))
                    break;
                for (Seguro seg : s.getListaSeguros()) {
                    if (seg.getId() == id) {
                        for (Condutor c : seg.getListaCondutores()) {
                            if (c.getCpf().equals(dado6)) {
                                removeu4 = seg.desautorizarCondutor(c);
                            }
                        }
                    }
                }
                if (!removeu4) {
                    System.out.println("Algo deu errado, tente novamente.");
                    break;
                }
                break;
            case EXCLUIR_FROTA: 
                System.out.println("CNPJ: ");
                String cnpj2 = input.nextLine();
                if (!validarDados(cnpj2, input)) {
                    break;
                }
                for (Cliente c : s.getListaClientes()) {
                    if (c.getDado().equals(cnpj2)) {       
                        System.out.println("Em qual frota deseja verificar? ");
                        ((ClientePJ) c).listaFrotas();
                        int posicao = input.nextInt();
                        Frota frota = ((ClientePJ) c).getlistaFrotas().get(posicao);
                        ((ClientePJ) c).atualizarFrota(frota);
                        break;
                    }
                }
                System.out.println("Algo deu errado, tente novamente.");
                break;
            case EXCLUIR_SEGURO:
                System.out.println("ID do seguro: ");
                int id2 = input.nextInt();
                boolean cancelou = s.cancelarSeguro(id2);
                if (!cancelou) {
                    System.out.println("Algo deu errado, tente novamente.");
                    break;
                }
                break;
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
		"Av. Francisco Glicério, 1424", "59.133.540/0001-96");
		System.out.println(" Nova seguradora criada \n");
		System.out.println("===================\n");
		Seguradora.getListaSeguradoras().add(seguradora);
		System.out.println(seguradora);
		System.out.println("===================\n");
		Date data = new Date();
		try {
			data = formataData.parse("06/08/1970");
		} catch (Exception e) {
			System.out.println("Data inserida inválida!");
		}
        
        System.out.println(" Validação CPF \n");
        System.out.println("===================\n");
        System.out.println("Cpf válido? " + Validacao.validarCpf("11924569809"));
        Date data2 = formataData.parse("10/05/2023");
		ClientePF clientePF = new ClientePF("Homero", "11993210987", "Rua Barão de Atibaia", "h@email.com", "Completissima", "Masc", data, "119.245.698-09");
		seguradora.cadastrarCliente(clientePF);
        Veiculo v1 = new Veiculo("RFS890I", "HONDA", "FIT", 2019);
        Veiculo v2 = new Veiculo("XZS890J", "CHEVROLET", "ZAFIRA", 2010);
        Veiculo v3 = new Veiculo("T2R880K", "FIAT", "UNO", 2005);
        clientePF.cadastrarVeiculo(v1);
        clientePF.cadastrarVeiculo(v2);
        clientePF.cadastrarVeiculo(v3);
        System.out.println(" Novos veículos no ClientePF \n");
		System.out.println("===================\n");
		clientePF.listarVeiculos();
		System.out.println("===================\n");
		System.out.println(" ClientePF criado \n");
		System.out.println("===================\n");
		System.out.println(clientePF);
		System.out.println("===================\n");

        System.out.println(" Validação CNPJ \n");
        System.out.println("===================\n");
        System.out.println("CNPJ válido? " + Validacao.validarCnpj("61198164000160"));
		ClientePJ clientePJ = new ClientePJ("Clínica Casonato", "53987650145" ,"Rua Barão de Atibaia, 2121", "clinica@email.com", "61198164000160", data2);
		seguradora.cadastrarCliente(clientePJ);
        Veiculo v4 = new Veiculo("FOV890I", "HONDA", "HRV", 2019);
        Veiculo v5 = new Veiculo("12RX80J", "CHEVROLET", "ONIX", 2023);
        Veiculo v6 = new Veiculo("42R7980", "FIAT", "ARGO", 2020);
        Frota frota = new Frota("Frota Clínica");
        frota.cadastrarVeiculo(v4);
        frota.cadastrarVeiculo(v5);
        frota.cadastrarVeiculo(v6);
        System.out.println(" Nova frota no ClientePJ \n");
        System.out.println("===================\n");
        clientePJ.listaFrotas();
        System.out.println(" ClientePJ criado \n");
		System.out.println("===================\n");
		System.out.println(clientePJ);
		System.out.println("===================\n");

        Date data3 = formataData.parse("10/05/2020");
        Date data4 = formataData.parse("10/05/2030");
        SeguroPF seguroPF = new SeguroPF(data3, data4, seguradora, v1, clientePF);
        System.out.println(" Novo seguro para cliente PF \n");
        System.out.println("===================\n");
        System.out.println(seguroPF);
        System.out.println("===================\n");
        SeguroPF seguroPF2 = new SeguroPF(data3, data4, seguradora, v2, clientePF);
        System.out.println(" Novo seguro para cliente PF \n");
        System.out.println("===================\n");
        System.out.println(seguroPF2);
        System.out.println("===================\n");
        SeguroPF seguroPF3 = new SeguroPF(data3, data4, seguradora, v3, clientePF);
        System.out.println(" Novo seguro para cliente PF \n");
        System.out.println("===================\n");
        System.out.println(seguroPF3);
        System.out.println("===================\n");
        seguradora.gerarSeguro(seguroPF);
        seguradora.gerarSeguro(seguroPF2);
        seguradora.gerarSeguro(seguroPF3);

        Date data5 = formataData.parse("24/10/2020");
        Date data6 = formataData.parse("24/10/2030");
        SeguroPJ seguroPJ = new SeguroPJ(data5, data6, seguradora, frota, clientePJ);
        System.out.println(" Novo seguro para cliente PJ \n");
        System.out.println("===================\n");
        System.out.println(seguroPJ);
        System.out.println("===================\n");
        seguradora.gerarSeguro(seguroPJ);

        Date data7 = formataData.parse("24/10/2000");
        Date data8 = formataData.parse("24/10/2001");
        Date data9 = formataData.parse("24/10/2002");
        Date data0 = formataData.parse("24/10/2003");
        Condutor condutorPF = new Condutor("973.376.310-14", "José", "33984652370", "Rua Jose 123", "j@email.com", data7);
        System.out.println(" Condutor criado \n");
		System.out.println("===================\n");
		System.out.println(condutorPF);
		System.out.println("===================\n");
        Condutor condutorPJ = new Condutor("247.377.020-48", "Ana", "439701245670", "Rua Ana Banana", "a@email.com", data8);
        System.out.println(" Condutor criado \n");
		System.out.println("===================\n");
		System.out.println(condutorPJ);
		System.out.println("===================\n");
        Condutor condutorPJ2 = new Condutor("492.573.600-08", "Rodrigo", "56907454809", "Rua Roro", "r@email.com",  data9);
        System.out.println(" Condutor criado \n");
		System.out.println("===================\n");
		System.out.println(condutorPJ2);
		System.out.println("===================\n");
        Condutor condutorPJ3 = new Condutor("953.528.200-09", "Carlo", "21913456789", "Rua Carlao", "c@email.com", data0);
        System.out.println(" Condutor criado \n");
		System.out.println("===================\n");
		System.out.println(condutorPJ3);
		System.out.println("===================\n");
        seguroPF.autorizarCondutor(condutorPF);
        seguroPJ.autorizarCondutor(condutorPJ);
        seguroPJ.autorizarCondutor(condutorPJ2);
        seguroPJ.autorizarCondutor(condutorPJ3);

        Date dataS = formataData.parse("14/11/2017");
        Date dataS2 = formataData.parse("07/12/2018");
        Date dataS3 = formataData.parse("04/09/2019"); 
        Sinistro sinistro = new Sinistro(dataS, "Rua Ana Banana", condutorPF, seguroPF);
		System.out.println("===================\n");
        System.out.println(" Sinistro criado ");
		System.out.println(sinistro);
		System.out.println("===================\n");
        Sinistro sinistro2 = new Sinistro(dataS2, "Rua Carlao", condutorPJ2, seguroPJ);
		System.out.println("===================\n");
        System.out.println(" Sinistro criado ");
		System.out.println(sinistro2);
		System.out.println("===================\n");
        Sinistro sinistro3 = new Sinistro(dataS3, "Rua Carlao", condutorPJ2, seguroPJ);
		System.out.println("===================\n");
        System.out.println(" Sinistro criado ");
		System.out.println(sinistro3);
		System.out.println("===================\n");
        seguroPF.gerarSinistro(sinistro, condutorPF);
        seguroPJ.gerarSinistro(sinistro2, condutorPJ2);
        seguroPJ.gerarSinistro(sinistro3, condutorPJ2);

        MenuOperacoes op;
        do {
            exibirMenuExterno();
            op = lerOpcaoMenuExterno();
            executarOpcaoMenuExterno(op);
        } while (op != MenuOperacoes.SAIR);
        System.out.println("Saiu do sistema.");
    }
}