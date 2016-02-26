package eu.atos.sla.parser.json;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import eu.atos.sla.parser.IParser;
import eu.atos.sla.parser.ParserException;
import eu.atos.sla.parser.ValidationHandler;
import eu.atos.sla.parser.data.wsag.Agreement;
import eu.atos.sla.parser.data.wsag.AllTerms;
import eu.atos.sla.parser.data.wsag.BusinessValueList;
import eu.atos.sla.parser.data.wsag.Context;
import eu.atos.sla.parser.data.wsag.GuaranteeTerm;
import eu.atos.sla.parser.data.wsag.IAgreement;
import eu.atos.sla.parser.data.wsag.IAllTerms;
import eu.atos.sla.parser.data.wsag.IBusinessValueList;
import eu.atos.sla.parser.data.wsag.IContext;
import eu.atos.sla.parser.data.wsag.IGuaranteeTerm;
import eu.atos.sla.parser.data.wsag.IKPITarget;
import eu.atos.sla.parser.data.wsag.IServiceDescriptionTerm;
import eu.atos.sla.parser.data.wsag.IServiceLevelObjective;
import eu.atos.sla.parser.data.wsag.IServiceProperties;
import eu.atos.sla.parser.data.wsag.IServiceScope;
import eu.atos.sla.parser.data.wsag.ITerms;
import eu.atos.sla.parser.data.wsag.IVariable;
import eu.atos.sla.parser.data.wsag.IVariableSet;
import eu.atos.sla.parser.data.wsag.KPITarget;
import eu.atos.sla.parser.data.wsag.ServiceDescriptionTerm;
import eu.atos.sla.parser.data.wsag.ServiceLevelObjective;
import eu.atos.sla.parser.data.wsag.ServiceProperties;
import eu.atos.sla.parser.data.wsag.ServiceScope;
import eu.atos.sla.parser.data.wsag.Terms;
import eu.atos.sla.parser.data.wsag.Variable;
import eu.atos.sla.parser.data.wsag.VariableSet;
import eu.atos.sla.parser.data.wsag.custom.CustomBusinessValue;
import eu.atos.sla.parser.data.wsag.custom.ICustomBusinessValue;
import eu.atos.sla.parser.json.deserializer.AllTermsDeserializer;
import eu.atos.sla.parser.json.deserializer.BusinessValueListDeserializer;
import eu.atos.sla.parser.json.deserializer.ContextDeserializer;
import eu.atos.sla.parser.json.deserializer.CustomBusinessValueDeserializer;
import eu.atos.sla.parser.json.deserializer.GuaranteeTermDeserializer;
import eu.atos.sla.parser.json.deserializer.KPITargetDeserializer;
import eu.atos.sla.parser.json.deserializer.ServiceDescriptionTermDeserializer;
import eu.atos.sla.parser.json.deserializer.ServiceLevelObjectiveDeserializer;
import eu.atos.sla.parser.json.deserializer.ServicePropertiesDeserializer;
import eu.atos.sla.parser.json.deserializer.ServiceScopeDeserializer;
import eu.atos.sla.parser.json.deserializer.TermsDeserializer;
import eu.atos.sla.parser.json.deserializer.VariableDeserializer;
import eu.atos.sla.parser.json.deserializer.VariableSetDeserializer;
public class AgreementParser implements IParser<IAgreement> {
	private static Logger logger = LoggerFactory.getLogger(AgreementParser.class);

