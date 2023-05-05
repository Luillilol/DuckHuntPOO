module DuckHunt {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens fes.aragon to javafx.graphics, javafx.fxml;
}
