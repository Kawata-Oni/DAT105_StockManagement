import Product.Management;
import Product.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBasicData extends JFrame {

    // attribute
    private JPanel editBasicData;
    private JButton btnConfirm;
    private JButton btnCancel;
    private JTextField editName;
    private JTextField editPrice;
    private JTextField editMax;
    private JTextField editMin;

    private MainWindowForm mainWindowForm;
    private Management management;
    String productId;

    // constructor
    public EditBasicData(MainWindowForm mainWindowForm, Management management, String productId) {
        this.mainWindowForm = mainWindowForm;
        this.management = management;
        this.productId = productId;

        setTitle("Edit Product - " + productId);
        setContentPane(editBasicData);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack(); // ให้หน้าต่างปรับขนาดพอดีกับข้อมูล
        setLocationRelativeTo(null); // ให้อยู่กลางจอ

        // ดึงข้อมูลเดิมมาเดิมในช่องพิมพ์
        Product p = management.findProduct(productId);
        if (p != null) {
            editName.setText(p.getProductName());
            editPrice.setText(String.valueOf(p.getProductPrice()));
            editMax.setText(String.valueOf(p.getProductMax()));
            editMin.setText(String.valueOf(p.getProductMin()));
        }

        // ปุ่ม Cancel
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // ปุ่ม Confirm
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameStr = editName.getText().trim();
                String priceStr = editPrice.getText().trim();
                String maxStr = editMax.getText().trim();
                String minStr = editMin.getText().trim();

                // เช็คว่ามีข้อมูลทุกช่องรึยัง
                if (nameStr.isEmpty() || priceStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill in all fields.",
                            "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    // แปลงข้อความเป็นตัวเลข
                    double newPrice = Double.parseDouble(priceStr);
                    int newMax = Integer.parseInt(maxStr);
                    int newMin = Integer.parseInt(minStr);

                    // เช็คว่าต้องไม่มีอะไรน้อยกว่า 0
                    if (newPrice < 0 || newMax < 0 || newMin < 0) {
                        JOptionPane.showMessageDialog(null, "Numbers cannot be negative!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (newMax < newMin) {
                        JOptionPane.showMessageDialog(null, "Max cannot be less than Min!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // แก้ไขข้อมูล
                    management.editProduct(productId, nameStr, newPrice, newMin, newMax);

                    dispose();

                } catch (NumberFormatException ex) {
                    // ดักไว้ว่าต้องพิมพ์เป็นตัวเลข
                    JOptionPane.showMessageDialog(null, "Invalid number format!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
