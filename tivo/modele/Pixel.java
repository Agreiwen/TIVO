package tivo.modele;

public class Pixel {

	private int red;
	private int green;
	private int blue;
	
	Pixel(int r, int g, int b){
		this.red=r;
		this.green=g;
		this.blue=b;
	}
	
	public Pixel(Pixel pixel) {
		red = pixel.red;
		green = pixel.green;
		blue = pixel.blue;
	}

	public int getRed(){
		return red;
	}
	
	public int getGreen(){
		return green;
	}
	
	public int getBlue(){
		return blue;
	}
	
	public void setRed(int r){
		this.red = r;
	}
	
	public void setGreen(int g){
		this.green = g;
	}
	
	public void setBlue(int b){
		this.blue = b;
	}
	
	public String toString(){
		return "["+red+" "+green+" "+blue+"]";
	}
}