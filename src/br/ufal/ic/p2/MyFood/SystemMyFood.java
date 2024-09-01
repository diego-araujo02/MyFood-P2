package br.ufal.ic.p2.MyFood;

import java.io.File;
import java.util.ArrayList;

public class SystemMyFood {
    private XMLUser xmlUser;

    public SystemMyFood(String filePathUsers, String rootNameUsers) {
        this.xmlUser = new XMLUser(filePathUsers, rootNameUsers);
        XML.loadCurrentId(xmlUser);
    }
    public void criarUsuario(String name, String email, String senha, String endereco, String cpf){
        User.setCurrentId(User.getCurrentId() + 1);
        User user = new Owner(name, email, senha, endereco, cpf);
        xmlUser.addUser("owner", user);
    }

    public void criarUsuario(String name, String email, String senha, String endereco){
        User.setCurrentId(User.getCurrentId() + 1);
        User user = new Customer(name, email, senha, endereco);
        xmlUser.addUser("customer", user);
    }

    public void zerarSistema() {
        ArrayList<String> filePaths = new ArrayList<String>();
        filePaths.add("src/br/ufal/ic/p2/MyFood/data/users.xml");
        filePaths.add("src/br/ufal/ic/p2/MyFood/data/pedidos.xml");

        for (String filePath : filePaths){
            File xmlfFile = new File(filePath);
            if (xmlfFile.exists()){
                xmlUser.clearXML();
            }
        }
    }
}
