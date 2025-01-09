package ChessGame;

public class Pion extends Unite {

	public Pion(int x, int y, Colors color) {
		super(x, y, color, Rule.Pion);
	}
	
	public boolean estPromus() {
	    return ((this.pos_x == 0) || (this.pos_x == 7));
	}

}
