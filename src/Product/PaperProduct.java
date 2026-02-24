package Product;

public abstract class PaperProduct extends Product {

    // attribute
    private String size;
    private int gsm;

    // constructor
    public PaperProduct (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String size, int gsm) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin);
        this.size = size;
        this.gsm = gsm;
    }

    // getter
    public String getSize() {
        return size;
    }
    public int getGsm() {
        return gsm;
    }

    @Override
    public abstract String toString();
}
