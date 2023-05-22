package fes.aragon.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.modelo.Fondo;
import fes.aragon.modelo.Mira;
import fes.aragon.modelo.Pato;
//import fes.aragon.modelo.Disparos;
//import fes.aragon.modelo.Enemigos;
//import fes.aragon.modelo.Fondo;
//import fes.aragon.modelo.Nave;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JuegoController implements Initializable{
	private Scene escena;
	private GraphicsContext graficos;
	private Fondo fondo;
	private Pato pato1;
	private Mira mira;
	private Thread hiloFondo;
	
    @FXML
    private Canvas canvas;
    
    public void iniciar() {
		componentesIniciar();		
		pintar();
		eventosTeclado();
		ciclo();
	}
    
    private void componentesIniciar() {
		graficos = canvas.getGraphicsContext2D();
		
		/*carga la musica*/
		MusicaCiclica entrada = new MusicaCiclica("musica_entrada");
		hiloFondo = new Thread(entrada);
//		hiloFondo.start();
		
//		enemigos = new Enemigos(20, 20, null, 1);
		
		/*cargarfondo*/
		fondo=new Fondo(0, 0,"/fes/aragon/resource/fondo.jpg",1,2);
		pato1= new Pato(0, 0, "/fes/aragon/resource/pato1.png", 4,3);
		mira = new Mira(0,0,"/fes/aragon/resource/mira.png",1,1);
//		nave=new Nave(20,255,"/fes/aragon/resource/navefinal.png",2);
//		nave.setrEnemigo(enemigos.getEnemigos());
//		disparos=new Disparos(0, 0, null, 2);
//		nave.setDisparos(disparos);
		
	}
    
    public void ciclo() {
		long tiempoInicio = System.nanoTime();
		
		AnimationTimer tiempo = new AnimationTimer() {
			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicio) / 1000000000.00;
				System.out.println(t);
//				fondo.setTiempo(t);
				pato1.setTiempo(t);
				calculosLogica();
				pintar();
			}

		};
		tiempo.start();
	}
    
    private void calculosLogica() {
    	this.pato1.logicaCalculos();
//    	this.mira.logicaCalculos();
//		this.enemigos.logicaCalculos();
//		this.fondo.logicaCalculos();
//		this.nave.logicaCalculos();
//		this.disparos.logicaCalculos();
	}
    
    private void eventosTeclado() {
    	System.out.println("EVENTO TECLASDO");
    	
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {				
				// TODO Auto-generated method stubs
//				mira.teclado(arg0,true);
				
			}			
		});
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
//				mira.teclado(arg0,false);
			
			}
			
		});
		
	}
    
private void pintar() {
		
		this.fondo.pintar(graficos);
		this.pato1.pintar(graficos);
		this.mira.pintar(graficos);
//		this.nave.pintar(graficos);
//		this.enemigos.pintar(graficos);
//		this.disparos.pintar(graficos);
		
	}
    
    public void setEscena(Scene escena) {
		this.escena = escena;	
		System.out.println("ESTABLECER ESCENEA ENUEGO");
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

    
    	/*PRUEBA*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("HOLA IMPLEMETNTAR");
		this.iniciar();
		
		
	}
    
    
    
    
}
