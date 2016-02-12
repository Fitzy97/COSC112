
public class Road extends Actor {

    //count variable used by Road and Forest to keep track of when to spawn Carrion
    private int count;

    public Road(World h, int r, int c) {
        super(h);
	currentLocation = new Hex(r, c);
	count = (int)(Math.random() * 100);
	alive = true;
    }

    public void actOn(Patient p) {
	p.acceptAction(this);
    }

    //spawns a new Carrion every 100 time steps
    public Agent spawn() {
	count++;
	if (count % 100 == 0)
	    return new Carrion(home, currentLocation);
	return null;
    }

}
