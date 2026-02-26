import Product.Management;

import javax.swing.*;

public class AddWindowForm extends JFrame {
    private JButton btnConfirm;
    private JButton btnCancel;
    private JRadioButton choicePencil;
    private JRadioButton choicePen;
    private JRadioButton ChoiceNotebook;
    private JRadioButton ChoiceReportPaper;
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
    }
}
