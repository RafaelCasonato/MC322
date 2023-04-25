import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone; 
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;   

    //Métodos
    public String toString() { 
        return "Seguradora:\nNome: " + nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nEndereço: " + endereco +
            "\nSinistros: " + listaSinistros + "\nClientes: " + listaClientes;
    }

    public Boolean cadastrarCliente(Cliente cliente) {
        if (listaClientes.contains(cliente) == true) {
            System.out.println("Cliente já cadastrado.\n");    
            return false;
        }
        try {
            listaClientes.add(cliente);
            System.out.println("Cadastro realizado com sucesso.\n");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removerCliente(String dado) {
        //remover a partir do cpf ou cnpj
        if (listaClientes.size() == 0) {
            System.out.println("Não existem clientes cadastrados.\n");
            return false;
        }
    
        String novoDado = dado.replaceAll("[^\\d]", "");
        try {
            if (novoDado.length() == 11) {
            //O dado é um CPF e o cliente uma PF
                for (int i = 0; i < listaClientes.size(); i++) {
                    if (((ClientePF) listaClientes.get(i)).getCpf() == dado) {
                        removerTodosSinistros(listaClientes.get(i));
                        System.out.println("Cliente removido com sucesso!\n");
                        return listaClientes.remove(listaClientes.get(i));
                    }
                }
            }
            else if (novoDado.length() == 13) { 
            //O dado é um CNPJ e o cliente uma PJ
                for (int i = 0; i < listaClientes.size(); i++) {
                    if (((ClientePJ) listaClientes.get(i)).getCnpj() == dado) {
                        removerTodosSinistros(listaClientes.get(i));
                        System.out.println("Cliente removido com sucesso!\n");
                        return listaClientes.remove(listaClientes.get(i));
                    }
                }
            }
        } catch (Exception e) {
            return false;
        } 
        return false;
    }

    public void listarClientes() {
        for (int j = 0; j < listaClientes.size(); j++) {
            System.out.println(listaClientes.get(j));
            System.out.println("\n########################\n");
        }
    }

    public Boolean gerarSinistro(Sinistro sinistro) {
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
            System.out.println("\n########################\n");
        }
    }

    //Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<Sinistro>();
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

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
}