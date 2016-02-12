

public class Carrion extends Actor {

    public Carrion(World w, Hex h) {
	super(w);
	currentLocation = h;
	alive = true;
    }

    public void actOn(Patient p) {
	p.acceptAction(this);
    }

    //spawns a new Scent every time step
    public Agent spawn() {
	return new Scent(home, getLocation(), this);
    }

    //gets killed when it comes into contact with a devil
    public void acceptAction(TasmanianDevil d) {
	alive = false;
    }

}
