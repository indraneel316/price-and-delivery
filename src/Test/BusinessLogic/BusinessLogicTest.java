package Test.BusinessLogic;

import BusinessLogic.BusinessLogic;
import Pojo.IPackagesDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BusinessLogicTest {
     BusinessLogic businessLogic = new BusinessLogic();
    @Test
    public void testForCostCalculation() {
        Assertions.assertEquals(2675, businessLogic.calculateCost(100,200, 115));
    }

    @Test
    public void testForDeliveryTimeCalculation()  {
        Assertions.assertEquals(1.43, businessLogic.calculateDeliveryTime(100,70));
    }

    @Test
    public void testForDiscount() {
        IPackagesDetails package1 = new IPackagesDetails();
        package1.setPackageId("PKG 1");
        package1.setPackageWeightInKg(150);
        package1.setDistanceInKms(110);
        package1.setOfferCode("OFR001");
        Assertions.assertEquals(1935,businessLogic.applyDiscount(package1,businessLogic.calculateCost(100,150, 110)));
    }

    @Test
    public void testForDiscountNegativeCase() {
        IPackagesDetails package1 = new IPackagesDetails();
        package1.setPackageId("PKG 1");
        package1.setPackageWeightInKg(150);
        package1.setDistanceInKms(110);
        package1.setOfferCode("NA");
        Assertions.assertEquals(2150,businessLogic.applyDiscount(package1,businessLogic.calculateCost(100,150, 110)));
    }
}
