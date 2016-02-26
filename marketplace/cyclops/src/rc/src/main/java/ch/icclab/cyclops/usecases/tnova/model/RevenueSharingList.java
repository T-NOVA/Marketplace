package ch.icclab.cyclops.usecases.tnova.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Manu
 *         Created on 04.12.15.
 */
public class RevenueSharingList {
    private Charge charge;

    private class Charge {

        private List<String> columns;

        private List<List<Object>> points;

        public Integer getPriceIndex() {
            return columns.indexOf("price");
        }

        public Integer getResourceIndex() {
            return columns.indexOf("resource");
        }

        public Integer getProviderIndex() {
            return columns.indexOf("SProvider");
        }

        protected HashMap<String, Double> getAggregation() {
            HashMap<String, Double> price = new HashMap<String, Double>();
            Integer resourceIndex = this.getResourceIndex();
            Integer priceIndex = this.getPriceIndex();
            Double sum = 0.0;
            for (List<Object> point : points) {
                String resource = (String) point.get(resourceIndex);
                if (!price.containsKey(resource)) {
                    price.put(resource, (Double) point.get(priceIndex));
                } else {
                    sum = price.get(resource);
                    sum = sum + (Double) point.get(priceIndex);
                    price.put(resource, sum);
                }
            }
            return price;
        }

        protected ArrayList<String> getProviders() {
            ArrayList<String> providers = new ArrayList<String>();
            int providerIndex = getProviderIndex();
            for (List<Object> point : points) {
                String provider = (String) point.get(providerIndex);
                if (!providers.contains(provider)) {
                    providers.add(provider);
                }
            }
            return providers;
        }

        protected ArrayList<List<Object>> getPointsPerProvider(String provider) {
            ArrayList<List<Object>> result = new ArrayList<List<Object>>();
            for (List<Object> point : points) {
                if (point.get(this.getProviderIndex()).equals(provider))
                    result.add(point);
            }
            return result;
        }

        public List<String> getColumns() {
            return columns;
        }

        public List<List<Object>> getPoints() {
            return points;
        }

    }

    public List<List<Object>> getPoints() {
        return this.charge.getPoints();
    }

    public HashMap<String, Double> getAggregation() {
        return this.charge.getAggregation();
    }

    public ArrayList<String> getProviders() {
        return this.charge.getProviders();
    }

    public ArrayList<List<Object>> getPointsPerProvider(String provider) {
        return this.charge.getPointsPerProvider(provider);
    }

    public List<String> getColumns() {
        return this.charge.getColumns();
    }

}
