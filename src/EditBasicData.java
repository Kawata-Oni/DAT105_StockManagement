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

                    // แก้ข้อมูลเฉพาะ =====================================================
                    int choice = JOptionPane.showConfirmDialog(EditBasicData.this,
                            "Do you want to edit specific data for this product?",
                            "Edit Specific Data",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (choice == JOptionPane.YES_OPTION) {
                        // instanceof เช็ค class ของ product นั้นๆ
                        if (p instanceof Pencil) {
                            Pencil pencil = (Pencil) p; // กำหนดให้มอง p เป็น class Pencil แล้วเอาไปใส่ใน obj pencil
                            String color = JOptionPane.showInputDialog(EditBasicData.this, "Enter Color:", pencil.getColor());
                            if (color == null) return; // Cancel

                            String grade = JOptionPane.showInputDialog(EditBasicData.this, "Enter Pencil Grade (e.g., HB, 2B):", pencil.getGrade());
                            if (grade == null) return;

                            pencil.setColor(color);
                            pencil.setGrade(grade);

                        } else if (p instanceof Pen) {
                            Pen pen = (Pen) p;
                            String color = JOptionPane.showInputDialog(EditBasicData.this, "Enter Color:", pen.getColor());
                            if (color == null) return;

                            String tipSizeStr = JOptionPane.showInputDialog(EditBasicData.this, "Enter Tip Size (e.g., 0.5):", pen.getTipSize());
                            if (tipSizeStr == null) return;
                            double tipSize = Double.parseDouble(tipSizeStr);

                            String penType = JOptionPane.showInputDialog(EditBasicData.this, "Enter Pen Type (e.g., Gel, Ballpoint):", pen.getPenType());
                            if (penType == null) return;

                            pen.setColor(color);
                            pen.setTipSize(tipSize);
                            pen.setPenType(penType);

                        } else if (p instanceof Notebook) {
                            Notebook nb = (Notebook) p;
                            String size = JOptionPane.showInputDialog(EditBasicData.this, "Enter Paper Size (e.g., A4, B5):", nb.getSize());
                            if (size == null) return;

                            String gsmStr = JOptionPane.showInputDialog(EditBasicData.this, "Enter Paper GSM (e.g., 70, 80):", nb.getGsm());
                            if (gsmStr == null) return;
                            int gsm = Integer.parseInt(gsmStr);

                            String pagesStr = JOptionPane.showInputDialog(EditBasicData.this, "Enter Number of Pages:", nb.getNumberOfPages());
                            if (pagesStr == null) return;
                            int pages = Integer.parseInt(pagesStr);

                            nb.setSize(size);
                            nb.setGsm(gsm);
                            nb.setNumberOfPages(pages);

                        } else if (p instanceof ReportPaper) {
                            ReportPaper rp = (ReportPaper) p;
                            String size = JOptionPane.showInputDialog(EditBasicData.this, "Enter Paper Size (e.g., A4):", rp.getSize());
                            if (size == null) return;

                            String gsmStr = JOptionPane.showInputDialog(EditBasicData.this, "Enter Paper GSM (e.g., 70, 80):", rp.getGsm());
                            if (gsmStr == null) return;
                            int gsm = Integer.parseInt(gsmStr);

                            String sheetsStr = JOptionPane.showInputDialog(EditBasicData.this, "Enter Number of Sheets:", rp.getNumberOfSheets());
                            if (sheetsStr == null) return;
                            int sheets = Integer.parseInt(sheetsStr);

                            rp.setSize(size);
                            rp.setGsm(gsm);
                            rp.setNumberOfSheets(sheets);

                        } else if (p instanceof GeneralStationery) {
                            GeneralStationery gs = (GeneralStationery) p;
                            String statType = JOptionPane.showInputDialog(EditBasicData.this, "Enter Stationery Type (e.g., Ruler, Eraser):", gs.getStationeryType());
                            if (statType == null) return;

                            gs.setStationeryType(statType);
                        }
                    }

                    // แก้ไขข้อมูลพื้นฐาน
                    management.editProduct(productId, nameStr, newPrice, newMin, newMax);
                    mainWindowForm.updateTable();
                    dispose();

                } catch (NumberFormatException ex) {
                    // ดักไว้ว่าต้องพิมพ์เป็นตัวเลข
                    JOptionPane.showMessageDialog(null,
                            "Invalid number format! Please enter valid numbers for Price, Max, and Min.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
