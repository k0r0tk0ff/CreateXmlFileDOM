package ru.k0r0tk0ff.main;


import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by k0r0tk0ff on 5/26/2017.
 * lesson https://vk.com/feed?w=wall-71245615_1577
 * @since 1.6+
 */
public class Main {
    public static void main(String[] arg) throws ParserConfigurationException,
            IOException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("root");
        Element font = document.createElement("font");
        Text text = document.createTextNode("TimesNewRoman");

        document.appendChild(root);
        root.appendChild(font);
        font.appendChild(text);
        font.setAttribute("size", "20");

        // save xml to file

        // 1 method create temp.xml (encoding UTF-8)
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document),
                        new StreamResult(
                            new FileOutputStream("temp.xml")
                                         )
        );

        // 2 method (we can create String variable with xml)
        // create temp2.xml (encoding UTF-8)
        // create String with xml from document
        DOMImplementation impl = document.getImplementation();
        DOMImplementationLS implLS = (DOMImplementationLS)impl.getFeature("LS", "3.0");
        LSSerializer ser = implLS.createLSSerializer();
        ser.getDomConfig().setParameter("format-pretty-print", true);
        String string = ser.writeToString(document);

        // output of string
        System.out.println(string);

        LSOutput out = implLS.createLSOutput();
        out.setEncoding("UTF-8");
        //out.setByteStream(Files.newOutputStream(Paths.get("temp2.xml")));
        out.setByteStream(new FileOutputStream( new File("temp2.xml")));
        ser.write(document, out);
        String string2 = ser.writeToString(document);
        System.out.println(string2);
    }
}
