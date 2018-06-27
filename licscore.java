import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class licscore extends JFrame implements ActionListener
{
	JButton b1;
	DefaultTableModel model = new DefaultTableModel();
    public licscore()
    {
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("fb-connections");
        model.addColumn("score");

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/social";
        String userid = "root";
        String password = "";
        String sql = "SELECT id,name,lic,lics FROM socialmedia where incs=(select max(lics) from socialmedia)";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        // Vector is deprecated but I am using them in this example to keep 
        // things simple - the best practice would be to create a custom defined
        // class which inherits from the AbstractTableModel class
      
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
        JTable table = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );

        JPanel buttonPanel = new JPanel();
        getContentPane().add( buttonPanel, BorderLayout.SOUTH );
        b1=new JButton("Back");
        b1.setBounds(50,250,80,80);
        
        b1.addActionListener(this);
        buttonPanel.add(b1);
        
        pack();
        setVisible(true);
      
    }
    @Override
	public void actionPerformed(ActionEvent arg0) {
    	
    	form3 ob=new form3();
    	dispose();
		// TODO Auto-generated method stub
		
	}
    
}

 