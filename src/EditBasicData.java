import Product.Management;
import Product.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBasicData extends JFrame {

    private JPanel editBasicData;
    private JButton btnConfirm;
    private JButton btnCancel;
    private JTextField editName;
    private JTextField editPrice;
    private JTextField editMax;
    private JTextField editMin;

    private MainWindowForm mainWindowForm;
    private Management management;
    private String productId;

    public EditBasicData(MainWindowForm mainWindowForm, Management management, String productId) {
        this.mainWindowForm = mainWindowForm;
        this.management = management;
        this.productId = productId;

        setContentPane(editBasicData);
        setTitle("Edit Product");
        setSize(400, 300);
        setLocationRelativeTo(null);

        Product p = management.findProduct(productId);
        if (p != null) {
            editName.setText(p.getProductName());
            editPrice.setText(String.valueOf(p.getProductPrice()));
            editMax.setText(String.valueOf(p.getProductMax()));
            editMin.setText(String.valueOf(p.getProductMin()));
        } else {
            JOptionPane.showMessageDialog(null,
                    "Product not found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        // ================= CONFIRM =================
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = editName.getText().trim();
                String priceStr = editPrice.getText().trim();
                String maxStr = editMax.getText().trim();
                String minStr = editMin.getText().trim();

                if (name.isEmpty() || priceStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill all fields",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    double price = Double.parseDouble(priceStr);
                    int max = Integer.parseInt(maxStr);
                    int min = Integer.parseInt(minStr);

                    if (min > max) {
                        JOptionPane.showMessageDialog(null,
                                "Min cannot be greater than Max",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    //FIX: ใช้ updateProduct แทน
                    management.updateProduct(productId, name, price, max, min);

                    JOptionPane.showMessageDialog(null,
                            "Product updated successfully");

                    mainWindowForm.updateTable();
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid number format",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ================= CANCEL =================
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
