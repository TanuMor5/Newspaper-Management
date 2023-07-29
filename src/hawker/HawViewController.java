package hawker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import hawker.MySqlConnector;


public class HawViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtallocatearea;

    @FXML
    private ComboBox<String> txtarea;

    @FXML
    private ComboBox<String> txthawkername;

    @FXML
    private ImageView txtimage;

    @FXML
    private TextField txtmobile;
    
    @FXML
    private DatePicker txtdoj;

    @FXML
    private Label txtresp;
    
    @FXML
    private Label txtpath;
    
    Connection con;
    PreparedStatement pst;
    
    @FXML
    void doselectarea(ActionEvent event) {

		String area=txtarea.getSelectionModel().getSelectedItem();
		
		txtallocatearea.setText(area+"  ");

    }


    @FXML
    void doenroll(ActionEvent event) {
    	  String name=(txthawkername.getSelectionModel().getSelectedItem());
          int mobile=Integer.parseInt(txtmobile.getText());
          LocalDate ld=txtdoj.getValue();
          java.sql.Date dt=java.sql.Date.valueOf(ld);
          
          
          try {
       	   pst=con.prepareStatement("insert into hawkerss values(?,?,?,?,?,?)");
       	pst.setString(1, txthawkername.getValue());
       	pst.setLong(2, mobile);
       	pst.setString(3, txtaddress.getText());
       	pst.setString(4, txtallocatearea.getText());
       	pst.setString(5, txtpath.getText());
       	pst.setDate(6, dt);
       	   pst.executeUpdate();
       	   txtresp.setText("howker enrolled");
          }
          catch(SQLException e)
          {
       	   e.printStackTrace();
          }
       }

    

    

    @FXML
    void dofire(ActionEvent event) {
    	try {
    		String name=(txthawkername.getSelectionModel().getSelectedItem());
    		pst=con.prepareStatement("delete from hawkerss where hname=?");
    		pst.setString(1,txthawkername.getValue());
    		int count=pst.executeUpdate();
    		if(count!=0)
    			txtresp.setText(count+"Howker Deleted");
    		else
    			txtresp.setText("Invalid ID");
    		
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}
    }
    

    

    @FXML
    void donew(ActionEvent event) {
    	  txthawkername.setValue("");
          txtmobile.setText("");
          txtaddress.setText("");
          txtallocatearea.setText("");
          txtpath.setText("");
          txtimage.setImage(null);
          txtdoj.getEditor().clear();
    }

    @FXML
    void dosearch(ActionEvent event) {
    	try {
    	String name=(txthawkername.getSelectionModel().getSelectedItem());
       	 pst=con.prepareStatement("select* from hawkerss where hname=?");
       	 pst.setString(1,name);
       	 ResultSet table=pst.executeQuery();
       	 while(table.next())
       	 {
       		 
       		 Long mobile=table.getLong("mobile");
       		String address =table.getString("address");
       		String alloarea=table.getString("alloarea");
       		 
       		 String picpath=table.getString("picpath");
       		 java.sql.Date dobb= table.getDate("doj");
       	
   		 txtmobile.setText(String.valueOf(mobile));
   		 txtaddress.setText(address);
   		 txtallocatearea.setText(alloarea);
   		 txtpath.setText(picpath);
   		 txtdoj.setValue(dobb.toLocalDate());
   		 txtimage.setImage(new Image(new FileInputStream(picpath)));
       		 
       		 System.out.println(name+"\t"+mobile+"\t"+address+"\t"+alloarea +"\t"+picpath+"\t"+dobb.toString());
       	 }
        }
        catch(Exception exp)
        {
       	 System.out.println(exp);
        }
    }

    @FXML
    void doupdate(ActionEvent event) {
    	String name=(txthawkername.getSelectionModel().getSelectedItem());
    	long mobile=Long.parseLong(txtmobile.getText());
         LocalDate ld=txtdoj.getValue();
         java.sql.Date dt=java.sql.Date.valueOf(ld);
         try {
         	pst=con.prepareStatement("update hawkerss set mobile=?,address=?,alloarea=?,picpath=?, doj=? where hname=?");
         	
         	   pst.setLong(1,mobile);
         	   pst.setString(2,txtaddress.getText());
         	   pst.setString(3,txtallocatearea.getText());
         	   pst.setString(4, txtpath.getText());
             pst.setDate(5,dt);
             pst.setString(6,txthawkername.getValue());
         	int count=pst.executeUpdate();
         	txtresp.setText(count+"RECORD UPDATED---");
         }
         catch(SQLException e)
         {
         	e.printStackTrace();
         }
    }

    @FXML
    void douploadpic(ActionEvent event) throws FileNotFoundException {
    	 FileChooser fileChooser=new FileChooser();
         fileChooser.setTitle(("OPERN RESOURCE FILE"));
         fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Filees","*.png","*.jpg","*.gif"));
         File  selectedFile=fileChooser.showOpenDialog(null);
         if(selectedFile!=null)
         {
      	   txtpath.setText(selectedFile.getPath());
      	   Image img=new Image(selectedFile.toURI().toString());
      	   System.out.println(selectedFile.toURI().toString());
      	   txtimage.setImage(new Image(new FileInputStream(selectedFile)));
         }
         
         else
         {
      	   txtpath.setText("nopic.jpg");
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
    	 txtpath.setVisible(false);
    	
       try {
    	   pst=con.prepareStatement("select hname from hawkerss;");
    	   ResultSet table=pst.executeQuery();
    	   System.out.println(table);
    	   while(table.next())
    	   {
    		   String name=table.getString("hname");
    		   ArrayList<String> items1=new ArrayList<String>();
    		   items1.add(name);
    		   txthawkername.getItems().addAll(items1);
    		   
    	   }
       }catch(SQLException e)
       {
    	   e.printStackTrace();
       }
        ArrayList<String>area=new ArrayList<String>(Arrays.asList("select","gohana","park venue","new cantt road","shiv colony","teacher colony","punjabi colony","hari colony"));
    	txtarea.getItems().addAll(area);
    	txtpath.setText("nopic.jpg");
    	
    
        assert txtaddress != null : "fx:id=\"txtaddress\" was not injected: check your FXML file 'HawView.fxml'.";
        assert txtallocatearea != null : "fx:id=\"txtallocatearea\" was not injected: check your FXML file 'HawView.fxml'.";
        assert txtarea != null : "fx:id=\"txtarea\" was not injected: check your FXML file 'HawView.fxml'.";
        assert txthawkername != null : "fx:id=\"txthawkername\" was not injected: check your FXML file 'HawView.fxml'.";
        assert txtimage != null : "fx:id=\"txtimage\" was not injected: check your FXML file 'HawView.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'HawView.fxml'.";
        assert txtresp != null : "fx:id=\"txtresp\" was not injected: check your FXML file 'HawView.fxml'.";

    }

    }
