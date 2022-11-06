package Test.PriceAndDeliveryService;
import Pojo.IPackagesDetails;

import Service.impl.PriceAndDeliveryServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PriceAndDeliveryTest {
    IPackagesDetails package1 = null;
    IPackagesDetails package2 = null;
    List<IPackagesDetails> packages = null;
    PriceAndDeliveryServiceImpl priceAndDeliveryService = new PriceAndDeliveryServiceImpl();
    @BeforeEach
    public void packageInit() {
        package1 = new IPackagesDetails();
        package2 = new IPackagesDetails();
        packages = new ArrayList<>();
        package1.setPackageId("PKG 1");
        package1.setPackageWeightInKg(50);
        package1.setDistanceInKms(200);
        package1.setOfferCode("NA");
        package2.setPackageId("PKG 2");
        package2.setPackageWeightInKg(75);
        package2.setDistanceInKms(200);
        package2.setOfferCode("NA");
        packages.add(package1);
        packages.add(package2);

    }
    @Test
    public void testForSinglePackage(){
        IPackagesDetails packagesDetails = new IPackagesDetails();
        packagesDetails.setPackageId("PKG 1");
        packagesDetails.setPackageWeightInKg(70);
        List<IPackagesDetails> packagesDetailsList = new ArrayList<>();
        packagesDetailsList.add(packagesDetails);
        List<IPackagesDetails> iPackagesDetails = priceAndDeliveryService.prioritizePackages(75, packagesDetailsList);
        Assertions.assertEquals("PKG 1",iPackagesDetails.get(0).getPackageId());
    }
    @Test
    public void testForSinglePackageNegativeCase() {
        IPackagesDetails packagesDetails = new IPackagesDetails();
        packagesDetails.setPackageId("PKG 1");
        packagesDetails.setPackageWeightInKg(155);
        List<IPackagesDetails> packagesDetailsList = new ArrayList<>();
        packagesDetailsList.add(packagesDetails);
        List<IPackagesDetails> iPackagesDetails = priceAndDeliveryService.prioritizePackages(11, packagesDetailsList);
        Assertions.assertEquals(0, iPackagesDetails.size());
    }
    @Test
    public void testForPriceAndDeliveryForTwoPackages(){
        List<IPackagesDetails> iPackagesDetails = priceAndDeliveryService.prioritizePackages(50,packages);
        Assertions.assertEquals("PKG 1", iPackagesDetails.get(0).getPackageId());
    }

    @Test
    public void testForSelectingFromTwoPackagesNegativeCase() {
        List<IPackagesDetails> iPackagesDetails = priceAndDeliveryService.prioritizePackages(11,packages);
        Assertions.assertEquals(0, iPackagesDetails.size());
    }

    @Test
    public void testForSelectingSinglePackageFromMultiplePackages() {
        IPackagesDetails packagesDetails = new IPackagesDetails();
        packagesDetails.setPackageId("PKG 3");
        packagesDetails.setPackageWeightInKg(250);
        packagesDetails.setDistanceInKms(200);
        packages.add(packagesDetails);
        List<IPackagesDetails> iPackagesDetails = priceAndDeliveryService.prioritizePackages(250, packages);
        Assertions.assertEquals("PKG 3",iPackagesDetails.get(0).getPackageId());
    }

    @Test
    public void testCaseForSelectingMultiplePackagesFromGroupOfPackages() {
        IPackagesDetails package3 = new IPackagesDetails();
        package3.setPackageId("PKG 3");
        package3.setPackageWeightInKg(175);
        package3.setDistanceInKms(200);
        IPackagesDetails package4 = new IPackagesDetails();
        package4.setPackageId("PKG 4");
        package4.setPackageWeightInKg(110);
        package4.setDistanceInKms(200);
        IPackagesDetails package5 = new IPackagesDetails();
        package5.setPackageId("PKG 5");
        package5.setPackageWeightInKg(55);
        package5.setDistanceInKms(200);
        packages.add(package3);
        packages.add(package4);
        packages.add(package5);
        List<String> actual = getIdsFromPackageObject(priceAndDeliveryService.prioritizePackages(200, packages));
        List<String> expected = new ArrayList<>(Arrays.asList("PKG 4","PKG 2"));
        Assertions.assertEquals(expected,actual);
    }

    public List<String> getIdsFromPackageObject(List<IPackagesDetails> packageDetails) {
        List<String> packageIds = new ArrayList<>();
        for (IPackagesDetails packagesDetails: packageDetails) {
            packageIds.add(packagesDetails.getPackageId());
        }
        return packageIds;
    }

}
