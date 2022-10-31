package Service.impl;

import BusinessLogic.BusinessLogic;
import Pojo.InputVo;
import Pojo.PackagesAndVehicleDetails;
import Pojo.IPackagesDetails;
import Pojo.Vehicle;
import Validator.Validator;
import IO.IOClass;
import Service.PriceAndDeliveryService;
import Transformer.Transformer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;

public class PriceAndDeliveryServiceImpl implements PriceAndDeliveryService {

    BusinessLogic businessLogic = new BusinessLogic();
    Transformer transformer = new Transformer();

    public void estimateDeliveryCostAndDeliveryTime(InputVo inputVo) {
        if(new Validator().validateInput(inputVo)) {
            PackagesAndVehicleDetails packagesAndVehicleDetails = new PackagesAndVehicleDetails();
            List<Vehicle> vehicles = new ArrayList<>();
            int noOfVehicles = inputVo.getNoOfVehicles();
            while (noOfVehicles > 0) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleAvailable(true);
                vehicles.add(vehicle);
                noOfVehicles--;
            }
            packagesAndVehicleDetails.setVehicleDetails(vehicles);
            sendPackagesForDelivery(inputVo, packagesAndVehicleDetails);
        }
    }

    public void processPackages(List<IPackagesDetails> chosenPackages, InputVo inputVo, PackagesAndVehicleDetails packagesAndVehicleDetails) {
        List<Vehicle> vehicles = packagesAndVehicleDetails.getVehicleDetails();
        boolean isVehicleAvailable = vehicles.stream().anyMatch(Vehicle::getVehicleAvailable);
        Vehicle availableVehicle;
        if (isVehicleAvailable) {
            availableVehicle = vehicles.stream().filter(Vehicle::getVehicleAvailable).findFirst().get();
        } else {
            availableVehicle = vehicles.stream()
                    .min(Comparator.comparing(Vehicle::getVehicleReturnTime)).get();
        }
        double maxDeliveryTimeForVehicle = Integer.MIN_VALUE;

        for (IPackagesDetails packageDetail : chosenPackages) {
            double deliveryTime = new BusinessLogic().calculateDeliveryTime(packageDetail.getDistanceInKms(),
                    inputVo.getMaxSpeedOfVehicle());
            maxDeliveryTimeForVehicle = max(maxDeliveryTimeForVehicle, deliveryTime);
            double cost = businessLogic.calculateCost(inputVo.getBaseDeliveryCost(),
                    packageDetail.getPackageWeightInKg(), packageDetail.getDistanceInKms());
            double finalCost = businessLogic.applyDiscount(packageDetail, cost);
            packagesAndVehicleDetails.getPackageDetails().add(transformer.
                    organizePackagesWithEstimatedCostAndDelivery(packageDetail.getPackageId(),
                            finalCost, cost - finalCost,
                    availableVehicle.getVehicleReturnTime(), deliveryTime));
        }
        transformer.alterVehicleData(availableVehicle, maxDeliveryTimeForVehicle);
    }

    public void sendPackagesForDelivery(InputVo inputVo, PackagesAndVehicleDetails packagesAndVehicleDetails) {
        List<IPackagesDetails> packages = inputVo.getPackages();
        while (packages.size() > 0) {
            List<IPackagesDetails> chosenPackages = prioritizePackages(inputVo.getMaxLoadOfVehicle(), packages);
            packages.removeAll(chosenPackages);
            processPackages(chosenPackages, inputVo, packagesAndVehicleDetails);
        }
        IOClass ioClass = new IOClass();
        ioClass.provideOutput(packagesAndVehicleDetails);

    }

    public List<IPackagesDetails> prioritizePackages(int maxLoad, List<IPackagesDetails> iPackagesDetails) {
        int size = iPackagesDetails.size(), i, j;
        int[] packageWeights = new int[iPackagesDetails.size()];
        int index = 0;
        for (IPackagesDetails packageDetail : iPackagesDetails) {
            packageWeights[index] = packageDetail.getPackageWeightInKg();
            index++;
        }
        int[][] weightsLookup = new int[size + 1][maxLoad + 1];
        for (i = 0; i <= size; i++) {
            weightsLookup[i][0] = 0;
        }
        for (j = 1; j <= maxLoad; j++) {
            weightsLookup[0][j] = 0;
        }

        for (i = 1; i <= size; i++) {
            for (j = 1; j <= maxLoad; j++) {
                weightsLookup[i][j] = weightsLookup[i - 1][j];
                if (packageWeights[i - 1] <= j) {
                    weightsLookup[i][j] = max(weightsLookup[i - 1][j - packageWeights[i - 1]] + packageWeights[i - 1],
                            weightsLookup[i][j]);
                }
            }
        }
        return selectPackagesForDelivery(weightsLookup, maxLoad, size, iPackagesDetails, packageWeights);
    }

    private List<IPackagesDetails> selectPackagesForDelivery(int[][] weightsLookup, int maxLoad, int size,
                                                             List<IPackagesDetails> iPackagesDetails,
                                                             int[] packageWeights) {
        List<IPackagesDetails> chosenPackages = new ArrayList<>();
        int weight = weightsLookup[size][maxLoad];
        int capacity = maxLoad;
        for (int i = size; i > 0 && weight > 0; i--) {
            if (weight != weightsLookup[i - 1][capacity]) {
                chosenPackages.add(iPackagesDetails.get(i - 1));
                weight = weight - packageWeights[i - 1];
                capacity = capacity - packageWeights[i - 1];
            }
        }
        return chosenPackages;
    }
}
