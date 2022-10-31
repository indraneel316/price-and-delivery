package Pojo;

import java.util.List;

public class InputVo {
    int baseDeliveryCost;
    int noOfPackages;

    @Override
    public String toString() {
        return "Pojo.InputVo{" +
                "baseDeliveryCost=" + baseDeliveryCost +
                ", noOfPackages=" + noOfPackages +
                ", noOfVehiclesAvailable=" + noOfVehiclesAvailable +
                ", maxSpeedOfVehicle=" + maxSpeedOfVehicle +
                ", maxLoadOfVehicle=" + maxLoadOfVehicle +
                ", packages=" + packages +
                '}';
    }

    public int getNoOfVehicles() {
        return noOfVehiclesAvailable;
    }

    public void setNoOfVehiclesAvailable(int noOfVehiclesAvailable) {
        this.noOfVehiclesAvailable = noOfVehiclesAvailable;
    }

    public double getMaxSpeedOfVehicle() {
        return maxSpeedOfVehicle;
    }

    public void setMaxSpeedOfVehicle(int maxSpeedOfVehicle) {
        this.maxSpeedOfVehicle = maxSpeedOfVehicle;
    }

    public int getMaxLoadOfVehicle() {
        return maxLoadOfVehicle;
    }

    public void setMaxLoadOfVehicle(int maxLoadOfVehicle) {
        this.maxLoadOfVehicle = maxLoadOfVehicle;
    }

    int noOfVehiclesAvailable;

    double maxSpeedOfVehicle;

    int maxLoadOfVehicle;



    public int getBaseDeliveryCost() {
        return baseDeliveryCost;
    }

    public void setBaseDeliveryCost(int baseDeliveryCost) {
        this.baseDeliveryCost = baseDeliveryCost;
    }

    public int getNoOfPackages() {
        return noOfPackages;
    }

    public void setNoOfPackages(int noOfPackages) {
        this.noOfPackages = noOfPackages;
    }

    public List<IPackagesDetails> getPackages() {
        return packages;
    }

    public void setPackages(List<IPackagesDetails> packages) {
        this.packages = packages;
    }
    List<IPackagesDetails> packages;
}
