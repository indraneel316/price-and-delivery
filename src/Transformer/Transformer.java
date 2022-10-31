package Transformer;

import Pojo.OPackageDetails;
import Pojo.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transformer {

    public OPackageDetails organizePackagesWithEstimatedCostAndDelivery(String packageId, double finalCost,
                                                                        double discountApplied,
                                                                        double vehicleReturnTime, double deliveryTime) {
        OPackageDetails OPackageDetails = new OPackageDetails();
        OPackageDetails.setPackageId(packageId);
        OPackageDetails.setTotalCost(BigDecimal.valueOf(finalCost).setScale(2, RoundingMode.HALF_UP));
        OPackageDetails.setDiscountApplied(BigDecimal.valueOf(discountApplied).setScale(2, RoundingMode.HALF_UP));
        OPackageDetails.setDeliveryTime(BigDecimal.valueOf(vehicleReturnTime + deliveryTime).setScale(2,RoundingMode.HALF_DOWN));
        return OPackageDetails;
    }

    public void alterVehicleData(Vehicle vehicle, double maxDeliveryTimeForVehicle) {
        vehicle.setVehicleAvailable(false);
        vehicle.setVehicleReturnTime(vehicle.getVehicleReturnTime() + (2 * maxDeliveryTimeForVehicle));
    }
}
