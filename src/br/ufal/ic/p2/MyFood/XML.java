package br.ufal.ic.p2.MyFood;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class XML {
    private Document doc;
    private String filePath;
    private String rootName;

    public XML(String filePath, String rootName) {
        this.filePath = filePath;
        this.rootName = rootName;
        loadXML(this.filePath, this.rootName);
    }

    private void loadXML(String filePath, String rootName) {
        try {
            File xmlfFile = new File(filePath);
            if (!xmlfFile.exists()) {
                createNewXMLFile(filePath, rootName);
            } else {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                this.doc = builder.parse(xmlfFile);
                this.doc.getDocumentElement().normalize();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createNewXMLFile(String filePath, String rootName){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.doc = builder.newDocument();
            Element root = this.doc.createElement(rootName);
            this.doc.appendChild(root);
            saveXML();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public Document getDocument() {
        return this.doc;
    }

    public static void loadCurrentId(XML xml) {
        Document doc = xml.getDocument();
        Element rootElement = doc.getDocumentElement();

        String lastIdStr = rootElement.getAttribute("lastId");
        if (lastIdStr != null && !lastIdStr.isEmpty()) {
            User.setCurrentId(Integer.parseInt(lastIdStr));
        } else {
            User.setCurrentId(0);
        }
    }

    private void saveXML() throws TransformerException {
        try {
            transformerXML(filePath, doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void transformerXML(String filePath, Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource src = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(src, result);
    }

    public static void clearXML(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            while (root.hasChildNodes()) {
                root.removeChild(root.getFirstChild());
            }
            transformerXML(filePath, doc);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addElement(String objectType, User user) {
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

            if (objectType.equals("Owner")){
                Element cpfElement = doc.createElement("cpf");
                cpfElement.appendChild(doc.createTextNode(user.getCpf()));
                newUser.appendChild(cpfElement);
            }

            doc.getDocumentElement().appendChild(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
