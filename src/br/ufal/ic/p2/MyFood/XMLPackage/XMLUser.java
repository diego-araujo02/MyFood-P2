package br.ufal.ic.p2.MyFood.XMLPackage;

import br.ufal.ic.p2.MyFood.UserPackage.Customer;
import br.ufal.ic.p2.MyFood.UserPackage.Owner;
import br.ufal.ic.p2.MyFood.UserPackage.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLUser extends XML {

    public XMLUser(String filePath, String rootName) {
        super(filePath, rootName);
    }

    @Override
    public void clearXML() {
        try {
            if (doc != null) {
                Element root = doc.getDocumentElement();
                while (root.hasChildNodes()) {
                    root.removeChild(root.getFirstChild());
                }
                updateLastId(0);
                saveXML();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLastId(int newId) {
        try {
            Element root = doc.getDocumentElement();
            root.setAttribute("lastId", String.valueOf(newId));
            saveXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                Owner owner = (Owner) user;
                Element cpfElement = doc.createElement("cpf");
                cpfElement.appendChild(doc.createTextNode(owner.getCpf()));
                newUser.appendChild(cpfElement);
            }

            doc.getDocumentElement().appendChild(newUser);
            updateLastId(user.getId());
            saveXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> loadUsersFromXML(String xmlFilePath) {
        ArrayList<User> usersArray = new ArrayList<>();
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList ownerList = doc.getElementsByTagName("owner");
            NodeList customerList = doc.getElementsByTagName("customer");

            // Processar os Owners
            for (int i = 0; i < ownerList.getLength(); i++) {
                Element ownerElement = (Element) ownerList.item(i);

                int id = Integer.parseInt(ownerElement.getElementsByTagName("id").item(0).getTextContent());
                String nome = ownerElement.getElementsByTagName("name").item(0).getTextContent();
                String email = ownerElement.getElementsByTagName("email").item(0).getTextContent();
                String senha = ownerElement.getElementsByTagName("senha").item(0).getTextContent();
                String endereco = ownerElement.getElementsByTagName("endereco").item(0).getTextContent();
                String cpf = ownerElement.getElementsByTagName("cpf").item(0).getTextContent();

                Owner owner = new Owner(nome, email, senha, endereco, cpf);
                owner.setId(id);
                usersArray.add(owner);
            }

            // Processar os Customers
            for (int i = 0; i < customerList.getLength(); i++) {
                Element customerElement = (Element) customerList.item(i);

                int id = Integer.parseInt(customerElement.getElementsByTagName("id").item(0).getTextContent());
                String nome = customerElement.getElementsByTagName("name").item(0).getTextContent();
                String email = customerElement.getElementsByTagName("email").item(0).getTextContent();
                String senha = customerElement.getElementsByTagName("senha").item(0).getTextContent();
                String endereco = customerElement.getElementsByTagName("endereco").item(0).getTextContent();

                Customer customer = new Customer(nome, email, senha, endereco);
                customer.setId(id);
                usersArray.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersArray;
    }


}
