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

    // method setter
    public void setTipSize(double tipSize) {
        this.tipSize = tipSize;
    }
    public void setPenType(String penType) {
        this.penType = penType;
    }

    @Override
    public String toString() {
        return "Pen - Basic Data ----------------------------------" +
                "\n" +
                "\nProduct ID       : " + this.getProductId() +
                "\nProduct Name     : " + this.getProductName() +
                "\nPrice            : " + this.getProductPrice() +
                "\nCurrent Quantity : " + this.getProductQuantity() +
                "\nMaximum Quantity : " + this.getProductMax() +
                "\nMinimum Quantity : " + this.getProductMin() + "\n" +
                "\nPen - Specific Data ----------------------------" +
                "\n" +
                "\nColor            : " + this.getColor() +
                "\nTip Size         : " + this.getTipSize() +
                "\nPen Type         : " + this.getPenType();
    }
}
