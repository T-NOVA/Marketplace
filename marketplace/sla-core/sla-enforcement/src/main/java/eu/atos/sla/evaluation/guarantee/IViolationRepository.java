package eu.atos.sla.evaluation.guarantee;

import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IViolation;

public interface IViolationRepository {
	
	List<IViolation> getViolationsByTimeRange(IAgreement agreement, String guaranteeTermName, Date begin, Date end);
	Date getLastViolationDate(IAgreement agreement, String guaranteeTermName);
}