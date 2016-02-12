

public class TasmanianDevil extends Actor {

    private Hex goalLocation;
    //boolean s for whether this devil is sick or not
    private boolean s;

    /**
     * Creates a devil in World h, in a random location in that world
     * and with a random goal.
     */
    public TasmanianDevil(World h) {
        super(h);
	currentLocation = home.randomLocation();
	goalLocation = home.randomLocation();
	alive = true;
    }

    /**
     * Creates a devil in World h, sick if sick = true.
     */
    public TasmanianDevil(World h, boolean sick) {
	this(h);
	s = sick;
    }

    public void update() {
	move();
    }

    public void actOn(Patient p) {
	p.acceptAction(this);
    }

    public void acceptAction(TasmanianDevil d) {
	d.interact(this);
    }

    //sets this devil's goal to that Scent's Carrion's location
    public void acceptAction(Scent s) {
	goalLocation = s.getCarrion().getLocation();
    }

    /**
     * Returns whether this devil is infected.
     */
    public boolean isInfected() {
	return s;
    }

    /**
     * Returns this devil's current Hex goal.
     */
    public Hex getGoal() {
	return goalLocation;
    }

    /**
     * Sets this devil's sick attribute and returns the old value (NOTE:
     * the return value is unnecessary but that's usually how mutators
     * are set up so it might come in handy.
     */
    public boolean setSick(boolean sick) {
	boolean temp = s;
	s = sick;
	return temp;
    }

    /**
     * If this devil is sick and shares the same Hex as other, other has
     * an 80% chance of infection.  Returns the number to be added to 
     * the numSick variable in World class.
     */
    public int interact(TasmanianDevil other) {
	if (s) {
	    if (other.getLocation().equals(currentLocation) &&
		!other.isInfected() &&
		Math.random() < 0.80) {

		other.setSick(true);
		return 1;
	    }
	}
	return 0;
    }

    public String toString() {
	return currentLocation + "->" + goalLocation;
    }

    /**
     * Moves this devil.  If it has reached its goal, set a new random goal,
     * then take one step toward goal.
     */
    public void move() {
	if (currentLocation.equals(goalLocation))
	    goalLocation = home.randomLocation();
	currentLocation = currentLocation.step(goalLocation);
    }

}

