

public class Scent extends Actor {

    //needs a Carrion so that when a devil hits a Scent, it can access that Scent's Carrion's location
    private Carrion carrion;
    private Hex goalLocation;

    public Scent(World w, Hex h, Carrion c) {
        super(w);
	currentLocation = h; 
	goalLocation = home.randomBorder();
	alive = true;
	carrion = c;
    }

    public void actOn(Patient p) {
	p.acceptAction(this);
    }

    public void update() {
	move();
    }

    public void move() {
	if (currentLocation.equals(goalLocation))
	    alive = false;
	else
	    currentLocation = currentLocation.step(goalLocation);
    }

    //for the devil's to track the Carrion
    public Carrion getCarrion() {
	return carrion;
    }

}
