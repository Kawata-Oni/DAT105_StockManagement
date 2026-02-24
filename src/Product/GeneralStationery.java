package Product;

public class GeneralStationery extends Product {

    // attribute
    private String stationeryType;

    // constructor
    public GeneralStationery (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String stationeryType) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin);
        this.stationeryType = stationeryType;
    }

    // method getter
    public String getStationeryType() {
        return stationeryType;
    }

    @Override
    public String toString() {
        return "";
    }
}
