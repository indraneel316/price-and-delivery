package IO;

import Pojo.IPackagesDetails;
import Pojo.InputVo;
import Pojo.OPackageDetails;
import Pojo.PackagesAndVehicleDetails;
import Service.PriceAndDeliveryService;
import Service.impl.PriceAndDeliveryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOClass {

    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        String[] baseDeliveryCostAndNoOfPackages = scanner.nextLine().split(" ");
        InputVo inputVo = new InputVo();
        inputVo.setBaseDeliveryCost(Integer.parseInt(baseDeliveryCostAndNoOfPackages[0]));
        inputVo.setNoOfPackages(Integer.parseInt(baseDeliveryCostAndNoOfPackages[1]));
        List<IPackagesDetails> packages = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(baseDeliveryCostAndNoOfPackages[1]); i++) {
            String[] inputs = scanner.nextLine().split(" ");
            IPackagesDetails IPackagesDetails = new IPackagesDetails();
            IPackagesDetails.setPackageId(inputs[0]);
            IPackagesDetails.setPackageWeightInKg(Integer.parseInt(inputs[1]));
            IPackagesDetails.setDistanceInKms(Integer.parseInt(inputs[2]));
            IPackagesDetails.setOfferCode(inputs[3]);
            packages.add(IPackagesDetails);
        }
        inputVo.setPackages(packages);
        String [] vehicleDetails = scanner.nextLine().split(" ");
        inputVo.setNoOfVehiclesAvailable(Integer.parseInt(vehicleDetails[0]));
        inputVo.setMaxSpeedOfVehicle(Integer.parseInt(vehicleDetails[1]));
        inputVo.setMaxLoadOfVehicle(Integer.parseInt(vehicleDetails[2]));
        PriceAndDeliveryService priceAndDeliveryService = new PriceAndDeliveryServiceImpl();
        priceAndDeliveryService.estimateDeliveryCostAndDeliveryTime(inputVo);
    }

    public void provideOutput(PackagesAndVehicleDetails packagesAndVehicleDetails) {
        for (OPackageDetails packageDetails : packagesAndVehicleDetails.getPackageDetails()) {
            System.out.println(packageDetails.getPackageId() + " " +
                    packageDetails.getDiscountApplied() + " " +
                    packageDetails.getTotalCost() + " " +
                    packageDetails.getDeliveryTime());
        }
    }
}
