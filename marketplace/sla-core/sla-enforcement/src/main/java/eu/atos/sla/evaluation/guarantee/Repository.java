package eu.atos.sla.evaluation.guarantee;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import eu.atos.sla.dao.IBreachDAO;
import eu.atos.sla.dao.IViolationDAO;
import eu.atos.sla.dao.IViolationDAO.SearchParameters;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBreach;
import eu.atos.sla.datamodel.IViolation;

/**
 * Implements the access to a repository that stores breaches and compensations.
 * @author rsosa
 *
 */
public class Repository implements IBreachRepository, IViolationRepository {

	private static Logger logger = LoggerFactory.getLogger(Repository.class);
	
	@Autowired
	IBreachDAO breachDao;

	@Autowired
	IViolationDAO violationDao;
	
	public Repository() {
	}

	@Override
	public Date getLastViolationDate(IAgreement agreement, String kpiName) {
		Date result = violationDao.getLastViolationDate(agreement, kpiName);
		return result;
	}
	@Override
	public List<IBreach> getBreachesByTimeRange(IAgreement agreement, String kpiName, Date begin, Date end) {

		List<IBreach> result = breachDao.getByTimeRange(agreement, kpiName, begin, end);
		return result;
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveBreaches(List<IBreach> breaches) {

		logger.debug("Saving list of {} breaches", breaches.size());
		for (IBreach breach : breaches) {
		
			breachDao.save(breach);
		}
	}

	@Override
	public List<IViolation> getViolationsByTimeRange(IAgreement agreement,
			String guaranteeTermName, Date begin, Date end) {
		SearchParameters params = newSearchParameters(agreement, guaranteeTermName, begin, end);
		List<IViolation> result = violationDao.search(params);
		
		return result;
	}

	private SearchParameters newSearchParameters(IAgreement agreement,
			String guaranteeTermName, Date begin, Date end) {
		SearchParameters params = new SearchParameters();
		params.setAgreementId(agreement.getAgreementId());
		params.setGuaranteeTermName(guaranteeTermName);
		params.setBegin(begin);
		params.setEnd(end);
		return params;
	}
}
