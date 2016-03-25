package tivo.vue;

import javax.swing.JPanel;

import tivo.modele.Modele;

@SuppressWarnings("serial")
public class VueGraphique extends JPanel{

	protected Modele m;
	protected VueImage vi;
	
	public VueGraphique(Modele mod) {
		this.m=mod;
		vi = new VueImage(m);
		this.add(vi);
	}
}
