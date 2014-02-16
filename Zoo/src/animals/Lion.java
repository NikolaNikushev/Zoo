package animals;

public class Lion extends Mammal {
	public Lion(byte type, String name, int age) {
		super(type, name, age);
		this.biologicalSigns = BiologicalSigns.Land;
		this.isPredator = true;
		// TODO Auto-generated constructor stub
	}

}
