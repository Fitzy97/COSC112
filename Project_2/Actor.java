
public class Actor implements Agent {

    //All actors in the simulation need these variables
    protected Hex currentLocation;
    protected World home;
    protected boolean alive;

    public Actor(World w) {
	home = w;
        alive = false;
    }

    //Most Actors have an empty update() method because they don't move.  Those that do move override this method.
    public void update() {}

    //Must be empty because of the statement p.acceptAction(this).  Each Actor's implementation of that statement calls a different "this".  If the Actor class had that statement, the compiler would use the Actor class's actOn(...) method instead of the subclasses actOn(...) and would throw an error because there is no acceptAction(Actor a) method.
    public void actOn(Patient p) {}

    public boolean isAlive() {
	return alive;
    }

    //most Actors don't spawn anything
    public Agent spawn() {
	return null;
    }

    //All Actors use this
    public Hex getLocation() {
	return currentLocation;
    }

    //Most acceptAction methods are empty in each class
    public void acceptAction(TasmanianDevil d) {}

    public void acceptAction(Scent s) {}

    public void acceptAction(Carrion c) {}

    public void acceptAction(Forest f) {}

    public void acceptAction(Road r) {}

}
