package eu.atos.sla.parser;
/**
 * 
 * @author Elena Garrido
 */
public interface IParser <T> {
	/*
	 * getWsagObject receives in serializedData the object information in an xml, json or any other format and 
	 * must return the T object (eu.atos.sla.parser.data.wsag.Agreement or an eu.atos.sla.parser.data.wsag.Template)
	 */
	public T getWsagObject(String serializedData) throws ParserException;

	/*
	 * getWsagAsSerializedData receives in serializedData the object information in an xml, json or any other format and 
	 * must return information following and xml in wsag standard.
	 */
	public String getWsagAsSerializedData(String serializedData) throws ParserException;
	
	/*
	 * getSerializedData receives in wsagSerialized the information in wsag standard as it was generated with the
	 * getWsagAsSerializedData method and returns it in a xml, json or any other format
	 */
	public String getSerializedData(String wsagSerialized) throws ParserException;
}
