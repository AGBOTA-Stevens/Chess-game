package ChessGame;

//import javax.swing.JFrame;

public class App {

	public static final void jeu(Joueur[] args) {
		Joueur j1 = new Joueur("Joueur n°1", Colors.BLANC, Position.BAS);
		Joueur j2 = new Joueur("Joueur n°2", Colors.NOIR, Position.HAUT);
		args = new Joueur[2];
		args[0] = j1;
		args[1] = j2;
		Carte carte = new Carte();
		carte.affich_itell(j1, j2);
		int i=0;
		while (!(args[0].gameOver()) && !(args[1].gameOver())) {
		    System.out.println("Le tour de " + args[i%2].get_nom());
		    carte.mouvementValide(args[i%2], args[(i+1)%2]);
		    carte.affich_itell(args[0], args[1]);
		    i++;
		}
	}
	public static void main(String[] args) {
		/*
		JFrame fenetre = new JFrame("Mon jeu"); // Utilisation de JFrame
        fenetre.setSize(800, 600);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
        */
		jeu(null);
	}
}