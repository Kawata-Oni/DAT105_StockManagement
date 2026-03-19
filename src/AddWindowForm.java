import Product.Management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindowForm extends JFrame {

    // attribute
    private JButton btnConfirm;
    private JButton btnCancel;
    private JRadioButton choicePencil;
    private JRadioButton choicePen;
    private JRadioButton choiceNotebook;
    private JRadioButton choiceReportPaper;
    private JRadioButton choiceGeneral;
    private JPanel add_form;

    private MainWindowForm mainWindowForm;
    private Management management;

    // constructor
    public AddWindowForm(MainWindowForm mainWindowForm, Management management) {

        // รับค่าจากหน้าหลัก
        this.mainWindowForm = mainWindowForm;
        this.management = management;

        // ตั้งค่าหน้าต่าง
        setTitle("Add Window");
        setContentPane(add_form);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // ให้อยู่กลางจอ

        // ================= GROUP RADIO BUTTON =================
        ButtonGroup group = new ButtonGroup();
        group.add(choicePencil);
        group.add(choicePen);
        group.add(choiceNotebook);
        group.add(choiceReportPaper);
        group.add(choiceGeneral);

        // ================= ปุ่ม Cancel =================
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // ปิดหน้าต่าง
            }
        });

        // ================= ปุ่ม Confirm =================
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 🔥 เช็คว่าผู้ใช้เลือกประเภทสินค้าหรือยัง
                if (!choicePencil.isSelected() &&
                    !choicePen.isSelected() &&
                    !choiceNotebook.isSelected() &&
                    !choiceReportPaper.isSelected() &&
                    !choiceGeneral.isSelected()) {

                    JOptionPane.showMessageDialog(null,
                            "Please select an item!",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 🔥 เปิดหน้ากรอกข้อมูลตามประเภทที่เลือก
                if (choicePencil.isSelected()) {

                    InputBasicData inputForm =
                            new InputBasicData("Pencil", mainWindowForm, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choicePen.isSelected()) {

                    InputBasicData inputForm =
                            new InputBasicData("Pen", mainWindowForm, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choiceNotebook.isSelected()) {

                    InputBasicData inputForm =
                            new InputBasicData("Notebook", mainWindowForm, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choiceReportPaper.isSelected()) {

                    InputBasicData inputForm =
                            new InputBasicData("Report Paper", mainWindowForm, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choiceGeneral.isSelected()) {

                    InputBasicData inputForm =
                            new InputBasicData("General Stationery", mainWindowForm, management, AddWindowForm.this);
                    inputForm.setVisible(true);
                }

                // ปิดหน้าปัจจุบันหลังเลือกประเภทแล้ว
                dispose();
            }
        });
    }
}
