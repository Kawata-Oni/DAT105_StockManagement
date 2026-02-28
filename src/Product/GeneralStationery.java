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
        return "General Stationery - Basic Data ----------------------------------" +
                "\n" +
                "\nProduct ID       : " +
                "\nProduct Name     : " +
                "\nPrice            : " +
                "\nCurrent Quantity : " +
                "\nMaximum Quantity : " +
                "\nMinimum Quantity : " +
                "\nGeneral Stationery - Specific Data ----------------------------" +
                "\n" +
                "\nStationery Type  : ";
    }
}
