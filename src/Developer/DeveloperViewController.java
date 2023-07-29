package Developer;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import hawker.MySqlConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DeveloperViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtb;

    @FXML
    private Label txtc;

    @FXML
    private Label txtd;

    @FXML
    private Label txti;

    @FXML
    private Label txtid;

    @FXML
    private Label txtn;

    @FXML
    private ImageView txts;

    @FXML
    private ImageView txtt;
    Connection con;
    PreparedStatement pst;

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
        assert txtb != null : "fx:id=\"txtb\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert txtc != null : "fx:id=\"txtc\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert txtd != null : "fx:id=\"txtd\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert txti != null : "fx:id=\"txti\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert txtid != null : "fx:id=\"txtid\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert txtn != null : "fx:id=\"txtn\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert txts != null : "fx:id=\"txts\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert txtt != null : "fx:id=\"txtt\" was not injected: check your FXML file 'DeveloperView.fxml'.";

    }

}
