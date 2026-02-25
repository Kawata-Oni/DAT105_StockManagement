import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowForm {

    // attribute
    private JPanel main_form;
    private JPanel managementPanel;   // JPanel ของปุ่ม Management ทั้งหมด
    private JButton btnAdd;
    private JButton btnDecrease;
    private JButton btnEdit;
    private JButton btnIncrease;
    private JScrollPane basicInformation;   // JScrollPanel ของตาราง
    private JTable basicData;
    private JButton btnFullData;

    private JFrame frame;

    // การทำงานใน Main
    public MainWindowForm() {
        frame = new JFrame("Stock Management System");

        setupTable();

        if (main_form != null) {
            frame.setContentPane(main_form);
        } else {
            System.err.println("Error: main_form is null. Please check your GUI Designer binding.");
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null); // ให้อยู่กึ่งกลางหน้าจอ
        frame.setVisible(true);

        // ปุ่ม Add
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Click Add");
            }
        });

        // ปุ่ม increase
        btnIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Click Increase");
            }
        });

        // ปุ่ม decrease
        btnDecrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Click Decrease");
            }
        });

        // ปุ่ม edit
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Click Edit");
            }
        });

        // ปุ่ม full data
        btnFullData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Click Full Data");
            }
        });
    }

    // setup ค่าเริ่มต้นของตาราง basicData
    private void setupTable() {
        String[] columnNames = {"ID", "Name", "Price", "Quantity", "Max", "Min", "Status"}; // array ของ column ตาราง
        DefaultTableModel model = new DefaultTableModel(null, columnNames); // DefaultTableModel ใช้ตั้งค่าตาราง
        basicData.setModel(model);
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindowForm();
            }
        });
    }
}