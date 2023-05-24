package fes.aragon.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.modelo.Fondo;
import fes.aragon.modelo.Mira;
import fes.aragon.modelo.Pato;
//import fes.aragon.modelo.Disparos;
//import fes.aragon.modelo.Enemigos;
//import fes.aragon.modelo.Fondo;
//import fes.aragon.modelo.Nave;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JuegoController implements Initializable{
	private Scene escena;
	private GraphicsContext graficos;
	private Fondo fondo;
	private Pato pato1;
	private Pato pato2;
	private Mira mira;
	private Thread hiloFondo;
	public int contadorTiempoGlobal=0;
	
	private int contadorTimerPato1=0;
	private int contadorTimerPato2=0;
	
	private int contadorPuntos=0;
	private Timer timerGlobal = new Timer();
	
	
    @FXML
    private Canvas canvas;
    
    @FXML
    private Text txtContadorPuntos;
    @FXML
    private Text txtTiempo;
    @FXML
    private Button btnRegresar;
    
    
    
    public void iniciar() {
    	/*
    	 * Crear la funcion para el timer principal
    	 * */
    	timerGlobal();
    	
		componentesIniciar();		
		pintar();
		eventosTeclado();
		ciclo();
	}
    
    public void timerGlobal() {
    	
    	TimerTask task1 =  new TimerTask() {
    		@Override
    		public void run() {
    			// TODO Auto-generated method stub
    			contadorTiempoGlobal++;
    			System.out.println("TIEMPO:"+contadorTiempoGlobal);
    			if(contadorTiempoGlobal==5) {
    				timerGlobal.cancel();
    				pato1.setTemporizadorActivo(false);
    				pato2.setTemporizadorActivo(false);
    				btnRegresar.setDisable(false);
    			}
    			
    		}
    		
    	};
    	timerGlobal.schedule(task1, 0, 1000);
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
		pato1= new Pato(0, 0, "/fes/aragon/resource/pato1Volteado.png", 4,3);
		pato2= new Pato(0, 0, "/fes/aragon/resource/pato1Volteado.png", 4,3);
		mira = new Mira(0,0,"/fes/aragon/resource/mira.png",1,1);
		
		/*Traspaso las propiedades del rectangulo del pato al objeto mira*/
		mira.setPatoEnemigo1(pato1.getR());
		mira.setPatoEnemigo2(pato2.getR());
		
		
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
				if(contadorTiempoGlobal < 5) {					
					double t = (tiempoActual - tiempoInicio) / 1000000000.00;
	//				fondo.setTiempo(t);
					pato1.setTiempo(t);
					pato2.setTiempo(t);
					calculosLogica();
					pintar();
				}else {
					txtTiempo.setOpacity(1);
					btnRegresar.setOpacity(1);
				}
			}
		};
		tiempo.start();
	}
    
    public void timerPatoRespawn(int i) {	
//    	System.out.println("PATO 1 FALSO");
    		if(contadorTimerPato1==0 && i==1) {  
    			contadorPuntos+=100;
    			System.out.println("EVENTO TIMER RWSPAWN PATO 1");
    			Timer timer = new Timer();
    			TimerTask task =new TimerTask() {

    				@Override
    				public void run() {
    					// TODO Auto-generated method stub
    					System.out.println("TIMER NUEVO PATO 1");
    					pato1.setVida(true);
    					mira.setVidaPato1(true);
    					contadorTimerPato1=0;
    					timer.cancel();
    				}
    				
    			};	
    			timer.scheduleAtFixedRate(task, 2000, 2000);
		    	contadorTimerPato1++;    
		    	System.out.println("TIMER ADENTTRO UNA VEZ PATO 1");
    		}
    		
    		if(contadorTimerPato2==0 && i==2) {   	   
    			contadorPuntos+=100;
    			System.out.println("EVENTO TIMER RWSPAWN 2222222");
    			Timer timer1 = new Timer();
    			TimerTask task =new TimerTask() {

    				@Override
    				public void run() {
    					// TODO Auto-generated method stub
    					System.out.println("TIMER NUEVO222222");
    					pato2.setVida(true);
    					mira.setVidaPato2(true);
    					contadorTimerPato2=0;
    					timer1.cancel();
    				}
    				
    			};	
    			timer1.scheduleAtFixedRate(task, 2000, 2000);
		    	contadorTimerPato2++;    
		    	System.out.println("TIMER ADENTTRO UNA VEZ222222");
    		}
    		
    		
    }
    
    
    private void calculosLogica() {
    	
    	this.pato1.logicaCalculos();
    	this.pato2.logicaCalculos();
    	this.mira.logicaCalculos();
    	
    	pato1.setVida(mira.isVidaPato1());
    	pato2.setVida(mira.isVidaPato2());
    	
    	if(pato1.isVida()==false) {
//    		System.out.println("entro a la condicion");    		
    		timerPatoRespawn(1);    		
    	}
    	if(pato2.isVida()==false) {
//    		System.out.println("entro a la condicion");
//    		contadorPuntos++;
    		timerPatoRespawn(2);    		
    	}
    	//llamar al afuncion del respawn del pato
//    	System.out.println("logca juegocontroller");

	}
    
    private void eventosTeclado() {
    	System.out.println("EVENTO TECLASDO");
    	
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {				
				// TODO Auto-generated method stubs
				mira.teclado(arg0,true);
				
			}			
		});
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				mira.teclado(arg0,false);
			
			}
			
		});
		
	}
    
private void pintar() {
		
		this.fondo.pintar(graficos);
		if(pato1.isVida()==true)
			this.pato1.pintar(graficos);
		if(pato2.isVida()==true)		
			this.pato2.pintar(graficos);		
		this.mira.pintar(graficos);
		
		txtContadorPuntos.setText("Puntos "+contadorPuntos);
		
		

		
	}
    
    public void setEscena(Scene escena) {
		this.escena = escena;	
		System.out.println("ESTABLECER ESCENEA ENUEGO");
		initialize(null, null);
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
    void regresar(ActionEvent event) {
    	System.out.println("BOTON DE REGRESAR");
    	this.hiloFondo.stop();
    	this.nuevaVentana("Inicio");
    	this.cerrarVentana(btnRegresar);
    	
    }
    
    public void nuevaVentana(String archivo) {
		try  {
		
			FXMLLoader root = new FXMLLoader(getClass().getResource("/fes/aragon/fxml/"+archivo+".fxml"));
			Scene scene = new Scene(root.load());
			Stage escenario = new Stage();
			InicioController hola = root.getController();
			hola.setEscena(scene);
			escenario.setScene(scene);
			hola.setHiloFondo(this.hiloFondo);
//			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			hola.eventosVentana();
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

    
    	/*PRUEBA*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(escena!= null) {
			this.iniciar();			
		}
		
	}
}
