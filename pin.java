package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class pin {
    private static JFrame frame;
    private static JButton b1, b2, b3;
    private static JPasswordField tf;
    private static JTextField tf1;
    public static int enteredPin;
    public static int pinFromDatabase;
    public static int cardData;
    
    
    
   public static JLabel l;
public static JLabel l1;

   pin() {
        frame = new JFrame("ATM Buttons");
        frame.setLocation(400, 150);

        b1 = new JButton("Enter");
        b2 = new JButton("Forget Pin");
        b3 = new JButton("Exit");
        tf = new JPasswordField();
        tf1 = new JTextField();
        l=new JLabel("Enter Your  Atm Pin : ");
        l1=new JLabel("Enter Your Card No : ");

        b1.setFont(new Font("Arial", Font.PLAIN, 14));
        b2.setFont(new Font("Arial", Font.PLAIN, 14));
        b3.setFont(new Font("Arial", Font.PLAIN, 14));

        b1.setBackground(new Color(0, 92, 95));
        b2.setBackground(new Color(0, 92, 95));
        b3.setBackground(new Color(0, 92, 95));
        l.setForeground(Color.WHITE);
        l1.setForeground(Color.WHITE);
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        l.setBounds(10, 60, 120, 30);
        l1.setBounds(10, 20, 120, 30);
        b2.setBounds(0, 100, 120, 30);
        b1.setBounds(470, 100, 120, 30);
        b3.setBounds(0, 170, 120, 30);
        tf.setBounds(200, 60, 100, 25);
        tf1.setBounds(200, 20, 100, 25);

        frame.getContentPane().setBackground(new Color(0, 121, 120));
        frame.add(tf1);
        frame.add(l);
        frame.add(l1);
        frame.add(tf);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);

        frame.setSize(600, 450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forget.forget();
               
            }
        });

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verifyPin();
            }
        });
    }

    public static void verifyPin() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "sam";
        String password = "satyam";

        char[] passwordChars = tf.getPassword();
        String password1 = new String(passwordChars);
        String cardno=tf1.getText();
        int enteredPin = Integer.parseInt(password1);
        int card=Integer.parseInt(cardno);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT * FROM accounts WHERE pin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, enteredPin);
           
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                     int pinFromDatabase = resultSet.getInt("pin");
                        cardData = resultSet.getInt("cardno");
                        
                       
                         
                        if (pinFromDatabase == enteredPin) {
                        	if(cardData==card) {
                            JOptionPane.showMessageDialog(null, "PIN verified.");
                          
                           
                            System.out.println(pin.cardData);
                            atm.atm();
                            
                        	}
                        	else {
                        		 JOptionPane.showMessageDialog(null, "Card No is Incorrect ");
                        	}
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, " Incorrect PIN .", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

      
    }

    public static void main(String[] args) {
        
        	
            new pin();
       
    }
}