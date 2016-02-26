package eu.atos.sla.service.rest.helpers;

import static eu.atos.sla.datamodel.IGuaranteeTerm.GuaranteeTermStatusEnum.FULFILLED;
import static eu.atos.sla.datamodel.IGuaranteeTerm.GuaranteeTermStatusEnum.NON_DETERMINED;
import static eu.atos.sla.datamodel.IGuaranteeTerm.GuaranteeTermStatusEnum.VIOLATED;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IGuaranteeTerm.GuaranteeTermStatusEnum;
import eu.atos.sla.datamodel.bean.GuaranteeTerm;


public class AgreementHelperETest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAgreementStatus() {
		testStatus(FULFILLED,       FULFILLED);
		testStatus(NON_DETERMINED);
		testStatus(NON_DETERMINED,  FULFILLED, NON_DETERMINED);
		testStatus(NON_DETERMINED,  NON_DETERMINED, FULFILLED);
		testStatus(VIOLATED,        FULFILLED, VIOLATED);
		testStatus(VIOLATED,        NON_DETERMINED, VIOLATED);
		testStatus(VIOLATED,        VIOLATED, NON_DETERMINED);
		testStatus(VIOLATED,        VIOLATED, NON_DETERMINED, FULFILLED);
		
	}
	
	private void testStatus(GuaranteeTermStatusEnum expected, GuaranteeTermStatusEnum... termsStatus) {
		
		List<IGuaranteeTerm> terms = buildTerms(termsStatus);
				
		GuaranteeTermStatusEnum current = AgreementHelperE.AgreementStatusCalculator.getStatus(terms);
		
		assertEquals(expected, current);
	}
	
	private List<IGuaranteeTerm> buildTerms(GuaranteeTermStatusEnum... termsStatus) {
		
		List<IGuaranteeTerm> result = new ArrayList<IGuaranteeTerm>();
		for (GuaranteeTermStatusEnum status: termsStatus) {
			
			GuaranteeTerm term = new GuaranteeTerm();
			term.setStatus(status);
			result.add(term);
			
		}
		return result;
	}

}
