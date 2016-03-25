package tivo.vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import tivo.controleur.EcouteurFiltreBleu;
import tivo.controleur.EcouteurFiltreRouge;
import tivo.controleur.EcouteurFiltreVert;
import tivo.controleur.EcouteurNegatif;
import tivo.controleur.EcouteurNuanceGris;
import tivo.controleur.EcouteurParcourir;
import tivo.controleur.EcouteurQuitter;
import tivo.controleur.EcouteurSauvegarder;
import tivo.modele.Modele;

@SuppressWarnings("serial")
public class VueMenu extends JMenuBar implements Observer {
	protected Modele m;
	protected JMenuItem jMenuItemOuvrir;
	protected JMenuItem jMenuItemEnregistrer;
	protected JMenuItem jMenuItemQuitter;
	protected JMenuItem jMenuItemFiltreRouge;
	protected JMenuItem jMenuItemFiltreVert;
	protected JMenuItem jMenuItemFiltreBleu;
	protected JMenuItem jMenuItemNegatif;
	protected JMenuItem jMenuItemNuanceGris;
	protected ImageIcon iconParcourirMenu = new ImageIcon(VueMenu.class.getResource("/tivo/folder/parcourir-menu.png"));
	protected ImageIcon iconEnregistrer = new ImageIcon(VueMenu.class.getResource("/tivo/folder/enregistrer.png"));
	protected ImageIcon iconQuitterMenu = new ImageIcon(VueMenu.class.getResource("/tivo/folder/quitter-menu.png"));
	protected ImageIcon iconFiltreRouge = new ImageIcon(VueMenu.class.getResource("/tivo/folder/filtreRouge.png"));
	protected ImageIcon iconFiltreVert = new ImageIcon(VueMenu.class.getResource("/tivo/folder/filtreVert.png"));
	protected ImageIcon iconFiltreBleu = new ImageIcon(VueMenu.class.getResource("/tivo/folder/filtreBleu.png"));
	protected ImageIcon iconNegatif = new ImageIcon(VueMenu.class.getResource("/tivo/folder/negatif.png"));
	protected ImageIcon iconNuanceGris = new ImageIcon(VueMenu.class.getResource("/tivo/folder/nuanceGris.png"));
	
	public VueMenu(Modele mod) {
		this.m=mod;
		m.addObserver(this);
		JMenu jMenu1 = new JMenu("Fichier");
		jMenuItemOuvrir = new JMenuItem("Ouvrir");
		jMenuItemOuvrir.setIcon(iconParcourirMenu);
		jMenuItemOuvrir.addActionListener(new EcouteurParcourir(m));
		jMenuItemEnregistrer = new JMenuItem("Enregistrer-sous");
		jMenuItemEnregistrer.setIcon(iconEnregistrer);
		jMenuItemEnregistrer.addActionListener(new EcouteurSauvegarder(m));
		jMenuItemQuitter = new JMenuItem("Quitter");
		jMenuItemQuitter.setIcon(iconQuitterMenu);
		jMenuItemQuitter.addActionListener(new EcouteurQuitter(m));
		jMenu1.add(jMenuItemOuvrir);
		jMenu1.add(jMenuItemEnregistrer);
		jMenu1.add(jMenuItemQuitter);
		JMenu jMenu2 = new JMenu("Filtres");
		jMenuItemFiltreRouge = new JMenuItem("Rouge");
		jMenuItemFiltreRouge.setIcon(iconFiltreRouge);
		jMenuItemFiltreRouge.addActionListener(new EcouteurFiltreRouge(m));
		jMenuItemFiltreVert = new JMenuItem("Vert");
		jMenuItemFiltreVert.setIcon(iconFiltreVert);
		jMenuItemFiltreVert.addActionListener(new EcouteurFiltreVert(m));
		jMenuItemFiltreBleu = new JMenuItem("Bleu");
		jMenuItemFiltreBleu.setIcon(iconFiltreBleu);
		jMenuItemFiltreBleu.addActionListener(new EcouteurFiltreBleu(m));
		jMenuItemNegatif = new JMenuItem("Negatif");
		jMenuItemNegatif.setIcon(iconNegatif);
		jMenuItemNegatif.addActionListener(new EcouteurNegatif(m));
		jMenuItemNuanceGris = new JMenuItem("Nuance de gris");
		jMenuItemNuanceGris.setIcon(iconNuanceGris);
		jMenuItemNuanceGris.addActionListener(new EcouteurNuanceGris(m));
		jMenu2.add(jMenuItemFiltreRouge);
		jMenu2.add(jMenuItemFiltreVert);
		jMenu2.add(jMenuItemFiltreBleu);
		jMenu2.add(jMenuItemNegatif);
		jMenu2.add(jMenuItemNuanceGris);
		this.add(jMenu1);
		this.add(jMenu2);
	}

	public void update(Observable arg0, Object arg1) {
		jMenuItemEnregistrer.setEnabled(m.getFichierSelect() != null && !m.IsRun());
		jMenuItemOuvrir.setEnabled(!m.IsRun());
	}
}
