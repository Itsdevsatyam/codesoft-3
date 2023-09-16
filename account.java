package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class account {
    private static JLabel l, l1, l2, l3, l4, l6;
    private static JButton b;
    public static JFrame f;
    public static int amount;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
           new account();
        });
    }

  public static void  account() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "sam";
        String password = "satyam";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            int cardno=  pin.cardData; // Default or predefined account number
            String query = "SELECT * FROM accounts WHERE cardno = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cardno);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String bankName = resultSet.getString("bankname");
                        String branch = resultSet.getString("branch");
                        String ifsc = resultSet.getString("ifsc");
                        int accountNo = resultSet.getInt("accno");
                         amount = resultSet.getInt("balance");
                        String type = resultSet.getString("type");

                        JFrame f = new JFrame();
                        
                        f.setSize(600, 450);
                        b = new JButton("Back");
                        
                        Font font = new Font("Arial", Font.PLAIN, 20);

                        // Create JLabels and set their text...
                        l = new JLabel("Bank name: " + bankName);
                        l1 = new JLabel("Branch: " + branch);
                        l2 = new JLabel("IFSC: " + ifsc);
                        l3 = new JLabel("AccountNo" +accountNo);
                        l4 = new JLabel("Balance: " + amount);
                        l6 = new JLabel("Type: " + type);
                        b = new JButton("Back");
                        l.setBounds(20, 40, 200, 20);
                        l1.setBounds(20, 70, 200, 20);
                        l2.setBounds(20, 100, 200, 20);
                        l3.setBounds(20, 130, 200, 20);
                        l4.setBounds(20, 160, 200, 20);
                        l6.setBounds(20, 190, 200, 20);
                        b.setBounds(0, 290, 100, 30);
                        b.setBackground(new Color(0, 92, 95));
                        b.setForeground(Color.WHITE);
                        
                        f.add(l);
                        f.add(l1);
                        f.add(l2);
                        f.add(l3);
                        f.add(l4);
                        f.add(l6);
                        f.add(b);
                        
                        f.getContentPane().setBackground(new Color(0, 121, 120));
                        f.setLayout(null);
                        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        f.setVisible(true);
                        f.setLocation(400, 150);
                        b.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                f.dispose(); // Close the frame
                            }
                        });
                    } else {
                        System.out.println("Account not found");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
