package Pojo;

import java.math.BigDecimal;

public class OPackageDetails {
    private String packageId;
    private BigDecimal discountApplied;

    public String getPackageId() {
        return packageId;
    }

    public BigDecimal getDiscountApplied() {
        return discountApplied;
    }

    public BigDecimal getDeliveryTime() {
        return deliveryTime;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    private BigDecimal deliveryTime;

    public void setDeliveryTime(BigDecimal deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Pojo.OutputStructure{" +
                "packageId='" + packageId + '\'' +
                ", discountApplied=" + discountApplied +
                ", deliveryTime=" + deliveryTime +
                ", totalCost=" + totalCost +
                '}';
    }

    private BigDecimal totalCost;


    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setDiscountApplied(BigDecimal discountApplied) {
        this.discountApplied = discountApplied;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
