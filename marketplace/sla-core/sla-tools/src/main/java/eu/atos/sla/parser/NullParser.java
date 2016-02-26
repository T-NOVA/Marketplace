package eu.atos.sla.parser;

/**
 * 
 * @author Elena Garrido
 */

@SuppressWarnings("rawtypes")
public class NullParser implements IParser {


	@Override
	public Object getWsagObject(String serializedData) throws ParserException{
		throw new ParserException("Method not implemented");
	}

	@Override
	public String getWsagAsSerializedData(String serializedData) throws ParserException {
		throw new ParserException("Method not implemented");
	}

	@Override
	public String getSerializedData(String wsagSerialized) throws ParserException{
		throw new ParserException("Method not implemented");
	}
	
	
}
