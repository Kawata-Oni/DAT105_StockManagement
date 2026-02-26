import Product.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputBasicData extends JFrame {

    // attribute
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

    // contribute
    public InputBasicData(String productType, MainWindowForm mainWindowForm, Management management, AddWindowForm addWindowForm) {
        this.productType = productType;
        this.mainWindowForm = mainWindowForm;
        this.management = management;
        this.addWindowForm = addWindowForm;

        setTitle("Input Basic Data" + productType);
        setContentPane(inputBasicData);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

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
                // ดึง txt ที่พิมพ์ในช่อง
                String idStr = typeId.getText().trim();
                String nameStr = typeName.getText().trim();
                String priceStr = typePrice.getText().trim();
                String qtyStr = typeCurQty.getText();
                String maxStr = typeMax.getText().trim();
                String minStr = typeMin.getText().trim();

                // ถ้าขาดไปสัดอันให้แจ้งเตือน
                if (idStr.isEmpty() || nameStr.isEmpty() || priceStr.isEmpty() ||
                        qtyStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {

                    JOptionPane.showMessageDialog(null,
                            "Please fill in all basic information fields.",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // ถ้า Id ซ้ำให้แจ้งเตือน
                if (management.checkProductId(idStr)) {
                    JOptionPane.showMessageDialog(null, "This Product ID has been uesd",
                            "Duplicate Product ID",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Validate ข้อมูลเตรียมสร้าง obj ===========================================================
                String productId = idStr;
                String productName = nameStr;
                double productPrice = 0;
                int productQuantity = 0;
                int productMax = 0;
                int productMin = 0;

                // เช็ค NumberFormatException
                try {
                    productPrice = Double.parseDouble(priceStr);
                    productQuantity = Integer.parseInt(qtyStr);
                    productMax = Integer.parseInt(maxStr);
                    productMin = Integer.parseInt(minStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(InputBasicData.this,
                            "Invalid number format! Please enter valid numbers for Price, Quantity, Max, and Min.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // เช็คว่า Qty ต้องไม่เกิน Max
                if (productQuantity > productMax) {
                    JOptionPane.showMessageDialog(InputBasicData.this,
                            "Quantity cannot be greater than Max limit!",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return; // หยุดการทำงาน ให้ผู้ใช้แก้ตัวเลขใหม่
                }

                // เช็คว่า Min ต้องไม่ติดลบ
                if (productMin < 0) {
                    JOptionPane.showMessageDialog(InputBasicData.this,
                            "Min cannot be less than 0!",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return; // หยุดการทำงาน ให้ผู้ใช้แก้ตัวเลขใหม่
                }

                // เช็ค Max ต้องไม่น้อยกว่า Min
                if (productMax < productMin) {
                    JOptionPane.showMessageDialog(InputBasicData.this,
                            "Max cannot be less than Min!",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // เช็ค Qty ต้องไม่ติดลบ
                if (productQuantity < 0) {
                    JOptionPane.showMessageDialog(InputBasicData.this,
                            "Quantity cannot be negative numbers!",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // เช็ค Price ต้องไม่ติดลบ
                if (productPrice < 0) {
                    JOptionPane.showMessageDialog(InputBasicData.this,
                            "Price cannot be negative numbers!",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // สร้าง obj เพื่อเอาเข้า ArrayList =======================================================
                // สร้าง obj เตรียมไว้ใส่ค่าที่รับมา
                Product newProduct = null;

                try {
                    if (productType.equals("Pencil")) {
                        String color = JOptionPane.showInputDialog(null, "Enter Color:");
                        if (color == null) return;

                        String grade = JOptionPane.showInputDialog(null, "Enter Pencil Grade (e.g., HB, 2B):");
                        if (grade == null) return;

                        // สร้าง Object
                        newProduct = new Pencil(productId, productName, productPrice, productQuantity, productMax, productMin, color, grade);
                    } else if (productType.equals("Pen")) {
                        String color = JOptionPane.showInputDialog(null, "Enter Color:");
                        if (color == null) return;

                        String tipSizeStr = JOptionPane.showInputDialog(null, "Enter Tip Size (e.g., 0.5):");
                        if (tipSizeStr == null) return;
                        double tipSize = Double.parseDouble(tipSizeStr);

                        String penType = JOptionPane.showInputDialog(null, "Enter Pen Type (e.g., Gel, Ballpoint):");
                        if (penType == null) return;

                        // สร้าง Object
                        newProduct = new Pen(productId, productName, productPrice, productQuantity, productMax, productMin, color, tipSize, penType);
                    } else if (productType.equals("Notebook")) {
                        String size = JOptionPane.showInputDialog(null, "Enter Paper Size (e.g., A4, B5):");
                        if (size == null) return;

                        String gsmStr = JOptionPane.showInputDialog(null, "Enter Paper GSM (e.g., 70, 80):");
                        if (gsmStr == null) return;
                        int gsm = Integer.parseInt(gsmStr);

                        String pagesStr = JOptionPane.showInputDialog(null, "Enter Number of Pages:");
                        if (pagesStr == null) return;
                        int pages = Integer.parseInt(pagesStr);

                        // สร้าง Object
                        newProduct = new Notebook(productId, productName, productPrice, productQuantity, productMax, productMin, size, gsm, pages);
                    } else if (productType.equals("Report Paper")) {
                        String size = JOptionPane.showInputDialog(null, "Enter Paper Size (e.g., A4):");
                        if (size == null) return;

                        String gsmStr = JOptionPane.showInputDialog(null, "Enter Paper GSM (e.g., 70, 80):");
                        if (gsmStr == null) return;
                        int gsm = Integer.parseInt(gsmStr);

                        String sheetsStr = JOptionPane.showInputDialog(null, "Enter Number of Sheets:");
                        if (sheetsStr == null) return;
                        int sheets = Integer.parseInt(sheetsStr);

                        // สร้าง Object
                        newProduct = new ReportPaper(productId, productName, productPrice, productQuantity, productMax, productMin, size, gsm, sheets);
                    } else if (productType.equals("General Stationery")) {
                        String statType = JOptionPane.showInputDialog(null, "Enter Stationery Type (e.g., Ruler, Eraser):");
                        if (statType == null) return;

                        // สร้าง Object
                        newProduct = new GeneralStationery(productId, productName, productPrice, productQuantity, productMax, productMin, statType);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid number format in extra details. Please try again.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ถ้ามีข้อมูลใน newProduct ก็เพิ่มลง ArrayList
                if (newProduct != null) {
                    boolean isAdded = management.addProduct(newProduct);

                    if (isAdded) {
                        mainWindowForm.updateTable();

                        dispose();
                        addWindowForm.dispose();
                    }
                }
            }
        });
    }
}
