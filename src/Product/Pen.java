package Product;

public class Pen extends WritingTool {

    // attribute
    private double tipSize;
    private String penType;

    // constructor
    public Pen(String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String color, double tipSize, String penType) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, color);
        this.tipSize = tipSize;
        this.penType = penType;
    }

    // method getter
    public double getTipSize() {
        return tipSize;
    }

    public String getPenType() {
        return penType;
    }

    @Override
    public String toString() {
        return "";
    }
}
