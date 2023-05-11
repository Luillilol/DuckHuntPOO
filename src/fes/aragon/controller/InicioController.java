package fes.aragon.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InicioController {

    @FXML
    private Button btnCreditos;

    @FXML
    private Button btnInstruciones;

    @FXML
    private Button btnJugar;

    @FXML
    private Text txtTitulo;

    
    
    
    
    @FXML
    void creditos(ActionEvent event) {
    	System.out.println("Credits");
    	this.nuevaVentana("Creditos");
    	this.cerrarVentana(btnCreditos);
    }

    @FXML
    void instrucciones(ActionEvent event) {
    	System.out.println("Instrucciones");
    }

    @FXML
    void jugar(ActionEvent event) {
    	System.out.println("JUGAR");
    
    }
    
    public void nuevaVentana(String archivo) {
		try  {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + archivo + ".fxml"));
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
//			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();
	}
	
    
    
    

}
