package Product;

import java.util.ArrayList;

public class Management {

    // attribute
    private ArrayList<Product> products;

    // constructor
    public Management() {
        products = new ArrayList<>();
    }

    // กลุ่มเครื่องมือตรวจสอบ (find / check) ==============================================================

    public Product findProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public boolean checkProductId(String productId) {
        return findProduct(productId) != null;
    }
}

