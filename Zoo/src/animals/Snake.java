package animals;

public class Snake extends Reptile {

	public Snake(byte type, String name, int age) {
		super(type, name, age);
		this.biologicalSigns = BiologicalSigns.LandAndWater;
		this.isPredator = true;
		// TODO Auto-generated constructor stub
	}

}
