import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
public class form2 implements ActionListener	
{
	JFrame f2=new JFrame();
	JLabel j1;
	JButton b;
	JRadioButton r1,r2;
	
	form2()
	{
		j1=new JLabel("OPTIONS");
		j1.setBounds(160, 20, 500, 40);
		j1.setFont(new Font("Serif", Font.BOLD, 30));
		
		r1=new JRadioButton("Calculation");
		r1.setBounds(90,120,500,40);
		r1.setFont(new Font("Arial",Font.PLAIN, 20));
		r2=new JRadioButton("Show");
		r2.setBounds(90,160,500,40);
		r2.setFont(new Font("Arial",Font.PLAIN, 20));
		ButtonGroup bg=new ButtonGroup(); 
	
		bg.add(r1);
		bg.add(r2);    
		b=new JButton("Submit");    
		b.setBounds(90,220,100,30);    
		b.addActionListener(this);    
		f2.add(j1);
		f2.add(r1);
		f2.add(r2);
		
		f2.add(b);
		f2.setSize(400,500);
		f2.setLayout(null);
		f2.setVisible(true); 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(r1.isSelected())
			{    
				form3 f3=new form3();
				f2.dispose();
			 
			}    
			if(r2.isSelected())
			{    
			   form4 f4=new form4();
			   f2.dispose();
			}    
		
	}
}
	

