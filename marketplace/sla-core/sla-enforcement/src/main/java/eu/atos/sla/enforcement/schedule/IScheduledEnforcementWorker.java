package eu.atos.sla.enforcement.schedule;

/*
 * Interface extracted to avoid a spring initialization error.
 */

/**
 * @see ScheduledEnforcementWorker
 * @author rsosa
 *
 */
public interface IScheduledEnforcementWorker {

	void spawnMonitors();

}