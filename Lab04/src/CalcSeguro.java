public enum CalcSeguro {
    VALOR_BASE(100.0),
    FATOR_18_30(1.2),
    FATOR_30_60(1.0),
    FATOR_60_90(1.5);

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