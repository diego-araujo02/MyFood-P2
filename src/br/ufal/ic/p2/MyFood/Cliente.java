package br.ufal.ic.p2.MyFood;

public class Cliente extends Usuario{
    private String endereco;


    public Cliente(String nome, String email, String senha, String endereco) {
        super(nome, email, senha);
        this.endereco = endereco;
    }
}
