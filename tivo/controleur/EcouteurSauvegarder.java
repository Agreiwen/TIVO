package tivo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import tivo.modele.Modele;

public class EcouteurSauvegarder implements ActionListener {
	protected Modele m;

	public EcouteurSauvegarder(Modele mod) {
		this.m=mod;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			m.fileChooserSave();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
