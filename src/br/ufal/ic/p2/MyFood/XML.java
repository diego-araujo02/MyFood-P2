package br.ufal.ic.p2.MyFood;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public final class XML {
    private Document doc;
    private String filePath;
    private String rootName;

    public XML(String filePath, String rootName) {
        this.filePath = filePath;
        this.rootName = rootName;
        loadxml(this.filePath, this.rootName);
    }

    private void loadxml(String filePath, String rootName) {
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

    public void saveXML() throws TransformerException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource src = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(src, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
