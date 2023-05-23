package fes.aragon.modelo;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Mira extends ComponentesJuego{
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
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		graficos.drawImage(imagenes.get(indice),coordenadaX,coordenadaY, 150,150);
		
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
		if (derecha) {
			coordenadaX += 3;
		}
		if (izquierda) {
			coordenadaX -= 3;
		}
		if (arriba) {
			coordenadaY -= 3;
		}
		if (abajo) {
			coordenadaY += 3;
		}
		
		
//		System.out.println("Tiempo frame:"+tiempoFrame);
//		if((tiempo>tiempoFrame)) {
//			if(this.indice<imagenes.size()-1) {
//				indice++;
//				tiempoFrame+=.2;
//			}else {
//				indice=0;
//			}	
//		}
		
	}
	
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

}
