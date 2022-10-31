package Pojo;

public class IPackagesDetails {
    private String packageId;
    private int packageWeightInKg;
    private double distanceInKms;
    private String offerCode;

    @Override
    public String toString() {
        return "PackagesInput{" +
                "packageId='" + packageId + '\'' +
                ", packageWeightInKg=" + packageWeightInKg +
                ", distanceInKms=" + distanceInKms +
                ", offerCode='" + offerCode + '\'' +
                '}';
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public int getPackageWeightInKg() {
        return packageWeightInKg;
    }

    public void setPackageWeightInKg(int packageWeightInKg) {
        this.packageWeightInKg = packageWeightInKg;
    }

    public double getDistanceInKms() {
        return distanceInKms;
    }

    public void setDistanceInKms(int distanceInKms) {
        this.distanceInKms = distanceInKms;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }
}
