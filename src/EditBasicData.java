import Product.Management;
import Product.*;

import javax.swing.*;

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

        setTitle("Edit Product - " + productId);
        setContentPane(editBasicData);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        
        Product p = management.findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(this,
                    "ไม่มี ID นี้ในระบบ",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        
        editName.setText(p.getProductName());
        editPrice.setText(String.valueOf(p.getProductPrice()));
        editMax.setText(String.valueOf(p.getProductMax()));
        editMin.setText(String.valueOf(p.getProductMin()));

        btnCancel.addActionListener(e -> dispose());

        btnConfirm.addActionListener(e -> handleConfirm());
    }

    
    private void handleConfirm() {

      
        Product p = management.findProduct(productId);
        if (p == null) {
            JOptionPane.showMessageDialog(this,
                    "Product not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        String nameStr = editName.getText().trim();
        String priceStr = editPrice.getText().trim();
        String maxStr = editMax.getText().trim();
        String minStr = editMin.getText().trim();

       
        if (nameStr.isEmpty() || priceStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double newPrice;
        int newMax, newMin;

        try {
            newPrice = Double.parseDouble(priceStr);
            newMax = Integer.parseInt(maxStr);
            newMin = Integer.parseInt(minStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number format!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (newPrice < 0) {
            showError("Price cannot be negative!");
            return;
        }

        if (newMax < 0 || newMin < 0) {
            showError("Max and Min cannot be negative!");
            return;
        }

        if (newMax < newMin) {
            showError("Max cannot be less than Min!");
            return;
        }

        
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to edit specific data for this product?",
                "Edit Specific Data",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            if (!editSpecificData(p)) return;
        }

        
        management.editProduct(productId, nameStr, newPrice, newMin, newMax);
        mainWindowForm.updateTable();

        JOptionPane.showMessageDialog(this,
                "Product updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }

    
    private boolean editSpecificData(Product p) {
        try {
            if (p instanceof Pencil) {
                Pencil pencil = (Pencil) p;

                String color = JOptionPane.showInputDialog(this, "Enter Color:", pencil.getColor());
                if (color == null) return false;

                String grade = JOptionPane.showInputDialog(this, "Enter Grade:", pencil.getGrade());
                if (grade == null) return false;

                pencil.setColor(color);
                pencil.setGrade(grade);

            } else if (p instanceof Pen) {
                Pen pen = (Pen) p;

                String color = JOptionPane.showInputDialog(this, "Enter Color:", pen.getColor());
                if (color == null) return false;

                String tipStr = JOptionPane.showInputDialog(this, "Enter Tip Size:", pen.getTipSize());
                if (tipStr == null) return false;
                double tip = Double.parseDouble(tipStr);

                String penType = JOptionPane.showInputDialog(this, "Enter Pen Type:", pen.getPenType());
                if (penType == null) return false;

                pen.setColor(color);
                pen.setTipSize(tip);
                pen.setPenType(penType);

            } else if (p instanceof Notebook) {
                Notebook nb = (Notebook) p;

                String size = JOptionPane.showInputDialog(this, "Enter Size:", nb.getSize());
                if (size == null) return false;

                int gsm = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter GSM:", nb.getGsm()));
                int pages = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Pages:", nb.getNumberOfPages()));

                nb.setSize(size);
                nb.setGsm(gsm);
                nb.setNumberOfPages(pages);

            } else if (p instanceof ReportPaper) {
                ReportPaper rp = (ReportPaper) p;

                String size = JOptionPane.showInputDialog(this, "Enter Size:", rp.getSize());
                if (size == null) return false;

                int gsm = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter GSM:", rp.getGsm()));
                int sheets = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Sheets:", rp.getNumberOfSheets()));

                rp.setSize(size);
                rp.setGsm(gsm);
                rp.setNumberOfSheets(sheets);

            } else if (p instanceof GeneralStationery) {
                GeneralStationery gs = (GeneralStationery) p;

                String type = JOptionPane.showInputDialog(this, "Enter Type:", gs.getStationeryType());
                if (type == null) return false;

                gs.setStationeryType(type);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number format in specific data!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this,
                msg,
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
