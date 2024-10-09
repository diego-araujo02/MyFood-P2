package br.ufal.ic.p2.MyFood.UserPackage;

public class Owner extends User {
    private String cpf;

    public Owner(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean isOwner() {
        return true;
    }
}
