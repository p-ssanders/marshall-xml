package dev.samsanders.demo.xml;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import javax.xml.bind.Marshaller;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class XmlWriterThingTest {

  @Autowired
  private Marshaller marshaller;

  @Test
  void writeSomeXml() {
    String expected =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
      + "<SimpleThingType xmlns=\"http://samsanders.dev/SimpleThing.xsd\">\n"
      + "    <name>some-name</name>\n"
      + "    <time>2019-11-26-07:00</time>\n"
      + "</SimpleThingType>";
    XmlWriterThing xmlWriterThing = new XmlWriterThing(marshaller);

    Date date = new Date(1574810458000l);
    String actual = xmlWriterThing.writeSomeXml(new SimpleThing("some-name", date));

    assertEquals(actual.trim(), expected.trim());
  }
}