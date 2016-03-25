package tivo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tivo.modele.Modele;

public class EcouteurFiltreVert implements ActionListener {

	protected Modele m;
	
	public EcouteurFiltreVert(Modele model) {
		this.m = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		m.filtreVert();
	}

}
