package Service;

import Pojo.InputVo;
import Pojo.PackagesAndVehicleDetails;
import Pojo.IPackagesDetails;

import java.util.List;

public interface PriceAndDeliveryService {
    void estimateDeliveryCostAndDeliveryTime(InputVo inputVo);
    void processPackages(List<IPackagesDetails> chosenPackages, InputVo inputVo, PackagesAndVehicleDetails packagesAndVehicleDetails);
    void sendPackagesForDelivery(InputVo inputVo,PackagesAndVehicleDetails packagesAndVehicleDetails);
}