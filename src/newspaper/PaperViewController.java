package newspaper;

import java.awt.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import newspaper.MySqlConnector;

public class PaperViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
   
    @FXML
    private ComboBox<String> txtpaper;

    @FXML
    private TextField txtprice;
    
    @FXML
    void docombo(MouseEvent event) {
    	

    }
    Connection con;
    PreparedStatement pst;

    @FXML
    void doavail(ActionEvent event) {
    	String paper= (txtpaper.getSelectionModel().getSelectedItem());
    	float price= Float.parseFloat(txtprice.getText());
    	
    															//in parameters
    	try {
				pst=con.prepareStatement("insert into papers values(?,?)");
				pst.setString(1, paper);
				pst.setString(2, txtprice.getText());
				pst.executeUpdate();
				System.out.println("record saved");
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}
    }

    @FXML
    void doedit(ActionEvent event) {
    	String paper= (txtpaper.getSelectionModel().getSelectedItem());
    	float price= Float.parseFloat(txtprice.getText());
    	
    															//in parameters
    	try {
				pst=con.prepareStatement("update papers set price=? where paper=?");
				
			
				pst.setFloat(1, price);
				pst.setString(2, paper);
				
				
				int count=pst.executeUpdate();
				 
			System.out.println(count+"record updated.............");
				
			} 
    	catch (SQLException e) 
    		{
			  e.printStackTrace();
			}
    }

    @FXML
    void dosearch(ActionEvent event) throws SQLException {
    	pst=con.prepareStatement("select * from papers where paper=?");
		String paper= (txtpaper.getSelectionModel().getSelectedItem());
		pst.setString(1, paper);
		
		
	ResultSet table=pst.executeQuery(); //array of objects
	while(table.next())
	{
		
		float price=table.getFloat("price");
		txtprice.setText(String.valueOf(price));
		System.out.println(paper+"\t"+price);

    }
    }
	

    @FXML
    void dowithdraw(ActionEvent event) {
    	try{
        	String paper= (txtpaper.getSelectionModel().getSelectedItem());
        	pst=con.prepareStatement("delete from papers where paper=?");
        	pst.setString(1, paper);
        		int count=pst.executeUpdate();
        if(count!=0)
        		System.out.println(count+ " Records Deleted");
        	else
        	System.out.println("Invalid ");
        	}
        	catch(Exception exp)
        	{
        		System.out.println(exp.toString());
        	}

    }

   
    
    


    @FXML
    void initialize() {
    	
    	ArrayList<String>pap=new ArrayList<String>(Arrays.asList("select","dainik bhaster","The Timer OF India","Hindustan Times","Amar Ujala"));
    	((ComboBox<String>)txtpaper).getItems().addAll(pap);
    
    	con=MySqlConnector.doConnect();
    	if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected");

    	
        assert txtpaper != null : "fx:id=\"txtpaper\" was not injected: check your FXML file 'PaperView.fxml'.";
        assert txtprice != null : "fx:id=\"txtprice\" was not injected: check your FXML file 'PaperView.fxml'.";

    }

}
