package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class change {
	 private static JLabel l, l1, l2, l3, l4, l6;
	   
		private static JPasswordField tf,tf2,tf3;
	    private static JButton b,b2;
	    public static JFrame f;
public static void change(){
	
	   f = new JFrame();
       f.setSize(600, 450);
       f.setLayout(null);
       Font font = new Font("Arial", Font.PLAIN, 20);
      
       
                       l = new JLabel("Enter Old Pin");
                       l1 = new JLabel("Enter New Pin");
                       l2 = new JLabel("Confrim New Pin");
                       tf = new JPasswordField();
                       tf2 = new JPasswordField();
                       tf3 = new JPasswordField();
                       b=new  JButton("Change");
                       b2=new  JButton("Back");
                       
                    		  
                       
                       l.setBounds(30,50,120,35);
                       tf.setBounds(160,50,120,30);
                       
                       l1.setBounds(30,100,120,35);
                       tf2.setBounds(160,100,120,30);
                       l2.setBounds(30,150,120,35);
                       tf3.setBounds(160,150,120,30);
                       b.setBounds(480,290,120,30);
                       b2.setBounds(-10,290,120,30);
                       
                       f.add(l);
                       f.add(l1);
                       f.add(l2);
                       f.add(tf);
                       f.add(tf2);
                       f.add(tf3);
                       f.add(b);
                       f.add(b2);
                       
                       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                       f.setVisible(true);
                       f.setLayout(null);
                       f.setLocation(400, 150);
                       f.getContentPane().setBackground(new Color(0, 121, 120));
                       b2.addActionListener(new ActionListener() {
                           public void actionPerformed(ActionEvent e) {
                               f.dispose();
                           }
                       });
                       b.addActionListener(new ActionListener() {
                           public void actionPerformed(ActionEvent e) {
                               change1();
                           }
                       });
                        
                     
                       
	
}
public static void change1() {
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
    String username = "sam";
    String password = "satyam";

    
    char[] oldfirst = tf.getPassword();
    String changefirst = new String(oldfirst);
    char[] new1 = tf2.getPassword();
    String change = new String(new1);
    char[] new2 = tf3.getPassword();
    String change1 = new String(new2);
    
  
    int cno1= Integer.parseInt(changefirst);
    
    int newfirst=Integer.parseInt(change);
    int newsecond=Integer.parseInt(change1);
    
    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
        String query = "select * from accounts  WHERE pin = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cno1);
       
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int pinFromDatabase = resultSet.getInt("pin");

                     
                    if (pinFromDatabase == cno1) {
                    	if(newfirst==newsecond) {
                    		String updateSql = "UPDATE accounts SET pin =? WHERE pin = ?";
                        	try (PreparedStatement preparedStatement1 = connection.prepareStatement(updateSql)) {
                        	    preparedStatement1.setInt(1, newfirst);
                        	    preparedStatement1.setInt(2, cno1);
                        	    preparedStatement1.executeUpdate();
                        	  
                        JOptionPane.showMessageDialog(null, "PIN Changed Successfully.");
                      connection.commit();
                        f.dispose();
                       
                     ;
                        	}
                    	}
                    	else {
                    		 JOptionPane.showMessageDialog(null, "New Pin Not Matched ");
                    	}
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, " Incorrect PIN .", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        connection.commit();
    }
    catch (SQLException ex) {
        ex.printStackTrace();
    }


}

	public static void main(String[] args) {
		
new change();
	}

}
