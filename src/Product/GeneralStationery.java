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

    // method setter
    public void setStationeryType(String stationeryType) {
        this.stationeryType = stationeryType;
    }

    @Override
    public String toString() {
        return "General Stationery - Basic Data ----------------------------------" +
                "\n" +
                "\nProduct ID       : " + this.getProductId() +
                "\nProduct Name     : " + this.getProductName() +
                "\nPrice            : " + this.getProductPrice() +
                "\nCurrent Quantity : " + this.getProductQuantity() +
                "\nMaximum Quantity : " + this.getProductMax() +
                "\nMinimum Quantity : " + this.getProductMin() + "\n" +
                "\nGeneral Stationery - Specific Data ----------------------------" +
                "\n" +
                "\nStationery Type  : " + this.getStationeryType();
    }
}
