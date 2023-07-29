package billingmodule;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hawker.MySqlConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BillGenViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker txtbillupto;

    @FXML
    private TextField txtgenbillandsave;

    @FXML
    private DatePicker txtlastbillingdate;

    @FXML
    private TextField txtmissingdays;

    @FXML
    private ComboBox<String> txtmobile;

    @FXML
    private TextField txtpapers;

    @FXML
    private TextField txtprice;

    @FXML
    private Label txtresp;

    @FXML
    private TextField txttotalprice;
    
    
    Connection con;
    PreparedStatement pst;
    
    @FXML
    void docombo(ActionEvent event) {
        String selectedMobile = txtmobile.getValue();

        try {
            String query = "SELECT spapers, sprices FROM customers WHERE mobile=?";
            pst = con.prepareStatement(query);
            pst.setString(1, selectedMobile);
            ResultSet resultSet = pst.executeQuery();
            double totalPrice = 0;

            while (resultSet.next()) {
                String selectedPaper = resultSet.getString("spapers");
                String selectedPrice = resultSet.getString("sprices");

                txtpapers.setText(selectedPaper);
                txtprice.setText(selectedPrice);

                String[] priceArray = selectedPrice.split(",");
                for (String priceStr : priceArray) {
                    double price = Double.parseDouble(priceStr.trim());
                    totalPrice += price;
                }
            }

            txttotalprice.setText(String.valueOf(totalPrice));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    @FXML
    void dobillandsave(ActionEvent event) {
    	String mobile = txtmobile.getValue();
        LocalDate lastBillingDate = txtlastbillingdate.getValue();
        LocalDate billUpToDate = txtbillupto.getValue();
        int missingDays = Integer.parseInt(txtmissingdays.getText());

       
        double pricePerDay = Double.parseDouble(txttotalprice.getText());
        Duration duration = Duration.between(lastBillingDate.atStartOfDay(), billUpToDate.atStartOfDay());

        double totalBill = pricePerDay * duration.toDays() - (pricePerDay * missingDays);
        txtgenbillandsave.setText(String.valueOf(totalBill));
        
        try {
            String query = " insert into bills (mobile,datefrom,dateto, billstatus,bill) values (?, ?, ?, ?,?)";
            pst = con.prepareStatement(query);
          
			pst.setString(1,mobile);
            pst.setDate(2, java.sql.Date.valueOf(lastBillingDate));
            pst.setDate(3, java.sql.Date.valueOf(billUpToDate));
            pst.setInt(4, missingDays);
            pst.setDouble(5, totalBill);
            


            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                txtresp.setText("Bill saved successfully");
            } else {
                txtresp.setText("Failed to save the bill");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void dofetchmobile(ActionEvent event) {
        try {
        	String number="select mobile from customers";
        	pst=con.prepareStatement(number);
        	ResultSet resultset=pst.executeQuery();
        	while(resultset.next()) {
        		String mobilenumber=resultset.getString("mobile");
        		txtmobile.getItems().add(mobilenumber);
        	}
        	
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }

    @FXML
    void dolastbilling(ActionEvent event) {
    	
    	    String selectedMobile = txtmobile.getValue();

    	    try {
    	        String query = "select dos from customers where mobile=?";
    	        pst = con.prepareStatement(query);
    	        pst.setString(1, selectedMobile);
    	        ResultSet resultSet = pst.executeQuery();

    	        if (resultSet.next()) {
    	            String lastBillingDate = resultSet.getString("dos");
    	            txtlastbillingdate.setValue(LocalDate.parse(lastBillingDate));
    	        } else {
    	            System.out.println("No matching record found");
    	        }

    	        resultSet.close();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}


    

    @FXML
    void dototalprice(ActionEvent event) {

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
        assert txtbillupto != null : "fx:id=\"txtbillupto\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtgenbillandsave != null : "fx:id=\"txtgenbillandsave\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtlastbillingdate != null : "fx:id=\"txtlastbillingdate\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtmissingdays != null : "fx:id=\"txtmissingdays\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtpapers != null : "fx:id=\"txtpapers\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtprice != null : "fx:id=\"txtprice\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtresp != null : "fx:id=\"txtresp\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txttotalprice != null : "fx:id=\"txttotalprice\" was not injected: check your FXML file 'BillGenView.fxml'.";

    }

}
