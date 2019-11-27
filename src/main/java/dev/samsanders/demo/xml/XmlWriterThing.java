package dev.samsanders.demo.xml;

import dev.samsanders.demo.xml.generated.SimpleThingType;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;


public class XmlWriterThing {

  private final Marshaller marshaller;

  public XmlWriterThing(Marshaller marshaller) {
    this.marshaller = marshaller;
  }

  public String writeSomeXml(SimpleThing simpleThing) {
    SimpleThingType simpleThingType = getSimpleThingType(simpleThing);

    StringWriter result = new StringWriter();
    try {
      JAXBElement<SimpleThingType> jaxbElement =
          new JAXBElement<>(new QName("http://samsanders.dev/SimpleThing.xsd", "SimpleThingType"), SimpleThingType.class, simpleThingType);

      marshaller.marshal(jaxbElement, result);
    } catch (JAXBException e) {
      e.printStackTrace();
    }

    return result.toString();
  }

  private SimpleThingType getSimpleThingType(SimpleThing simpleThing) {
    SimpleThingType simpleThingType = new SimpleThingType();
    simpleThingType.setName(simpleThing.getName());
    GregorianCalendar c = new GregorianCalendar();
    c.setTime(simpleThing.getTime());
    try {
      simpleThingType.setTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
    } catch (DatatypeConfigurationException e) {
      e.printStackTrace();
    }
    return simpleThingType;
  }
}
