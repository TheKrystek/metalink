package pl.put;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Author: Krystian Świdurski
 */
public class XMLWriter {

    public void write(Object object, String file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(object, System.out);
        m.marshal(object, new File(file));
    }

}
