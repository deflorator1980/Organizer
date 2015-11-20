package company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Executor {
    public Node clients;
    public Document doc;
    public int idNext;
    public String filepath = "data1.xml";

    public void connectXml() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.parse(filepath);
        clients = doc.getElementsByTagName("clients").item(0);
    }

    public void help() {
        System.out.println("\t-help\t\t\t\t\t\t\t\tвывод справки по командам органайзера");
        System.out.println("\t-insert <ФИО(в кавычках) должность организация email\n\t" +
                " телефоны(если несколько в одних кавычках)>\t\t\t\tдобавить нового клиента\n");
        System.out.println("\t-update <id_клиента  поле значение >\t\t\t\tредактировать");
        System.out.println("\t-delete <id_клиента>\t\t\t\t\t\tудалить клиента");
        System.out.println("\t-delete [-ph id_клиента телефон]\t\t\t\t удалить телефон");
        System.out.println("\t-list [поле по которому идёт сортировка]\t\t\tвывести список клиентов.");
        System.out.println("\t-find <номер телефона>\t\t\t\t\t\tпоиск клиента по номеру телефона");
    }

    public void listElement() {
        LinkedList<Storage> everyThing = new LinkedList<>();

        Storage storage;

        NodeList list = clients.getChildNodes();
        int length = list.getLength();
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if ("customer".equals(node.getNodeName())) {
                storage = new Storage();

                storage.id = node.getAttributes().getNamedItem("id")
                        .getTextContent();
                Element docElement = (Element) node;

                Node name = docElement.getElementsByTagName("name").item(0);
                storage.name = name.getTextContent();

                Node post = docElement.getElementsByTagName("post").item(0);
                storage.post = post.getTextContent();

                Node company = docElement.getElementsByTagName("company").item(
                        0);
                storage.company = company.getTextContent();

                Node email = docElement.getElementsByTagName("email").item(0);
                storage.setEmail(email.getTextContent());
                // storage.email = email.getTextContent();

                Node phones = docElement.getElementsByTagName("phones").item(0);
                ArrayList<String> phoneNumbers = new ArrayList<>();

                Element phonesElement = (Element) phones;

                NodeList phonesList = phonesElement.getChildNodes();

                int lengthPhones = phonesList.getLength();
                if (lengthPhones != 0) {
                    for (int n = 0; n < lengthPhones; n++) {
                        Node phone = phonesList.item(n);
                        if ("phone".equals(phone.getNodeName())) {

                            phoneNumbers.add(phone.getTextContent());

                        }
                        storage.setPhones(phoneNumbers);
                    }
                }

                everyThing.add(storage);

            }

        }

        for (Storage st : everyThing) {
            System.out.println(st);
        }
    }

    public void listElement(String field) {
        LinkedList<Storage> everyThing = new LinkedList<>();

        Storage storage;

        NodeList list = clients.getChildNodes();
        int length = list.getLength();
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if ("customer".equals(node.getNodeName())) {
                storage = new Storage();

                storage.id = node.getAttributes().getNamedItem("id")
                        .getTextContent();
                Element docElement = (Element) node;

                Node name = docElement.getElementsByTagName("name").item(0);
                storage.name = name.getTextContent();

                Node post = docElement.getElementsByTagName("post").item(0);
                storage.post = post.getTextContent();

                Node company = docElement.getElementsByTagName("company").item(0);
                storage.company = company.getTextContent();

                Node email = docElement.getElementsByTagName("email").item(0);
                storage.setEmail(email.getTextContent());

                Node phones = docElement.getElementsByTagName("phones").item(0);
                ArrayList<String> phoneNumbers = new ArrayList<>();

                Element phonesElement = (Element) phones;

                NodeList phonesList = phonesElement.getChildNodes();

                int lengthPhones = phonesList.getLength();
                if (lengthPhones != 0) {
                    for (int n = 0; n < lengthPhones; n++) {
                        Node phone = phonesList.item(n);
                        if ("phone".equals(phone.getNodeName())) {

                            phoneNumbers.add(phone.getTextContent());

                        }
                        storage.setPhones(phoneNumbers);
                    }
                }

                everyThing.add(storage);

            }

        }

        switch (field) {
            case "name":
                Collections.sort(everyThing, new Comparator<Storage>() {
                    public int compare(Storage o1, Storage o2) {
                        return o1.getName().toUpperCase()
                                .compareTo(o2.getName().toUpperCase());
                    }
                });
                break;

            case "post":
                Collections.sort(everyThing, new Comparator<Storage>() {
                    public int compare(Storage o1, Storage o2) {
                        return o1.getPost().toUpperCase()
                                .compareTo(o2.getPost().toUpperCase());
                    }
                });
                break;

            case "company":
                Collections.sort(everyThing, new Comparator<Storage>() {
                    public int compare(Storage o1, Storage o2) {
                        return o1.getCompany().toUpperCase()
                                .compareTo(o2.getCompany().toUpperCase());
                    }
                });
                break;

            case "email":
                Collections.sort(everyThing, new Comparator<Storage>() {
                    public int compare(Storage o1, Storage o2) {
                        return o1.getEmail().toUpperCase()
                                .compareTo(o2.getEmail().toUpperCase());
                    }
                });
                break;
        }

        for (Storage st : everyThing) {
            System.out.println(st);
        }

    }

    public void findPhone(String number) {
        boolean doesntExist = true;
        NodeList list = clients.getChildNodes();
        int length = list.getLength();
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);

            if ("customer".equals(node.getNodeName())) {
                Element phoneElement = (Element) node;
                int phoneQuantity = phoneElement.getElementsByTagName("phone")
                        .getLength();
                if (phoneQuantity != 0) {
                    for (int n = 0; n < phoneQuantity; n++) {
                        Node phoneNode = phoneElement.getElementsByTagName(
                                "phone").item(n);
                        if (number.equals(phoneNode.getTextContent())) {
                            System.out.println("phone '"
                                    + number
                                    + "' has customer id="
                                    + node.getAttributes().getNamedItem("id")
                                    .getTextContent());
                            doesntExist = false;
                        }
                    }
                }
            }
        }
        if (doesntExist) {
            System.out.println("phone '" + number + "' doesn't exist");
        }
    }

    public void insertElement(String cName, String cPost, String cCompany, String cEmail, String phoneNumbers) {

        String id = getId();

        Element customer = doc.createElement("customer");
        clients.appendChild(customer);

        customer.setAttribute("id", id);

        StringTokenizer st = new StringTokenizer(phoneNumbers, ", ");
        ArrayList<String> numbers = new ArrayList<>();
        while (st.hasMoreTokens()) {
            numbers.add(st.nextToken());
        }

        NodeList list = clients.getChildNodes();
        int length = list.getLength();
        int itemNumber = 0;
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if ("customer".equals(node.getNodeName()))
                itemNumber++;
        }

        Node customerNode = doc.getElementsByTagName("customer").item(itemNumber - 1);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(cName));
        customerNode.appendChild(name);

        Element post = doc.createElement("post");
        post.appendChild(doc.createTextNode(cPost));
        customerNode.appendChild(post);


        Element company = doc.createElement("company");
        company.appendChild(doc.createTextNode(cCompany));
        customerNode.appendChild(company);

        Element email = doc.createElement("email");
        email.appendChild(doc.createTextNode(cEmail));
        customerNode.appendChild(email);

        Element phones = doc.createElement("phones");

        if (numbers.toString() == "[]") {
            Element phone = doc.createElement("phone");
            phone.appendChild(doc.createTextNode(""));
            phones.appendChild(phone);

        } else {
            for (String number : numbers) {
                Element phone = doc.createElement("phone");
                phone.appendChild(doc.createTextNode(number));
                phones.appendChild(phone);
            }
        }

        customerNode.appendChild(phones);

    }

    public String getId() {
        NodeList list = clients.getChildNodes();

        int length = list.getLength();
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if ("customer".equals(node.getNodeName())) {
                int id = Integer.parseInt(node.getAttributes().getNamedItem("id").getTextContent());
                if (idNext <= id)
                    idNext = id + 1;
            }
        }
        return Integer.toString(idNext);
    }

    public void deleteElement(String idDel) {
        NodeList list = clients.getChildNodes();
        int length = list.getLength();
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if ("customer".equals(node.getNodeName())) {
                String id = node.getAttributes().getNamedItem("id").getTextContent();
                if (idDel.equals(id)) {
                    clients.removeChild(node);
                    System.out.println("User "+ idDel+" is deleted");
                    return;
                }
            }
        }
        System.out.println("User "+idDel+" doesn't exist");
    }

    public void deleteElement(String idUpdate, String phoneDel) {
        NodeList list = clients.getChildNodes();
        int length = list.getLength();
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if ("customer".equals(node.getNodeName())) {
                String id = node.getAttributes().getNamedItem("id").getTextContent();
                if (idUpdate.equals(id)) {
                    Element phoneElement = (Element) node;
                    int phoneQuantity = phoneElement.getElementsByTagName("phone").getLength();
                    if (phoneQuantity != 0) {
                        for (int n = 0; n < phoneQuantity; n++) {
                            Node phoneNode = phoneElement.getElementsByTagName("phone").item(n);
                            if (phoneDel.equals(phoneNode.getTextContent())) {
                                Node phoneSNode = phoneNode.getParentNode();
                                phoneSNode.removeChild(phoneNode);
                                System.out.println("phone " + phoneDel + " deleted by user " + idUpdate);
                                return;
                            }
                        }
                        System.out.println("phone " + phoneDel + " doesn't exist by user " + idUpdate);
                    }
                }
            }
        }
    }

    public void updateElement(String idUpd, String field, String newValue) {
        NodeList list = clients.getChildNodes();
        int length = list.getLength();
        for (int i = 0; i < length; i++) {
            Node node = list.item(i);
            if ("customer".equals(node.getNodeName())) {
                String id = node.getAttributes().getNamedItem("id").getTextContent();
                if (idUpd.equals(id)) {

                    Element docElement = (Element) node;

                    Node nodeName = docElement.getElementsByTagName(field).item(0);

                    if("phone".equals(nodeName.getNodeName())){


                        Node phones = nodeName.getParentNode();
                        Element phone = doc.createElement("phone");
                        phone.appendChild(doc.createTextNode(newValue));
                        phones.appendChild(phone);

                    }

                    else {
                        nodeName.setTextContent(newValue);
                    }
                }
            }
        }
    }

    public void writeXml() throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        t.transform(source, result);

        System.out.println("Written");
    }
}

