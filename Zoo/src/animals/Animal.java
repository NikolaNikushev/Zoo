package animals;

public class Animal {
	protected String name;
	protected byte type;
	protected int age;
	protected BiologicalSigns biologicalSigns;
	protected boolean isPredator;
	
	public BiologicalSigns getBiologicalSigns() {
		return biologicalSigns;
	}

	public boolean isPredator() {
		return isPredator;
	}

	public void setBiologicalSigns(BiologicalSigns biologicalSigns) {
		this.biologicalSigns = biologicalSigns;
	}

	public void setPredator(boolean isPredator) {
		this.isPredator = isPredator;
	}

	Animal(byte type, String name, int age){
		setName(name);
		setType(type);
		setAge(age);
		biologicalSigns = null;
		isPredator = false;
	}

	public String getName() {
		return name;
	}

	public byte getType() {
		return type;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
