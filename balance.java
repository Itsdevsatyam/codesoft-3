package demo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class balance {
	private static pin p1;
	public static void balance() {
        JFrame jf = new JFrame();
        jf.setSize(600, 450);
        jf.setLocation(400,150);
        JButton b=new JButton("Check Balance");
        JButton b2=new JButton("Exit");
        JButton b3=new JButton("Last 5 Transaction");
        JButton b4=new JButton("Home");
        JComboBox<String> accountChoice = new JComboBox<String>();
        accountChoice.addItem("Saving");
        accountChoice.addItem("Current");
        accountChoice.addItem("FD/RD");
        
        JLabel l1; // Removed l2 and l3 since they are now in the choice
        l1 = new JLabel("Select the Account :");

        accountChoice.setBounds(180, 40, 100, 25);
        l1.setBounds(20, 50, 160, 20);
        b.setBounds(0, 140, 160, 35);
        b2.setBounds(00, 240, 160, 35);
        b3.setBounds(440, 140, 160, 35);
        b4.setBounds(440, 240, 160, 35);
        b.setBackground(new Color(0, 92, 95));
        b2.setBackground(new Color(0, 92, 95));
        b3.setBackground(new Color(0, 92, 95));
        b4.setBackground(new Color(0, 92, 95));
        l1.setForeground(Color.WHITE);
        
        b.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        b4.setForeground(Color.WHITE);
        

        jf.add(accountChoice);
        jf.add(l1);
        jf.add(b);
        jf.add(b2);
        jf.add(b3);
        jf.add(b4);
        jf.getContentPane().setBackground(new Color(0, 121, 120));
        jf.setLayout(null); // Disable layout manager
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jf.setVisible(true);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
             
               new pin();
            }
        });
    
	 b4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           jf.dispose();
           atm.atm();
         }
     });
	
	 b.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 System.out.println(account.amount);
        	 JOptionPane.showMessageDialog(jf, "Your Balance is  :" +  account.amount);
         }
     });
	}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
        	
         
        	new balance();
            
        });
    }
}
