package br.ufal.ic.p2.MyFood;

import java.io.File;
import java.util.ArrayList;

public final class SystemMyFood {

    public User criarUsuario(String nome, String email, String senha, String endereco, String cpf){
        User.setCurrentId(User.getCurrentId() + 1);
        if (!cpf.isEmpty()){
            return new Owner(nome, email, senha, endereco, cpf);
        } else {
            return new Customer(nome, email, senha, endereco);
        }
    }

    public void addUserToXML(User user){

    }

    public void zerarSistema() {
        ArrayList<String> filePaths = new ArrayList<String>();
        filePaths.add("src/br/ufal/ic/p2/MyFood/data/users.xml");
        filePaths.add("src/br/ufal/ic/p2/MyFood/data/pedidos.xml");

        for (String filePath : filePaths){
            File xmlfFile = new File(filePath);
            if (xmlfFile.exists()){
                XML.clearXML(filePath);
            }
        }
    }
}
