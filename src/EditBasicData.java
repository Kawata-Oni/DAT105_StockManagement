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
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Do you want to edit specific data for this product?",
                            "Edit Specific Data",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (choice == JOptionPane.YES_OPTION) {
                        // instanceof เช็ค class ของ product นั้นๆ
                        if (p instanceof Pencil) {
                            Pencil pencil = (Pencil) p; // กำหนดให้มอง p เป็น class Pencil แล้วเอาไปใส่ใน obj pencil
                            String color = JOptionPane.showInputDialog(null, "Enter Color (e.g., Black, Red):", pencil.getColor());
                            if (color == null) return; // Cancel

                            String grade = JOptionPane.showInputDialog(null, "Enter Pencil Grade (e.g., HB, 2B):", pencil.getGrade());
                            if (grade == null) return;

                            pencil.setColor(color);
                            pencil.setGrade(grade);

                        } else if (p instanceof Pen) {
                            Pen pen = (Pen) p;
                            String color = JOptionPane.showInputDialog(null, "Enter Color (e.g., Blue, Red, Black):", pen.getColor());
                            if (color == null) return;

                            double tipSize = 0.0;
                            String tipSizeStr = pen.getTipSize() + ""; // นำค่าเดิมมาแปลงเป็น String เพื่อเตรียมแสดง
                            while (true) {
                                tipSizeStr = JOptionPane.showInputDialog(null, "Enter Tip Size (e.g., 0.5, 0.7):", tipSizeStr);
                                if (tipSizeStr == null) return; // ดักปุ่ม Cancel

                                try {
                                    tipSize = Double.parseDouble(tipSizeStr);
                                    break; // แปลงสำเร็จ หลุด loop
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid format! Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            String penType = JOptionPane.showInputDialog(null, "Enter Pen Type (e.g., Gel, Ballpoint):", pen.getPenType());
                            if (penType == null) return;

                            pen.setColor(color);
                            pen.setTipSize(tipSize);
                            pen.setPenType(penType);

                        } else if (p instanceof Notebook) {
                            Notebook nb = (Notebook) p;
                            String size = JOptionPane.showInputDialog(null, "Enter Paper Size (e.g., A4, B5):", nb.getSize());
                            if (size == null) return;

                            int gsm = 0;
                            String gsmStr = nb.getGsm() + "";
                            while (true) {
                                gsmStr = JOptionPane.showInputDialog(null, "Enter Paper GSM (e.g., 70, 80):", gsmStr);
                                if (gsmStr == null) return;

                                try {
                                    gsm = Integer.parseInt(gsmStr);
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid format! Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            int pages = 0;
                            String pagesStr = nb.getNumberOfPages() + "";
                            while (true) {
                                pagesStr = JOptionPane.showInputDialog(null, "Enter Number of Pages:", pagesStr);
                                if (pagesStr == null) return;

                                try {
                                    pages = Integer.parseInt(pagesStr);
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid format! Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            nb.setSize(size);
                            nb.setGsm(gsm);
                            nb.setNumberOfPages(pages);

                        } else if (p instanceof ReportPaper) {
                            ReportPaper rp = (ReportPaper) p;
                            String size = JOptionPane.showInputDialog(null, "Enter Paper Size (e.g., A4):", rp.getSize());
                            if (size == null) return;

                            int gsm = 0;
                            String gsmStr = rp.getGsm() + "";
                            while (true) {
                                gsmStr = JOptionPane.showInputDialog(null, "Enter Paper GSM (e.g., 70, 80):", gsmStr);
                                if (gsmStr == null) return;

                                try {
                                    gsm = Integer.parseInt(gsmStr);
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid format! Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            int sheets = 0;
                            String sheetsStr = rp.getNumberOfSheets() + "";
                            while (true) {
                                sheetsStr = JOptionPane.showInputDialog(null, "Enter Number of Sheets:", sheetsStr);
                                if (sheetsStr == null) return;

                                try {
                                    sheets = Integer.parseInt(sheetsStr);
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid format! Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            rp.setSize(size);
                            rp.setGsm(gsm);
                            rp.setNumberOfSheets(sheets);

                        } else if (p instanceof GeneralStationery) {
                            GeneralStationery gs = (GeneralStationery) p;
                            String statType = JOptionPane.showInputDialog(null, "Enter Stationery Type (e.g., Ruler, Eraser):", gs.getStationeryType());
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
