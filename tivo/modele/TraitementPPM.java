package tivo.modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class TraitementPPM {
	
	private Pixel[][] image;
	private Modele model;
	
	public TraitementPPM(Modele m){
		model = m;
	}
	
	public void lireFichier(){
		image = readppm();
		Pixel[][] original = new Pixel[image.length][image[0].length];
		for(int i = 0; i < original.length; i++)
			for(int j = 0; j < original[0].length; j++)
				original[i][j] = new Pixel(image[i][j]);
		model.setOriginal(original);
	}
	
	public Pixel[][] readppm() {
		try {
			File test = model.getFichierSelect();
			InputStream f = new FileInputStream(test);
			BufferedReader d = new BufferedReader(new InputStreamReader(f));
			@SuppressWarnings("unused")
			String magic = d.readLine();
			String line = d.readLine();
			while (line.startsWith("#")) {
				line = d.readLine();
			}
			@SuppressWarnings("resource")
			Scanner s = new Scanner(line);
			int width = s.nextInt();
			int height = s.nextInt();
			model.setWidth(width);
			model.setHeight(height);
			line = d.readLine();
			s = new Scanner(line);
			@SuppressWarnings("unused")
			int maxVal = s.nextInt();
			Pixel[][] im = new Pixel[height][width];
			s = new Scanner(d);
			int count = 0;
			while (count < height * width) {
				int r = s.nextInt();
				int g = s.nextInt();
				int b = s.nextInt();
				im[count / width][count % width] = new Pixel(r,g,b);
				count++;
			}
			/*
			// Affichage de l'image
			System.out.println("Image :\n");
			for(int i=0; i<im.length; i++){
				for(int j=0; j<im[i].length; j++){
					System.out.print(im[i][j]);
				}
				System.out.println();
			}
			*/
			return im;
		}
		catch (Throwable t) {
			t.printStackTrace(System.err);
			return null;
		}
	}
	
	public void writeppm(String test){		
		int width = image[0].length;
	    int height = image.length;
	    try {
	            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(test)));
	            pw.println("P3");
	            pw.print(width);
	            pw.print(" ");
	            pw.println(height);
	            pw.println("255");
	            for(int i = 0; i< height; i++){
	                    for(int j = 0; j<width; j++){
	                            pw.print(image[i][j].getRed()+" "+image[i][j].getGreen()+" "+image[i][j].getBlue());
	                            pw.print("    ");
	                    }
	                    pw.println("\n");
	            }
	            pw.close();
	    } catch (IOException e) {
	           
	            e.printStackTrace();
	    }
	}
	
	public Pixel[][] getImage() {
		return image;
	}
	
	public void setImage(Pixel[][] img){
		this.image = img;
	}
}
