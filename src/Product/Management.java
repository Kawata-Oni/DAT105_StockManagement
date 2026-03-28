package Product;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Management {

    private ArrayList<Product> products;

    public Management() {
        loadData();
    }

    // ================= FIND =================

    public Product findProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public boolean checkProductId(String productId) {
        return findProduct(productId) != null; // เจอ return true
    }

    // ================= ADD =================

    public boolean addProduct(Product newProduct) {

        if (checkProductId(newProduct.getProductId())) {
            JOptionPane.showMessageDialog(null,
                    "Product ID already exists",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        products.add(newProduct);

        saveData();

        JOptionPane.showMessageDialog(null,
                newProduct.getProductName() + " added successfully",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        warnLowStock(newProduct);

        return true;
    }

    // ================= EDIT =================

    public void editProduct(String productId, String newName, double newPrice, int newMin, int newMax) {

        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "Product not found",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        p.setProductName(newName);
        p.setProductPrice(newPrice);
        p.setProductMin(newMin);
        p.setProductMax(newMax);

        p.updateStatus();

        saveData();

        JOptionPane.showMessageDialog(null,
                "Product edited successfully",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        warnLowStock(p);
    }

    //FIX: wrapper ให้ตรงกับ UI
    public void updateProduct(String productId, String newName, double newPrice, int newMax, int newMin) {
        editProduct(productId, newName, newPrice, newMin, newMax);
    }

    // ================= INCREASE =================

    public boolean increaseProductQuantity(String productId, int addedQuantity) {

        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "Product not found",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int newQty = p.getProductQuantity() + addedQuantity;

        if (newQty > p.getProductMax()) {
            JOptionPane.showMessageDialog(null,
                    "Maximum stock limit is " + p.getProductMax(),
                    "Stock Alert", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        p.setProductQuantity(newQty);

        saveData();

        JOptionPane.showMessageDialog(null,
                "Increased Successfully",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    // ================= DECREASE =================

    public boolean decreaseProductQuantity(String productId, int removedQuantity) {

        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "Product not found",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (p.getProductQuantity() < removedQuantity) {
            JOptionPane.showMessageDialog(null,
                    "Not enough stock",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int newQty = p.getProductQuantity() - removedQuantity;

        p.setProductQuantity(newQty);

        saveData();

        JOptionPane.showMessageDialog(null,
                "Decreased Successfully ",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        warnLowStock(p);

        return true;
    }

    // ================= WARNING =================

    public void warnLowStock(Product p) {

        if (p.getProductQuantity() <= p.getProductMin()) {

            JOptionPane.showMessageDialog(null,
                    "LOW STOCK WARNING\n\n" +
                            "ID: " + p.getProductId() +
                            "\nName: " + p.getProductName() +
                            "\nCurrent: " + p.getProductQuantity(),
                    "Stock Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ================= SAVE =================

    public void saveData() {

        try {

            ObjectOutputStream out = // สร้างหรือเปิดไฟล์
                    new ObjectOutputStream(
                            new FileOutputStream("products.dat")); // แปลงไฟล์เป็น byte stream

            out.writeObject(products);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= LOAD =================

    public void loadData() {

        try {

            File file = new File("products.dat");

            if (!file.exists()) {
                products = new ArrayList<>();
                return;
            }

            ObjectInputStream in = // เปิดไฟล์
                    new ObjectInputStream(
                            new FileInputStream(file)); // แปลง Byte Stream เป็น Java

            products = (ArrayList<Product>) in.readObject();

            in.close();

        } catch (Exception e) {

            products = new ArrayList<>();

        }
    }

    // ================= GET =================

    public ArrayList<Product> getProducts() {
        return products;
    }
}
