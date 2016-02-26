package eu.atos.sla.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import eu.atos.sla.dao.IViolationDAO;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.bean.Violation;

@Repository("ViolationRepository")
public class ViolationDAOJpa implements IViolationDAO {
	private static Logger logger = LoggerFactory.getLogger(ViolationDAOJpa.class);
	private EntityManager entityManager;

	@PersistenceContext(unitName = "slarepositoryDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Violation getById(Long id) {
		return entityManager.find(Violation.class, id);
	}

	public Date getLastViolationDate(String agreement, String kpiName) {
		try {
			Query query = entityManager
					.createNamedQuery(Violation.QUERY_FIND_LAST_VIOLATION_DATE);
			query.setParameter("contractUuid", agreement);
			query.setParameter("kpiName", kpiName);
			
			Violation violation = null;

			Date violation_date = (Date) query.getSingleResult();
			System.out.println("Violation date: " + violation_date);

			return violation_date;

		} catch (NoResultException e) {
			logger.debug("No Result found: " + e);
			return null;
		}
	}
	
	public Violation getViolationByUUID(String uuid) {
		try {
			Query query = entityManager
					.createNamedQuery(Violation.QUERY_FIND_BY_UUID);
			query.setParameter("uuid", uuid);
			Violation violation = null;

			violation = (Violation) query.getSingleResult();
			System.out.println("Violation" + violation.getUuid());

			return violation;

		} catch (NoResultException e) {
			logger.debug("No Result found: " + e);
			return null;
		}

	}

	public List<IViolation> getByAgreement(String agreementId, String termName) {
		List<IViolation> violations = null;
		if (agreementId != null) {
			TypedQuery<IViolation> query = entityManager.createNamedQuery(
					Violation.QUERY_FIND_VIOLATIONS_BY_AGREEMENT,
					IViolation.class);
			query.setParameter("agreementId", agreementId);
			query.setParameter("termName", termName);

			violations = null;

			violations = (List<IViolation>) query.getResultList();

		}

		if (violations != null) {
			logger.debug("Number of violations by AgreementId " + agreementId + " : " + violations.size());
		} else {
			logger.debug("No Result found.");
		}
		return violations;
	}

	public List<IViolation> getByAgreementIdInARangeOfDates(String agreementId,
			String termName, Date begin, Date end) {
		List<IViolation> violations = null;
		if (agreementId != null) {
				TypedQuery<IViolation> query = entityManager
						.createNamedQuery(
								Violation.QUERY_FIND_VIOLATIONS_BY_AGREEMENT_IN_A_RANGE_OF_DATES,
								IViolation.class);
				query.setParameter("agreementId", agreementId);
				query.setParameter("termName", termName);
				query.setParameter("begin", begin);
				query.setParameter("end", end);

				violations = null;

				violations = (List<IViolation>) query.getResultList();
		}

		if (violations != null) {
			logger.debug("Number of violations between " + begin + " and "
					+ end + ":" + violations.size());
		} else {
			logger.debug("No Result found.");
		}
		return violations;
	}

	@Override
	@Rollback(false)
	public List<IViolation> getByProvider(String providerUuid) {

		List<IViolation> violations = null;

		if (providerUuid != null) {
			TypedQuery<IViolation> query = entityManager.createNamedQuery(
					Violation.QUERY_FIND_VIOLATIONS_BY_PROVIDER,
					IViolation.class);
			query.setParameter("providerUuid", providerUuid);

			violations = null;

			violations = (List<IViolation>) query.getResultList();

		}
		if (violations != null) {
			logger.debug("Number of violations of the providerUuuid "
					+ providerUuid + " :" + violations.size());
		} else {
			logger.debug("No Result found.");
		}

		return violations;
	}

	@Override
	@Rollback(false)
	public List<IViolation> getByProviderInaRangeOfDates(String providerUuid,
			Date begin, Date end) {

		List<IViolation> violations = null;
		if (providerUuid != null) {
				TypedQuery<IViolation> query = entityManager
						.createNamedQuery(
								Violation.QUERY_FIND_VIOLATIONS_BY_PROVIDER_IN_A_RANGE_OF_DATES,
								IViolation.class);
				query.setParameter("providerUuid", providerUuid);
				query.setParameter("begin", begin);
				query.setParameter("end", end);

				violations = null;

				violations = (List<IViolation>) query.getResultList();

		}

		if (violations != null) {
			logger.debug("Number of violations between " + begin + " and "
					+ end + ":" + violations.size());
		} else {
			logger.debug("No Result found.");
		}

		return violations;
	}

	public List<IViolation> getAll() {
		TypedQuery<IViolation> query = entityManager.createNamedQuery(
				Violation.QUERY_FIND_ALL, IViolation.class);
		List<IViolation> violations = null;
		violations = query.getResultList();

		if (violations != null) {
			logger.debug("Number of violations:" + violations.size());
		} else {
			logger.debug("No Result found.");
		}

		return violations;
	}

	@Override
	public IViolation save(IViolation violation) {

		entityManager.persist(violation);
		entityManager.flush();

		return violation;
	}

	@Override
	public List<IViolation> search(SearchParameters params) {

		TypedQuery<IViolation> query = entityManager.createNamedQuery(
						Violation.QUERY_SEARCH,
						IViolation.class);
		
		query.setParameter("providerUuid", params.getProviderUuid());
		query.setParameter("agreementId", params.getAgreementId());
		query.setParameter("termName", params.getGuaranteeTermName());
		query.setParameter("begin", params.getBegin());
		query.setParameter("end", params.getEnd());

		List<IViolation> violations = query.getResultList();
		return violations;
	}

	@Override
	public Date getLastViolationDate(IAgreement agreement, String kpiName) {
		Date violationDate = null;
                List resultDates = null;

		if (agreement != null) {
			TypedQuery<Date> query = entityManager.createNamedQuery(
					Violation.QUERY_FIND_LAST_VIOLATION_DATE,
					Date.class);
			query.setParameter("agreementId", agreement.getAgreementId());
			query.setParameter("kpiName", kpiName);


                        resultDates = query.getResultList();
		}
		if ((resultDates == null) || (resultDates.get(0) == null)) {
		    logger.debug("No Result found.");
		} else {
                    violationDate = (Date) resultDates.get(0); 
		    logger.debug("Date of the last violation of " + agreement + "and metric " + kpiName + ": " + violationDate.toString());
		}

		return violationDate;
	}

}
