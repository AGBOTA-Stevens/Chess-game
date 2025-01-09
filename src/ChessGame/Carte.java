package ChessGame;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Carte {
	private String name;
	private int height;
	private int width;
	private Vector<Vector<? extends Tuile>> cases = new Vector<>();
	// Constructors
	Scanner sc = new Scanner(System.in); 
	public Carte() {
		this.name = "ChessCarte";
		this.height = 8;
		this.width = 8;
		for (int i = 0; i < this.height; i++) {
			Vector<Tuile> line = new Vector<>();
			for (int j = 0; j < this.width; j++) {
				Tuile t = new Tuile(i, j);
				line.add(t);
			}
			this.cases.add(line);
		}
	}
	
	public Carte(String n, int h, int w) {
		this.name = n;
		this.height = Math.abs(h);
		this.width = Math.abs(w);
		for (int i = 0; i < this.height; i++) {
			Vector<Tuile> line = new Vector<>();
			for (int j = 0; j < this.width; j++) {
				Tuile t = new Tuile(i, j);
				line.add(t);
			}
			this.cases.add(line);
		}
	}
	
	public Carte(Carte original) {
		this.name = original.name;
		this.height = original.height;
		this.width = original.width;
		for (int i = 0; i < this.height; i++) {
			Vector<Tuile> line = new Vector<>();
			for (int j = 0; j < this.width; j++) {
				Tuile t = new Tuile(i, j);
				line.add(t);
			}
			this.cases.add(line);
		}
	}
	
	// Getters
	
	public String get_name() {
		return name;
	}
	
	public int get_height() {
		return height;
	}
	
	public int get_width() {
		return width;
	}
	
	public Vector<Vector<? extends Tuile>> get_cases() {
		return cases;
	}
	// Setters
	
	public void set_height(int height) {
		this.height = Math.abs(height);
	}
	
	public void set_width(int width) {
		this.width = Math.abs(width);
	}
	
	public void set_cases(Vector<Vector<? extends Tuile>> cases) {
		this.cases = cases;
	}
	
	public void set_name(String name) {
		this.name = name;
	}
	// Another methods
	
	public int surface() {
		return this.height * this.width;
	}
	private void afficher() {
		System.out.print("\t\t\t⊫");
		for (int i = 0; i < this.width; i++)
			System.out.print("===");
		System.out.println("⫥");
		
		for (int i = 0; i < this.height; i++) {
			System.out.print("\t\t\t‖");
			for (int j = 0; j < this.width; j++) {
				if (i%2==0 && j%2==0)
					System.out.print(" □ ");
				else if (i%2==0 && j%2!=0)
					System.out.print(" ■ ");
				else if (i%2!=0 && j%2==0)
					System.out.print(" ■ ");
				else
					System.out.print(" □ ");
			}
			System.out.println("‖");
		}
		System.out.print("\t\t\t⊫");
		for (int i = 0; i < this.width; i++)
			System.out.print("===");
		System.out.println("⫥");
	}
	public String toString() {
		this.afficher();
		return "";
	}
	public boolean tuileExiste(int abs, int ord) {
		if ((0<=abs && abs<this.height) && (0<=ord && ord<this.width))
			return true;
		return false;
	}
	private void caseOcupper(Joueur j1, Joueur j2) {
		for (Unite u : j1.get_unites())
			this.cases.get(u.pos_x).get(u.pos_y).occuper();
		for (Unite u : j2.get_unites())
			this.cases.get(u.pos_x).get(u.pos_y).occuper();
		for (Pion p : j1.get_pions())
			this.cases.get(p.pos_x).get(p.pos_y).occuper();
		for (Pion p : j2.get_pions())
			this.cases.get(p.pos_x).get(p.pos_y).occuper();
	}

	// Affichage intéligente
	public void affich_itell(Joueur j1, Joueur j2) {
		if (j1.get_pos()==j2.get_pos() || j1.get_color()==j2.get_color())
			throw new RuntimeException("Les deux joueurs ne peuvent pas avoir la mème couleur ou etre au mème endroit...");
		this.caseOcupper(j1, j2);
		System.out.print("\t\t⊫");
		for (int i = 0; i < this.width; i++)
			System.out.print("===");
		System.out.println("⫥");
		
		for (int i = 0; i < this.height; i++) {
			System.out.print("\t\t‖");
			for (int j = 0; j < this.width; j++) {
				if (this.cases.get(i).get(j).is_occuper()) {
					for (Unite u : j1.get_unites())
						if (u.pos_x==i && u.pos_y ==j)
							System.out.print(u);
					for (Unite u : j2.get_unites())
						if (u.pos_x==i && u.pos_y ==j)
							System.out.print(u);
					for (Pion p : j1.get_pions())
						if (p.pos_x==i && p.pos_y==j)
							System.out.print(p);
					for (Pion p : j2.get_pions())
						if (p.pos_x==i && p.pos_y==j)
						System.out.print(p);
				} else {
					if (i%2==0 && j%2==0)
						System.out.print(" □ ");
					else if (i%2==0 && j%2!=0)
						System.out.print(" ■ ");
					else if (i%2!=0 && j%2==0)
						System.out.print(" ■ ");
					else
						System.out.print(" □ ");
				}
			}
			System.out.println("‖");
		}
		System.out.print("\t\t⊫");
		for (int i = 0; i < this.width; i++)
			System.out.print("===");
		System.out.println("⫥");
	}

	public void mouvementValide(Joueur j1, Joueur j2) {
		Vector<Tuile> tuilesValide = new Vector<>();
		Unite u;
		int choix, uChoix;
		do {
		    System.out.print("Voulez-vous déplacez une unité ou un pion (1 pour l'unité 0 pour pion): ");
		    uChoix = sc.nextInt();
			u = (uChoix==1) ? j1.choix_Unite() : j1.choix_Pion();
		    int x = u.pos_x, y = u.pos_y;
		    if (u.rule==Rule.Cavalier) {
			    if (this.tuileExiste(x-2, y-1))
				    tuilesValide.add(this.cases.get(x-2).get(y-1));
			
			    if (this.tuileExiste(x-2, y+1))
				    tuilesValide.add(this.cases.get(x-2).get(y+1));
				
			    if (this.tuileExiste(x-1, y-2))
				    tuilesValide.add(this.cases.get(x-1).get(y-2));
			
			    if (this.tuileExiste(x+1, y-2))
				    tuilesValide.add(this.cases.get(x+1).get(y-2));
				
			    if (this.tuileExiste(x-1, y+2))
				    tuilesValide.add(this.cases.get(x-1).get(y+2));
				
			    if (this.tuileExiste(x+1, y+2))
				    tuilesValide.add(this.cases.get(x+1).get(y+2));
			
			    if (this.tuileExiste(x+2, y-1))
				    tuilesValide.add(this.cases.get(x+2).get(y-1));
			
			    if (this.tuileExiste(x+2, y+1))
				    tuilesValide.add(this.cases.get(x+2).get(y+1));
			
		    } else if (u.rule==Rule.Tour) {
		        choix = 1;
			    while (choix<=4) {
				    int i = x;
				    int k = y;
				    if (choix==1) {
				        // en haut
					    i--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i--;
					    }
				    } else if (choix==2) {
				        //en bas
					    i++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i++;
					    }
				    } else if (choix==3) {
				        // à gauche
					    k--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    k--;
					    }
				    } else if (choix==4) {
				        // à droite
					    k++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    k++;
					    }
				    }
				    choix++;
			    }
		    } else if (u.rule==Rule.Roi) {
			    if (this.tuileExiste(x-1, y-1))
				    tuilesValide.add(this.cases.get(x-1).get(y-1));
			
			    if (this.tuileExiste(x-1, y))
				    tuilesValide.add(this.cases.get(x-1).get(y));
				
			    if (this.tuileExiste(x-1, y+1))
				    tuilesValide.add(this.cases.get(x-1).get(y+1));
			
			    if (this.tuileExiste(x, y+1))
				    tuilesValide.add(this.cases.get(x).get(y+1));
				
			    if (this.tuileExiste(x+1, y+1))
				    tuilesValide.add(this.cases.get(x+1).get(y+1));
				
			    if (this.tuileExiste(x+1, y))
				    tuilesValide.add(this.cases.get(x+1).get(y));
			
			    if (this.tuileExiste(x+1, y-1))
				    tuilesValide.add(this.cases.get(x+1).get(y-1));
			
			    if (this.tuileExiste(x, y-1))
				    tuilesValide.add(this.cases.get(x).get(y-1));
			
		    } else if (u.rule==Rule.Pion) {
			    if (j1.get_pos()==Position.HAUT) {
				    if (this.tuileExiste(x+1, y) && !(this.cases.get(x+1).get(y).is_occuper()))
					    tuilesValide.add(this.cases.get(x+1).get(y));
				    if (x==1 && this.tuileExiste(x+2, y) && !(this.cases.get(x+1).get(y).is_occuper()) && !(this.cases.get(x+2).get(y).is_occuper()))
					    tuilesValide.add(this.cases.get(x+2).get(y));
				    if (this.tuileExiste(x+1, y-1))
					    if (this.cases.get(x+1).get(y-1).is_occuper())
						    tuilesValide.add(this.cases.get(x+1).get(y-1));
				    if (this.tuileExiste(x+1, y+1))
					    if (this.cases.get(x+1).get(y+1).is_occuper())
						    tuilesValide.add(this.cases.get(x+1).get(y+1));

			    } else {
				    if (this.tuileExiste(x-1, y) && !(this.cases.get(x-1).get(y).is_occuper()))
					    tuilesValide.add(this.cases.get(x-1).get(y));
				    if (x==6 && this.tuileExiste(x-2, y) && !(this.cases.get(x-1).get(y).is_occuper()) && !(this.cases.get(x-2).get(y).is_occuper()))
					    tuilesValide.add(this.cases.get(x-2).get(y));
				    if (this.tuileExiste(x-1, y-1))
					    if (this.cases.get(x-1).get(y-1).is_occuper())
						    tuilesValide.add(this.cases.get(x-1).get(y-1));
				    if (this.tuileExiste(x-1, y+1))
					    if (this.cases.get(x-1).get(y+1).is_occuper())
						    tuilesValide.add(this.cases.get(x-1).get(y+1));
			    }
		    } else if (u.rule==Rule.Fou) {
		        choix = 1;
			    while (choix<=4) {
				    int i = x;
				    int k = y;
				    if (choix==1) {
				        // en haut à gauche
					    i--;
					    k--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i--;
						    k--;
					    }
				    } else if (choix==2) {
				        // en haut à droite
					    i--;
					    k++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i--;
						    k++;
					    }
				    } else if (choix==3) {
				        // en bas à gauche
				        i++;
					    k--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i++;
						    k--;
					    }
				    } else if (choix==4) {
				        // en bas à droite
				        i++;
					    k++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i++;
						    k++;
					    }
				    }
				    choix++;
			    }
		    } else {
		        choix = 1;
			    while (choix<=8) {
				    int i = x;
				    int k = y;
				    if (choix==1) {
				        // en haut à gauche
					    i--;
					    k--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i--;
						    k--;
					    }
				    } else if (choix==2) {
				        // en haut
					    i--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i--;
					    }
				   } else if (choix==3) {
				        // en haut à droite
					    i--;
					    k++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i--;
						    k++;
					    }
				    } else if (choix==6) {
				        // en bas
					    i++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i++;
					    }
				    } else if (choix==7) {
				        // en bas à gauche
				        i++;
					    k--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i++;
						    k--;
					    }
				    } else if (choix==5) {
				        // en bas à droite
				        i++;
					    k++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    i++;
						    k++;
					    }
				    } else if (choix==8) {
				        // à gauche
					    k--;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    k--;
					    }
				    } else if (choix==4) {
				        // à droite
					    k++;
					    while (this.tuileExiste(i, k)) {
						    if (this.cases.get(i).get(k).is_occuper()) {
							    for (Unite uj : j1.get_unites())
								    if (uj.pos_x==i && uj.pos_y==k)
									    i = -1;
							    for (Pion pj : j1.get_pions())
								    if (pj.pos_x==i && pj.pos_y==k)
									    i = -1;
							    if (i!=-1) 
								    tuilesValide.add(this.cases.get(i).get(k));
							    break;
						    } else
							    tuilesValide.add(this.cases.get(i).get(k));
						    k++;
					    }
				    }
				    choix++;
			    }
		    }
		    if (u.rule==Rule.Tour || u.rule==Rule.Fou || u.rule==Rule.Reine) 
		        choix=5;
		    else {
		        Iterator<Tuile> it = tuilesValide.iterator();
		        while (it.hasNext()) {
			        Tuile t = (Tuile) it.next();
			        if (t.is_occuper()) {
				        for (Unite uj : j1.get_unites())
					        if (uj.pos_x==t.get_x() && uj.pos_y==t.get_y())
						        it.remove();
				        for (Pion pj : j1.get_pions())
					        if (pj.pos_x==t.get_x() && pj.pos_y==t.get_y())
						        it.remove();
			        }
		        }
		    }
		    String resultat = (tuilesValide.size() == 0) ? "Choix invalide" : "Choix valide";
		    System.out.println(resultat);
		} while (tuilesValide.size()==0);
		System.out.print("\t\t⊫");
		for (int i = 0; i < this.width; i++)
			System.out.print("===");
		System.out.println("⫥");
		
		for (int i = 0; i < this.height; i++) {
			System.out.print("\t\t‖");
			for (int j = 0; j < this.width; j++) {
				if (this.cases.get(i).get(j).is_occuper()) {
					for (Unite uj1 : j1.get_unites())
						if (uj1.pos_x==i && uj1.pos_y ==j)
							System.out.print(uj1);
					for (Unite uj2 : j2.get_unites())
						if (uj2.pos_x==i && uj2.pos_y ==j)
							System.out.print(uj2);
					for (Pion pj1 : j1.get_pions())
						if (pj1.pos_x==i && pj1.pos_y==j)
							System.out.print(pj1);
					for (Pion pj2 : j2.get_pions())
						if (pj2.pos_x==i && pj2.pos_y==j)
							System.out.print(pj2);
				} else {
				    boolean casser = false;
				    int b = 1;
				    for (int n=0; n<tuilesValide.size(); n++) {
				        if (tuilesValide.get(n).get_x()==i && tuilesValide.get(n).get_y()==j) {
				            b += n;
				            System.out.print(" " + b + " ");
				            casser = true;
				            break;
				        }
				    }
				    if (casser)
				        continue;
					else if (i%2==0 && j%2==0)
						System.out.print(" □ ");
					else if (i%2==0 && j%2!=0)
						System.out.print(" ■ ");
					else if (i%2!=0 && j%2==0)
						System.out.print(" ■ ");
					else
						System.out.print(" □ ");
				    
				}
			}
			System.out.println("‖");
		}
		System.out.print("\t\t⊫");
		for (int i = 0; i < this.width; i++)
			System.out.print("===");
		System.out.println("⫥");
		System.out.print("Les mouvements possibles sont: ");
		for (int j= 0; j<tuilesValide.size(); j++)
		    System.out.print(j + 1 + " ");
		System.out.println("\nNB: si le numéros n'est pas sur l'affichage de sugestion, alors c'est qu'il y a une unité adverse sur cette place...");
		System.out.print("\nChoisissez un des mouvements ");
		int j = sc.nextInt();
		j--;
		if (uChoix==1) {
		    j2.set_unites(tuilesValide.get(j).get_x(), tuilesValide.get(j).get_y());
		    j2.set_pions(tuilesValide.get(j).get_x(), tuilesValide.get(j).get_y());
		    this.cases.get(u.pos_x).get(u.pos_y).liberer();
		    for (Unite uj : j1.get_unites())
		        if (uj.pos_x==u.pos_x && uj.pos_y==u.pos_y)
		            uj.seDeplacer(tuilesValide.get(j).get_x(), tuilesValide.get(j).get_y());
		} else {
			j2.set_unites(tuilesValide.get(j).get_x(), tuilesValide.get(j).get_y());
		    j2.set_pions(tuilesValide.get(j).get_x(), tuilesValide.get(j).get_y());
		    this.cases.get(u.pos_x).get(u.pos_y).liberer();
		    for (Pion pj : j1.get_pions())
		        if (pj.pos_x==u.pos_x && pj.pos_y==u.pos_y)
		            pj.seDeplacer(tuilesValide.get(j).get_x(), tuilesValide.get(j).get_y());
		    
		}
	}
}