package billcollection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hawker.MySqlConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BillCollViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtamount;

    @FXML
    private DatePicker txtendingdate;

    @FXML
    private TextField txtmobile;

    @FXML
    private Label txtrecord;

    @FXML
    private DatePicker txtstartingdate;
    

    Connection con;
    PreparedStatement pst;

    @FXML
    void dobilldetails(ActionEvent event) {
    	 String mobileNumber = txtmobile.getText();

         
         if (!mobileNumber.isEmpty()) {
             try {
                
                 String detail = "select bill, datefrom, dateto from bills where mobile = ?";
                 pst = con.prepareStatement(detail);
                 pst.setString(1, mobileNumber);
                 
                 ResultSet resultSet = pst.executeQuery();
                 if (resultSet.next()) {
                    
                     double amount = resultSet.getDouble("bill");
                     String dateFrom = resultSet.getString("datefrom");
                     String dateTo = resultSet.getString("dateto");
                     txtamount.setText(String.valueOf(amount));
                     txtstartingdate.setValue(LocalDate.parse(dateFrom));
                     txtendingdate.setValue(LocalDate.parse(dateTo));
                 } else {
                     txtrecord.setText("No bill found for this mobile number");
                 }
                
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         } else {
             txtrecord.setText("Please enter a mobile number.");
         }
     }


    

    @FXML
    void dodone(ActionEvent event) {
    	 String bill = txtamount.getText();
         String startdate = txtstartingdate.getValue().toString();
         String enddate = txtendingdate.getValue().toString();

         if (!bill.isEmpty() && startdate != null && enddate != null) {
             try {
                 txtrecord.setText("Payment is done");

                 
                
             } catch (Exception e) {
                 e.printStackTrace();
             }
         } else {
             // Fields are not filled
             txtrecord.setText("Please fill in all the fields.");
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
        assert txtamount != null : "fx:id=\"txtamount\" was not injected: check your FXML file 'BillCollView.fxml'.";
        assert txtendingdate != null : "fx:id=\"txtendingdate\" was not injected: check your FXML file 'BillCollView.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'BillCollView.fxml'.";
        assert txtrecord != null : "fx:id=\"txtrecord\" was not injected: check your FXML file 'BillCollView.fxml'.";
        assert txtstartingdate != null : "fx:id=\"txtstartingdate\" was not injected: check your FXML file 'BillCollView.fxml'.";

    }

}
