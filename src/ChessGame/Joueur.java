package ChessGame;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
public class Joueur {
	Scanner sc = new Scanner(System.in);
	private String nom;
	private int nbr_unites;
	private Vector<Unite> unites = new Vector<>();
	private Vector<Pion> pions = new Vector<>();
	private boolean gagner;
	private Colors color;
	private Position pos;
	
	// Constructors

	public Joueur(Colors color, Position pos) {
		this.nom = "Joueur";
		this.nbr_unites = 16;
		this.color = color;
		this.pos = pos;
		Unite u;
		Pion p;
		for (int y = 0; y < nbr_unites/2; y++) {
			if (this.pos == Position.HAUT) {
				p = new Pion(1, y, color);
				if (y==0 || y==7)
					u = new Tour(0, y, color);
				else if (y==1 || y==6)
					u = new Cavalier(0, y, color);
				else if (y==2 || y==5)
					u = new Fou(0, y, color);
				else if (y==3)
					u = new Reine(0, y, color);
				else
					u = new Roi(0, y, color);
			} else {
				p = new Pion(6, y, color);
				if (y==0 || y==7)
					u = new Tour(7, y, color);
				else if (y==1 || y==6)
					u = new Cavalier(7, y, color);
				else if (y==2 || y==5)
					u = new Fou(7, y, color);
				else if (y==3)
					u = new Reine(7, y, color);
				else
					u = new Roi(7, y, color);
			}
			this.unites.add(u);
			this.pions.add(p);
		}
		this.gagner = false;
	}
	
	public Joueur(String nom,Colors color, Position pos) {
		this.nom = nom;
		this.nbr_unites = 16;
		this.color = color;
		this.pos = pos;
		Unite u;
		Pion p;
		for (int y = 0; y < nbr_unites/2; y++) {
			if (this.pos == Position.HAUT) {
				p = new Pion(1, y, color);
				if (y==0 || y==7)
					u = new Tour(0, y, color);
				else if (y==1 || y==6)
					u = new Cavalier(0, y, color);
				else if (y==2 || y==5)
					u = new Fou(0, y, color);
				else if (y==3)
					u = new Reine(0, y, color);
				else
					u = new Roi(0, y, color);
			} else {
				p = new Pion(6, y, color);
				if (y==0 || y==7)
					u = new Tour(7, y, color);
				else if (y==1 || y==6)
					u = new Cavalier(7, y, color);
				else if (y==2 || y==5)
					u = new Fou(7, y, color);
				else if (y==3)
					u = new Reine(7, y, color);
				else
					u = new Roi(7, y, color);
			}
			this.unites.add(u);
			this.pions.add(p);
		}
		this.gagner = false;
	}
	public Joueur(Joueur original) {
		this.nom = original.nom;
		this.nbr_unites = original.nbr_unites;
		this.unites = original.unites;
		this.pions = original.pions;
		this.gagner = original.gagner;
		this.color = original.color;
		this.pos = original.pos;
	}
	// Getters
	
	public String get_nom() {
		return nom;
	}
	
	public int get_nbr_unites() {
		return nbr_unites;
	}
	
	public Vector<Unite> get_unites() {
		return unites;
	}
	
	public Vector<Pion> get_pions() {
		return pions;
	}
	
	public boolean agagner() {
		return gagner;
	}
	
	public Colors get_color() {
		return color;
	}
	
	public Position get_pos() {
		return pos;
	}
	
	// Setters
	
	public void set_nom(String nom) {
		this.nom = nom;
	}

	public void set_nbr_unites(int nbr_unites) {
		this.nbr_unites = nbr_unites;
	}

	public void set_unites(int a, int b) {
		Iterator<Unite> iu = this.unites.iterator();
		while (iu.hasNext()) {
			Unite u = (Unite) iu.next();
			if (u.pos_x==a && u.pos_y==b) {
				this.nbr_unites -- ;
				iu.remove();
			}
		}
	}

	public void set_pions(int a, int b) {
		Iterator<Pion> ip = this.pions.iterator();
		Unite u;
		while (ip.hasNext()) {
			Pion p = (Pion) ip.next();
			if (p.pos_x==a && p.pos_y==b) {
				this.nbr_unites -- ;
				ip.remove();
			} else if (p.estPromus()) {
				System.out.println("1- Reine\n2- Tour\n3- Fou\n4- Cavalier");
				int choix = sc.nextInt();
				switch (choix) {
					case 1:
						u = new Reine(p.pos_x, p.pos_y, this.color);
						break;
					case 2:
						u = new Tour(p.pos_x, p.pos_y, this.color);
						break;
					case 3:
						u = new Fou(p.pos_x, p.pos_y, this.color);
						break;
					case 4:
						u = new Cavalier(p.pos_x, p.pos_y, this.color);
						break;
					default:
						u = new Reine(p.pos_x, p.pos_y, this.color);
						break;
				}
			    this.unites.add(u);
			    ip.remove();
			}
		}
	}

	public void set_gagner(boolean gagner) {
		this.gagner = gagner;
	}


	public void set_color(Colors color) {
		this.color = color;
	}


	public void set_pos(Position pos) {
		this.pos = pos;
	}

	// Another methods

	public Unite choix_Unite() {
		int choix;
		for (int i=0; i < unites.size(); i++)
			System.out.println(i+1 + "-" + this.unites.get(i));
		do {
			System.out.print("Selectionez le Numero de l'unite que vous voulez déplacer (ex : 3) ");
			choix = sc.nextInt();
		} while (choix<1 || choix>8);
		return this.unites.get(choix-1);
	}

	public Pion choix_Pion() {
		int choix;
		for (int i=0; i < pions.size(); i++)
			System.out.println(i+1 + "-" + this.pions.get(i));
		do {
			System.out.print("Selectionez le Numero de l'unite que vous voulez déplacer (ex : 3) ");
			choix = sc.nextInt();
		} while (choix<1 || choix>8);
		return this.pions.get(choix-1);
	}
	public boolean echec() {
	    boolean echec = true;
	    for (Unite u : this.unites) {
	        if (u.rule == Rule.Roi){
	            echec = false;
	            break;
	        } 
	    }
	   if (echec)
	       System.out.println("ECHEC ET MAT!!!");
	   return echec;
	}
	public boolean gameOver() {
	    return (this.nbr_unites==0 || this.echec());
	}
}