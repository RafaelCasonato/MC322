public enum MenuOperacoes {
    CADASTROS("Cadastros", new SubmenuOperacoes[] {
        SubmenuOperacoes.CADASTRAR_CLIENTE,
        SubmenuOperacoes.CADASTRAR_VEICULO,
        SubmenuOperacoes.CADASTRAR_SEGURADORA,
        SubmenuOperacoes.VOLTAR
    }),
    LISTAR("Listar", new SubmenuOperacoes[] {
        SubmenuOperacoes.LISTAR_CLIENTES,
        SubmenuOperacoes.LISTAR_SINISTROS,
        SubmenuOperacoes.LISTAR_VEICULOS,
        SubmenuOperacoes.VOLTAR
    }),
    EXCLUIR("Excluir", new SubmenuOperacoes[]{
        SubmenuOperacoes.EXCLUIR_CLIENTE,
        SubmenuOperacoes.EXCLUIR_VEICULO,
        SubmenuOperacoes.EXCLUIR_SINISTRO,
        SubmenuOperacoes.VOLTAR
    }),
    GERAR_SINISTRO("Gerar Sinistro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
    TRANSFERIR_SEGURO("Transferir Seguro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
    CALCULAR_RECEITA("Calcular Receita Seguradora", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
    SAIR("Sair", new SubmenuOperacoes[] {});
    
    //Atributos
    private final String descricao;
    private final SubmenuOperacoes[] submenu;

    //Construtor
    MenuOperacoes(String descricao, SubmenuOperacoes[] submenu) {
        this.descricao = descricao;
        this.submenu = submenu;
    }

    //Getters
    public String getDescricao() {
        return descricao;
    }

    public SubmenuOperacoes[] getSubmenu() {
        return submenu;
    }

}