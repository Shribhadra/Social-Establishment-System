import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class form3 implements ActionListener

{
	JFrame j3=new JFrame();
	String sql;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	JLabel s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
	JRadioButton r1,r2,r3,r4;
	JRadioButton op1,op2,op3;
	JButton b1;
	JButton b2;
	Statement stm;
	ResultSet res,res1,res2,res3,res4,res5;
	Connection con;
	//JComboBox<String>options=new JComboBox();
	form3()
	{
		l1=new JLabel("CALCULATIONS");
		l1.setBounds(100, 20, 500, 40);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		
		r1=new JRadioButton("ACCOUNTS");
		r1.setBounds(5,80,90,30);
		
		r2=new JRadioButton("CONNECTIONS");
		r2.setBounds(100,80,110,30);
		
		r3=new JRadioButton("POSTS");
		r3.setBounds(210,80,90,30);
		
		b1=new JButton("OK");
		b1.setBounds(300,80,60,30);
		
		b2=new JButton("Back");
		b2.setBounds(20,400,80,30);
		
		b1.addActionListener(this);
		
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		bg.add(r3);
		j3.add(l1);
		j3.add(r1);
		j3.add(r2);
		j3.add(r3);
		j3.add(b1);
		j3.add(b2);
		j3.setSize(400,500);
		j3.setLayout(null);
		j3.setVisible(true); 
	}
	
	@Override
	public void actionPerformed(ActionEvent n) 
	{
		if(r1.isSelected())
		{
			JComboBox cb=new JComboBox<String>();
			cb.setBounds(5, 120,160, 20);
			cb.addItem("Facebook");
			cb.addItem("Instagram");
			cb.addItem("Linkedin");
			cb.addItem("Twitter");
			cb.addItem("Overall");
			l3=new JLabel();
			l3.setBounds(10,200,160,90);
			s1=new JLabel();
			s1.setBounds(10, 220, 160, 90);
			l4=new JLabel();
			l4.setBounds(10,240,160,90);
			s2=new JLabel();
			s2.setBounds(10, 260, 160, 90);
			l5=new JLabel();
			l5.setBounds(10,200,160,90);
			s3=new JLabel();
			s3.setBounds(10, 220, 160, 90);
			l6=new JLabel();
			l6.setBounds(10,240,160,90);
			s4=new JLabel();
			s4.setBounds(10, 260, 160, 90);
			l7=new JLabel();
			l7.setBounds(10,200,160,90);
			s5=new JLabel();
			s5.setBounds(10, 220, 160, 90);
			l8=new JLabel();
			l8.setBounds(10,240,160,90);
			s6=new JLabel();
			s6.setBounds(10, 260, 160, 90);
			l9=new JLabel();
			l9.setBounds(10,200,160,90);
			s7=new JLabel();
			s7.setBounds(10, 220, 160, 90);
			l10=new JLabel();
			l10.setBounds(10,240,160,90);
			s8=new JLabel();
			s8.setBounds(10, 260, 160, 90);
			l11=new JLabel();
			l11.setBounds(10,200,160,90);
			s9=new JLabel();
			s9.setBounds(10, 220, 160, 90);
			l12=new JLabel();
			l12.setBounds(10,240,160,90);
			s10=new JLabel();
			s10.setBounds(10, 260, 160, 90);
			j3.add(cb);
			
			 cb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent l) {
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/social","root","");
						stm=con.createStatement();
					}
					catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JComboBox cb=(JComboBox) l.getSource();
					String s=(String)cb.getSelectedItem();
					if(s=="Facebook")
					{
						try {
							
							res=stm.executeQuery("select id,name,fba,fbs from socialmedia where fbs=(select max(fbs) from socialmedia)");
							while(res.next()) {
							l3.setText( "\nID:"+ res.getString("id"));
							s1.setText("NAME : "+ res.getString("name"));
							l4.setText("\n ACCOUNTS : "+res.getString("fba"));
							s2.setText("\nSCORE :"+res.getString("fbs"));
							j3.add(l3);
							j3.add(s1);
							j3.add(l4);
							j3.add(s2);
							
							  if(l3.isVisible())
								{
									l3.show();
									s1.show();
									l4.show();
									s2.show();
								}
							
							}
							res.close();
							
						}catch(SQLException w) {
							
						} 
						
					}
				//instagram	
					else if(s=="Instagram")
					{
						try {
							l3.hide();
							s1.hide();
							l4.hide();
							s2.hide();
							res=stm.executeQuery("select id,name,ina,ins from socialmedia where ins=(select max(ins) from socialmedia)");
							while(res.next()) {
							l5.setText("\nID:"+ res.getString("id"));
							s3.setText("NAME : "+ res.getString("name"));
							l6.setText("\nACCOUNTS : "+res.getString("ina"));
							s4.setText(" SCORE :"+res.getString("ins"));
							j3.add(l5);
							j3.add(s3);
						    j3.add(l6);
						    j3.add(s4);
						    
						    if(l5.isVisible())
							{
								l5.show();
								s3.show();
								l6.show();
								s4.show();
							}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					//linkedin
					else if(s=="Linkedin")
					{
						try {
							l3.hide();
							s1.hide();
							l4.hide();
							s2.hide();
							l5.hide();
							s3.hide();
							l6.hide();
							s4.hide();
						res=stm.executeQuery("select id,name,lia,lis from socialmedia where lis=(select max(lis) from socialmedia)");
						while(res.next()) {
						l7.setText("\nID:"+ res.getString("id"));
						s5.setText("NAME : "+ res.getString("name"));
						l8.setText("\n ACCOUNTS : "+res.getString("lia"));
						s6.setText("\nSCORE :"+res.getString("lis"));
						j3.add(l7);
						j3.add(l8);
						if(l7.isVisible())
						{
							l7.show();
							s5.show();
							l8.show();
							s6.show();
						}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					//twitter
					else if(s=="Twitter")
					{
						try {
							l3.hide();
							s1.hide();
							l4.hide();
							s2.hide();
							l5.hide();
							s3.hide();
							l6.hide();
							s4.hide();
							l7.hide();
							s5.hide();
							l8.hide();
							s6.hide();
						res=stm.executeQuery("select id,name,twa,tws from socialmedia where tws=(select max(tws) from socialmedia)");
						while(res.next()) {
						l9.setText( "\nID:"+ res.getString("id"));
						s7.setText("NAME : "+ res.getString("name"));
						l10.setText("\n ACCOUNTS : "+res.getString("twa"));
						s8.setText("\nSCORE :"+res.getString("tws"));
						j3.add(l9);
						j3.add(l10);
						if(l9.isVisible())
						{
							l9.show();
							s5.show();
							l10.show();
							s6.show();
						}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					//overall
					else if(s=="Overall")
					{
						try {
							l3.hide();
							s1.hide();
							l4.hide();
							s2.hide();
							l5.hide();
							s3.hide();
							l6.hide();
							s4.hide();
							l7.hide();
							s5.hide();
							l8.hide();
							s6.hide();
							l9.hide();
							s7.hide();
							l10.hide();
							s8.hide();
							res=stm.executeQuery("select id,name,scorea from socialmedia where scorea=(select max(scorea) from socialmedia)");
							while(res.next()) {
								
						l11.setText("\nID:"+ res.getString("id"));
						s9.setText("NAME : "+ res.getString("name"));
						l12.setText("\n    SCORE :"+res.getString("scorea"));
						j3.add(l11);
						j3.add(l12);
						if(l11.isVisible())
						{
							l11.show();
							l12.show();
						}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					
					
					
				}
			});
			 
		}
		
		//radio button2
		
		if(r2.isSelected())
		{
			JComboBox cb=new JComboBox<String>();
			cb.setBounds(5, 120,160, 20);
			cb.addItem("Facebook");
			cb.addItem("Instagram");
			cb.addItem("Linkedin");
			cb.addItem("Twitter");
			cb.addItem("Overall");
			l3=new JLabel();
			l3.setBounds(10,200,160,90);
			s1=new JLabel();
			s1.setBounds(10, 220, 160, 90);
			l4=new JLabel();
			l4.setBounds(10,240,160,90);
			s2=new JLabel();
			s2.setBounds(10, 260, 160, 90);
			l5=new JLabel();
			l5.setBounds(10,200,160,90);
			s3=new JLabel();
			s3.setBounds(10, 220, 160, 90);
			l6=new JLabel();
			l6.setBounds(10,240,160,90);
			s4=new JLabel();
			s4.setBounds(10, 260, 160, 90);
			l7=new JLabel();
			l7.setBounds(10,200,160,90);
			s5=new JLabel();
			s5.setBounds(10, 220, 160, 90);
			l8=new JLabel();
			l8.setBounds(10,240,160,90);
			s6=new JLabel();
			s6.setBounds(10, 260, 160, 90);
			l9=new JLabel();
			l9.setBounds(10,200,160,90);
			s7=new JLabel();
			s7.setBounds(10, 220, 160, 90);
			l10=new JLabel();
			l10.setBounds(10,240,160,90);
			s8=new JLabel();
			s8.setBounds(10, 260, 160, 90);
			l11=new JLabel();
			l11.setBounds(10,200,160,90);
			s9=new JLabel();
			s9.setBounds(10, 220, 160, 90);
			l12=new JLabel();
			l12.setBounds(10,240,160,90);
			s10=new JLabel();
			s10.setBounds(10, 260, 160, 90);
			j3.add(cb);
			
			
			 cb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent l) {
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/social","root","");
						stm=con.createStatement();
					}
					catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JComboBox cb=(JComboBox) l.getSource();
					String s=(String)cb.getSelectedItem();
					if(s=="Facebook")
					{
						try {
							
							res=stm.executeQuery("select id,name,fbc,fbcs from socialmedia where fbcs=(select max(fbcs) from socialmedia)");
							while(res.next()) {
								l3.setText( "\nID:"+ res.getString("id"));
								s1.setText("NAME : "+ res.getString("name"));
								l4.setText("\n ACCOUNTS : "+res.getString("fbc"));
								s2.setText("\nSCORE :"+res.getString("fbcs"));
								j3.add(l3);
								j3.add(s1);
								j3.add(l4);
								j3.add(s2);
								
								if(l3.isVisible())
								{
									
									l3.show();
									s1.show();
									l4.show();
									s2.show();
									
								}
								
								}
							res.close();
							
						}catch(SQLException w) {
							
						} 
						
					}
				//instagram	
					else if(s=="Instagram")
					{
						try {
							l3.hide();
							s1.hide();
							l4.hide();
							s2.hide();
							res=stm.executeQuery("select id,name,inc,incs from socialmedia where incs=(select max(incs) from socialmedia)");
							while(res.next()) {
								l5.setText("\nID:"+ res.getString("id"));
								s3.setText("NAME : "+ res.getString("name"));
								l6.setText("\nACCOUNTS : "+res.getString("inc"));
								s4.setText(" SCORE :"+res.getString("incs"));
								j3.add(l5);
								j3.add(s3);
							    j3.add(l6);
							    j3.add(s4);
							    
							    if(l5.isVisible())
								{
									l5.show();
									s3.show();
									l6.show();
									s4.show();
								}
							}
							res.close();	
								
						}catch(SQLException w) {
							
						} 
						
					}
					//linkedin
					else if(s=="Linkedin")
					{
						try {
							l3.hide();
							l4.hide();
							l5.hide();
							l6.hide();
							s1.hide();
							s2.hide();
							s3.hide();
							s4.hide();
						res=stm.executeQuery("select id,name,lic,lics from socialmedia where lics=(select max(lics) from socialmedia)");
						while(res.next()) {
							l7.setText("\nID:"+ res.getString("id"));
							s5.setText("NAME : "+ res.getString("name"));
							l8.setText("\n ACCOUNTS : "+res.getString("lia"));
							s6.setText("\nSCORE :"+res.getString("lis"));
							j3.add(l7);
							j3.add(l8);
							if(l7.isVisible())
							{
								l7.show();
								s5.show();
								l8.show();
								s6.show();
							}
							}
							res.close();	
								
						}catch(SQLException w) {
							
						} 
						
					}
					
					//twitter
					else if(s=="Twitter")
					{
						try {
							l3.hide();
							l4.hide();
							l5.hide();
							l6.hide();
							l7.hide();
							l8.hide();
							s1.hide();
							s2.hide();
							s3.hide();
							s4.hide();
							s5.hide();
							s6.hide();
						res=stm.executeQuery("select id,name,twc,twcs from socialmedia where twcs=(select max(twcs) from socialmedia)");
						while(res.next()) {
							l9.setText( "\nID:"+ res.getString("id"));
							s7.setText("NAME : "+ res.getString("name"));
							l10.setText("\n ACCOUNTS : "+res.getString("twc"));
							s8.setText("\nSCORE :"+res.getString("twcs"));
							j3.add(l9);
							j3.add(l10);
							if(l9.isVisible())
							{
								l9.show();
								s7.show();
								l10.show();
								s8.show();
							}
							}
							
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					//overall
					else if(s=="Overall")
					{
						try {
							l3.hide();
							l4.hide();
							l5.hide();
							l6.hide();
							l7.hide();
							l8.hide();
							l9.hide();
							l10.hide();
							s1.hide();
							s2.hide();
							s3.hide();
							s4.hide();
							s5.hide();
							s6.hide();
							s7.hide();
							s8.hide();
							res=stm.executeQuery("select id,name,scorec from socialmedia where scorec=(select max(scorec) from socialmedia)");
							while(res.next()) {
										
									l11.setText("\nID:"+ res.getString("id"));
									s9.setText("NAME : "+ res.getString("name"));
									l12.setText("\n    SCORE :"+res.getString("scorec"));
									j3.add(l11);
									j3.add(l12);
									if(l11.isVisible())
									{
										l11.show();
										s9.show();
										l12.show();
										s10.show();
									}
									}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					
					
					
				}
			});
			 
		}

		//radio button3
		
		if(r3.isSelected())
		{
			
			JComboBox cb=new JComboBox<String>();
			cb.setBounds(5, 120,160, 20);
			cb.addItem("Facebook");
			cb.addItem("Instagram");
			cb.addItem("Linkedin");
			cb.addItem("Twitter");
			cb.addItem("Overall");
			l3=new JLabel();
			l3.setBounds(10,200,160,90);
			l4=new JLabel();
			l4.setBounds(10,220,160,90);
			l5=new JLabel();
			l5.setBounds(10,200,160,90);
			l6=new JLabel();
			l6.setBounds(10,220,160,90);
			l7=new JLabel();
			l7.setBounds(10,200,160,90);
			l8=new JLabel();
			l8.setBounds(10,220,160,90);
			l9=new JLabel();
			l9.setBounds(10,240,160,90);
			l10=new JLabel();
			l10.setBounds(10,260,160,90);
			l11=new JLabel();
			l11.setBounds(10,240,160,90);
			l12=new JLabel();
			l12.setBounds(10,260,160,90);
			j3.add(cb);
			
			 cb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent l) {
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/social","root","");
						stm=con.createStatement();
					}
					catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JComboBox cb=(JComboBox) l.getSource();
					String s=(String)cb.getSelectedItem();
					if(s=="Facebook")
					{
						try {
							
							res=stm.executeQuery("select id,name,fbp,fbps from socialmedia where fbps=(select max(fbps) from socialmedia)");
							while(res.next()) {
							l3.setText("NAME : "+ res.getString("name")+ "\nID:"+ res.getString("id"));
							l4.setText("\n ACCOUNTS : "+res.getString("fbp")+"\nSCORE :"+res.getString("fbps"));
							j3.add(l3);
							j3.add(l4);
							
							if(l3.isVisible())
							{
								
								l3.show();
								l4.show();
								
							}
							
							}
							res.close();
							
						}catch(SQLException w) {
							
						} 
						
					}
				//instagram	
					else if(s=="Instagram")
					{
						try {
							l3.hide();
							l4.hide();
							res=stm.executeQuery("select id,name,inp,inps from socialmedia where inps=(select max(inps) from socialmedia)");
							while(res.next()) {
							l5.setText("NAME : "+ res.getString("name")+ "\nID:"+ res.getString("id"));
							l6.setText("\nACCOUNTS : "+res.getString("inp")+"\n \t\t SCORE :"+res.getString("inps"));
							j3.add(l5);
						    j3.add(l6);
						    
						    if(l5.isVisible())
							{
								l5.show();
								l6.show();
							}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					//linkedin
					else if(s=="Linkedin")
					{
						try {
							l3.hide();
							l4.hide();
							l5.hide();
							l6.hide();
						res=stm.executeQuery("select id,name,linp,lips from socialmedia where lics=(select max(linps) from socialmedia)");
						while(res.next()) {
						l7.setText("NAME : "+ res.getString("name")+ "\nID:"+ res.getString("id"));
						l8.setText("\n ACCOUNTS : "+res.getString("linp")+"\nSCORE :"+res.getString("linps"));
						j3.add(l7);
						j3.add(l8);
						if(l7.isVisible())
						{
							l7.show();
							l8.show();
						}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					//twitter
					else if(s=="Twitter")
					{
						try {
							l3.hide();
							l4.hide();
							l5.hide();
							l6.hide();
							l7.hide();
							l8.hide();
						res=stm.executeQuery("select id,name,twp,twps from socialmedia where twps=(select max(twps) from socialmedia)");
						while(res.next()) {
						l9.setText("NAME : "+ res.getString("name")+ "\nID:"+ res.getString("id"));
						l10.setText("\n ACCOUNTS : "+res.getString("twp")+"\nSCORE :"+res.getString("twps"));
						j3.add(l9);
						j3.add(l10);
						if(l9.isVisible())
						{
							l9.show();
							l10.show();
						}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					//overall
					else if(s=="Overall")
					{
						try {
							l3.hide();
							l4.hide();
							l5.hide();
							l6.hide();
							l7.hide();
							l8.hide();
							l9.hide();
							l10.hide();
							res=stm.executeQuery("select id,name,scorep from socialmedia where scorep=(select max(scorep) from socialmedia)");
							while(res.next()) {
								
						l11.setText("NAME : "+ res.getString("name")+ "\nID:"+ res.getString("id"));
						l12.setText("\n SCORE :"+res.getString("scorep"));
						j3.add(l11);
						j3.add(l12);
						if(l11.isVisible())
						{
							l11.show();
							l12.show();
						}
						}
						res.close();	
						}catch(SQLException w) {
							
						} 
						
					}
					
					
					
					
				}
			});
			 
		}

		 b2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					form2 ov=new form2();
					j3.dispose();
					
					
				}
		    });	
	}
	
	public static void main(String args[]) {
		form3 n=new form3();
	}
}
