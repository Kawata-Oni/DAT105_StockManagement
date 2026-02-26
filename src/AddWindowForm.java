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


        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!choicePencil.isSelected() && !choicePen.isSelected() &&
                        !choiceNotebook.isSelected() && !choiceReportPaper.isSelected() &&
                        !choiceGeneral.isSelected()) {

                    JOptionPane.showMessageDialog(null, "Please select an item!");
                    return;
                }
            }
        });
    }
}
