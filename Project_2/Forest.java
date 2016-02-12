
public class Forest extends Actor {

    //count variable for Forest and Road classes to keept track of when to spawn
    private int count;

    public Forest(World h, int r, int c) {
	super(h);
	currentLocation = new Hex(r, c);
	count = (int)(Math.random() * 10000);
	alive = true;
    }

    public void actOn(Patient p) {
	p.acceptAction(this);
    }

    //spawns a new Carrion every 10000 seconds
    public Agent spawn() {
	count++;
	if (count % 10000 == 0)
	    return new Carrion(home, currentLocation);
	return null;
    }

}
