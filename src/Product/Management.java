package Product;

import javax.swing.*;
import java.util.ArrayList;

public class Management {

    // attribute
    private ArrayList<Product> products;

    // constructor
    public Management() {
        products = new ArrayList<>();
    }

    // กลุ่มเครื่องมือตรวจสอบ (find / check) ==============================================================

    // ดึง obj ของ Product ออกมาจาก ArrayList
    public Product findProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }
    // หาว่ามี productId นี้รึป่าว ถ้ามี return True
    // สำหรับทุก method ที่ต้องระบุ productId
    public boolean checkProductId(String productId) {
        return findProduct(productId) != null;
    }

    // กลุ่มจัดการสินค้า (Add / Edit) ==============================================================

    // สร้าง obj Product ใหม่แล้วยัดลง ArrayList
    public boolean addProduct(Product newProduct) {
        // เช็คว่า ProductId ซ้ำรึป่าว
        if (checkProductId(newProduct.getProductId())) {
            JOptionPane.showMessageDialog(null,
                    "Product ID: " + newProduct.getProductId() + " has been added",
                    "Duplicate Product ID", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // ถ้าไม่ซ้ำสร้าง obj Product ลง ArrayList Products
        products.add(newProduct);
        JOptionPane.showMessageDialog(null,
                newProduct.getProductName() + " added successfully",
                "succeed", JOptionPane.INFORMATION_MESSAGE);

        return true; // สำหรับเช็คว่าสร้างแล้วจริงๆ นะ
    }

    // แก้ไขข้อมูลที่อุญาตให้แก้ได้
        // ProductId สำหรับระบุ obj Product ที่จะแก้ไข
    public void editProduct(String productId, String newName, double newPrice, int newMin, int newMax) {
        // เช็คว่า ProductId ที่ส่งเข้ามามีในคลัง(ArrayList)มั้ย
        Product p = findProduct(productId);
        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "There is no product ID : " + productId + " for editing",
                    "have some mistake", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ถ้า productId นั้นมีในคลังก็แก้ข้อมูลซะ
        p.setProductName(newName);
        p.setProductPrice(newPrice);
        p.setProductMin(newMin);
        p.setProductMax(newMax);

        JOptionPane.showMessageDialog(null,
                productId + " edited successfully",
                "succeed", JOptionPane.INFORMATION_MESSAGE);
    }
}

