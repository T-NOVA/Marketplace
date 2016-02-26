package eu.atos.sla.service.types;

import javax.ws.rs.WebApplicationException;

public class BooleanParam {

	private final Boolean value;
	
	public static BooleanParam valueOf(String dateStr) {
		
		return new BooleanParam(dateStr);
	}

	public static Boolean getValue(BooleanParam instance) {

		return instance == null? null : instance.getValue();
	}
	
	public BooleanParam(String str) throws WebApplicationException {
		if ("".equals(str)) {
			this.value = null;
			return;
		}

		if ("0".equals(str) || "false".equalsIgnoreCase(str)) {
			
			this.value = Boolean.FALSE;
		}
		else {
			this.value = Boolean.TRUE;
		}
	}

	public Boolean getValue() {
		return value;
	}
}
