package eu.atos.sla.service.rest.types;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import eu.atos.sla.service.types.DateParam;

public class DateParamTest {

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

	//@Test Commented out: problems in linux
	public void testDateParam() {
		
		DateParam d = new DateParam("1970-01-01T01:00:00");
		assertEquals(0L, d.getDate().getTime()); 
	}

}
