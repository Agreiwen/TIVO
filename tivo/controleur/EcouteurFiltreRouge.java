package tivo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tivo.modele.Modele;

public class EcouteurFiltreRouge implements ActionListener {

	protected Modele m;
	
	public EcouteurFiltreRouge(Modele model) {
		this.m = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.filtreRouge();
	}

}
