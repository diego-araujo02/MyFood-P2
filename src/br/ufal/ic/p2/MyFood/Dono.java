package br.ufal.ic.p2.MyFood;

public class Dono extends Usuario{
    private String cpf;
    private String endereco;

    public Dono(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha);
        this.endereco = endereco;
        this.cpf = cpf;
    }
}
