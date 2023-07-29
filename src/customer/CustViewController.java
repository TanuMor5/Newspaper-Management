package customer;

import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import hawker.MySqlConnector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button txtfetch;

    @FXML
    private TextField txtaddress;

    @FXML
    private ComboBox<String> txtarea;
    
    

    @FXML
    private DatePicker txtdos;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txthawker;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtname;
    
    @FXML
    private Label txtrespp;

    @FXML
    private ListView<String> txtpaperavailable;

    @FXML
    private ListView<String> txtpaperprice;

    @FXML
    private ListView<String> txtselectedpaper;

    @FXML
    private ListView<String> txtselectedprice;
    
    Connection con;
    PreparedStatement pst;
    String spapers;
    String sprices;
    
    @FXML
    void dofetch(ActionEvent event) {
    	try {
    		long mobile=Long.parseLong(txtmobile.getText());
           	 pst=con.prepareStatement("select* from customers where mobile=?");
           	pst.setLong(1, mobile);
           	 ResultSet table=pst.executeQuery();
           	 while(table.next())
           	 {
           		 
           		 String name=table.getString("name");
           		String spapers =table.getString("spapers");
           		String sprices=table.getString("sprices");
           		String area=table.getString("area");
           		String hawker=table.getString("hawker");
           		 
           		 String email=table.getString("email");
           		String address=table.getString("address");
           		 java.sql.Date dobb= table.getDate("dos");
           	
       		 txtname.setText(name);
       		txtselectedpaper.getItems().setAll(Arrays.asList(spapers.split(",")));
       	 txtselectedprice.getItems().setAll(Arrays.asList(sprices.split(",")));
       	 txtarea.setValue(area);          
       	 txthawker.setText(hawker);
       	 txtemail.setText(email);
       	 txtaddress.setText(address);
       		 txtdos.setValue(dobb.toLocalDate());
       			 
           		 System.out.println(mobile+"\t"+name+"\t"+spapers+"\t"+sprices +"\t"+area+"\t"+hawker+"\t"+email+"\t"+address+"\t"+txtdos.toString());
           	 }
            }
            catch(Exception exp)
            {
           	 System.out.println(exp);
            }
    }

    
    @FXML
    void dopaperprice(MouseEvent event) {

    }


    @FXML
    void doarea(ActionEvent event) {
         String area=txtarea.getSelectionModel().getSelectedItem();
         try {
        	 pst=con.prepareStatement("select * from hawkerss where alloarea like ?");
        	 pst.setString(1,"%"+area+"%");
        	 ResultSet table=pst.executeQuery();
        	 while(table.next()) {
        		 txthawker.setText(table.getString("hname"));
        	 }
         }
    
    catch(Exception ex) {
    	ex.printStackTrace();
    }
    }

    @FXML
    void dopaperavailable(MouseEvent event) {
         if(event.getClickCount()==2)
         {
        	
        	int a=txtpaperavailable.getSelectionModel().getSelectedIndex();
        	 txtselectedpaper.getItems().add(txtpaperavailable.getItems().get(a));
        	 txtselectedprice.getItems().add( txtpaperprice.getItems().get(a));
        	  spapers = spapers+","+a;
        	  sprices=sprices+","+a;
        	  
        	  if(spapers.startsWith(",")) {spapers=spapers.substring(2);}
        	  if(sprices.startsWith(",")) {sprices=sprices.substring(2);}
        	  
        	  }
     		
         }
   
    

    @FXML
    void dosubscribe(ActionEvent event) {
    	long mobile=Long.parseLong(txtmobile.getText());
    	 
         LocalDate ld=txtdos.getValue();
         java.sql.Date dt=java.sql.Date.valueOf(ld);
         
         
         try {
      	   pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?)");
      	pst.setLong(1, mobile);
      	pst.setString(2, txtname.getText());
      	 ObservableList<String> selectedPapers = txtselectedpaper.getItems();
         String spapers = String.join(",", selectedPapers);
         pst.setString(3, spapers);

         ObservableList<String> selectedPrices = txtselectedprice.getItems();
         String sprices = String.join(",", selectedPrices);
         pst.setString(4, sprices);
     
     	  pst.setString(4, sprices);
     	  System.out.println("spapers");
     	  System.out.println("sprices");
      	  
      	pst.setString(5, txtarea.getSelectionModel().getSelectedItem());
      	pst.setString(6,txthawker.getText());
      	pst.setString(7,txtemail.getText());
      	pst.setString(8,txtaddress.getText());
      	pst.setDate(9, dt);
      	
      	   pst.executeUpdate();
      	 txtrespp.setText(" enrolled successfully");
         }
         catch(SQLException e)
         {
      	   e.printStackTrace();
         }
      }

   
  

    @FXML
    void dounsubscribe(ActionEvent event) {
    	try {
    		 
    		pst=con.prepareStatement("delete from customers where mobile=?");
    		pst.setLong(1, Long.parseLong(txtmobile.getText()));
    		int count=pst.executeUpdate();
    		if(count!=0)
    			txtrespp.setText(count+"Record Deleted");
    		else
    			txtrespp.setText("Invalid ID");
    		
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}
    }

    @FXML
    void doupdate(ActionEvent event) {
    	 long mobile = Long.parseLong(txtmobile.getText());
    	    LocalDate ld = txtdos.getValue();
    	    java.sql.Date dt = java.sql.Date.valueOf(ld);
    	    
    	    try {
    	        pst = con.prepareStatement("UPDATE customers SET name=?, spapers=?, sprices=?, area=?, hawker=?, email=?, address=?, dos=? WHERE mobile=?");
    	        pst.setString(1, txtname.getText());
    	        
    	        ObservableList<String> selectedPapers = txtselectedpaper.getItems();
    	        String spapers = String.join(",", selectedPapers);
    	        pst.setString(2, spapers);
    	        
    	        ObservableList<String> selectedPrices = txtselectedprice.getItems();
    	        String sprices = String.join(",", selectedPrices);
    	        pst.setString(3, sprices);
    	        
    	        pst.setString(4, txtarea.getSelectionModel().getSelectedItem());
    	        pst.setString(5, txthawker.getText());
    	        pst.setString(6, txtemail.getText());
    	        pst.setString(7, txtaddress.getText());
    	        pst.setDate(8, dt);
    	        pst.setLong(9, mobile);
    	        
    	        int count = pst.executeUpdate();
    	        txtrespp.setText(count + " RECORD UPDATED");
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    }

    @FXML
    void initialize() {
    	
    	
    	con=MySqlConnector.doConnect();
    	if(con==null)
    	{
    		System.out.println("connection problem");	
    	}
    	else
    	{
    		System.out.println("Connected");
    	}
    
    	
       /* ListView<String>listView=new ListView<>();
        ObservableList<String> papers = FXCollections.observableArrayList();
        papers.add("The Times OF INDIA");
        papers.add("Hindustan Times");
        papers.add("The Tribute Hindustan");
        papers.add("Punjab Kesri");
        papers.add("Indian Tribute");
        papers.add("The Hindu");
        papers.add("The India Express");
        papers.add("The Hitavada");
        txtpaperavailable.setItems(papers);
        
        ListView<String>listView1=new ListView<>();
        ObservableList<String> price = FXCollections.observableArrayList();
        price.add("4");
        price.add("5");
        price.add("6");
        price.add("3");
        price.add("5");
        price.add("7");
        price.add("8");
        price.add("2");
        
        txtpaperprice.setItems(price); */
    	try {
    		pst=con.prepareStatement("select paper from papers;");
    		ResultSet table=pst.executeQuery();
    		System.out.println(table);
    		while(table.next())
    		{
    			String paper=table.getString("paper");
    			ArrayList<String>items=new ArrayList<String>();
    			items.add(paper);
    			txtpaperavailable.getItems().addAll(items);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	
    	//===========================================
    	try {
    		pst=con.prepareStatement("select price from papers;");
    		ResultSet table=pst.executeQuery();
    		System.out.println(table);
    		while(table.next())
    		{
    			String prc=table.getString("price");
    			ArrayList<String> items=new ArrayList<String>();
    			items.add(prc);
    			txtpaperprice.getItems().addAll(items);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	//====
    	ArrayList<String>area=new ArrayList<String>(Arrays.asList("select","gohana","park venue","new cantt road","shiv colony","teacher colony","punjabi colony","hari colony"));
    	txtarea.getItems().addAll(area);
        
    
        assert txtfetch != null : "fx:id=\"dofetch\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtaddress != null : "fx:id=\"txtaddress\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtarea != null : "fx:id=\"txtarea\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtdos != null : "fx:id=\"txtdos\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtemail != null : "fx:id=\"txtemail\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txthawker != null : "fx:id=\"txthawker\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtpaperavailable != null : "fx:id=\"txtpaperavailable\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtpaperprice != null : "fx:id=\"txtpaperprice\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtselectedpaper != null : "fx:id=\"txtselectedpaper\" was not injected: check your FXML file 'CustView.fxml'.";
        assert txtselectedprice != null : "fx:id=\"txtselectedprice\" was not injected: check your FXML file 'CustView.fxml'.";

    }

}
