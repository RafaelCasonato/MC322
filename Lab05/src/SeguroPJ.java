import java.util.Date;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;
    
    //Construtor
    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota,
            ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    //Getters e setters
    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
    public String getDado() {
        return cliente.getDado();
    }

    //Métodos
    public int quantidadeVeiculos() {
        int quantidade = 0;
        for (Frota f : cliente.getlistaFrotas()) {
            if (f.getListaVeiculos() == null) {
                return 0;
            }
            quantidade += f.getListaVeiculos().size();
        }
        return quantidade;
    }

    @Override
    public String toString() {
        return "Seguro: " + "\nFrota: " + frota + "\nCliente: " + cliente + "\n";
    }
    
    public double calcularValor() {
        double valor = 0;
        valor = (CalcSeguro.VALOR_BASE.getFator() * (10 + ((super.getListaCondutores().size())/10)) * 
                (1 + (1/(quantidadeVeiculos()+2))) * (1 + (1/cliente.getIdade()+2)) * 
                (2 + (super.getListaSinistros().size()/10)) * (5 + (super.quantidadeSinistrosCondutores()/10)));
        return valor;
    }
}    