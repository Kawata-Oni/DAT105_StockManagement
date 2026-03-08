package Product;

public class Pencil extends WritingTool {

    // attribute
    private String grade;

    // constructor
    public Pencil (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String color, String grade) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, color);
        this.grade = grade;
    }

    // method getter
    public String getGrade() {
        return grade;
    }

    // method setter
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Pencil - Basic Data ----------------------------------" +
                "\n" +
                "\nProduct ID       : " + this.getProductId() +
                "\nProduct Name     : " + this.getProductName() +
                "\nPrice            : " + this.getProductPrice() +
                "\nCurrent Quantity : " + this.getProductQuantity() +
                "\nMaximum Quantity : " + this.getProductMax() +
                "\nMinimum Quantity : " + this.getProductMin() + "\n" +
                "\nPencil - Specific Data ----------------------------" +
                "\n" +
                "\nColor            : " + this.getColor() +
                "\nGrade            : " + this.getGrade();
    }
}
