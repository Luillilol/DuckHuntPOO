package fes.aragon.controller;

import java.io.IOException;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.controller.JuegoController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class InicioController {
	private Scene escena;
	private Scene escenaJuego;
	private Thread hiloFondo;

	public JuegoController hola;
    @FXML
    private Button btnCreditos;

    @FXML
    private Button btnInstruciones;

    @FXML
    private Button btnJugar;

    @FXML
    private Text txtTitulo;

    /*Cosas para que el main funcione*/
    public void iniciar() {
		componentesIniciar();
//		
//		pintar();
//		eventosTeclado();
//		ciclo();
	}

    private void componentesIniciar() {
//		graficos = canvas.getGraphicsContext2D();
//		MusicaCiclica entrada = new MusicaCiclica("musica_entrada");
//		hiloFondo = new Thread(entrada);
//		hiloFondo.start();
//		enemigos = new Enemigos(20, 20, null, 1);
//		fondo=new Fondo(0, 0,"/fes/aragon/resource/fondo/img (1).gif" , 4,24);
//		nave=new Nave(20,255,"/fes/aragon/resource/navefinal.png",2);
//		nave.setrEnemigo(enemigos.getEnemigos());
//		disparos=new Disparos(0, 0, null, 2);
//		nave.setDisparos(disparos);
		
	}
    
    public void setEscena(Scene escena) {
		this.escena = escena;
		
	}
    
    public void eventosVentana() {
		Stage escenario=(Stage)escena.getWindow();
		escenario.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				hiloFondo.stop();
			}
		});
    }
	
    
	
    
    
    
    
    @FXML
    void creditos(ActionEvent event) {
    	System.out.println("Credits");
    	this.nuevaVentana("Creditos");
    	this.cerrarVentana(btnCreditos);
    }

    @FXML
    void instrucciones(ActionEvent event) {
    	System.out.println("Instrucciones");
    	this.nuevaVentana("Instrucciones");
    	this.cerrarVentana(btnInstruciones);
    }

    @FXML
    void jugar(ActionEvent event) {
    	System.out.println("JUGAR");
    	this.nuevaVentana("Juego");
    	this.cerrarVentana(btnJugar);
    	
//    	JuegoController nuevo = new JuegoController();
//    	nuevo.iniciar();
    }
    
    public void nuevaVentana(String archivo) {
		try  {
		
			FXMLLoader root = new FXMLLoader(getClass().getResource("/fes/aragon/fxml/"+archivo+".fxml"));
			Scene scene = new Scene(root.load());
			Stage escenario = new Stage();
			JuegoController hola = root.getController();
			hola.setEscena(scene);
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
