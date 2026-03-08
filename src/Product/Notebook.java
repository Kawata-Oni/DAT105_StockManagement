package Product;

public class Notebook extends PaperProduct {

    // attribute
    private int numberOfPages;

    // constructor
    public Notebook (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String size, int gsm, int numberOfPages) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, size, gsm);
        this.numberOfPages = numberOfPages;
    }

    // method getter
    public int getNumberOfPages() {
        return numberOfPages;
    }

    // method setter
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Notebook - Basic Data ----------------------------------" +
                "\n" +
                "\nProduct ID       : " + this.getProductId() +
                "\nProduct Name     : " + this.getProductName() +
                "\nPrice            : " + this.getProductPrice() +
                "\nCurrent Quantity : " + this.getProductQuantity() +
                "\nMaximum Quantity : " + this.getProductMax() +
                "\nMinimum Quantity : " + this.getProductMin() +
                "\nNotebook - Specific Data ----------------------------" +
                "\n" +
                "\nSize             : " + this.getSize() +
                "\nGSM              : " + this.getGsm() +
                "\nNumber of Pages  : " + this.getNumberOfPages();
    }
}
