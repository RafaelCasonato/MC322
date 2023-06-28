import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone; 
    private String email;
    private String endereco;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Cliente> listaClientes;  
    private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>(); 
    public static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    //Métodos
    public String toString() { 
        return "Seguradora: \nCNPJ: " + cnpj + "\nNome: " + nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nEndereço: " + endereco + "\nClientes: " + listaClientes;
    }

    public boolean cadastrarCliente(ClientePJ cliente) {
        if (!listaClientes.contains(cliente)) {
            listaClientes.add(cliente);
            return true;
        }
        System.out.println("Cliente ja cadastrado.");
        return false;
    }

    public boolean cadastrarCliente(ClientePF cliente) {
        if (!listaClientes.contains(cliente)) {
            listaClientes.add(cliente);
            return true;
        }
        System.out.println("Cliente ja cadastrado.");
        return false;
    }

    public Boolean cadastrarCliente(String nome, Scanner input) throws Exception {
    /* Função que adiciona um cliente, PF ou PJ, na lista de clientes da seguradora. */
        for (Cliente c : listaClientes) {
            if (c.getNome().equals(nome)) {
                System.out.println("Cliente já cadastrado, tente novamente.");
                return false;
            }
        }
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Telefone: ");
        String telefone = input.nextLine();
        System.out.println("Endereço: ");
        String endereco = input.nextLine();
        System.out.println("CPF/CNPJ: ");
        String dadoPuro = input.nextLine();    
        String dado = dadoPuro.replaceAll("[^\\d]", "");
        boolean verificacao = true;
        if (dado.length() == 11) {
            verificacao = Validacao.validarCpf(dado);
            if (!verificacao) {
                System.out.println("CPF inválido, tente novamente.");
                return false;
            }
            System.out.println("Data de nascimento (dd/MM/aaaa): ");
            String datePura = input.nextLine();
            Date date3 = formataData.parse(datePura);
            System.out.println("Gênero: ");
            String genero = input.nextLine();
            System.out.println("Educação: ");
            String educacao = input.nextLine();
            ClientePF clientePF = new ClientePF(nome, telefone, endereco, email, educacao, genero, date3, dado);
            listaClientes.add(clientePF);
            return true;
        }
        else if (dado.length() == 14) {
            verificacao = Validacao.validarCnpj(dado);
            if (!verificacao) {
                System.out.println("CNPJ inválido, tente novamente.");
                return false;
            }
            System.out.println("Data de fundação (dd/MM/aaaa): ");
            String dataPura = input.nextLine();
            Date date = formataData.parse(dataPura);
            ClientePJ clientePJ = new ClientePJ(nome, telefone, endereco, email, dado, date);
            listaClientes.add(clientePJ);
            return true;
        }
        return false;
    }

    public Boolean removerCliente(String dado) {
    /* Função que remove um cliente, PF ou PJ, e todos os seus sinistros das listas de clientes e sinistros da seguradora. */
        if (listaClientes == null || listaClientes.size() == 0) {
            System.out.println("Não existem clientes cadastrados.\n");
            return false;
        }
    
        for (Cliente c : listaClientes) {
            if (c.getDado().equals(dado)) {
                removerTodosSeguros(c);
                System.out.println("Cliente removido com sucesso!\n");
                return listaClientes.remove(c);
            }
        }
        return false;
    }

    public void listarClientes() {
    /* Função que imprime todos os clientes e seus dados, PF ou PJ, da lista de clientes da seguradora. */
        for (Cliente c : listaClientes) {
            System.out.println(c);
            System.out.println("\n===================");
        }
    }

    public Boolean gerarSeguro(Scanner input) {
    /* Função que cria e adiciona um seguro na lista de seguros da seguradora. */
        try {
            System.out.println("Pessoa física ou jurídica? (PF/PJ)");
            String resposta = input.nextLine();
            System.out.println("Data de início (dd/MM/aaaa): ");
            String datePura = input.nextLine();
            Date dataInicio = formataData.parse(datePura);
            System.out.println("Data do fim (dd/MM/aaaa): ");
            String datePura2 = input.nextLine();
            Date dataFim = formataData.parse(datePura2);
            System.out.println("Seguradora: ");
            String nomeSeg = input.nextLine(); //validar o nome
            Boolean existe = false;
            Seguradora seg = new Seguradora(nomeSeg, nomeSeg, nomeSeg, nomeSeg, nomeSeg);
            ClientePJ clientePJ = new ClientePJ(nomeSeg, nomeSeg, nomeSeg, nomeSeg, nomeSeg, dataFim);
            ClientePF clientePF = new ClientePF(nomeSeg, nomeSeg, nomeSeg, nomeSeg, nomeSeg, nomeSeg, dataFim, nomeSeg);
            for (Seguradora s : listaSeguradoras) {
                if (s.getNome().equals(nomeSeg)) {
                    seg = s;
                    existe = true;
                }
            }
            if (!existe) {
                System.out.println("Seguradora não encontrada, tente cadastrá-la.");
                System.out.println("\nSeguro não cadastrado.");
                return false;
            }
            if (resposta.equals("PF")) {
                System.out.println("CPF do cliente: ");
                String cpfC = input.nextLine();
                String dado = cpfC.replaceAll("[^\\d]", "");
                boolean verificacao = true;
                verificacao = Validacao.validarCpf(dado);
                if (!verificacao) {
                    System.out.println("CPF inválido, tente novamente.");
                    return false;
                }
                for (Cliente c : listaClientes) {
                    if (c.getDado().equals(cpfC)) 
                        clientePF = (ClientePF) c;
                }            
                System.out.println("Para qual veículo deseja cadastrar?");
                clientePF.listaVeiculos();
                int posicao1 = input.nextInt();
                Veiculo veiculo = clientePF.getListaVeiculos().get(posicao1);
                SeguroPF seguroPF = new SeguroPF(dataInicio, dataFim, seg, veiculo, (ClientePF) clientePF);
                return listaSeguros.add(seguroPF);
            }
            if (resposta.equals("PJ")) {
                System.out.println("CNPJ do cliente: ");
                String cnpjC = input.nextLine();
                String dado2 = cnpjC.replaceAll("[^\\d]", "");
                boolean verificacao = true;
                verificacao = Validacao.validarCnpj(dado2);
                if (!verificacao) {
                    System.out.println("CNPJ inválido, tente novamente.");
                    return false;
                }
                for (Cliente c : listaClientes) {
                    if (c.getDado().equals(cnpjC)) 
                        clientePJ = (ClientePJ) c;
                }       
                System.out.println("Para qual frota deseja cadastrar?");
                clientePJ.listaFrotas();
                int posicao = input.nextInt();
                Frota frota = clientePJ.getlistaFrotas().get(posicao);
                SeguroPJ seguroPJ = new SeguroPJ(dataInicio, dataFim, seg, frota, (ClientePJ) clientePJ);
                return listaSeguros.add(seguroPJ);
            }
            System.out.println("Seguro cadastrado com sucesso.\n");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean gerarSeguro(SeguroPF seguro) {
        if (!listaSeguros.contains(seguro)) {
            listaSeguros.add(seguro);
            return true;
        }
        System.out.println("Seguro ja existente.");
        return false;
    }
    public boolean gerarSeguro(SeguroPJ seguro) {
        if (!listaSeguros.contains(seguro)) {
            listaSeguros.add(seguro);
            return true;
        }
        System.out.println("Seguro ja existente.");
        return false;
    }

    public Boolean cancelarSeguro(int id) {
    /* Função que apaga um seguro da lista de seguros da seguradora, cancelando-o. */
        for (Seguro s : listaSeguros) {
            if (s.getId() == id)
                System.out.println("Seguro cancelado com sucesso.");    
                return listaSeguros.remove(s);
            }
        System.out.println("Nenhum seguro encontrado.");
        return false;
    }

    public void removerTodosSeguros(Cliente cliente) {
    /* Função que remove todos os sinistros atrelados a um cliente. */
        if (listaSeguros == null || listaSeguros.size() == 0) {
            System.out.println("Nenhum seguro existente.\n");
            return;
        }
        for (Seguro s : listaSeguros) {
            if (s.getCliente() == cliente)
                listaSeguros.remove(s);
        }
        return;
    }

    public ArrayList<Seguro> getSegurosPorCliente(String dado) {
    /* Função que retorna uma lista com todos os seguros atrelados a um CPF ou CNPJ. */
        ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
        for (Seguro s : listaSeguros) {
            if (s.getDado().equals(dado))
                segurosCliente.add(s);
        }
        return segurosCliente;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(String dado) {
    /* Função que retorna uma lista com todos os sinistros atrelados a um CPF ou CNPJ. */
        ArrayList<Seguro> segurosCliente = getSegurosPorCliente(dado);
        ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();
        for (Seguro s : segurosCliente) {
            for (Sinistro sinistro : s.getListaSinistros()) {
                sinistrosCliente.add(sinistro);
            }
        }
        return sinistrosCliente;
    }

    public double calcularReceita() {
    /* Função que calcula toda a receita de uma seguradora. */
        double receita = 0;
        for (Seguro s : listaSeguros) 
            receita += s.getValorMensal();
        return receita;
    }
    
    public static boolean cadastraSeguradora(Scanner input, String nome) {
    /* Função que cadastra uma nova seguradora e adiciona ela na lista de seguradoras. */
        System.out.println("CNPJ: ");
        String cnpj = input.nextLine();  
        String dado2 = cnpj.replaceAll("[^\\d]", "");
        boolean verificacao = true;
        verificacao = Validacao.validarCnpj(dado2);
        if (!verificacao) {
            System.out.println("CNPJ inválido, tente novamente.");
            return false;
        }  
        System.out.println("Telefone: ");
        String telefone = input.nextLine();
        System.out.println("Email: ");
        String email = input.nextLine();
        System.out.println("Endereço: ");
        String enderecoSeg = input.nextLine();
        Seguradora seguradora = new Seguradora(nome, telefone, email, enderecoSeg, cnpj);
        listaSeguradoras.add(seguradora);
        return true;
    }

    public void adicionaSeguradora(Seguradora seguradora) {
    /* Função que adiciona uma seguradora à lista de seguradoras. */
        listaSeguradoras.add(seguradora);
        System.out.println("\nSeguradora registrada com sucesso!");
    }

    //Construtor
    public Seguradora(String nome, String telefone, String email, String endereco, String cnpj) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.listaSeguros= new ArrayList<Seguro>();
        this.listaClientes = new ArrayList<Cliente>();
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {   
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    } 
    
    public String getCnpj() {
        return cnpj;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static ArrayList<Seguradora> getListaSeguradoras() {
        return listaSeguradoras;
    }
}
