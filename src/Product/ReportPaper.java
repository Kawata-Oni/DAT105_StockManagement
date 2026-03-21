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

    // method setter
    public void setNumberOfSheets(int numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
    }

    @Override
    public String toString() {
        return "Report Paper - Basic Data ----------------------------------" +
                "\n" +
                "\nProduct ID : " + this.getProductId() +
                "\nProduct Name : " + this.getProductName() +
                "\nPrice : " + this.getProductPrice() +
                "\nCurrent Quantity : " + this.getProductQuantity() +
                "\nMaximum Quantity : " + this.getProductMax() +
                "\nMinimum Quantity : " + this.getProductMin() + "\n" +
                "\nReport Paper - Specific Data ----------------------------" +
                "\n" +
                "\nSize : " + this.getSize() +
                "\nGSM : " + this.getGsm() +
                "\nNumber of Sheets : " + this.getNumberOfSheets();
    }
}
