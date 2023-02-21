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
import java.lang.Integer;
import java.util.LinkedList;

public class StudentXMLRecorder {
    public static LinkedList<Student> loadStudentData() {
        final String FILE = "StudentList.xml";
        LinkedList<Student> studentList = new LinkedList<Student>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILE));
            doc.getDocumentElement().normalize();
            // get <student>
            NodeList list = doc.getElementsByTagName("student");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
  
                if (node.getNodeType() == Node.ELEMENT_NODE) {
  
                    Element element = (Element) node;
  
                    // get text
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String idnumberstring = element.getElementsByTagName("idnumber").item(0).getTextContent();
                    String contactnumber = element.getElementsByTagName("contactnumber").item(0).getTextContent();
                    int id = Integer.parseInt(idnumberstring);
                    Student tempStudent = new Student(name, id, contactnumber);
                    studentList.add(tempStudent);
                }
            }
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            System.out.println("The file is empty");
        }
        return studentList;
    }


    public static void saveStudentData(LinkedList<Student> studentList) throws ParserConfigurationException, TransformerException {
        try {
 
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("studentlist");
            document.appendChild(root);
            // create student with its element
            for(Student student: studentList) {
                Element student_ = document.createElement("student");
                root.appendChild(student_);
                Element name = document.createElement("name");
                name.setTextContent(student.getName());
                student_.appendChild(name);
                
                Element idNumber = document.createElement("idnumber");
                idNumber.setTextContent(Integer.toString(student.getIdNumber()));
                student_.appendChild(idNumber);

                Element contractNumber = document.createElement("contactnumber");
                contractNumber.setTextContent(student.getContactNumber());
                student_.appendChild(contractNumber);
            }
 
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("StudentList.xml"));
 
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
