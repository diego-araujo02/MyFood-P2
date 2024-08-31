package br.ufal.ic.p2.MyFood;

public class Owner extends User {
    private String cpf;
    private String endereco;

    public Owner(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha);
        this.endereco = endereco;
        this.cpf = cpf;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getEndereco() {
        return endereco;
    }

}
