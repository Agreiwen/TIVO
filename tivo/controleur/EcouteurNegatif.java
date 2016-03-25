package tivo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tivo.modele.Modele;

public class EcouteurNegatif implements ActionListener {

	protected Modele m;
	
	public EcouteurNegatif(Modele model) {
		this.m = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.filtreNegatif();
	}

}
