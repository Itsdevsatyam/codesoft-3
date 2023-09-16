package demo;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class atm {
	public static JFrame frame;
	private static  JButton b,b1,b2,b3,b4,b5;
    public static void atm() {
        JFrame frame = new JFrame("ATM Buttons");
        frame.setLocation(400,150);
        
       
        b = new JButton("Deposit");
        b1 = new JButton("Change Pin");
        b2 = new JButton("Check Balance");
        b3 = new JButton("Account Info");
        b4 = new JButton("Help");
        b5 = new JButton("Withdraw");
        b1.setBounds(0, 50, 130, 40);
        b2.setBounds(0, 120, 130, 40);
        b3.setBounds(0, 210, 130, 40);
        b4.setBounds(0, 290, 130, 40);
        b.setBounds(460, 50, 130, 40);
        b5.setBounds(460, 120, 130, 40);
       

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        b1.setFont(buttonFont);
        b2.setFont(buttonFont);
        b3.setFont(buttonFont);
        b.setFont(buttonFont);
        b5.setFont(buttonFont);
        b4.setFont(buttonFont);

        b1.setBackground(new Color(0, 92, 95));
        b2.setBackground(new Color(0, 92, 95));
        b3.setBackground(new Color(0, 92, 95));
        b.setBackground(new Color(0, 92, 95));
        b5.setBackground(new Color(0, 92, 95));
        b4.setBackground(new Color(0, 92, 95));
        

        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        b.setForeground(Color.WHITE);
        b5.setForeground(Color.WHITE);
        b4.setForeground(Color.WHITE);
        
        frame.getContentPane().setBackground(new Color(0, 121, 120));
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b);
        frame.add(b5);
        frame.add(b4);

        frame.setSize(600, 450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
       
        
        
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the deposit method
                deposit.deposit();
            }
        });
        
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                change.change();
            }
        });

       

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the checkBalance method
                balance.balance();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the accountInfo method
                account.account();
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the help method
                help.help();
            }
        });
        
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the help method
                withdraw.withdraw();
            }
        });

        
    }
       public static void main(String[]args) {
    	   javax.swing.SwingUtilities.invokeLater(() -> {
    	   new atm();
    	   });
       }
       
    }
    

    
