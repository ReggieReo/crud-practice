import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class AdminXMLRecorder {
    public static LinkedList<Admin> loadAdminData() {
        final String FILE = "AdminList.xml";
        LinkedList<Admin> adminList = new LinkedList<Admin>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILE));
            doc.getDocumentElement().normalize();
            // get <student>
            NodeList list = doc.getElementsByTagName("admin");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
  
                if (node.getNodeType() == Node.ELEMENT_NODE) {
  
                    Element element = (Element) node;
  
                    // get text
                    String userName = element.getElementsByTagName("username").item(0).getTextContent();
                    String passWord = element.getElementsByTagName("password").item(0).getTextContent();
                    Admin tempAdmin = new Admin(userName, passWord);
                    adminList.add(tempAdmin);
                }
            }
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            System.out.println("The file is empty");
        }
        return adminList;
    }


    public static void saveAdminData(LinkedList<Admin> adminList) throws ParserConfigurationException, TransformerException {
        try {
 
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("adminlist");
            document.appendChild(root);
            // create student with its element
            for(Admin admin: adminList) {
                Element admin_ = document.createElement("admin");
                root.appendChild(admin_);
                Element name = document.createElement("username");
                name.setTextContent(admin.getAdminUserName());
                admin_.appendChild(name);

                Element adminPassword = document.createElement("password");
                adminPassword.setTextContent(admin.getAdminPassword());
                admin_.appendChild(adminPassword);
            }
 
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("AdminList.xml"));
 
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);
            
            
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
