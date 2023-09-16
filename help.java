package demo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class help {
	private static JButton b2 ;
   private static  JButton b3;
	 
    public static void help() {
    	

        JFrame frame = new JFrame("Help");
        frame.setSize(600, 450);
        frame.setLocation(400,150);
        b2 = new JButton("Forget Pin");
        b3 = new JButton("Exit");
        JLabel l1, l2, l3, l4, l5, l6; 
        l1 = new JLabel("How to Change the Pin ? click here!");
        l2 = new JLabel("How to deposit money from the ATM? Click on the button below.");
        l3 = new JLabel("How to withdraw money from the ATM? Click on the button below.");
        l4 = new JLabel("Contact:");
        l5 = new JLabel("Phone: +1234567890");
        l6 = new JLabel("Email: info@example.com | Website: www.example.com");

        l1.setBounds(20, 40, 390, 20);
        l2.setBounds(20, 110, 390, 20);
        l3.setBounds(20, 190, 390, 20);
        l4.setBounds(20, 270, 100, 20);
        l5.setBounds(20, 300, 200, 20);
        l6.setBounds(20, 320, 350, 20);
        JButton button1 = new JButton("Change Pin");
        JButton button2 = new JButton("Deposit");
        JButton button3 = new JButton("Withdraw");

        frame.add(l1);
        frame.add(button1);
        frame.add(l2);
        frame.add(button2);
        frame.add(l3);
        frame.add(button3);
        frame.add(b2);
        frame.add(b3);

        frame.add(l4);
        frame.add(l5);
        frame.add(l6);
        button1.setBackground(new Color(0, 92, 95));
        button2.setBackground(new Color(0, 92, 95));
        button3.setBackground(new Color(0, 92, 95));
        button1.setForeground(Color.WHITE);
        button2.setForeground(Color.WHITE);
        button3.setForeground(Color.WHITE);
        button3.setBackground(new Color(0, 92, 95));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(0, 92, 95));
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(0, 92, 95));

        button1.setBounds(00,70, 120, 30);
        button2.setBounds(0, 140, 120, 30);
        button3.setBounds(0, 220, 120, 30);
        b2.setBounds(0, 140, 120, 30);
        b3.setBounds(450, 220, 120, 30);

        
       
        frame.getContentPane().setBackground(new Color(0, 121, 120));
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new pin();
            }
        });
       
        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(frame, "click on change pin\n Enter old pin \n enter new pin \n confirm new pin \n click on change button", "Deposit Message", JOptionPane.INFORMATION_MESSAGE);
    	       
    	    }
    	});

        button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	JOptionPane.showMessageDialog(frame, "click on deposit button\n Enter the account number \n select type \n click on deposit", "Deposit Message", JOptionPane.INFORMATION_MESSAGE);
	       
	    }
	});
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	       
    	        JOptionPane.showMessageDialog(frame, "click on withdraw button\n Enter the card no \n enter the amount \n select the type \n click on withdraw button", "Deposit Message", JOptionPane.INFORMATION_MESSAGE);
    	    }
    	});
    }
    

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new help();
        });
    }
}