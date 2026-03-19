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

    private MainWindowForm mainWindowForm;
    private Management management;

    public AddWindowForm(MainWindowForm mainWindowForm, Management management) {
        this.mainWindowForm = mainWindowForm;
        this.management = management;

        setTitle("Add Window");
        setContentPane(add_form);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ButtonGroup group = new ButtonGroup();
        group.add(choicePencil);
        group.add(choicePen);
        group.add(choiceNotebook);
        group.add(choiceReportPaper);
        group.add(choiceGeneral);

        btnCancel.addActionListener(e -> dispose());

        btnConfirm.addActionListener(e -> handleConfirm());
    }

    private void handleConfirm() {

        String selectedCategory = getSelectedCategory();

        if (selectedCategory == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select an item!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        InputBasicData inputForm =
                new InputBasicData(selectedCategory, mainWindowForm, management, this);

        inputForm.setVisible(true);

        setVisible(false);
    }

    private String getSelectedCategory() {
        if (choicePencil.isSelected()) return "Pencil";
        if (choicePen.isSelected()) return "Pen";
        if (choiceNotebook.isSelected()) return "Notebook";
        if (choiceReportPaper.isSelected()) return "Report Paper";
        if (choiceGeneral.isSelected()) return "General Stationery";
        return null;
    }
}
