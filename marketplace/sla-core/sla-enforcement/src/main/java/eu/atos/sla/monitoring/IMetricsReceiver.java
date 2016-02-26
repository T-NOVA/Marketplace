package eu.atos.sla.monitoring;

import eu.atos.sla.datamodel.IAgreement;

/**
 * An IMetricsReceiver<T> must enforce metrics in type T for an agreement.
 */
public interface IMetricsReceiver<T> {

	/**
	 * Enforces an agreement with some data.
	 */
	void run(IAgreement agreement, T data);
	
	/**
	 * Implements a IMetricsReceiver that receives data in type T, translates to 
	 * type expected by enforcement, and enforces the agreement.
	 */
//	public class MetricsReceiver<T> implements IMetricsReceiver<T> {
//
//		IAgreementEnforcement enforcement;
//		IMetricTranslator<T> translator;
//		
//		/**
//		 * Constructs the MetricsReceiver.
//		 * @param translator translates type T to Map<IGuaranteeTerm, List<IMonitoringMetric>>
//		 * @param enforcement IAgreementEnforcement properly initialized.
//		 */
//		public MetricsReceiver(IMetricTranslator<T> translator, IAgreementEnforcement enforcement) {
//			
//			this.translator = translator;
//			this.enforcement = enforcement;
//		}
//		
//		@Override
//		public void run(IAgreement agreement, T data) {
//
//			Map<IGuaranteeTerm, List<IMonitoringMetric>> metricsMap = translator.translate(agreement, data);
//			enforcement.enforce(agreement, metricsMap);
//		}
//	}
}
