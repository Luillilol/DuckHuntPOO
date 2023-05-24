package fes.aragon.modelo;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Mira extends ComponentesJuego{
	
	private Rectangle r;
	private Rectangle patoEnemigo1;
	private Rectangle patoEnemigo2;
	private ArrayList<Image> imagenes=new ArrayList<>();
	private int coordAlX;
	private int coordAlY;
	private int coordenadaX=300;
	private int coordenadaY=150;
	
	private double tiempo;
	private double tiempoFrame=.2;
	private int indice=0;
	private int frames;
	
	private boolean derecha = false;
	private boolean izquierda = false;
	private boolean arriba = false;
	private boolean abajo = false;
	private boolean disparo = false;
	private boolean vidaPato1=true;	
	private boolean vidaPato2=true;
	
	
	public Mira(int x, int y, String imagen, int velocidad, int frames) {
		super(x, y, imagen, velocidad);
//		this.frames=frames;
//		coordAlX = (int) (Math.random()*810);
//		System.out.println("COORD X: "+coordAlX);
//		coordAlY = (int) (Math.random()*350);
//		System.out.println("COORD Y: "+coordAlY);
//		String ruta="";
//		for(int i=1;i<=frames;i++) {
//			ruta=imagen.replace("1", i+"");
//			imagenes.add(new Image(ruta));
//		}
//		System.out.println("Ruta:"+ruta);
		imagenes.add(new Image(imagen)); 
		
		r= new Rectangle(x, y, 60,60);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public void disparoPato() {
		
		System.out.println("Aqui checo si se dispara o no");
		if(r.getBoundsInLocal().intersects(patoEnemigo1.getBoundsInLocal())) {
			this.vidaPato1=false;
//			System.out.println("COLISION PATO1"+this.vidaPato1);
		}
		if(r.getBoundsInLocal().intersects(patoEnemigo2.getBoundsInLocal())) {
			this.vidaPato2=false;
//			System.out.println("COLISION PATO2"+this.vidaPato2);
		}
	}
	
	

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		graficos.drawImage(imagenes.get(indice),coordenadaX,coordenadaY, 150,150);
		graficos.strokeRect(r.getX()+45, r.getY()+45, r.getWidth(), r.getHeight());
		
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		if(presiona) {
			switch (evento.getCode().toString()) {
			
			case "RIGHT":
				derecha = true;
				System.out.println("DERECHA");
				break;
			case "LEFT":
				izquierda = true;
				System.out.println("IZQUIERDA");
				break;
			case "UP":
				arriba = true;
				System.out.println("ARRIBA");
				break;
			case "DOWN":
				abajo = true;
				System.out.println("ABAJO");
				break;
			case "SPACE":
				disparoPato();
//				disparo=true;
				break;

			}
		}else {
			switch (evento.getCode().toString()) {
			case "RIGHT":
				derecha = false;
				break;
			case "LEFT":
				izquierda = false;
				break;
			case "UP":
				arriba = false;
				break;
			case "DOWN":
				abajo = false;
				break;

			}
		}
		
	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicaCalculos() {
		// TODO Auto-generated method stub
		if (derecha && coordenadaX<750) {
			coordenadaX += 3;
		}
		if (izquierda && coordenadaX>0) {
			coordenadaX -= 3;
		}
		if (arriba && coordenadaY>0) {
			coordenadaY -= 3;
		} 
		if (abajo && coordenadaY<350) {
			coordenadaY += 3;
		}
		
		this.r.setX(coordenadaX);
		this.r.setY(coordenadaY);
		
		/*Detectar colision mira con pato1 y pato2*/
		if(r.getBoundsInLocal().intersects(patoEnemigo1.getBoundsInLocal())) {
//			System.out.println("COLISION PATO1");
		}
		if(r.getBoundsInLocal().intersects(patoEnemigo2.getBoundsInLocal())) {
//			System.out.println("COLISION PATO2");
		}
		
		
		
		/*Colision otro codigo*/
//		int i=0;
//		boolean col=false;
//		for (Rectangle rec : rEnemigo) {			
//			if (this.r.getBoundsInLocal().intersects((rec.getBoundsInLocal()))) {
//				System.out.println("Colision");
//				col=true;
//				break;
//			}
//			i++;
//		}	
		
	}
	
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}





	public Rectangle getPatoEnemigo1() {
		return patoEnemigo1;
	}

	public void setPatoEnemigo1(Rectangle patoEnemigo) {
		this.patoEnemigo1 = patoEnemigo;
	}
	
	public Rectangle getPatoEnemigo2() {
		return patoEnemigo2;
	}

	public void setPatoEnemigo2(Rectangle patoEnemigo) {
		this.patoEnemigo2 = patoEnemigo;
	}
	
	public boolean isVidaPato1() {
		return vidaPato1;
	}
	public boolean isVidaPato2() {
		return vidaPato2;
	}
	
	public void setVidaPato1(boolean vidaPato1) {
		this.vidaPato1 = vidaPato1;
	}	
	public void setVidaPato2(boolean vidaPato2) {
		this.vidaPato2 = vidaPato2;
	}

}
