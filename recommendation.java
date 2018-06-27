import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;

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

public class recommendation implements ActionListener
{
	JFrame js=new JFrame();
	String sql;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	JLabel s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
	 String m1,m2;
	 
	 String m3;
		double sd3 = 0 ;//=new double[30]  ;
		double mean3 = 0;
		double std3=0;
	
	 String mean;
	String grades;
		double sd = 0,t=0 ;//=new double[30]  ;
		double mean1 = 0,std=0;
		
		int  i=0;
		double[] k=new double[30];
	
	JRadioButton r1,r2,r3,r4;
	//JRadioButton op1,op2,op3;
	JButton b1,b2,b3,b4,b5,b6;
	Statement stm;
	ResultSet res,res1,res2,res3,res4,res5;
	java.sql.PreparedStatement ps;
	java.sql.PreparedStatement ps1;
	java.sql.PreparedStatement ps2;
	Connection con;
	//JComboBox<String>options=new JComboBox();
	recommendation()
	{
		l1=new JLabel("RECOMMENTATION");
		l1.setBounds(100, 20, 500, 40);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		
		l3=new JLabel("RECOMMENTATION");
		l3.setBounds(50, 140, 500, 40);
		l3.setFont(new Font("Serif", Font.PLAIN, 15));
		
		l4=new JLabel("RECOMMENTATION");
		l4.setBounds(50, 160, 500, 40);
		l4.setFont(new Font("Serif", Font.PLAIN,15));
		
		l5=new JLabel("RECOMMENTATION");
		l5.setBounds(50, 140, 500, 40);
		l5.setFont(new Font("Serif", Font.PLAIN,15));
		
		l6=new JLabel("RECOMMENTATION");
		l6.setBounds(50, 160, 500, 40);
		l6.setFont(new Font("Serif", Font.PLAIN,15));
		
		l7=new JLabel("RECOMMENTATION");
		l7.setBounds(50, 140, 500, 40);
		l7.setFont(new Font("Serif", Font.PLAIN,15));
		
		l8=new JLabel("RECOMMENTATION");
		l8.setBounds(50, 160, 500, 40);
		l8.setFont(new Font("Serif", Font.PLAIN,15));
		
		r1=new JRadioButton("ACCOUNTS");
		r1.setBounds(5,80,90,30);
		r2=new JRadioButton("CONNECTIONS");
		r2.setBounds(100,80,110,30);
		r3=new JRadioButton("POSTS");
		r3.setBounds(210,80,90,30);
		
		r4=new JRadioButton("SCOREWISE");
		r4.setBounds(280,80,90,30);
		
		
		b1=new JButton("OK");
		b1.setBounds(320,80,60,30);
		b2=new JButton("Reset");
		b2.setBounds(20,400,80,30);
		b3=new JButton("Back");
		b3.setBounds(100,400,80,30);
		
		b4=new JButton("Grade");
		b4.setBounds(50,300,80,30);
		
		b5=new JButton("Grade");
		b5.setBounds(50,300,80,30);
		
		b6=new JButton("Grade");
		b6.setBounds(50,300,80,30);
		
		
		b1.addActionListener(this);
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1); bg.add(r2);	bg.add(r3);	js.add(l1);
		js.add(r1);	js.add(r2); js.add(r3); js.add(b1);
		js.add(b2); js.add(b3);
		js.setSize(400,500);
		js.setLayout(null);
		js.setBackground(Color.white);
		js.setVisible(true); 
	}
	@Override
	public void actionPerformed(ActionEvent n) 
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/social","root","");
			stm=con.createStatement();
			
		}
		catch(Exception e)
		{
			
		}
		
		if(r1.isSelected())
		{
			

			try {
				res=stm.executeQuery("select sum(scorea)/30 as txt from socialmedia;");
				
				while(res.next())
				{
					mean=res.getString("txt");
					mean1=Double.parseDouble(mean);
					l3.setText("mean : " + res.getString("txt"));
					js.add(l3);
				}
				
				//res.close();
				res1=stm.executeQuery("select scorea from socialmedia");
				
				while(res1.next())
				{
					k[i]= Double.parseDouble(res1.getString("scorea"));
					
					//sd
					sd=sd+((k[i]-mean1)*(k[i]-mean1));
					std=Math.sqrt(sd/30);
					
					l4.setText("Standard Deviation : " + std);
					js.add(l4);
					
				}
				//Grading of Accounts
				
				js.add(b4);
				
				b4.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try {
							int i=0;
							
							double c;
							int d;
							String query;
							ResultSet re= stm.executeQuery("select id,scorea from socialmedia");
							
							while(re.next())
							{							
								if(i<30) {
								 c=Double.parseDouble(re.getString("scorea"));
								 d=re.getInt("id");
								
								 
								if(c > (mean1+std))
								{
								query= "update socialmedia set gradeA = ? where id=?";
								 ps=con.prepareStatement(query);
								 ps.setString(1,"S");
								 ps.setInt(2,d);
								 ps.executeUpdate();
								}
								
								//Grade A
								if((mean1+(std/2))<c && c<(mean1+std))
								{
								query= "update socialmedia set gradeA = ? where id=?";
								 ps=con.prepareStatement(query);
								 ps.setString(1,"A");
								 ps.setInt(2,d);
								 ps.executeUpdate();
								 ps.close();
								 }
								
								//Grade B
								
								else if(mean1 <c && c<(mean1+std/2))
								{
								query= "update socialmedia set gradeA = ? where id=?";
								 ps=con.prepareStatement(query);
								 ps.setString(1,"B");
								 ps.setInt(2,d);
								 ps.executeUpdate();
								 ps.close();
								}
								
								//Grade C
								
								else if((mean1-std/2) <c && c<(mean1))
								{
								query= "update socialmedia set gradeA = ? where id=?";
						 ps=con.prepareStatement(query);
								 ps.setString(1,"C");
								 ps.setInt(2,d);
								 ps.executeUpdate();
								 ps.close();
								}
								
								//Grade D
								else if((mean1-std) <c && c<(mean1-std/2))
								{
								query= "update socialmedia set gradeA = ? where id=?";
		
								 ps=con.prepareStatement(query);
								 ps.setString(1,"D");
								 ps.setInt(2,d);
								 ps.executeUpdate();
								 ps.close();
								}
								
								else if(c<(mean1-std))
								{
									query= "update socialmedia set gradeA = ? where id=?";
									  ps=con.prepareStatement(query);
									 ps.setString(1,"E");
									 ps.setInt(2,d);
									 ps.executeUpdate();
									 ps.close();
								}
								
							
								i=i+1;
								}							
							}
							GradeA ob=new GradeA();
							}
							catch(Exception e)
							{
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Exception");
							}
					}				
				});
				
			}

			catch (Exception e) {
				// TODO: handle exception
			}
		
		}
				
				//radiobutton2
				if(r2.isSelected())
				{
					
					double sd2 = 0 ;//=new double[30]  ;
					double mean2 = 0,std2=0;
					int  j=0;
					double[] l=new double[30];

					try {
						res2=stm.executeQuery("select sum(scorec)/30 as txt from socialmedia;");
						
						while(res2.next())
						{
							m1=res2.getString("txt");
							mean2=Double.parseDouble(m1);
							l5.setText("mean : " + res2.getString("txt"));
							js.add(l5);
						}
						
						//res.close();
						res3=stm.executeQuery("select scorec from socialmedia");
						
						while(res3.next())
						{
							l[j]= Double.parseDouble(res3.getString("scorec"));
							
							//sd
							sd2=sd2+((l[j]-mean2)*(l[j]-mean2));
							std2=Math.sqrt(sd2/30);
							
							l6.setText("Standard Deviation : " + std2);
							js.add(l6);
							
						}	
						js.add(b5);
					try {	
						int i=0;
						double e;
						int f;
						String query1;
						ResultSet rec= stm.executeQuery("select id,scorec from socialmedia");
						
						while(rec.next())
						{							
							if(i<30) {
							 e=Double.parseDouble(rec.getString("scorec"));
							 f=rec.getInt("id");
							
							 
							if(e > (mean2+std2))
							{
							query1= "update socialmedia set gradeC = ? where id=?";
							 ps1=con.prepareStatement(query1);
							 ps1.setString(1,"S");
							 ps1.setInt(2,f);
							 ps1.executeUpdate();
							 ps1.close();
							 //JOptionPane.showMessageDialog(null,"database updateds");
							}
							
							//Grade A
							if((mean2+(std2/2))<e && e<(mean2+std2))
							{
							query1= "update socialmedia set gradeC = ? where id=?";
							 ps1=con.prepareStatement(query1);
							 ps1.setString(1,"A");
							 ps1.setInt(2,f);
							 ps1.executeUpdate();
							 ps1.close();
							 }
							
							//Grade B
							
							else if(mean2 <e && f<(mean2+std2/2))
							{
							query1= "update socialmedia set gradeC = ? where id=?";
							 ps1=con.prepareStatement(query1);
							 ps1.setString(1,"B");
							 ps1.setInt(2,f);
							 ps1.executeUpdate();
							 ps1.close();
							}
							
							//Grade C
							
							else if((mean2-std2/2) <e && e<(mean2))
							{
							query1= "update socialmedia set gradeC = ? where id=?";
					 ps1=con.prepareStatement(query1);
							 ps1.setString(1,"C");
							 ps1.setInt(2,f);
							 ps1.executeUpdate();
							 ps1.close();
							}
							
							//Grade D
							else if((mean2-std2) <e && e<(mean2-std2/2))
							{
							query1= "update socialmedia set gradeC = ? where id=?";
	
							 ps1=con.prepareStatement(query1);
							 ps1.setString(1,"D");
							 ps1.setInt(2,f);
							 ps1.executeUpdate();
							 ps1.close();
							}
							
							else if(e<(mean2-std2))
							{
								query1= "update socialmedia set gradeC = ? where id=?";
								
								  ps1=con.prepareStatement(query1);
								 ps1.setString(1,"E");
								 ps1.setInt(2,f);
								 ps1.executeUpdate();
								ps1.close();
							}
							
							i=i+1;
							}							
						}
						GradeC ob=new GradeC();
						}
						catch(Exception e)
						{
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Exception");
						}
	
					
						
			} 
			
			catch (SQLException e) {
				
				e.printStackTrace();
			}
		}		
		//radiobutton 3
					
					if(r3.isSelected())
					{
						
						//int  i=0;
						double[] v =new double[30];

						try {
							res4=stm.executeQuery("select sum(scorep)/30 as txt from socialmedia;");
							
							while(res4.next())
							{
								m3=res4.getString("txt");
								mean3=Double.parseDouble(m3);
								l7.setText("mean : " + res4.getString("txt"));
								js.add(l7);
							}
							
							//res.close();
							res5=stm.executeQuery("select scorep from socialmedia");
						
							while(res5.next())
							{
								v[i]= Double.parseDouble(res5.getString("scorep"));
								//sd
								sd3=sd3+((v[i]-mean3)*(v[i]-mean3));
								std3=Math.sqrt(sd3/30);
								
								
								l8.setText("Standard Deviation : " + std3);
								js.add(l8);
								
							}
							
							js.add(b6);
							b6.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent bsix) {
									// TODO Auto-generated method stub
									
									try {	
										int i=0;
										double g;
										int h;
										String query2;
										ResultSet rep= stm.executeQuery("select id,scorep from socialmedia");
										
										while(rep.next())
										{							
											if(i<30) {
											 g=Double.parseDouble(rep.getString("scorep"));
											 h=rep.getInt("id");
											
											 
											if(g > (mean3+std3))
											{
											query2= "update socialmedia set gradeP = ? where id=?";
											 ps2=con.prepareStatement(query2);
											 ps2.setString(1,"S");
											 ps2.setInt(2,h);
											 ps2.executeUpdate();
											 ps2.close();
											 //JOptionPane.showMessageDialog(null,"database updateds");
											}
											
											//Grade A
											if((mean3+(std3/2))<g && g<(mean3+std3))
											{
											query2= "update socialmedia set gradeP = ? where id=?";
											 ps2=con.prepareStatement(query2);
											 ps2.setString(1,"A");
											 ps2.setInt(2,h);
											 ps2.executeUpdate();
											 ps2.close();
											 }
											
											//Grade B
											
											else if(mean3 <g && g<(mean3+std3/2))
											{
											query2= "update socialmedia set gradeP = ? where id=?";
											 ps2=con.prepareStatement(query2);
											 ps2.setString(1,"B");
											 ps2.setInt(2,h);
											 ps2.executeUpdate();
											 ps2.close();
											}
											
											//Grade C
											
											else if((mean3-std3/2) <g && g<(mean3))
											{
											query2= "update socialmedia set gradeP = ? where id=?";
									 ps2=con.prepareStatement(query2);
											 ps2.setString(1,"C");
											 ps2.setInt(2,h);
											 ps2.executeUpdate();
											 ps2.close();
											}
											
											//Grade D
											else if((mean3-std3) <g && h<(mean3-std3/2))
											{
											query2= "update socialmedia set gradeP = ? where id=?";
					
											 ps2=con.prepareStatement(query2);
											 ps2.setString(1,"D");
											 ps2.setInt(2,h);
											 ps2.executeUpdate();
											 ps2.close();
											}
											
											else if(g<(mean3-std3))
											{
												query2= "update socialmedia set gradeP = ? where id=?";
												
												 ps2=con.prepareStatement(query2);
												 ps2.setString(1,"E");
												 ps2.setInt(2,h);
												 ps2.executeUpdate();
											     ps2.close();
											}
											
											i=i+1;
											}							
										}
										GradeP ob=new GradeP();
										}
										catch(Exception e)
										{
											e.printStackTrace();
											JOptionPane.showMessageDialog(null, "Exception");
										}
					
							}});						
						}
						catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					
					

			
}
	
	
	
	b2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(l3.isVisible()) {
			l3.setText("");
			l4.setText("");
			}
			
			else if(l5.isVisible()) {
				l5.setText("");
				l6.setText("");
				}
			
			else if(l7.isVisible()) {
				l7.setText("");
				l8.setText("");
				}
			
			
		}
	});
	
b3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			form2 on=new form2();
			js.dispose();
			
			
		}
	});
			
	}
	
	
	
	
	/*public static void main(String args[])
	{
		recommendation o=new recommendation();
	}*/
}