public enum CalcSeguro {
    VALOR_BASE(10),
    FATOR_menor30(1.25),
    FATOR_30_60(1.0),
    FATOR_maior60(1.5);

    //Atributo
    private final double fator;

    //Construtor
    CalcSeguro(double fator) {
        this.fator = fator;
    }

    //Getter
    public double getFator() {
        return fator;
    }
}