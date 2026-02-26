import Product.Management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindowForm extends JFrame {
    private JButton btnConfirm;
    private JButton btnCancel;
    private JRadioButton choicePencil;
    private JRadioButton choicePen;
    private JRadioButton choiceNotebook;
    private JRadioButton choiceReportPaper;
    private JRadioButton choiceGeneral;
    private JPanel add_form;

    // attribute
    private MainWindowForm mainWindow;
    private Management management;

    // constructor + การทำงานใน Add
    public AddWindowForm(MainWindowForm mainWindow, Management management) {
        // เพื่อให้รู้ว่าหน้าต่างหลักคืออันไหน และเชื่อมกับ ArrayList ของ management
        this.mainWindow = mainWindow;
        this.management = management;

        // setup window
        setTitle("Add Window");
        setContentPane(add_form);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // group JRadioButton
        ButtonGroup group = new ButtonGroup();
        group.add(choicePencil);
        group.add(choicePen);
        group.add(choiceNotebook);
        group.add(choiceReportPaper);
        group.add(choiceGeneral);

        // ปุ่ม cancel
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // ปุ่ม confirm
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // เช็คว่าได้เลือกมั้ย
                if (!choicePencil.isSelected() && !choicePen.isSelected() &&
                        !choiceNotebook.isSelected() && !choiceReportPaper.isSelected() &&
                        !choiceGeneral.isSelected()) {

                    JOptionPane.showMessageDialog(null, "Please select an item!");
                    return;
                }

                // เช็คว่าเลือกอะไร แล้วสร้าง obj ของสินค้าด้วย class นั้นๆ
                // ส่ง String ของ category ที่เลือกเข้าไปเพื่อให้หน้าต่าง input รู้ว่าเลือกอะไรไป
                if (choicePencil.isSelected()) {
                    InputBasicData inputForm = new InputBasicData("Pencil", mainWindow, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choicePen.isSelected()) {
                    InputBasicData inputForm = new InputBasicData("Pen", mainWindow, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choiceNotebook.isSelected()) {
                    InputBasicData inputForm = new InputBasicData("Notebook", mainWindow, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choiceReportPaper.isSelected()) {
                    InputBasicData inputForm = new InputBasicData("Report Paper", mainWindow, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                } else if (choiceGeneral.isSelected()) {
                    InputBasicData inputForm = new InputBasicData("General Stationery", mainWindow, management, AddWindowForm.this);
                    inputForm.setVisible(true);

                }
                dispose();
            }
        });
    }
}
