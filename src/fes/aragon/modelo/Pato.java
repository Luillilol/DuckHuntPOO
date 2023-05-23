package fes.aragon.modelo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Pato extends ComponentesJuego{
	
	private Rectangle r;
	private ArrayList<Image> imagenes=new ArrayList<>();
	private int coordAlX;
	private int coordAlY;
	
	//Valores de estimacion de dimensiones de los patos: 72 altura 54 ancho
	private int coordenadasX;
	private int coordenadasY;
	
	private double tiempo;
	private double tiempoFrame=.2;
	private int indice=0;
	private int frames;
	
	private boolean derecha = false;
	private boolean izquierda = false;
	private boolean arriba = false;
	private boolean abajo = false;
	
//	public TimerTask task=new TimerTask() {
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			System.out.println("TIMER GENERADO TXTO");
//		}
//		
//	};
//	
	//hola
	public Pato(int x, int y, String imagen, int velocidad, int frames) {
		super(x, y, imagen, velocidad);
		this.frames=frames;
		//coords aleatorias de aparicion del pato 
		coordAlX = (int) (Math.random()*810);
		coordAlY = (int) (Math.random()*350);
		coordenadasX=coordAlX;
		coordenadasY=coordAlY;
		this.rumboAleatorio();
		//establecer el timer para el movimiento aleatorio del pat
		//this.temporizador();
		String ruta="";
		for(int i=1;i<=frames;i++) {
			ruta=imagen.replace("1", i+"");
			imagenes.add(new Image(ruta));
		}
		r= new Rectangle(x, y, 72,54);
		
//		imagenes.add(new Image(imagen)); 
		// TODO Auto-generated constructor stub
	}
	
	private void rumboAleatorio() {
		int i = (int) (Math.random()*2)+1; // i representa arriba y abajo
		int j = (int) (Math.random()*2)+1; // j representa izq y derecha
		System.out.println("Num aleatorio de derecha e izquierda");
//		System.out.println("num aleatorio:"+i);
		if(i==1) {
			this.arriba=true;
			this.abajo=false;
		}else if(i==2){
			this.abajo=true;
			this.arriba=false;
		}
		
		if(j==1) {
			this.izquierda=true;
			this.derecha=false;
			System.out.println("Izqueirda TRUE");
		}else if(j==2){
			this.derecha=true;
			this.izquierda=false;
			System.out.println("DERECHA TRUE");			
		}
		
	}
	
	/*public void temporizador(){
		Timer timer = new Timer();
		TimerTask task =new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("TEMPORIZADOR PATOS");
//				rumboAleatorio();
			}
			
		};		
		timer.schedule(task,1,1000);
	}*/
	
	
	

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stubs
		graficos.drawImage(imagenes.get(indice),coordenadasX,coordenadasY, 72,54);
		graficos.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		
//		System.out.println("PATOPATOPATOPAT");
		
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub		
		
	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicaCalculos() {
		// TODO Auto-generated method stub
		System.out.println("logica calculos");
		//Parte que se encarga del calculo del tiempo y del gif
//		System.out.println("Tiempo frame:"+tiempoFrame)
		
		if(this.coordenadasX<=0) {
			//si se pasa checar de nuevo cuál es true y cambiarla a false
			this.izquierda=false;
			this.derecha=true;			
		}
		
		if(this.coordenadasX>=830) {
			//si se pasa checar de nuevo cuál es true y cambiarla a false
			this.izquierda=true;
			this.derecha=false;			
		}
		
		
		if(this.coordenadasY<=0) {
			this.arriba=false;
			this.abajo=true;
			System.out.println("IR ABAJO"+this.coordenadasY);
			
		}
		if(this.coordenadasY>=350) {
			this.arriba=true;
			this.abajo=false;			
		}
		
		
		if((this.derecha==true)&&(this.izquierda==false)) {
			this.coordenadasX++;
		}else if(this.izquierda==true && this.derecha==false) {
			this.coordenadasX--;
		}
		
		if(this.arriba==true && this.abajo==false) {
			this.coordenadasY--;
		}else if(this.abajo==true && this.arriba==false) {
			this.coordenadasY++;
		}
		
		this.r.setX(coordenadasX);
		this.r.setY(coordenadasY);
		
		
//		imagenes.add(new Image(ruta));
		if((tiempo>tiempoFrame)) {
			if(this.indice<imagenes.size()-1) {
				indice++;
				tiempoFrame+=.2;
			}else {
				indice=0;
			}	
		}
		
		//parte encargada del timer y el movimiento del pato
//		System.out.println("PARTE DEL MOV DEL PATO");
		
		
		
		
		
	}
	
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

}
