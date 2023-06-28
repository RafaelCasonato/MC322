public enum SubmenuOperacoes {
    CADASTRAR_CLIENTE("Cadastrar cliente"),
    CADASTRAR_VEICULO("Cadastrar veiculo"),
    CADASTRAR_SEGURADORA("Cadastrar seguradora"),
    CADASTRAR_FROTA("Cadastrar frota"),
    CADASTRAR_SEGURO("Cadastrar seguro"),
    CADASTRAR_CONDUTOR("Cadastrar condutor"),
    LISTAR_CLIENTES("Listar clientes"),
    LISTAR_SINISTROS("Listar sinistros"),
    LISTAR_VEICULOS("Listar veiculo"),
    LISTAR_SEGURADORAS("Listar seguradoras"),
    LISTAR_SEGUROS("Listar seguros"),
    LISTAR_FROTA("Listar frota"),
    LISTAR_CONDUTOR("Listar condutores"),
    EXCLUIR_CLIENTE("Excluir cliente"),
    EXCLUIR_VEICULO("Excluir veiculo"),
    EXCLUIR_SEGURO("Excluir seguro"),
    EXCLUIR_CONDUTOR("Excluir/Desautorizar condutor"),
    EXCLUIR_FROTA("Excluir frota"),
    EXCLUIR_SINISTRO("Excluir sinistro"),
    VOLTAR("Voltar");

    //Atributos
    private final String descricao;

    //Construtor 
    SubmenuOperacoes(String descricao) {
        this.descricao = descricao;
    }

    //Getters
    public String getDescricao() {
        return descricao;
    }
}