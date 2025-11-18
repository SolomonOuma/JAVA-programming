import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame {

    JTextField txtID, txtName, txtAddress, txtContact;
    JRadioButton maleBtn, femaleBtn;
    JButton btnRegister, btnExit;
    JTable table;
    DefaultTableModel model;

    Connection conn;

    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        connectDatabase();
        initUI();
        loadTableData();
    }

    // ------------------------------------------------------
    // DATABASE CONNECTION
    // ------------------------------------------------------
    void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/registration_db",
                    "root",
                    ""       // your MySQL password here
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------
    // USER INTERFACE
    // ------------------------------------------------------
    void initUI() {

        JPanel leftPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Registration Form"));

        txtID = new JTextField();
        txtName = new JTextField();
        txtAddress = new JTextField();
        txtContact = new JTextField();

        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleBtn);
        bg.add(femaleBtn);

        btnRegister = new JButton("Register");
        btnExit = new JButton("Exit");

        leftPanel.add(new JLabel("ID"));
        leftPanel.add(txtID);

        leftPanel.add(new JLabel("Name"));
        leftPanel.add(txtName);

        leftPanel.add(new JLabel("Gender"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleBtn);
        genderPanel.add(femaleBtn);
        leftPanel.add(genderPanel);

        leftPanel.add(new JLabel("Address"));
        leftPanel.add(txtAddress);

        leftPanel.add(new JLabel("Contact"));
        leftPanel.add(txtContact);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnExit);
        btnPanel.add(btnRegister);
        leftPanel.add(btnPanel);

        // TABLE
        model = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Address", "Contact"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // layout
        setLayout(new GridLayout(1, 2));
        add(leftPanel);
        add(scroll);

        // BUTTON EVENTS
        btnRegister.addActionListener(e -> registerStudent());
        btnExit.addActionListener(e -> System.exit(0));
    }

    // ------------------------------------------------------
    // INSERT DATA
    // ------------------------------------------------------
    void registerStudent() {
        try {
            String gender = maleBtn.isSelected() ? "Male" : "Female";

            PreparedStatement pst = conn.prepareStatement(
                    "INSERT INTO students VALUES (?, ?, ?, ?, ?)"
            );

            pst.setInt(1, Integer.parseInt(txtID.getText()));
            pst.setString(2, txtName.getText());
            pst.setString(3, gender);
            pst.setString(4, txtAddress.getText());
            pst.setString(5, txtContact.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student Registered Successfully!");

            loadTableData();
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------
    // LOAD DATA INTO TABLE
    // ------------------------------------------------------
    void loadTableData() {
        try {
            model.setRowCount(0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("contact")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Load Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------
    // CLEAR FORM
    // ------------------------------------------------------
    void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        maleBtn.setSelected(false);
        femaleBtn.setSelected(false);
    }

    // ------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
