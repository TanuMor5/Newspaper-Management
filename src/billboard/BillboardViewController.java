package billboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import table.MySqlConnector;


public class BillboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup abc;

    @FXML
    private Button txtbillhistory;

    @FXML
    private Button txtexport;

    @FXML
    private TextField txtmobile;
    ObservableList<BillBean>list=FXCollections.observableArrayList();

    @FXML
    private RadioButton txtpaid;

    @FXML
    private RadioButton txtpending;

    @FXML
    private Button txtsearch;

    @FXML
    private TableView<BillBean> txttable;

    @FXML
    private TextField txttotalamount;

    Connection con;
    PreparedStatement pst;

    @FXML
    void doexport(ActionEvent event) throws IOException {
        // Implement exporting functionality if needed.
    	
    	Writer writer = null;
    	try
    	{
    		
        	File file = new File("C:\\Excel_java\\customers.csv");
        	writer = new BufferedWriter(new FileWriter(file));
        	String text = " mobile, datefrom, dateto, bill\n";
        	writer.write(text);
        	System.out.println("List size = "+list.size());
        	for(BillBean c : list)
        	{
        		text =c.getMobile()+","+c.getDatefrom()+","+c.getDateto()+","+c.getBill()+"\n";
        		System.out.println(text);
        		writer.write(text);
        		
        	}
        	System.out.println("exported");
    		
    	}
    	catch(Exception ex)
    	{
    		
    	}
    	finally
    	{
    		writer.flush();
    		writer.close();
    	}
    	
    }

    @SuppressWarnings("unchecked")
	@FXML
    void dosearch(ActionEvent event) {
    	txttable.getColumns().clear();
    	TableColumn<BillBean, String>mobile=new TableColumn<BillBean, String>("mobile");     //anything
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));     //name of column
        mobile.setMinWidth(100);
        
        TableColumn<BillBean, String>datefrom=new TableColumn<BillBean, String>("datefrom");     //anything
        datefrom.setCellValueFactory(new PropertyValueFactory<>("datefrom"));     
        datefrom.setMinWidth(50);
        
        
        TableColumn<BillBean, String>dateto=new TableColumn<BillBean, String>("dateto");     //anything
        dateto.setCellValueFactory(new PropertyValueFactory<>("dateto"));     
        dateto.setMinWidth(50);
        
        
        TableColumn<BillBean,String>bill=new TableColumn<BillBean, String>("bill");     //anything
        bill.setCellValueFactory(new PropertyValueFactory<>("bill"));     
        bill.setMinWidth(50);
        
        
        
      
       
       txttable.getColumns().addAll(mobile,datefrom,dateto,bill);
       list = fetchAllBills();
       System.out.println("List size = "+list.size());
       txttable.setItems(list);
       
        
    }
    
    ObservableList<BillBean> fetchAllBills() {
        ObservableList<BillBean> ary = FXCollections.observableArrayList();
        if(txtpaid.isSelected())
        {
        	float sum=0;
        	try {
        		pst=con.prepareStatement("select* from bills where billstatus=1");
        		ResultSet table=pst.executeQuery();
        	    while(table.next())
        	    {
        	    	sum+=Float.valueOf(table.getString("bill"));
        	    	String mobile=table.getString("mobile");
        	    	Date datef=table.getDate("datefrom");
        	    	String datefrom=datef.toString();
        	    	Date datet=table.getDate("dateto");
        	    	String dateto=datet.toString();
        	    	String bill=table.getString("bill");
        	    	ary.add(new BillBean(mobile,datefrom,dateto,bill));
        	    }
        	}
        	catch(SQLException e) {
        		e.printStackTrace();
        	}
        	txttotalamount.setText(String.valueOf(sum));
        	return ary;
        	
        }
        else if(txtpending.isSelected())
        {
        	float sum=0;
        	try {
        		pst=con.prepareStatement("select* from bills where billstatus=0");
        		ResultSet table=pst.executeQuery();
        		while(table.next())
        		{
        			sum+=Float.valueOf(table.getString("bill"));
        	    	String mobile=table.getString("mobile");
        	    	Date datef=table.getDate("datefrom");
        	    	String datefrom=datef.toString();
        	    	Date datet=table.getDate("dateto");
        	    	String dateto=datet.toString();
        	    	String bill=table.getString("bill");
        	    	ary.add(new BillBean(mobile,datefrom,dateto,bill));
        		}
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
        	txttotalamount.setText(String.valueOf(sum));
        	return ary;
        }
        else
        	return ary;
        
        
    }

    @SuppressWarnings("unchecked")
	@FXML
    void txtbillhistory(ActionEvent event) {
    	txttable.getColumns().clear();
    	
    	TableColumn<BillBean,String>mobile=new TableColumn<BillBean,String>("mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<BillBean,String>("mobile"));
    	mobile.setMinWidth(100);
    	
    	 TableColumn<BillBean, String>datefrom=new TableColumn<BillBean, String>("datefrom");     //anything
         datefrom.setCellValueFactory(new PropertyValueFactory<>("datefrom"));     
         datefrom.setMinWidth(50);
         
         
         TableColumn<BillBean, String>dateto=new TableColumn<BillBean, String>("dateto");     //anything
         dateto.setCellValueFactory(new PropertyValueFactory<>("dateto"));     
         dateto.setMinWidth(50);
         
         
         TableColumn<BillBean, String>bill=new TableColumn<BillBean, String>("bill");     //anything
         bill.setCellValueFactory(new PropertyValueFactory<>("bill"));     
         bill.setMinWidth(50);
         
      
         
         txttable.getColumns().addAll(mobile,datefrom,dateto,bill);
         list = fetchCustomerBills();
         System.out.println("List size = "+list.size());
         txttable.setItems(list);
         
      
    }
    
    ObservableList<BillBean>fetchCustomerBills()
    {
    	String mob=txtmobile.getText();
    	ObservableList<BillBean>ary=FXCollections.observableArrayList();
    	float sum=0;
    	try {
    		pst=con.prepareStatement("select* from bills where mobile=?");
    		pst.setString(1, mob);
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			sum+=Float.valueOf(table.getString("bill"));
    			String mobile=table.getString("mobile");
    			Date datef=table.getDate("datefrom");
    	    	String datefrom=datef.toString();
    	    	Date datet=table.getDate("dateto");
    	    	String dateto=datet.toString();
    	    	String bill=table.getString("bill");
    	    	ary.add(new BillBean(mobile,datefrom,dateto,bill));
    			
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	txttotalamount.setText(String.valueOf(sum));
    	return ary;
    }

    

   

    @FXML
    void initialize() {
        con = MySqlConnector.doConnect();
        if (con == null) {
            System.out.println("Invalid connection");
        } else {
            System.out.println("Connected");
        }
        assert abc != null : "fx:id=\"abc\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txtbillhistory != null : "fx:id=\"txtbillhistory\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txtexport != null : "fx:id=\"txtexport\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txtpaid != null : "fx:id=\"txtpaid\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txtpending != null : "fx:id=\"txtpending\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txtsearch != null : "fx:id=\"txtsearch\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txttable != null : "fx:id=\"txttable\" was not injected: check your FXML file 'BillboardView.fxml'.";
        assert txttotalamount != null : "fx:id=\"txttotalamount\" was not injected: check your FXML file 'BillboardView.fxml'.";

    }
}
