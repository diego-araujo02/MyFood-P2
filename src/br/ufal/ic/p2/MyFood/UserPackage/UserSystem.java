package br.ufal.ic.p2.MyFood.UserPackage;

import br.ufal.ic.p2.MyFood.Exceptions.EmailAlreadyExists;
import br.ufal.ic.p2.MyFood.Exceptions.InvalidAttribute;
import br.ufal.ic.p2.MyFood.Exceptions.UserNotRegistered;
import br.ufal.ic.p2.MyFood.XMLPackage.XML;
import br.ufal.ic.p2.MyFood.XMLPackage.XMLUser;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserSystem {
    private XMLUser xmlUser;
    private ArrayList<User> usersArray;

    public UserSystem(String filePath, String rootName) {
        this.xmlUser = new XMLUser(filePath, rootName);
        XML.loadCurrentId(xmlUser);
        loadUsers(filePath);
    }

    private void loadUsers(String filePath) {
        usersArray = XMLUser.loadUsersFromXML(filePath);
    }

    public void checkDataFromUser(String nome, String email, String senha, String endereco) throws InvalidAttribute {
        if (nome == null || nome.isEmpty()){
            throw new InvalidAttribute("Nome invalido");
        } else if (email == null || email.isEmpty() || !email.contains("@")){
            throw new InvalidAttribute("Email invalido");
        } else if (senha == null || senha.isEmpty() ){
            throw new InvalidAttribute("Senha invalido");
        } else if (endereco == null || endereco.isEmpty()){
            throw new InvalidAttribute("Endereco invalido");
        }
    }

    public void checkDataFromUser(String nome, String email, String senha, String endereco, String cpf) throws InvalidAttribute {
        checkDataFromUser(nome, email, senha, endereco);
        if (cpf == null || cpf.isEmpty() || cpf.length() != 14){
            throw new InvalidAttribute("CPF invalido");
        }
    }

    //customer
    public void criarUsuario(String nome, String email, String senha, String endereco) throws EmailAlreadyExists, InvalidAttribute {
        checkDataFromUser(nome, email, senha, endereco);

        if (usersArray.stream().anyMatch(user -> user.email.equals(email))){
            throw new EmailAlreadyExists();
        }

        Customer customer = new Customer(nome, email, senha, endereco);
        User.setCurrentId(User.getCurrentId() + 1);
        xmlUser.addUser("customer", customer);
        usersArray.add(customer);
    }

    //owner
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws EmailAlreadyExists, InvalidAttribute {
        checkDataFromUser(nome, email, senha, endereco, cpf);

        if (usersArray.stream().anyMatch(user -> user.email.equals(email))){
            throw new EmailAlreadyExists();
        }

        Owner owner = new Owner(nome, email, senha, endereco, cpf);
        User.setCurrentId(User.getCurrentId() + 1);
        xmlUser.addUser("owner", owner);
        usersArray.add(owner);
    }

    public String getAtributoUsuario(int id, String atributo) throws UserNotRegistered {
        if (!usersArray.stream().anyMatch(user -> user.getId() == id)) {
            throw new UserNotRegistered();
        }

        User user = usersArray.get(id);

        if (user.isOwner() && atributo.equals("cpf")) {
            Owner owner = (Owner) user;
            return owner.getCpf();
        }

        return switch (atributo) {
            case "id" -> String.valueOf(user.getId());
            case "name" -> user.getName();
            case "email" -> user.getEmail();
            case "senha" -> user.getSenha();
            case "endereco" -> user.getEndereco();
            default -> throw new UserNotRegistered();
        };
    }

    public int login(String email, String senha) throws InvalidAttribute{
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()){
            throw new InvalidAttribute("Login ou senha invalidos");
        }

        for (User user : usersArray) {
            if (user.getEmail() == email && user.getSenha() != senha) {
                throw new InvalidAttribute("Login ou senha invalidos");
            } else if (user.getEmail() != email && user.getSenha() == senha) {
                throw new InvalidAttribute("Login ou senha invalidos");
            } else if (user.getEmail() != email && user.getSenha() != senha){
                throw new InvalidAttribute("Login ou senha invalidos");
            }
        }
        return usersArray.stream().filter(user -> user.getEmail() == email && user.getSenha() == senha).findFirst().get().getId();
    }
    public void zerarSistema(){
        xmlUser.clearXML();

    }
}
