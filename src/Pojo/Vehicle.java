package Pojo;

public class Vehicle {
    private Boolean isVehicleAvailable;

    public double getVehicleReturnTime() {
        return vehicleReturnTime;
    }

    @Override
    public String toString() {
        return "Pojo.Vehicle{" +
                "isVehicleAvailable=" + isVehicleAvailable +
                ", vehicleReturnTime=" + vehicleReturnTime +
                '}';
    }

    public void setVehicleReturnTime(double vehicleReturnTime) {
        this.vehicleReturnTime = vehicleReturnTime;
    }

    double vehicleReturnTime;

    public Boolean getVehicleAvailable() {
        return isVehicleAvailable;
    }

    public void setVehicleAvailable(Boolean vehicleAvailable) {
        isVehicleAvailable = vehicleAvailable;
    }

}
