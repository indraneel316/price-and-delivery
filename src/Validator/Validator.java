package Validator;

import Pojo.IPackagesDetails;
import Pojo.InputVo;

import static java.util.Objects.isNull;

public class Validator {
    public boolean validateInput(InputVo inputVo) {
        if (inputVo.getNoOfVehicles() <= 0 || inputVo.getBaseDeliveryCost() <= 0 ||
                inputVo.getMaxSpeedOfVehicle() <= 0 || inputVo.getMaxLoadOfVehicle() <= 0) {
            System.out.println("Input Should not have values less than 0");
            return false;
        }
        for (IPackagesDetails packagesDetails : inputVo.getPackages()) {
            if (packagesDetails.getPackageWeightInKg() < 0 || packagesDetails.getDistanceInKms() < 0) {
                System.out.println("Input Should not have values less than 0");
                return false;
            }
            if(isNull(packagesDetails.getPackageId())) {
                System.out.println("Package id should not be null");
                return false;
            }
        }
        return true;
    }
}
