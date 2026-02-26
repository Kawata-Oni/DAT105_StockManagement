import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBasicData extends JFrame {
    private JPanel editBasicData;
    private JButton btnConfirm;
    private JButton btnCancel;
    private JTextField typeName;
    private JTextField typePrice;
    private JTextField typeMax;
    private JTextField typeMin;

    public EditBasicData() {
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
    }
}
