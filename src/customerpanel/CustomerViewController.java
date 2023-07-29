
package customerpanel;

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
import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    ObservableList<CustomerBean>list=FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> txtarea;

    @FXML
    private ComboBox<String> txtpaper;

    @FXML
    private TableView<CustomerBean> txttable;
    Connection con;
    PreparedStatement pst;

    @FXML
    void doexportsave(ActionEvent event) throws IOException {
    	
    	Writer writer = null;
    	try
    	{
    		
        	File file = new File("C:\\Excel_java\\customers.csv");
        	writer = new BufferedWriter(new FileWriter(file));
        	String text = "name, mobile, hawker, address, email\n";
        	writer.write(text);
        	System.out.println("List size = "+list.size());
        	for(CustomerBean c : list)
        	{
        		text = c.getName()+","+c.getMobile()+","+c.getHawker()+","+c.getAddress()+","+c.getEmail()+"\n";
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
    void dofilter(ActionEvent event) {
       txttable.getColumns().clear();
       TableColumn<CustomerBean,String>name= new TableColumn<CustomerBean,String>("Customer Name"); //we can write anything
       name.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("name"));
       name.setMinWidth(100);
       
       TableColumn<CustomerBean,String>mobile=new TableColumn<CustomerBean,String>("Customer Mobile");
       mobile.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("mobile"));
       name.setMinWidth(100);
       
       TableColumn<CustomerBean,String>hawker=new TableColumn<CustomerBean,String>("Hawker Name");
       hawker.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("hawker"));
       hawker.setMinWidth(100);
       
       TableColumn<CustomerBean,String>address=new TableColumn<CustomerBean,String>("Customer Address");
       address.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("address"));
       address.setMinWidth(100);
       
       TableColumn<CustomerBean,String>email=new TableColumn<CustomerBean,String>("Email");
       email.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("email"));
       email.setMinWidth(100);
       
      
       
       txttable.getColumns().addAll(name,mobile,hawker,address,email);
       list = fetchAllAreaCustomers();
       System.out.println("List size = "+list.size());
       txttable.setItems(list);
       
       
       
    }
    
    
    ObservableList<CustomerBean>fetchAllAreaCustomers()
    {
    	ObservableList<CustomerBean>ary=FXCollections.observableArrayList();
    	String area=txtarea.getSelectionModel().getSelectedItem();
    	try {
    		pst=con.prepareStatement("select* from customers where area=?");
    		pst.setString(1,area);
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			String name=table.getString("name");
    			String mobile=table.getString("mobile");
    			String hawker=table.getString("hawker");
    			String address=table.getString("address");
    			String email=table.getString("email");
    			
    			ary.add(new CustomerBean(name,mobile,hawker,address,email));
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ary;
    }
    
    
    
    

    @SuppressWarnings("unchecked")
	@FXML
    void dofilterpaper(ActionEvent event) {
    	txttable.getColumns().clear();
        TableColumn<CustomerBean,String>name= new TableColumn<CustomerBean,String>("Customer Name"); //we can write anything
        name.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("name"));
        name.setMinWidth(100);
        
        TableColumn<CustomerBean,String>mobile=new TableColumn<CustomerBean,String>("Customer Mobile");
        mobile.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("mobile"));
        name.setMinWidth(100);
        
        TableColumn<CustomerBean,String>hawker=new TableColumn<CustomerBean,String>("Hawker Name");
        hawker.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("hawker"));
        hawker.setMinWidth(100);
        
        TableColumn<CustomerBean,String>address=new TableColumn<CustomerBean,String>("Customer Address");
        address.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("address"));
        address.setMinWidth(100);
        
        TableColumn<CustomerBean,String>email=new TableColumn<CustomerBean,String>("Email");
        email.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("email"));
        email.setMinWidth(100);
        
   
        
        
        txttable.getColumns().addAll(name,mobile,hawker,address,email);
        list = fetchAllPaperCustomers();
        System.out.println("List size = "+list.size());
        txttable.setItems(list);
        
    }
     
    ObservableList<CustomerBean>fetchAllPaperCustomers()
    {
    	ObservableList<CustomerBean>ary=FXCollections.observableArrayList();
    	String ppr=txtpaper.getSelectionModel().getSelectedItem();
    	try {
    		pst=con.prepareStatement("select* from customers where spapers like ?;");
    	pst.setString(1,"%"+ppr+"%");
    	ResultSet table=pst.executeQuery();
    	while(table.next())
		{
			String name=table.getString("name");
			String mobile=table.getString("mobile");
			String hawker=table.getString("hawker");
			String address=table.getString("address");
			String email=table.getString("email");
			
			ary.add(new CustomerBean(name,mobile,hawker,address,email));
		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ary;
    }

    @FXML
    void initialize() {
    
	
		con=MySqlConnector.doConnect();
    	loadAreaFromDatabase();
    	loadPaperFromDatabase();
        assert txtarea != null : "fx:id=\"txtarea\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert txtpaper != null : "fx:id=\"txtpaper\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert txttable != null : "fx:id=\"txttable\" was not injected: check your FXML file 'CustomerView.fxml'.";
    }
        private void loadAreaFromDatabase() {
        	try {
        		String query="select distinct area from  customers";
        		pst=con.prepareStatement(query);
        		ResultSet rs=pst.executeQuery();
        		ObservableList<String>items=FXCollections.observableArrayList();
        		while(rs.next()) {
        			String areaName=rs.getString("area");
        			items.add(areaName);
        		}
        		txtarea.setItems(items);
        	}
        	catch(SQLException e) {
        		e.printStackTrace();
        	}
        }
        
        private void loadPaperFromDatabase() {
        	try {
        		String query="select paper from papers";
        		pst=con.prepareStatement(query);
        		ResultSet rs=pst.executeQuery();
        		ObservableList<String>items=FXCollections.observableArrayList();
        		while(rs.next())
        		{
        			String areaName=rs.getString("paper");
        			items.add(areaName);
        		}
        		txtpaper.setItems(items);
        	}
        	catch(SQLException e) {
        		e.printStackTrace();
        	}
        
    }

}
