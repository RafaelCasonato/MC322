import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Seguradora {
    private String nome;
    private String telefone; 
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;  
    private static ArrayList<Seguradora> listaSeguradoras; 
    public static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    //Métodos
    public String toString() { 
        return "Seguradora:\nNome: " + nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nEndereço: " + endereco +
            "\nSinistros: " + listaSinistros + "\nClientes: " + listaClientes;
    }

    public Boolean cadastrarCliente(String nome, Scanner input) throws Exception {
    /* Função que adiciona um cliente, PF ou PJ, na lista de clientes da seguradora. */
        for (Cliente c : listaClientes) {
            if (c.getNome().equals(nome)) {
                System.out.println("Cliente já cadastrado, tente novamente.");
                return false;
            }
        }
        System.out.println("Endereço: ");
        String endereco = input.nextLine();
        System.out.println("CPF/CNPJ: ");
        String dadoPuro = input.nextLine();    
        String dado = dadoPuro.replaceAll("[^\\d]", "");
        boolean verificacao = true;
        if (dado.length() == 11) {
            while (verificacao) {
                verificacao = Validacao.validarCpf(dado);
                if (!verificacao)
                System.out.println("CPF inválido, tente novamente.");
                dado = input.nextLine();
            }
            System.out.println("Data de nascimento (dd/MM/aaaa): ");
            String datePura = input.nextLine();
            Date date2 = formataData.parse(datePura);
            System.out.println("Data da licença (dd/MM/aaaa): ");
            String datePura2 = input.nextLine();
            Date date3 = formataData.parse(datePura2);
            System.out.println("Gênero: ");
            String genero = input.nextLine();
            System.out.println("Classe Econômica: ");
            String classeEconomica = input.nextLine();
            System.out.println("Educação: ");
            String educacao = input.nextLine();
            Cliente clientePF = new ClientePF(nome, endereco, educacao, genero, classeEconomica, date3, date2, dado);
            listaClientes.add(clientePF);
            return true;
        }
        else if (dado.length() == 14) {
            while (verificacao) {
                verificacao = Validacao.validarCnpj(dado);
                if (!verificacao)
                System.out.println("CNPJ inválido, tente novamente.");
                dado = input.nextLine();
            }
            System.out.println("Data de fundação (dd/MM/aaaa): ");
            String dataPura = input.nextLine();
            Date date = formataData.parse(dataPura);
            System.out.println("Quantidade de funcionários: ");
            int funcionarios = input.nextInt();
            Cliente clientePJ = new ClientePJ(nome, endereco, dado, date, funcionarios);
            listaClientes.add(clientePJ);
            return true;
        }
        return false;
    }

    public Boolean removerCliente(String dado) {
    /* Função que remove um cliente, PF ou PJ, e todos os seus sinistros das listas de clientes e sinistros da seguradora. */
        if (listaClientes.size() == 0) {
            System.out.println("Não existem clientes cadastrados.\n");
            return false;
        }
    
        for (int i = 0; i < listaClientes.size(); i++) {
            try {
                if (((ClientePF) listaClientes.get(i)).getCpf().equals(dado)) {
                    removerTodosSinistros(listaClientes.get(i));
                    System.out.println("Cliente removido com sucesso!\n");
                    return listaClientes.remove(listaClientes.get(i));
                }
            } catch (Exception e) {
                if (((ClientePJ) listaClientes.get(i)).getCnpj().equals(dado)) {
                    removerTodosSinistros(listaClientes.get(i));
                    System.out.println("Cliente removido com sucesso!\n");
                    return listaClientes.remove(listaClientes.get(i));
                }
            }
        }
        return false;
    }

    public void listarClientes() {
    /* Função que imprime todos os clientes e seus dados, PF ou PJ, da lista de clientes da seguradora. */
        for (int j = 0; j < listaClientes.size(); j++) {
            System.out.println(listaClientes.get(j));
            System.out.println("\n===================");
        }
    }

    public Boolean gerarSinistro(Sinistro sinistro) {
    /* Função que adiciona um sinistro na lista de sinistros da seguradora. */
        if (listaSinistros.contains(sinistro) == true) {
            System.out.println("Sinistro já cadastrado.\n");    
            return false;
        }
        try {
            System.out.println("Sinistro cadastrado com sucesso.\n");
            return listaSinistros.add(sinistro);
        } catch (Exception e) {
            return false;
        }
    }

    public void removerTodosSinistros(Cliente cliente) {
    /* Função que remove todos os sinistros atrelados a um cliente. */
        if (listaSinistros.size() == 0) {
            System.out.println("Nenhum sinistro existente.\n");
            return;
        }
        for (int i = 0; i < listaSinistros.size(); i++) {
            if (listaSinistros.get(i).getCliente() == cliente)
                listaSinistros.remove(listaSinistros.get(i));
        }
        return;
    }

    public Boolean visualizarSinistro(Cliente cliente) {
    /* Função que imprime todos os sinistros relacionados a um cliente, PF ou PJ. */
        try {
            Boolean visualizar = false;
            for (int i = 0; i < listaSinistros.size(); i++) {
                if (listaSinistros.get(i).getCliente() == cliente) {
                    System.out.println(listaSinistros.get(i));
                    visualizar = true;
                }
            }
            return visualizar;
        } catch (Exception e) {
            return false;
        }
    }

    public void listarSinistros() {
    /* Função que imprime todos os sinistros presentes na lista de sinistros da seguradora. */
        for (int j = 0; j < listaSinistros.size(); j++) {
            System.out.println("Data: ");
            System.out.println(listaSinistros.get(j).getData());
            System.out.println("\nId: ");
            System.out.println(listaSinistros.get(j).getId());
            System.out.println("\nNome: ");
            System.out.println((listaSinistros.get(j).getCliente()).getNome());
            System.out.println("\nVeículo: ");
            System.out.println((listaSinistros.get(j).getVeiculo()).getModelo()+ "\n" + 
            (listaSinistros.get(j).getVeiculo()).getPlaca());
            System.out.println("\n===================");
        }
    }

    public boolean removerSinistro(int id) {
    /* Função que, a partir do id de um sinistro, o remove da lista de sinsitros da seguradora. */
        for (Sinistro s : listaSinistros) {
            if (s.getId() == id) {
                listaSinistros.remove(s); 
                System.out.println("\nSinistro removido com sucesso!");
                return true;
            }
        }
        System.out.println("\nSinistro não encontrado, tente novamente.");
        return false;
    }

    public double calcularPrecoSeguroCliente(String nome) {
    /* Função que calcula o preço do seguro de cada cliente. */
        for (Cliente c : listaClientes) {
            if ((c.getNome()).equals(nome)) {
                return c.calculaScore() * (1 + listaSinistros.size());
            }
        }
        return 0;
    }

    public double calcularReceita() {
    /* Função que calcula toda a receita de uma seguradora. */
        double receita = 0;
        for (Cliente c : listaClientes) 
            receita += calcularPrecoSeguroCliente(c.getNome());
        return receita;
    }

    public boolean transferirSeguro(String nome, String nome2) {
    /* Função que transfere todos os veículos de um cliente para outro. */
        for (Cliente c : listaClientes) {
            if (c.getNome().equals(nome)) {
                for (Cliente x : listaClientes) {
                    if (x.getNome().equals(nome2))    
                        x.getListaVeiculos().addAll(c.getListaVeiculos());
                        c.getListaVeiculos().clear();
                        System.out.println("\nTransferência realizada com sucesso.");
                        return true;
                    
                }
            }
        }
        System.out.println("\nCliente não encontrado, tente novamente.");
        return false;
    }

    public void adicionaSeguradora(Seguradora seguradora) {
    /* Função que adiciona uma seguradora à lista de seguradoras. */
        listaSeguradoras.add(seguradora);
        System.out.println("\nSeguradora registrada com sucesso!");
    }

    //Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaClientes = new ArrayList<Cliente>();
        listaSeguradoras = new ArrayList<Seguradora>();
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

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static ArrayList<Seguradora> getListaSeguradoras() {
        return listaSeguradoras;
    }
}
