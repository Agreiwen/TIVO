package tivo.modele;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Modele extends Observable{

	private TraitementPPM ppm;
	private boolean run = false;
	private File fichierSelect;
	private int height;
	private int width;
	private Pixel[][] original;
	
	public Modele(){
    	ppm = new TraitementPPM(this);
	}
	
	public void setFichierSelect(File f){
		fichierSelect = f;
	}
	
	public File getFichierSelect(){
		return fichierSelect;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public Pixel[][] getImage(){
		return ppm.getImage();
	}

	public void fileChooserOpen() throws IOException {
		JFileChooser dialogue = new JFileChooser(new File("."));//"~"));  a décommenté version final
		dialogue.setDialogTitle("Ouvrir un fichier PPM");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "PGM & PPM Images", "ppm", "pgm");
		dialogue.setFileFilter(filter);
        File fichier = null;
        if (dialogue.showOpenDialog(null)==
            JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            setFichierSelect(fichier);
            String fichierSelect = dialogue.getSelectedFile().toString();
            //System.out.println("Nom du fichier : " + fichierSelect);
            if (fichierSelect.lastIndexOf(".") > 0) {
                // On récupère l'extension du fichier
                String ext = fichierSelect.substring(fichierSelect.lastIndexOf("."));
                // Si le fichier est un ppm
                if(ext.equals(".ppm")) {
                	//System.out.println("extension: " + ext);
                	ppm.lireFichier();
                }else {
                	//System.out.println("Choisissez un fichier ppm");
                }
                miseAJour();
            }
            else{
            	//System.out.println("Aucun fichier selectionné.");
            }
        }
	}
	
	public Pixel[][] getOriginal() {
		return original;
	}

	public void setOriginal(Pixel[][] original) {
		this.original = original;
	}

	public void miseAJour(){
		setChanged();
		notifyObservers();
	}
	
	public boolean IsRun(){
		return run;
	}
	
	public void setRun(boolean b){
		run = b;
	}
	
	public void fileChooserSave() throws IOException {
		JFileChooser dialogue = new JFileChooser(fichierSelect);
		dialogue.setDialogTitle("Sauvegarder votre fichier");  		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PGM & PPM Images", "ppm", "pgm");
		dialogue.setFileFilter(filter); 
		dialogue.setSelectedFile(fichierSelect);
        @SuppressWarnings("unused")
		File fichier = null;
		if (dialogue.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    fichier = dialogue.getSelectedFile();
		    String fichierSelect = dialogue.getSelectedFile().toString();
		    int index = fichierSelect.lastIndexOf(".");
	    	if(index != fichierSelect.length()-1){
			    if (index != -1) {
	                // On récupère l'extension du fichier
	                String ext = fichierSelect.substring(fichierSelect.lastIndexOf("."));
	                System.out.println(ext);
	                // Si le fichier est un ppm
	                if(ext.equals(".ppm")) {
	                		//on change pour lui et on l'avertie	 
            				index = fichierSelect.lastIndexOf(".");
	        	    		fichierSelect = fichierSelect.substring(0, index);
	        	    		fichierSelect = fichierSelect+".ppm";
	                }else {
	                	//on change pour lui 
	                	JOptionPane.showMessageDialog(null,
                			    "L'extention que vous avez choisi ne correspond pas a celle de votre fichier\n" +
                			    "initiale, le programme l'a changé pour vous.",
                			    "Attention",
                			    JOptionPane.WARNING_MESSAGE);
        				index = fichierSelect.lastIndexOf(".");
        	    		fichierSelect = fichierSelect.substring(0, index);
        	    		fichierSelect = fichierSelect+(".ppm");
	                }
	            }else{//pas d'extentions on l'a rajoute
    	    		fichierSelect = fichierSelect+(".ppm");
	            }
	    	}else{// juste un point a la fin
	    		fichierSelect = fichierSelect.substring(0, fichierSelect.length()-2);
	    		fichierSelect = fichierSelect+(".ppm");
	    	}
	    	//System.out.println(fichierSelect);
            //sauvegarde du fichier
		    ppm.writeppm(fichierSelect);
            miseAJour();
		}
	}

	private Pixel[][] copie(Pixel[][] im){
		Pixel[][] rep = new Pixel[im.length][im[0].length];
		for(int i = 0; i < original.length; i++)
			for(int j = 0; j < original[0].length; j++)
				rep[i][j] = new Pixel(im[i][j]);
		return rep;
	}
	
	public void filtreVert() {
		ppm.setImage(copie(original));
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				getImage()[i][j].setRed(0);
				getImage()[i][j].setBlue(0);
			}
		}
		miseAJour();
	}

	public void filtreRouge() {
		ppm.setImage(copie(original));
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				getImage()[i][j].setGreen(0);
				getImage()[i][j].setBlue(0);
			}
		}
		miseAJour();
	}

	public void filtreBleu() {
		ppm.setImage(copie(original));
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				getImage()[i][j].setRed(0);
				getImage()[i][j].setGreen(0);
			}
		}
		miseAJour();
	}

	public void filtreNegatif() {
		ppm.setImage(copie(original));
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				getImage()[i][j].setRed(255 - getImage()[i][j].getRed());
				getImage()[i][j].setGreen(255 - getImage()[i][j].getGreen());
				getImage()[i][j].setBlue(255 - getImage()[i][j].getBlue());
			}
		}
		miseAJour();
	}

	public void filtreNuanceGris() {
		ppm.setImage(copie(original));
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				double gris = 0.299 * getImage()[i][j].getRed() + 0.587 * getImage()[i][j].getGreen() + 0.114 * getImage()[i][j].getBlue(); 
				getImage()[i][j].setRed((int)gris);
				getImage()[i][j].setGreen((int)gris);
				getImage()[i][j].setBlue((int)gris);
			}
		}
		miseAJour();
	}
}
