package Product;

import java.io.Serializable;

public abstract class Product implements Serializable { // interface ที่ไม่มีอะไรต้อง override
    /* ส่งสัญญาณบอกระบบของ Java ว่า
    "อ็อบเจกต์ของคลาสนี้ อนุญาตให้แปลงร่างเป็น Byte เพื่อบันทึกลงไฟล์ได้นะ */

    private String productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private int productMax;
    private int productMin;
    private boolean productStatus;

    public Product(String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productMax = productMax;
        this.productMin = productMin;

        updateStatus();
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getProductMax() {
        return productMax;
    }

    public int getProductMin() {
        return productMin;
    }

    public boolean getProductStatus() {
        return productStatus;
    }

    public void setProductName(String newName) {
        this.productName = newName;
    }

    public void setProductPrice(double newPrice) {
        this.productPrice = newPrice;
    }

    public void setProductQuantity(int newQty) {
        this.productQuantity = newQty;
        updateStatus();
    }

    public void setProductMax(int newMax) {
        this.productMax = newMax;
    }

    public void setProductMin(int newMin) {
        this.productMin = newMin;
    }

    public void updateStatus() {
        if (this.productQuantity <= this.productMin) {
            this.productStatus = false;
        } else {
            this.productStatus = true;
        }
    }

    public abstract String toString();
}
