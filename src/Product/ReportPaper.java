package Product;

public class ReportPaper extends PaperProduct {

    // attribute
    private int numberOfSheets;

    // constructor
    public ReportPaper(String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String size, int gsm, int numberOfSheets) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, size, gsm);
        this.numberOfSheets = numberOfSheets;
    }

    // method getter
    public int getNumberOfSheets() {
        return numberOfSheets;
    }

    @Override
    public String toString() {
        return "";
    }
}
