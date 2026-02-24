package Product;

public abstract class Product {

    // attribute
    private String productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private int productMax;
    private int productMin;
    private boolean productStatus;

    // constructor
    public Product(String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productMax = productMax;
        this.productMin = productMin;
    }
}