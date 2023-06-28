import java.util.Date;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;
    
    //Getters e setters
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    @Override
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }
    public String getDado() {
        return cliente.getDado();
    }

    //Construtor 
    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Veiculo veiculo,
            ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
        super.setValorMensal(calcularValor());
    }

    //Métodos 
    @Override
    public String toString() {
        return "Seguro: " + "\nVeículo: " + veiculo + "\nCliente: " + cliente + "\n";
    }

    public double calcularValor() {
        double valor = 0;
        valor = (CalcSeguro.VALOR_BASE.getFator() * cliente.fatorIdade() * (1 + 1/((cliente.getListaVeiculos().size() + 2))) *
                (2 + (super.getListaSinistros().size()/10)) * (5 + (super.quantidadeSinistrosCondutores()/10)));
        return valor;
    }
}
