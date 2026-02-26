package Product;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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

    // แก้ไขข้อมูลพื้นฐานที่อุญาตให้แก้ได้
        // ProductId สำหรับระบุ obj Product ที่จะแก้ไข
    public void editProduct(String productId, String newName, double newPrice, int newMin, int newMax) {
        // เช็คว่า ProductId ที่ส่งเข้ามามีในคลัง(ArrayList)มั้ย
        Product p = findProduct(productId);
        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "Could not find product with ID " + productId + " for editing",
                    "error", JOptionPane.ERROR_MESSAGE);
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

    // กลุ่มจัดการสต็อก (increase / decrease / warning) ==============================================================

    // เพิ่ม qty ของ obj Product
    public void increaseProductQuantity(String productId, int addedQuantity) {
        // เช็คว่า ProductId ที่ส่งเข้ามามีในคลัง(ArrayList)มั้ย
        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "Could not find product with ID " + productId,
                    "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newQty = p.getProductQuantity() + addedQuantity;

        if (newQty > p.getProductMax()) {
            // แจ้งเตือน Warning แบบ Popup (ไอคอนตกใจสีเหลือง)
            JOptionPane.showMessageDialog(null,
                    "Can't add more. The limit is " + p.getProductMax(),
                    "stock alert", JOptionPane.WARNING_MESSAGE);
            return;
        }

        p.setProductQuantity(newQty);
        JOptionPane.showMessageDialog(null,
                "Inventory updated: " + p.getProductName() + " total is " + newQty,
                "succeed", JOptionPane.INFORMATION_MESSAGE);
    }

    public void decreaseProductQuantity(String productId, int removedQuantity) {
        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "Could not find product with ID " + productId,
                    "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (p.getProductQuantity() >= removedQuantity) {
            int newQty = p.getProductQuantity() - removedQuantity;
            p.setProductQuantity(newQty);
            JOptionPane.showMessageDialog(null,
                    "Sold successfully : " + removedQuantity + " items deducted",
                    "succeed", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Not enough stock (Only " + p.getProductQuantity() + " left)",
                    "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void warnLowStock(Product p) {
        // ถ้า qty ปัจจุบันน้อยกว่า min
        if (p.getProductQuantity() <= p.getProductMin()) {
            JOptionPane.showMessageDialog(null,
                    "Low Stock Warning!\n" +
                            "\nID: " + p.getProductId() +
                            "\nName: " + p.getProductName() +
                            "\nCurrent Inventory: " + p.getProductQuantity() + " items",
                    "stock alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    // กลุ่มทำงานกับตาราง ==============================================================

    // ดึง ArrayList ออกมาทั้งก้อน
    // ใช้เวลารัน loop เพื่อใส่ข้อมูลในตาราง
    public ArrayList<Product> getProducts() {
        return products;
    }
}

