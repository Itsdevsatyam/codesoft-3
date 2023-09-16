package demo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class deposit {
	public static  int amount;
	private static JTextField tf;
	private static JTextField tf1;
	static JRadioButton savingsRadio = new JRadioButton("Savings");
    static JRadioButton checkingRadio = new JRadioButton("Current");
    public static void  deposit() {
        JFrame frame = new JFrame("Deposit Form");
        frame.setSize(600, 450);
        frame.setLocation(400,150);
        
       
       
        JLabel l1, l2,l3;
        tf=new JTextField();
        tf1=new JTextField();
        l1 = new JLabel("Enter Amount");
        
        l2 = new JLabel("Select Account Type");
        l3 =new JLabel("Enter Account No");
        tf.setBounds(180, 60, 100, 25);
        tf1.setBounds(180, 100, 100, 25);
        
        l1.setBounds(20, 60, 120, 20);
        l3.setBounds(20,100,120,20);
        l2.setBounds(20, 150, 120, 20);

        

        savingsRadio.setBounds(20, 180, 100, 20);
        checkingRadio.setBounds(120, 180, 100, 20);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(savingsRadio);
        accountTypeGroup.add(checkingRadio);

       JButton b= new JButton("Deposit");
       b.setBounds(470, 230, 120, 40);
       b.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               // Call the deposit method
            depo();
            frame.dispose();
           }
       });
       JButton b1= new JButton("Back");
       b1.setBounds(0, 230, 120, 40);
       b1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               
            frame.dispose();
           }
       });
       frame.getContentPane().setBackground(new Color(0, 121, 120));
       savingsRadio.setBackground(new Color(0, 92, 95));
       savingsRadio.setForeground(Color.WHITE);
       checkingRadio.setBackground(new Color(0, 92, 95));
       checkingRadio.setForeground(Color.WHITE);
       b.setBackground(new Color(0, 92, 95));
       b.setForeground(Color.WHITE);
       b1.setBackground(new Color(0, 92, 95));
       b1.setForeground(Color.WHITE);
       l1.setBackground(new Color(0, 92, 95));
       l1.setForeground(Color.WHITE);
       l2.setBackground(new Color(0, 92, 95));
       l2.setForeground(Color.WHITE);
       l3.setBackground(new Color(0, 92, 95));
       l3.setForeground(Color.WHITE);
        frame.add(l1);
        frame.add(tf);
        frame.add(l3);
        frame.add(tf1);
        frame.add(l2);
        frame.add(savingsRadio);
        frame.add(checkingRadio);
        frame.add(b);
        frame.add(b1);
        frame.setLayout(null); // Disable layout manager
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new deposit();
        });
    }
	 public static void depo() {
	        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
	        String username = "sam";
	        String password = "satyam";

	        try {
	            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

	            
	            int accno = Integer.parseInt(tf1.getText());
	            int depositAmount = Integer.parseInt(tf.getText());

	            System.out.println("Connected to the database!");

	            String query = "SELECT type, accno,balance FROM accounts WHERE accno = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, accno);
	                ResultSet resultSet = preparedStatement.executeQuery();

	                if (resultSet.next()) {
	                    String retrievedAccountType = resultSet.getString("type");
	                    int retrievedAccountNumber = resultSet.getInt("accno");
	                    int amount = resultSet.getInt("balance");

	                    String selectedAccountType = null;
	                    if (savingsRadio.isSelected()) {
	                        selectedAccountType = "savings";
	                    } else if (checkingRadio.isSelected()) {
	                        selectedAccountType = "current";
	                    }

	                    if (retrievedAccountType.equals(selectedAccountType) && retrievedAccountNumber == accno) {
	                        String updateSql = "UPDATE accounts SET balance = balance + ? WHERE accno = ?";
	                        try (PreparedStatement preparedStatement1 = connection.prepareStatement(updateSql)) {
	                            preparedStatement1.setInt(1, depositAmount);
	                            preparedStatement1.setInt(2, accno);
	                            int rowsAffected = preparedStatement1.executeUpdate();

	                            if (rowsAffected > 0) {
	                                System.out.println("Money deposited successfully!");
	                            } else {
	                                System.out.println("Failed to deposit money.");
	                            }
	                        } catch (SQLException ex) {
	                            ex.printStackTrace();
	                        }
	                        JOptionPane.showMessageDialog(null, "Money Deposited in AccNo: " + accno + " thank you", "Thank You!", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Sorry, account type or account number mismatch.", "Account Mismatch", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "Account number not found in the database.", "Account Not Found", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            connection.commit();
	            connection.close();
	            System.out.println("Connection closed.");
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }	
	
}
