package br.ufal.ic.p2.MyFood.UserPackage;

public class Customer extends User {
    private String endereco;

    public Customer(String nome, String email, String senha, String endereco) {
        super(nome, email, senha, endereco);
        this.endereco = endereco;
    }

    @Override
    public boolean isOwner(){
        return false;
    }
}