	/*
	 * getWsagObject receives in serializedData the object information in json 
	 * must returns a eu.atos.sla.parser.data.wsag.Agreement 
	 */
	@Override
	public IAgreement getWsagObject(String serializedData) throws ParserException{
		IAgreement agreement = null;
		try{
			logger.info("Will parse {}", serializedData);
			ObjectMapper mapper = new ObjectMapper();

			SimpleModule module = new SimpleModule("PolymorphicTestObjectDeserializer", new Version(1, 0, 0, null, "eu.atos.sla.tnova", "spring-social-bootstrap"));	
			ContextDeserializer contextDeserializer = new ContextDeserializer();
			contextDeserializer.register(Context.class); 
			module.addDeserializer(IContext.class, contextDeserializer);

			TermsDeserializer termDeserializer = new TermsDeserializer();
			termDeserializer.register(Terms.class); 
			module.addDeserializer(ITerms.class, termDeserializer);

			AllTermsDeserializer alltermDeserializer = new AllTermsDeserializer();
			alltermDeserializer.register(AllTerms.class); 
			module.addDeserializer(IAllTerms.class, alltermDeserializer);

			ServiceDescriptionTermDeserializer serviceDescriptionTermDeserializer = new ServiceDescriptionTermDeserializer();
			serviceDescriptionTermDeserializer.register(ServiceDescriptionTerm.class); 
			module.addDeserializer(IServiceDescriptionTerm.class, serviceDescriptionTermDeserializer);

			ServicePropertiesDeserializer servicePropertiesDeserializer = new ServicePropertiesDeserializer();
			servicePropertiesDeserializer.register(ServiceProperties.class); 
			module.addDeserializer(IServiceProperties.class, servicePropertiesDeserializer);

			GuaranteeTermDeserializer guaranteeTermDeserializer = new GuaranteeTermDeserializer();
			guaranteeTermDeserializer.register(GuaranteeTerm.class); 
			module.addDeserializer(IGuaranteeTerm.class, guaranteeTermDeserializer);

			ServiceScopeDeserializer serviceScopeDeserializer = new ServiceScopeDeserializer();
			serviceScopeDeserializer.register(ServiceScope.class); 
			module.addDeserializer(IServiceScope.class, serviceScopeDeserializer);

			ServiceLevelObjectiveDeserializer serviceLevelObjectiveDeserializer = new ServiceLevelObjectiveDeserializer();
			serviceLevelObjectiveDeserializer.register(ServiceLevelObjective.class); 
			module.addDeserializer(IServiceLevelObjective.class, serviceLevelObjectiveDeserializer);

			KPITargetDeserializer kpiTragetDeserializer = new KPITargetDeserializer();
			kpiTragetDeserializer.register(KPITarget.class); 
			module.addDeserializer(IKPITarget.class, kpiTragetDeserializer);

			BusinessValueListDeserializer businessValueListDeserializer= new BusinessValueListDeserializer();
			businessValueListDeserializer.register(BusinessValueList.class); 
			module.addDeserializer(IBusinessValueList.class, businessValueListDeserializer);

			VariableDeserializer variableDeserializer = new VariableDeserializer();
			variableDeserializer.register(Variable.class); 
			module.addDeserializer(IVariable.class, variableDeserializer);

			VariableSetDeserializer variableSetDeserializer = new VariableSetDeserializer();
			variableSetDeserializer.register(VariableSet.class); 
			module.addDeserializer(IVariableSet.class, variableSetDeserializer);

			CustomBusinessValueDeserializer customBusinessValueDeserializer = new CustomBusinessValueDeserializer();
			customBusinessValueDeserializer.register(CustomBusinessValue.class); 
			module.addDeserializer(ICustomBusinessValue.class, customBusinessValueDeserializer);
			
			mapper.registerModule(module);

			
			agreement = (IAgreement)mapper.readValue(serializedData, Agreement.class);
			logger.info("Agreement parsed {}", agreement);
		} catch (JsonProcessingException e) {
			logger.error("Error in JsonProcessingException",e);
			throw new ParserException(e);
		} catch (Throwable e) {
			logger.error("Error in Throwable",e);
			throw new ParserException(e);
		}
		return agreement;

	}
	
	/*
	 * getWsagAsSerializedData receives in serializedData json  
	 * must return information following and xml in wsag standard.
	 */
	@Override
	public String getWsagAsSerializedData(String serializedData) throws ParserException {
		StringWriter stringWriter = new StringWriter();
		try {
			IAgreement agreement = getWsagObject(serializedData);
			JAXBContext jaxbContext = JAXBContext.newInstance(Agreement.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setEventHandler(new ValidationHandler());
			jaxbMarshaller.marshal(agreement, stringWriter);
		} catch (JAXBException e) {
			throw new ParserException(e);
		}
		return stringWriter.toString();
	}

	
	/*
	 * getSerializedData receives in wsagSerialized the information in wsag standard as it was generated with the
	 * getWsagAsSerializedData method and returns it in json 
	 */
	@Override
	public String getSerializedData(String wsagSerialized) throws ParserException{
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Agreement.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Agreement agreement = (Agreement)jaxbUnmarshaller.unmarshal(new StringReader(wsagSerialized));
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);
				String result = mapper.writeValueAsString(agreement);
				return result;
			} catch (JsonProcessingException e) {
				throw new ParserException(e);
			} catch (JAXBException e) {
				throw new ParserException(e);
			}
			
	}
	
}
