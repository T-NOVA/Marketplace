package eu.atos.sla.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.atos.sla.util.ModelConversion.ServiceLevelParser;
import eu.atos.sla.util.ModelConversion.ServiceLevelParser.Result;
import eu.atos.sla.util.ModelConversion.ServiceLevelParser.Window;

public class ServiceLevelParserTest {

	@Test
	public void testParseSimpleServiceLevel() throws ModelConversionException {
		String constraint;
		String slo;

		constraint = "Performance GT 0.1";
		
		/*
		 * accepted constraint
		 */
		slo = String.format("{\"constraint\" : \"%s\"}", constraint);
		checkParseSlo(slo, buildResult(constraint));
		
		/*
		 * constraint is a json object, instead of string
		 */
		constraint = "{\"hasMaxValue\":1.0}";
		slo = String.format("{\"constraint\" : %s}", constraint);
		checkParseSlo(slo, buildResult(constraint));
		
	}
	
	@Test
	public void testParseWindowedServiceLevel() throws ModelConversionException, JsonProcessingException {
		String constraint = "Performance GT 0.1";
		String slo;
		Window[] windows;
		
		windows = new Window[] {
				new Window(1, 0)
		};
		slo = buildSlo(constraint, windows);
		checkParseSlo(slo, buildResult(constraint, windows));

		windows = new Window[] {
				new Window(2, 120)
		};
		slo = buildSlo(constraint, windows);
		checkParseSlo(slo, buildResult(constraint, windows));

		windows = new Window[] {
			new Window(2, 120),
			new Window(3, 3600)
		};
		slo = buildSlo(constraint, windows);
		checkParseSlo(slo, buildResult(constraint, windows));
	}
	
	@Test
	public void testParseServiceLevelShouldPass() throws ModelConversionException {

		String constraint;
		String slo;
		
		constraint = "Performance GT 0.1";
		
		slo = String.format("{\"constraint\" : \"%s\"}", constraint);
		
		checkParseSlo(slo, buildResult(constraint));
	}

	@Test
	public void testParseServiceLevelShouldFail() {
		checkParseSloFails("{\"thereisnoconstraint\" : \"dont'care\"}");
	}

	private void checkParseSloFails(String slo) {
		try {
			parseServiceLevel(slo);
			fail("Parse of '"+ slo + "' should fail");
		} catch (ModelConversionException e) {
			/* 
			 * Does nothing
			 */
		}
	}

	private void checkParseSlo(String slo, Result expected) throws ModelConversionException {
		Result actual = parseServiceLevel(slo);
		assertEquals(expected.constraint, actual.constraint);
		assertThat(actual.windows, is(expected.windows));
	}
	
	private Result parseServiceLevel(String slo) throws ModelConversionException {
		
		return ServiceLevelParser.parse(slo);
	}
	
	private Result buildResult(String constraint, Window... windows) {
		Result result = new Result();
		result.constraint = constraint;
		if (windows.length > 0) {
			result.windows = Arrays.asList(windows);
		}
		return result;
	}

	private String buildSlo(String constraint, Window... windows) throws JsonProcessingException {
		Result r = buildResult(constraint, windows);
		
		ObjectMapper mapper = new ObjectMapper();
		String slo = mapper.writeValueAsString(r);
		return slo;
	}
}
