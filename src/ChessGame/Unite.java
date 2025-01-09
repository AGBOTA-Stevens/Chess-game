package ChessGame;

public abstract class Unite {
	protected int pos_x;
	protected int pos_y;
	protected Colors color;
	protected Rule rule;
	protected char type;
	
	// Constructors
	
	public Unite(int x, int y, Colors color, Rule rule) {
		this.pos_x = Math.abs(x);
		this.pos_y = Math.abs(y);
		this.color = color;
		this.rule = rule;
		if (this.color == Colors.BLANC) {
			if (this.rule == Rule.Reine)
				this.type = '♕';
			else if (this.rule == Rule.Roi)
				this.type = '♔';
			else if (this.rule == Rule.Tour)
				this.type = '♖';
			else if (this.rule == Rule.Cavalier)
				this.type = '♘';
			else if (this.rule == Rule.Fou)
				this.type = '♗';
			else 
				this.type = '♙';
		} else {
			if (this.rule == Rule.Reine)
				this.type = '♛';
			else if (this.rule == Rule.Roi)
				this.type = '♚';
			else if (this.rule == Rule.Tour)
				this.type = '♜';
			else if (this.rule == Rule.Cavalier)
				this.type = '♞';
			else if (this.rule == Rule.Fou)
				this.type = '♝';
			else 
				this.type = '♟';
		}
		
	}
	
	public Unite(Unite original) {
		this.pos_x = original.pos_x;
		this.pos_y = original.pos_y;
		this.color = original.color;
		this.rule = original.rule;
		this.type = original.type;
	}
	
	
	// Getters
	
	public int get_pos_x() {
		return pos_x;
	}
	
	public int get_pos_y() {
		return pos_y;
	}
	
	public Colors get_color() {
		return color;
	}
	
	public Rule get_rule() {
		return rule;
	}
	
	public char get_type() {
		return type;
	}
	
	
	// Setters
	
	public void set_pos_x(int pos_x) {
		this.pos_x = Math.abs(pos_x);
	}
	
	public void set_pos_y(int pos_y) {
		this.pos_y = Math.abs(pos_y);
	}
	
	public void set_color(Colors color) {
		this.color = color;
	}
	
	public void set_rule(Rule rule) {
		this.rule = rule;
	}
	
	public void set_type(char type) {
		this.type = type;
	}
	
	
	// Another methods
	
	public String toString() {
		return " " + this.type + " ";
	}
	
	public void seDeplacer(int new_x, int new_y) {
		this.pos_x = Math.abs(new_x);
		this.pos_y = Math.abs(new_y);
	}
}