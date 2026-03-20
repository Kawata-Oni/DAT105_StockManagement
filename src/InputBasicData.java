import Product.Management;
import Product.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputBasicData extends JFrame {

    private JPanel inputBasicData;
    private JTextField typeId;
    private JTextField typeName;
    private JTextField typePrice;
    private JTextField typeCurQty;
    private JTextField typeMax;
    private JTextField typeMin;
    private JButton btnConfirm;
    private JButton btnCancel;

    private Management management;

    public InputBasicData(Management management) {
        this.management = management;

        setContentPane(inputBasicData);
        setTitle("Add Product");
        setSize(400, 400);
        setLocationRelativeTo(null);

        // ================= CONFIRM =================
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = typeId.getText().trim();
                String name = typeName.getText().trim();
                String priceStr = typePrice.getText().trim();
                String qtyStr = typeCurQty.getText().trim();
                String maxStr = typeMax.getText().trim();
                String minStr = typeMin.getText().trim();

                // ================= VALIDATE EMPTY =================
                if (id.isEmpty() || name.isEmpty() || priceStr.isEmpty()
                        || qtyStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {

                    JOptionPane.showMessageDialog(null,
                            "Please fill all fields",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // ================= VALIDATE ID FORMAT =================
                if (!id.matches("P\\d{4}")) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid ID format. Must be Pxxxx (e.g., P0001)",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ================= CHECK DUPLICATE =================
                if (management.checkProductId(id)) {
                    JOptionPane.showMessageDialog(null,
                            "Product ID already exists",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double price = Double.parseDouble(priceStr);
                    int qty = Integer.parseInt(qtyStr);
                    int max = Integer.parseInt(maxStr);
                    int min = Integer.parseInt(minStr);

                    if (min > max) {
                        JOptionPane.showMessageDialog(null,
                                "Min cannot be greater than Max",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // ================= CREATE PRODUCT =================
                    Product p = new Product(id, name, price, qty, max, min);

                    management.addProduct(p);

                    JOptionPane.showMessageDialog(null,
                            "Product added successfully");

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
