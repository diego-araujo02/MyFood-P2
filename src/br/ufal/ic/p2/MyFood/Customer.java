package br.ufal.ic.p2.MyFood;

public class Customer extends User {
    private String endereco;


    public Customer(String nome, String email, String senha, String endereco) {
        super(nome, email, senha);
        this.endereco = endereco;
    }


    @Override
    public String getCpf() {
        return null;
    }

    @Override
    public String getEndereco() {
        return endereco;
    }
}
