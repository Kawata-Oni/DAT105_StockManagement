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

    @Override
    public String toString() {
        return "";
        /*
        แสดงข้อมูลทั้งหมด
        */
    }
}
