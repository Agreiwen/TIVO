package tivo.vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import tivo.modele.Modele;
 
@SuppressWarnings("serial")
class MainClass extends JFrame{
	
	public MainClass(){
		super("TIVO - Traitement d'image");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Modele m = new Modele();	    
	    VueGraphique vg = new VueGraphique(m);
	    this.add(vg, BorderLayout.CENTER);
        this.setJMenuBar(new VueMenu(m));
        m.miseAJour();
	    pack() ;
        setVisible(true);
	}
	
	public static void main(String[] args) {
        new MainClass() ;
    }
}