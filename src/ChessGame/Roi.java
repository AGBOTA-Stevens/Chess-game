package ChessGame;

public class Roi extends Unite{

	public Roi(int x, int y, Colors color) {
		super(x, y, color, Rule.Roi);
	}

	public boolean enDanger() {
		return false;
	}

}
