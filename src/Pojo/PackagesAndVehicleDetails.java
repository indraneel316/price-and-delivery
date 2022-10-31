package Pojo;

import java.util.ArrayList;
import java.util.List;

public class PackagesAndVehicleDetails {

   private List<OPackageDetails> packageDetails = new ArrayList<>();

   private  List<Vehicle> vehicleDetails = new ArrayList<>();

    @Override
    public String toString() {
        return "PackagesAndVehicleDetails{" +
                "packageDetails=" + packageDetails +
                ", vehicleDetails=" + vehicleDetails +
                '}';
    }

    public List<Vehicle> getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(List<Vehicle> vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public List<OPackageDetails> getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(List<OPackageDetails> packageDetails) {
        this.packageDetails = packageDetails;
    }
}
