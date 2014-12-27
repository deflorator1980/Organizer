package company;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by a on 24.12.2014.
 */
public class Organizer {
    static boolean toWrite = false;
    static Executor exe;
    static int length;
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        exe = new Executor();
        length = args.length;
        if (length == 0) {
            exe.help();
            return;
        }
        exe.connectXml();


        switch (args[0]) {
            case "-help":
                exe.help();
                break;

            case "-list":
                if (length > 1) {
                    exe.listElement(args[1]);
                } else exe.listElement();
                break;

            case "-find":
                if (length > 1) {
                    exe.findPhone(args[1]);
                } else {
                    System.out.println("Enter phone number ");
                }
                break;


            case "-insert":
                if (length > 5) {
                    exe.insertElement(args[1], args[2], args[3], args[4], args[5]);
                    toWrite = true;
                } else {
                    System.out.println("Enter all arguments ");
                }
                break;

            case "-delete":
                if (length > 1) {
                    if (args[1].equals("-ph")) {
                        if (length > 3) {
                            exe.deleteElement(args[2], args[3]);
                        }
                    } else exe.deleteElement(args[1]);
                    toWrite = true;
                } else {
                    System.out.println("Enter argument ");
                }
                break;

            case "-update":
                if(length > 3){
                    exe.updateElement(args[1], args[2], args[3]);
                    toWrite = true;
                }else System.out.println("Enter all arguments");
                break;
            default:
                System.out.println("Enter correct command");
        }
        if(toWrite) {
            exe.writeXml();
        }
    }
}
