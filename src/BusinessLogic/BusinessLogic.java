package BusinessLogic;

import Pojo.IPackagesDetails;
import Transformer.Constants.PDConstants;

public class BusinessLogic {
    
    PDConstants PDConstants = new PDConstants();

    public double calculateCost(int baseDeliveryCost, int packageWeightInKg, double distanceInKms) {
       return baseDeliveryCost +
                (packageWeightInKg * PDConstants.TEN) +
                (distanceInKms * PDConstants.FIVE);
    }

     public double calculateDeliveryTime(double distanceInKms, double maxSpeedOfVehicle) {
        return Double.parseDouble(String.format("%.2f", distanceInKms/ maxSpeedOfVehicle));
    }

    public double applyDiscount(IPackagesDetails IPackagesDetails, double cost) {
        switch (IPackagesDetails.getOfferCode()) {
            case "OFR001":
                if (IPackagesDetails.getDistanceInKms() <= PDConstants.TWO_HUNDRED &&
                        (IPackagesDetails.getPackageWeightInKg() >= PDConstants.SEVENTY
                                && IPackagesDetails.getPackageWeightInKg() <= PDConstants.TWO_HUNDRED)) {
                    cost = cost - (PDConstants.TEN * cost / PDConstants.HUNDRED);
                }
                break;
            case "OFR002":
                if (IPackagesDetails.getDistanceInKms() >= PDConstants.FIFTY && IPackagesDetails.getDistanceInKms() <= PDConstants.ONE_HUNDRED_FIFTY &&
                        (IPackagesDetails.getPackageWeightInKg() >= PDConstants.SEVENTY
                                && IPackagesDetails.getPackageWeightInKg() <= PDConstants.TWO_HUNDRED)) {
                    cost = cost - (PDConstants.SEVEN * cost / PDConstants.HUNDRED);
                }
                break;
            case "OFR003":
                if (IPackagesDetails.getDistanceInKms() >= PDConstants.FIFTY && IPackagesDetails.getDistanceInKms() <= PDConstants.TWO_HUNDRED_FIFTY &&
                        (IPackagesDetails.getPackageWeightInKg() >= PDConstants.TEN
                                && IPackagesDetails.getPackageWeightInKg() <= PDConstants.ONE_HUNDRED_FIFTY)) {
                    cost = cost - (PDConstants.FIVE * cost / PDConstants.HUNDRED);
                }
                break;
        }
        return cost;
    }
}
