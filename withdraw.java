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
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class withdraw {
	public static int amount;
	private static JTextField tf;
	private static JFrame frame;
	private static JTextField tf1;
	private static JRadioButton savingsRadio,checkingRadio;
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new withdraw();
        });
    }

    public static void	 withdraw() {
		
		JFrame frame = new JFrame("Withdraw Form");
        frame.setSize(600, 450);
        
      //  tf = new JTextField();
        JLabel l1, l2, l3; // l3 for ATM PIN label
       // l1 = new JLabel("Enter Last 4 Digits of Card");
        
        l3 = new JLabel("Enter Amount"); // ATM PIN label
        l2 = new JLabel("Select Account Type");
       // tf.setBounds(180, 40, 100, 25);
       // l1.setBounds(20, 40, 160, 20);
        l3.setBounds(20, 80, 120, 20);
        l2.setBounds(20, 120, 120, 20); // Position the ATM PIN label

        savingsRadio = new JRadioButton("Savings");
        checkingRadio = new JRadioButton("Current");

        savingsRadio.setBounds(20, 160, 100, 20);
        checkingRadio.setBounds(120, 160, 100, 20);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(savingsRadio);
        accountTypeGroup.add(checkingRadio);

        tf1 = new JTextField(); 
        tf1.setBounds(180, 80, 100, 25); 

        JButton b = new JButton("Withdraw");
        b.setBounds(470, 220, 120, 40);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
             with();
             frame.dispose();
            }
        });
        frame.getContentPane().setBackground(new Color(0, 121, 120));
    
        frame.add(l3); 
        frame.add(tf1); 
        frame.add(l2);
        frame.add(savingsRadio);
        frame.add(checkingRadio);
        b.setBackground(new Color(0, 92, 95));
        b.setForeground(Color.WHITE);
        savingsRadio.setBackground(new Color(0, 92, 95));
        checkingRadio.setBackground(new Color(0, 92, 95));
        savingsRadio.setForeground(Color.WHITE);
        checkingRadio.setForeground(Color.WHITE);
       
        l2.setBackground(new Color(0, 92, 95));
        l2.setForeground(Color.WHITE);
        l3.setBackground(new Color(0, 92, 95));
        l3.setForeground(Color.WHITE);
        
       
        frame.add(b);
        frame.setLayout(null); // Disable layout manager
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocation(400,150);
	}
	public static void with() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "sam";
        String password = "satyam";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        //    int cardno = Integer.parseInt(tf.getText());
            int withdrawAmount = Integer.parseInt(tf1.getText());

            String query = "SELECT type, cardno,balance FROM accounts WHERE cardno = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, pin.cardData);
              
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String retrievedAccountType = resultSet.getString("type");
                    int card = resultSet.getInt("cardno");
                    int amount = resultSet.getInt("balance");

                    String selectedAccountType = null;
                    if (savingsRadio.isSelected()) {
                        selectedAccountType = "savings";
                    } else if (checkingRadio.isSelected()) {
                        selectedAccountType = "current";
                    }
                    
                    if(withdrawAmount> 500 &&  withdrawAmount%100==0) { 
                    	if(withdrawAmount <= amount) {
                    		
                    	
                    if (retrievedAccountType.equals(selectedAccountType) && card == pin.cardData) {
                    	String updateSql = "UPDATE accounts SET balance = balance - ? WHERE cardno = ?";
                    	try (PreparedStatement preparedStatement1 = connection.prepareStatement(updateSql)) {
                    	    preparedStatement1.setInt(1, withdrawAmount);
                    	    preparedStatement1.setInt(2, pin.cardData);
                    	    int rowsAffected = preparedStatement1.executeUpdate();

                    	    if (rowsAffected > 0) {
                    	    	
                    	    	
                    	        JOptionPane.showMessageDialog(null, "Collect your cash " + withdrawAmount + " thank you", "Thank You!", JOptionPane.INFORMATION_MESSAGE);
                    	       
                    	        
                    	       
                    	    } else {
                    	        System.out.println("Failed to deposit money.");
                    	        JOptionPane.showMessageDialog(null, "Failed to deposit money. Please try again.", "Deposit Failed", JOptionPane.ERROR_MESSAGE);
                    	    }
                    	}
                    	 catch (SQLException ex) {
                    		    ex.printStackTrace();
                    		    
                    		   
                    		}
                    	}
                    else {
                        JOptionPane.showMessageDialog(null, "Account type or card number mismatch.", "Account Mismatch", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                	 JOptionPane.showMessageDialog(null,"Insufficent balance","error",JOptionPane.ERROR_MESSAGE);
                	
                }
                       }else {
                    JOptionPane.showMessageDialog(null, "Amount must be greater than 500", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Card Number is invalid!", "Account Not Found", JOptionPane.ERROR_MESSAGE);
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
