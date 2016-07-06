package eu.atos.sla.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.bean.PenaltyDefinition;
import eu.atos.sla.datamodel.bean.Policy;
import eu.atos.sla.parser.data.wsag.Context.ServiceProvider;

public class ModelConversionTest {

	private IModelConverter modelConverter = new ModelConversion();

	public ModelConversionTest() throws ModelConversionException {
		((ModelConversion)modelConverter).setBusinessValueListParser(new BusinessValueListParser());
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private eu.atos.sla.parser.data.wsag.Agreement readXml(File f) 
			throws JAXBException, FileNotFoundException {
		
		JAXBContext jaxbContext = JAXBContext
				.newInstance(eu.atos.sla.parser.data.wsag.Agreement.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		eu.atos.sla.parser.data.wsag.Agreement result = 
				(eu.atos.sla.parser.data.wsag.Agreement) jaxbUnmarshaller.unmarshal(
						new FileReader(f));
		
		return result;
	}
	
	private File getResourceFile(String path) {
		URL url = this.getClass().getResource(path);
		if (url == null) {
			throw new IllegalArgumentException("Resource file " + path + " not found");
		}
		return new File(url.getFile());
	}
	
	private void checkParseAgreementContext(eu.atos.sla.parser.data.wsag.Agreement agreementXML, 
			ServiceProvider rol) throws JAXBException, FileNotFoundException, ModelConversionException {
		
		
		String expectedProvider;
		String expectedConsumer;
		
		if (rol == null) {
			agreementXML.getContext().setServiceProvider("invalid value here");
			expectedProvider = null;
			expectedConsumer = null;
		}
		else {
			agreementXML.getContext().setServiceProvider(rol.toString());
			if (rol == ServiceProvider.AGREEMENT_INITIATOR) {
				expectedProvider = "initiator";
				expectedConsumer = "responder";
			} else if (rol == ServiceProvider.AGREEMENT_RESPONDER) {
				expectedConsumer = "initiator";
				expectedProvider = "responder";
			}
			else {
				throw new AssertionError();
			}
		}

		String actualProvider;
		String actualConsumer;
		try {
			IAgreement a = modelConverter.getAgreementFromAgreementXML((eu.atos.sla.parser.data.wsag.IAgreement)agreementXML, "");
			actualProvider = a.getProvider().getUuid();
			actualConsumer = a.getConsumer();
		} catch (ModelConversionException e) {
			actualProvider = null;
			actualConsumer = null;
		}
		
		/*
		 * Match provider
		 */
		if (rol == null) {
			assertNull(actualProvider);
			assertNull(actualConsumer);
		}
		else {
			assertEquals(expectedProvider, actualProvider);
			assertEquals(expectedConsumer, actualConsumer);
		}
	}
	
	@Test
	public void testParseAgreementContext() throws JAXBException, FileNotFoundException, ModelConversionException {
		File file = getResourceFile("/samples/test_parse_context.xml");
		eu.atos.sla.parser.data.wsag.Agreement agreementXML = readXml(file);
		
		checkParseAgreementContext(agreementXML, null);
		checkParseAgreementContext(agreementXML, ServiceProvider.AGREEMENT_RESPONDER);
		checkParseAgreementContext(agreementXML, ServiceProvider.AGREEMENT_INITIATOR);
	}
	
	@Test
	public void testCustomBusinessValue() throws JAXBException, FileNotFoundException, ModelConversionException {
		
		File file = getResourceFile("/samples/test_parse_business.xml");
		eu.atos.sla.parser.data.wsag.Agreement agreementXML = readXml(file);
		IAgreement a = modelConverter.getAgreementFromAgreementXML((eu.atos.sla.parser.data.wsag.IAgreement)agreementXML, "");
		
		IPenaltyDefinition[] expected = new IPenaltyDefinition[] {
			new PenaltyDefinition(1, new Date(0), "discount", "%", "35", "P1D"),
			new PenaltyDefinition(5, new Date(24*60*60*1000), "service", "", "sms", "P1M")
		};
		for (IGuaranteeTerm gt : a.getGuaranteeTerms()) {
			int i = 0;
			for (IPenaltyDefinition actual : gt.getBusinessValueList().getPenalties()) {
				
				assertEquals(expected[i], actual);
				i++;
			}
		}
	}
	
	@Test
	public void testPolicies() throws ModelConversionException, FileNotFoundException, JAXBException {
		
		File file = getResourceFile("/samples/test_parse_windows.xml");
		eu.atos.sla.parser.data.wsag.Agreement agreementXML = readXml(file);
		IAgreement a = modelConverter.getAgreementFromAgreementXML((eu.atos.sla.parser.data.wsag.IAgreement)agreementXML, "");

		checkPolicyExists(new Policy(2, new Date(120*1000)), a.getGuaranteeTerms().get(0).getPolicies());
		checkPolicyExists(new Policy(3, new Date(3600*1000)), a.getGuaranteeTerms().get(0).getPolicies());
	}
	
	private void checkPolicyExists(IPolicy expected, List<IPolicy> actuals) {
		boolean found = false;
		for (IPolicy actual : actuals) {
			System.out.println(actual.getCount() + " " + actual.getTimeInterval());
			if (equalsPolicy(expected, actual)) {
				found = true;
				break;
			}
		}
		if (!found) {
			fail("Policy " + expected + " not found");
		}
	}
	
	/*
	 * Being a jpa object, IPolicy do not implement equals.
	 * Nice read: http://www.onjava.com/lpt/a/6718
	 */
	private boolean equalsPolicy(IPolicy p1, IPolicy p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException();
		}
		
		return (p1.getCount() == p2.getCount() && p1.getTimeInterval().equals(p2.getTimeInterval()));
	}
}
