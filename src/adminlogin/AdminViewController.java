package adminlogin;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class AdminViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label txtresp;


    @FXML
    private PasswordField txtpassword;

    @FXML
    void dologin(ActionEvent event) {
    	String password="abc";
    	 String enteredPassword = txtpassword.getText();

         
         if (enteredPassword.equals(password)) {
            
             try {
            	 Parent root=FXMLLoader.load(getClass().getResource("/admindesk/AdmindeskView.fxml"));
            	 Scene scene=new Scene(root);
            	 Stage stage=new Stage();
            	 stage.setScene(scene);
            	 stage.show();
             }
             catch(Exception e)
             {
            	 e.printStackTrace();
             }
         } else {
            
             txtresp.setText("invalid password");
         }
    }

    @FXML
    void initialize() {
        assert txtpassword != null : "fx:id=\"txtpassword\" was not injected: check your FXML file 'AdminView.fxml'.";

    }

}
