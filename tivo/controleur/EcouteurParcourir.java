package tivo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import tivo.modele.Modele;

public class EcouteurParcourir implements ActionListener {
	
	protected Modele m;

	public EcouteurParcourir(Modele mod) {
		this.m=mod;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			m.fileChooserOpen();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
