package eu.atos.sla.datamodel;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

import eu.atos.sla.datamodel.bean.Template;

public class TemplateTest {

	@Ignore
	@Test
	public void testPojo() {

		
		String templateUuid = UUID.randomUUID().toString();
		
		Template template = new Template();
		template.setText("Template name 1");
		template.setUuid(templateUuid);

        
        
        
	}
}
