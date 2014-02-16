package animals;

public class Shark extends Fish {

	public Shark(byte type, String name, int age) {
		super(type, name, age);
		this.biologicalSigns = BiologicalSigns.Water;
		this.isPredator = true;
		// TODO Auto-generated constructor stub
	}

}
