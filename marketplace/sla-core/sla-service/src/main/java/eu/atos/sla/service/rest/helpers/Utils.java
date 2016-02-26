package eu.atos.sla.service.rest.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Deprecated
public class Utils<T> {

	private static final String PATH_SEP = "/";

	public String parseToXML(T elements, Class<T> classElem)
			throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(classElem);
		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		marshaller.marshal(elements, out);

		return out.toString();
	}

	public String parseToJson(T elements) throws JAXBException {

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_EMPTY);
		String guaranteeTermsString = null;
		try {
			guaranteeTermsString = mapper.writeValueAsString(elements);

		} catch (JsonGenerationException e) {
			throw new IllegalArgumentException("not valid json");
		} catch (JsonMappingException e) {
			throw new IllegalArgumentException("not valid json");
		} catch (IOException e) {
			throw new IllegalArgumentException("not valid json");
		}

		return guaranteeTermsString;
	}

	public static String buildResourceLocation(String collectionUri, String resourceId) {
		String result;
		if (collectionUri.endsWith(PATH_SEP)) {
			result = collectionUri + resourceId;
		}
		else {
			result = collectionUri + PATH_SEP + resourceId;
		}
		return result;
	}
}
