package br.ufal.ic.p2.MyFood;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class XML {
    protected Document doc;
    protected String filePath;
    protected String rootName;

    public XML(String filePath, String rootName) {
        this.filePath = filePath;
        this.rootName = rootName;
        loadXML(this.filePath, this.rootName);
    }

    private void loadXML(String filePath, String rootName) {
        try {
            File xmlFile = new File(filePath);
            if (!xmlFile.exists()) {
                createNewXMLFile(filePath, rootName);
            } else {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                this.doc = builder.parse(xmlFile);
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

    public void saveXML() throws TransformerException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource src = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(src, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearXML() {
        try {
            if (doc != null) {
                Element root = doc.getDocumentElement();
                while (root.hasChildNodes()) {
                    root.removeChild(root.getFirstChild());
                }
                saveXML();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}