
module newspaper {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens newspaper to javafx.graphics, javafx.fxml;
	opens hawker to javafx.graphics, javafx.fxml;
	opens customer to javafx.graphics, javafx.fxml;
	opens billingmodule to javafx.graphics, javafx.fxml;
	opens billcollection to javafx.graphics, javafx.fxml;
	opens table to javafx.graphics, javafx.fxml,javafx.base;
	opens Developer to javafx.graphics, javafx.fxml;
	opens customerpanel to javafx.graphics, javafx.fxml, javafx.base;
	opens billboard to javafx.graphics, javafx.fxml, javafx.base;
	opens adminlogin to javafx.graphics, javafx.fxml;
	opens admindesk to javafx.graphics, javafx.fxml;
	
	
}