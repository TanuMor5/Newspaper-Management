package table;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java .util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TableViewController {
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<HawkerBean> txttable;
    
    
    Connection con;
	PreparedStatement pst;


    @FXML
    void dofetch(ActionEvent event) {
        TableColumn<HawkerBean, String>name=new TableColumn<HawkerBean, String>("Hawker Name");     //anything
        name.setCellValueFactory(new PropertyValueFactory<>("hname"));     //name of column
        name.setMinWidth(100);
        
        TableColumn<HawkerBean, String>mobile=new TableColumn<HawkerBean, String>("Hawker mobile number");     //anything
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));     
        mobile.setMinWidth(50);
        
        
        TableColumn<HawkerBean, String>alloarea=new TableColumn<HawkerBean, String>("Hawker allocated area");     //anything
        alloarea.setCellValueFactory(new PropertyValueFactory<>("alloarea"));     
        alloarea.setMinWidth(50);
        
        
        TableColumn<HawkerBean, String>doj=new TableColumn<HawkerBean, String>("Hawker date of  joining");     //anything
        doj.setCellValueFactory(new PropertyValueFactory<>("doj"));     
        doj.setMinWidth(50);
        
        txttable.getColumns().addAll(new ArrayList<>(Arrays.asList(name,mobile,alloarea,doj)));
        txttable.setItems(FetchAllHawkers());
        
        
        
    }
    
    ObservableList<HawkerBean> FetchAllHawkers() {
    	 ObservableList<HawkerBean>  ary=FXCollections.observableArrayList();
    	try {
    		pst=con.prepareStatement("select* from hawkerss");
    		ResultSet table=pst.executeQuery();
    		
    		while(table.next()) {
    			String mno=table.getString("mobile");
    			String name=table.getString("hname");
    			String doj=String.valueOf(table.getDate("doj").toLocalDate());
    			String alloarea=table.getString("alloarea");
    			HawkerBean ref=new HawkerBean(name,mno,alloarea,doj);
    			ary.add(ref);
    		}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return ary;
    }

    @FXML
    void initialize() {
    	con=MySqlConnector.doConnect();
    	if(con==null)
    	{
    		System.out.println("invalid connection");
    	}
    	else
    	{
    		System.out.println("connected");
    	}
        assert txttable != null : "fx:id=\"txttable\" was not injected: check your FXML file 'TableView.fxml'.";

    }

}
