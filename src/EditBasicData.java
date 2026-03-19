import Product.Management;
import Product.*;

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
        pack();
        setLocationRelativeTo(null);

        //  เช็ค ID ก่อน 
        Product p = management.findProduct(productId);
        if (p == null) {
            JOptionPane.showMessageDialog(this,
                    "This ID is not in the system.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        // ใส่ข้อมูลเดิม
        editName.setText(p.getProductName());
        editPrice.setText(String.valueOf(p.getProductPrice()));
        editMax.setText(String.valueOf(p.getProductMax()));
        editMin.setText(String.valueOf(p.getProductMin()));

        // cancel
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // ปิดหน้าต่าง
            }
        });

        // confirm
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nameStr = editName.getText().trim();
                String priceStr = editPrice.getText().trim();
                String maxStr = editMax.getText().trim();
                String minStr = editMin.getText().trim();

                if (nameStr.isEmpty() || priceStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill in all fields.",
                            "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    double newPrice = Double.parseDouble(priceStr);
                    int newMax = Integer.parseInt(maxStr);
                    int newMin = Integer.parseInt(minStr);

                    if (newPrice < 0 || newMax < 0 || newMin < 0) {
                        JOptionPane.showMessageDialog(null, "Numbers cannot be negative!", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (newMax < newMin) {
                        JOptionPane.showMessageDialog(null, "Max cannot be less than Min!", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    management.editProduct(productId, nameStr, newPrice, newMin, newMax);
                    mainWindowForm.updateTable();
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
