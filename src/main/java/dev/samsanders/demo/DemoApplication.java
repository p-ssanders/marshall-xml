package dev.samsanders.demo;

import dev.samsanders.demo.xml.SimpleThing;
import dev.samsanders.demo.xml.XmlWriterThing;
import dev.samsanders.demo.xml.generated.SimpleThingType;
import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Configuration
	public class DemoApplicationConfig {

		@Bean
		public Marshaller marshaller() throws JAXBException, IOException, SAXException {
			Resource resource = new ClassPathResource("xsd/schema.xsd");
			File schemaFile = new File(resource.getURI());
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);

			JAXBContext context = JAXBContext.newInstance("dev.samsanders.demo.xml.generated");
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setSchema(schema);

			return marshaller;
		}

		@Bean
		public XmlWriterThing xmlWriterThing(Marshaller marshaller) {
			return new XmlWriterThing(marshaller);
		}

	}

}
