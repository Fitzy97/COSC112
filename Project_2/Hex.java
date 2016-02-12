import java.awt.geom.Point2D;

public class Hex {

    private int row, col;

    /**
     * Creates a Hex at location (r, c) in a given World.
     */
    public Hex (int r, int c) {
	row = r;
	col = c;
    }

    /**
     * Returns this Hex's row number.
     */
    public int getRow() {
	return row;
    }

    /**
     * Returns this Hex's column number.
     */
    public int getColumn() {
	return col;
    }

    /**
     * Returns whether this Hex is equal to Hex other based on whether
     * or not their row and column are equal.
     */
    public boolean equals(Hex other) {
	return row == other.getRow() && col == other.getColumn();
    }

    public String toString() {
	return "[" + row + ", " + col + "]";
    }

    /**
     * Returns the cartesian location of this Hex.
     */
    public Point2D.Double getCartesian() {

	Point2D.Double a = new Point2D.Double();
	/*
	  Set the y-coordinate to the negative value of the height of
	  the equilateral traingle formed by the centers of three adjacent
	  Hexs times the row index.  Negative because the top of the grid
	  is y=0 and y gets bigger as it goes down.
	*/
	double ycor = -Math.sqrt(3)/2.0 * row;

	/*
	  If the row is even, this Hex'x column is lined up with the
	  Cartesian plane.
	*/
	if (row%2 == 0)
	    a.setLocation(col, ycor);
	/*
	  If the row is odd, add 0.5 to this Hex's column to line it 
	  up with the Cartesian plane.
	*/
	else
	    a.setLocation(col + 0.5, ycor);

	return a;

    }

    /**
     * Returns the distance from this Hex to Hex other in the Cartesian plane.
     */
    public double distance(Hex other) {

	double thisx = getCartesian().getX();
	double thisy = getCartesian().getY();

	double otherx = other.getCartesian().getX();
	double othery = other.getCartesian().getY();

	//distance formula
	return Math.sqrt(Math.pow(thisx - otherx, 2) + Math.pow(thisy - othery, 2));

    }

    /**
     * Returns whether this Hex is adjacent to Hex other based on if the 
     * distance between them is in the range [1, 1.1).  Distance must be 
     * at least 1 or else other is this Hex, but below 1.1 or else its
     * too far away.  The reason for the range is because of slight 
     * discrepencies in the distance method.
     */
    public boolean isAdjacent(Hex other) {
	return Math.abs(distance(other)) >= 1 && Math.abs(distance(other)) < 1.1;
    }

    /**
     * Moves the TasmanianDevil on this Hex to an adjacent Hex one 
     * step closer toward its goal.
     */
    public Hex step(Hex other) {

	double d = distance(other);
	Hex val;
	//The goal is to the above the devil's current location.
	if (other.getRow() < row) {
	    //The goal is above and to the left.
	    if (other.getColumn() < col) {
		/*
		  Because of the diagonal pattern in the Hex columns, if the
		  row is even, the devil must move up and to the column on its
		  left, which will share a side with its current Hex.
		*/
		if (row%2 == 0)
		    val = new Hex(row-1, col-1);
		/*
		  If its in an odd row, the Hex above it and in the same
		  column is actually to the left.
		*/
		else
		    val = new Hex(row-1, col);
	    }
	    //The goal is above and either in the same column or to the right.
	    else {
		/*
		  If the row is even, the hex above and  in the same column
		  is also to the right, therefore it can stay in that column.
		*/
		if (row%2 == 0)
		    val = new Hex(row-1, col);
		//Row is not even.
		else {
		    /*
		      If the goal is in the same column, move up and stay
		      in that column.
		    */
		    if (other.getColumn() == col)
			val = new Hex(row-1, col);
		    //Otherwise, the goal is to the right.
		    else
			val = new Hex(row-1, col+1);
		}
	    }
	}
	//The goal is below the current location.
	else if (other.getRow() > row) {
	    //The goal is below and to the left.
	    if (other.getColumn() < col) {
		//If the row is even, the devil must switch columns to the left.
		if (row%2 == 0)
		    val = new Hex(row+1, col-1);
		/*
		  If the row is odd, the devil can stay in the same column
		  because the Hex in the lower row will also be to the left.
		*/
		else 
		    val = new Hex(row+1, col);
	    }
	    //The goal is below and either to the right or in that column.
	    else {
		/*
		  If the row is even, stay in that column because the devil
		  will go to the right when going down.
		*/
		if (row%2 == 0)
		    val = new Hex(row+1, col);
		//The row is odd.
		else {
		    //If the goal is in the same column, stay in that column.
		    if (other.getColumn() == col)
			val = new Hex(row+1, col);
		    //Otherwise, switch to the column to the right.
		    else
			val = new Hex(row+1, col+1);
		}
	    }
	}
	//The goal is in the same row, but in a column to the right.
	else if (other.getColumn() > col)
	    //Move to the right.
	    val = new Hex(row, col+1);
	/*
	  The goal is in the same row, but in a column to the left.  NOTE:
	  It cannot be the same Hex as the current location because the move()
	  method in the TasmanianDevil class changes the goal location before
	  calling step().
	*/
	else
	    val = new Hex(row, col-1);

	return val;

    }


}
