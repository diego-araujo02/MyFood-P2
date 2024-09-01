package br.ufal.ic.p2.MyFood;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLUser extends XML {

    public XMLUser(String filePath, String rootName) {
        super(filePath, rootName);
    }

    public void addUser(String objectType, User user) {
        try {
            Element newUser = doc.createElement(objectType);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(Integer.toString(user.getId())));
            newUser.appendChild(idElement);

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(user.getName()));
            newUser.appendChild(nameElement);

            Element emailElement = doc.createElement("email");
            emailElement.appendChild(doc.createTextNode(user.getEmail()));
            newUser.appendChild(emailElement);

            Element senhaElement = doc.createElement("senha");
            senhaElement.appendChild(doc.createTextNode(user.getSenha()));
            newUser.appendChild(senhaElement);

            Element enderecoElement = doc.createElement("endereco");
            enderecoElement.appendChild(doc.createTextNode(user.getEndereco()));
            newUser.appendChild(enderecoElement);

            if (objectType.equals("owner")) {
                Element cpfElement = doc.createElement("cpf");
                cpfElement.appendChild(doc.createTextNode(user.getCpf()));
                newUser.appendChild(cpfElement);
            }

            doc.getDocumentElement().appendChild(newUser);
            saveXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
