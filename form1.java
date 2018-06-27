import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class form1 implements ActionListener 
{
	JFrame f=new JFrame();
	JLabel l2,l1;
	JTextField u;
	JPasswordField p;
	JButton b;
	String u1 ;

	String p1;
	
	form1()
	{
		l1=new JLabel("SOCIAL ESTABLISHMENT SYSTEM");
		l1.setBounds(90, 20, 500, 40);
		l2=new JLabel("Welcome to the Social Establishment System");
		l2.setBounds(60,55,500,40);
		u=new JTextField();
		u.setBounds(100,140,160,30);
		p=new JPasswordField();
		p.setBounds(100,180,160,30);
		b=new JButton("LOGIN");
		b.setBounds(130,240,90, 40);
		
		f.add(l1);
		 f.add(l2);
		 f.add(u);
		 f.add(p);
		 f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 f.getContentPane().add(b);
			b.addActionListener(this);
     		f.setSize(400,500);
		//f.getContentPane().setBackground(Color.#FFFFFF);
		f.setLayout(null);
		f.setVisible(true); 
		}  
	
	
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		 u1=u.getText();
		 
		 p1=gp();
		 
		 if(u1.equals("admin")&& p1.equals("admin"))
			{
			 form2 oc=new form2();
			f.dispose();
			}
		 else if(u1!=u.getText())
		 {
			 JOptionPane.showMessageDialog(null,"oops wrong username or password" );
		 }
	}
	
	public String gp()
	{
		return new String(p.getPassword()); 
	}
	
	public static void main(String args[])
	{
		form1 ob=new form1();
		
	}

}


