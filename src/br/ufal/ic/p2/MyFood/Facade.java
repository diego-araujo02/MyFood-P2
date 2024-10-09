package br.ufal.ic.p2.MyFood;

import br.ufal.ic.p2.MyFood.Exceptions.EmailAlreadyExists;
import br.ufal.ic.p2.MyFood.Exceptions.InvalidAttribute;
import br.ufal.ic.p2.MyFood.Exceptions.UserNotRegistered;
import br.ufal.ic.p2.MyFood.UserPackage.UserSystem;

public class Facade {
    private UserSystem userSystem;

    public Facade() {
        this.userSystem = new UserSystem("src/br/ufal/ic/p2/MyFood/data/users.xml", "users");
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws EmailAlreadyExists, InvalidAttribute {
        userSystem.criarUsuario(nome, email, senha, endereco);
    }
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws EmailAlreadyExists, InvalidAttribute{
        userSystem.criarUsuario(nome, email, senha, endereco, cpf);
    }
    public void getAtributoUsuario(int id, String atributo) throws UserNotRegistered {
        userSystem.getAtributoUsuario(id, atributo);
    }
    public int login(String email, String senha) throws InvalidAttribute {
        return userSystem.login(email, senha);
    }
    public void zerarSistema(){
        userSystem.zerarSistema();
    }
}
