import Product.*;

import javax.swing.*;

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
    private String productType;
    private MainWindowForm mainWindowForm;
    private AddWindowForm addWindowForm;

    public InputBasicData(String productType, MainWindowForm mainWindowForm,
                          Management management, AddWindowForm addWindowForm) {

        this.productType = productType;
        this.mainWindowForm = mainWindowForm;
        this.management = management;
        this.addWindowForm = addWindowForm;

        setTitle("Input Basic Data - " + productType);
        setContentPane(inputBasicData);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        
        btnCancel.addActionListener(e -> {
            addWindowForm.setVisible(true);
            dispose();
        });

        
        btnConfirm.addActionListener(e -> handleConfirm());
    }

    private void handleConfirm() {

        String idStr = typeId.getText().trim();
        String nameStr = typeName.getText().trim();
        String priceStr = typePrice.getText().trim();
        String qtyStr = typeCurQty.getText().trim();
        String maxStr = typeMax.getText().trim();
        String minStr = typeMin.getText().trim();

        if (idStr.isEmpty() || nameStr.isEmpty() || priceStr.isEmpty()
                || qtyStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!idStr.matches("^P\\d{4}$")) {
            JOptionPane.showMessageDialog(this,
                    "Product ID must be in format P0001 - P9999",
                    "Invalid ID Format",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
ำ
        if (management.checkProductId(idStr)) {
            JOptionPane.showMessageDialog(this,
                    "This Product ID already exists",
                    "Duplicate Product ID",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        double price;
        int qty, max, min;

        try {
            price = Double.parseDouble(priceStr);
            qty = Integer.parseInt(qtyStr);
            max = Integer.parseInt(maxStr);
            min = Integer.parseInt(minStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number format!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (price < 0) {
            showError("Price cannot be negative!");
            return;
        }

        if (qty < 0) {
            showError("Quantity cannot be negative!");
            return;
        }

        if (min < 0) {
            showError("Min cannot be negative!");
            return;
        }

        if (max < min) {
            showError("Max cannot be less than Min!");
            return;
        }

        if (qty > max) {
            showError("Quantity cannot be greater than Max!");
            return;
        }

        
        Product newProduct = createProduct(idStr, nameStr, price, qty, max, min);

        if (newProduct == null) return;

        
        if (management.addProduct(newProduct)) {
            mainWindowForm.updateTable();

            JOptionPane.showMessageDialog(this,
                    "Product added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();
            addWindowForm.dispose();
        }
    }

    
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg,
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
    }

    
    private Product createProduct(String id, String name, double price,
                                  int qty, int max, int min) {

        try {
            switch (productType) {

                case "Pencil":
                    String color = JOptionPane.showInputDialog(this, "Enter Color:");
                    if (color == null) return null;

                    String grade = JOptionPane.showInputDialog(this, "Enter Pencil Grade:");
                    if (grade == null) return null;

                    return new Pencil(id, name, price, qty, max, min, color, grade);

                case "Pen":
                    color = JOptionPane.showInputDialog(this, "Enter Color:");
                    if (color == null) return null;

                    String tipStr = JOptionPane.showInputDialog(this, "Enter Tip Size:");
                    if (tipStr == null) return null;
                    double tip = Double.parseDouble(tipStr);

                    String penType = JOptionPane.showInputDialog(this, "Enter Pen Type:");
                    if (penType == null) return null;

                    return new Pen(id, name, price, qty, max, min, color, tip, penType);

                case "Notebook":
                    String size = JOptionPane.showInputDialog(this, "Enter Size:");
                    if (size == null) return null;

                    int gsm = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter GSM:"));
                    int pages = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Pages:"));

                    return new Notebook(id, name, price, qty, max, min, size, gsm, pages);

                case "Report Paper":
                    size = JOptionPane.showInputDialog(this, "Enter Size:");
                    if (size == null) return null;

                    gsm = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter GSM:"));
                    int sheets = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Sheets:"));

                    return new ReportPaper(id, name, price, qty, max, min, size, gsm, sheets);

                case "General Stationery":
                    String statType = JOptionPane.showInputDialog(this, "Enter Type:");
                    if (statType == null) return null;

                    return new GeneralStationery(id, name, price, qty, max, min, statType);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number format in extra details!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
}
