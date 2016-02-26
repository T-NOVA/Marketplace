package eu.atos.sla.monitoring;

import java.util.List;
import java.util.Map;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;

/**
 * Translates data in T format to the type expected by the enforcement process.
 */
public interface IMetricTranslator<T> {
	Map<IGuaranteeTerm, List<IMonitoringMetric>> translate(IAgreement agreement, T data);
}