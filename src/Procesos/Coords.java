package Procesos;

public class Coords {
	private int i;
	private int j;
	private char hold;

	public Coords(int i, int j, char hold) {
		this.i = i;
		this.j = j;
		this.hold = hold;
	}

	public Coords(Coords coords) {
		this.i = coords.i;
		this.j = coords.j;
		this.hold = coords.hold;
	}

	public boolean equalS (Object other) {
		if (other == null) {
                    return false;
                }
		if (other == this) {
                    return true;
                }
		if (this.i == ((Coords) other).i && this.j == ((Coords) other).j) {
                    return true;
                }
		if (!(other instanceof Coords)) {
                    return false;
                }
		return false;
	}

	public char getHold() {
		return this.hold;
	}

	public int getI() {
		return this.i;
	}

	public int getJ() {
		return this.j;
	}

	public boolean movDown(int limit) {
		this.i++;
		if (this.i == limit) {
			this.i--;
			return false;
		}
		return true;
	}

	public boolean movLeft() {
		this.j--;
		if (this.j < 0) {
			this.j = 0;
			return false;
		}
		return true;
	}

	public boolean movRight(int limit) {
		this.j++;
		if (this.j == limit) {
			this.j--;
			return false;
		}
		return true;
	}

	public boolean movUp() {
		this.i--;
		if (this.i < 0) {
			this.i = 0;
			return false;
		}
		return true;
	}

	public Coords newInstance(Coords n) {
		return new Coords(n.i, n.j, n.hold);
	}

	public void setCords(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public void setHold(char hold) {
		this.hold = hold;
	}

	@Override
	public String toString() {
		return "(" + this.i + "," + this.j + ")";
	}
}
