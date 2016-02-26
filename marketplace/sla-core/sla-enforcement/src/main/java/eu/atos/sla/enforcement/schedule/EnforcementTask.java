package eu.atos.sla.enforcement.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IEnforcementJob;
import eu.atos.sla.enforcement.AgreementEnforcement;

@Scope("prototype")
public class EnforcementTask implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(EnforcementTask.class);

	private IEnforcementJob job;
	@Autowired
	AgreementEnforcement agreementEnforcement;

	public EnforcementTask(){
		
	}

	public EnforcementTask(IEnforcementJob job) {
		this.job = job;
	}

	@Override
	public void run() {
		try{
            IAgreement agreement = job.getAgreement();

            agreementEnforcement.enforce(agreement, job.getLastExecuted(), false);
		}catch(Exception e){
			logger.error("Error with thread " + Thread.currentThread().getName(), e);
		}
	}
	

}	
