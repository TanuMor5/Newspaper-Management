package admindesk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdmindeskViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void dobillcollector(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/billcollection/BillCollView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }

    }

    @FXML
    void dobillgenerator(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/billingmodule/BillGenView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }

    @FXML
    void dobillstatus(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/billboard/BillboardView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }

    @FXML
    void docustomergoogler(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/customerpanel/CustomerView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }

    @FXML
    void docustomermaster(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/customer/CustView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }

    @FXML
    void dodisplayhawkers(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/table/TableView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }

    @FXML
    void dohawkermanager(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/hawker/HawView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }

    @FXML
    void dopapermaster(ActionEvent event) {
    	  try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/newspaper/PaperView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }
    

    @FXML
    void developer(ActionEvent event) {
    	 try {
         	 Parent root=FXMLLoader.load(getClass().getResource("/Developer/DeveloperView.fxml"));
         	 Scene scene=new Scene(root);
         	 Stage stage=new Stage();
         	 stage.setScene(scene);
         	 stage.show();
          }
          catch(Exception e)
          {
         	 e.printStackTrace();
          }
    }

    @FXML
    void initialize() {

    }

}