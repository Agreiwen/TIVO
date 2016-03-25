package tivo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tivo.modele.Modele;

public class EcouteurQuitter implements ActionListener {
	protected Modele m;

	public EcouteurQuitter(Modele mod) {
		this.m=mod;
	}

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
