package ChessGame;

public class Tuile{
	private int x;
	private int y;
	private boolean occuper;
	
	// Constructors
	public Tuile() {
		this.x = 0;
		this.y = 0;
		this.occuper = false;
	}
	public Tuile(int x, int y) {
		this.x = Math.abs(x);
		this.y = Math.abs(y);
		this.occuper = false;
	}
	public Tuile(Tuile originale) {
		this.x = originale.x;
		this.y = originale.y;
		this.occuper = originale.occuper;
	}
	// Getters
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public boolean is_occuper() {
		return occuper;
	}
	// Setters
	public void set_x(int x) {
		this.x = Math.abs(x);
	}
	public void set_y(int y) {
		this.y = Math.abs(y);
	}
	public void set_occuper(boolean occupe) {
		this.occuper = occupe;
	}
	// Another methods
	public boolean occuper() {
		if(!occuper) {
			this.occuper = true;
			return true;
		}
		return false;
	}
	public boolean liberer() {
		if(occuper) {
			this.occuper = false;
			return true;
		}
		return false;
	}
	public String toString() {
		String occupe = (this.occuper) ? "Yes" : "No";
		return "Status :\n" + "\tabs : " + this.x + "\n\tord : " + this.y + "\n\toccuper : " + occupe;
	}
	
	
}