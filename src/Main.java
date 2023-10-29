import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main extends JFrame implements ActionListener {

    private JLabel userLabel, passLabel;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;


    private Connection con;
    private Statement st;
    private ResultSet rs;

    
    Main() {
        
        userLabel = new JLabel("username:");
        passLabel = new JLabel("password:");
        userField = new JTextField(20);
        passField = new JPasswordField(20);
        loginButton = new JButton("login");

        loginButton.addActionListener(this);

        setLayout(new FlowLayout());

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);

        setTitle("login");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        connect();
    }


    public void connect() {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            // this the please for  database information
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");


            st = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void actionPerformed(ActionEvent ae) {
        try {

            String username = userField.getText();
            String password = new String(passField.getPassword());


            String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            rs = st.executeQuery(query);


            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "good ");
                new EmptyFrame();
                this.dispose();
            } else {

                JOptionPane.showMessageDialog(this, "password or username not true");
                userField.setText("");
                passField.setText("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

class EmptyFrame extends JFrame {
    EmptyFrame() {
        setTitle("new page");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}