package billingmodule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	public void start(Stage primaryStage) 
		{
		try {
				Parent root=(Parent) FXMLLoader.load(getClass().getResource("BillGenView.fxml")); 
				Scene scene = new Scene(root,800,520);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	public static void main(String[] args) 
	   {
		   launch(args);
	   }
}
