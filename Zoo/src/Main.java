import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.openmbean.InvalidKeyException;

import animals.Animal;
import animals.Dog;
import animals.Dolphin;
import animals.Fish;
import animals.Lion;
import animals.Lizard;
import animals.Mammal;
import animals.Reptile;
import animals.Shark;
import animals.Snake;
import animals.Trout;
import animals.Turtle;

public class Main {

	public static void main(String[] args) {
		String input = "";

		Scanner sc = null;
		try {
			try {
				sc = new Scanner(new InputStreamReader(new FileInputStream(
						"input/animals.txt"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		firstRun = true;
		ArrayList<Mammal> mammalArray = new ArrayList<Mammal>();
		ArrayList<Reptile> reptileArray = new ArrayList<Reptile>();
		ArrayList<Fish> fishArray = new ArrayList<Fish>();
		ArrayList<Animal> animalsArray = new ArrayList<Animal>();
		while (sc.hasNextLine()) {
			input = sc.nextLine();
			Animal animal = getAnimalFromInput(input);
			if (animal.getClass().getSuperclass().equals(Mammal.class)) {
				mammalArray.add((Mammal) animal);
			} else if (animal.getClass().getSuperclass().equals(Reptile.class)) {
				reptileArray.add((Reptile) animal);
			} else if (animal.getClass().getSuperclass().equals(Fish.class)) {
				fishArray.add((Fish) animal);
			}
			animalsArray.add(animal);
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter("output/output.txt", "UTF-8");
			writer.println("Task1:\r\nPredators are more than the others? "
					+ arePredatorsMoreThanOthers(animalsArray));
			writer.println("Task2:\r\nThe oldest animal is: "
					+ getOldestAnimals(animalsArray));
			writer.println("Task3:\r\nThe youngest animals are: \r\n"
					+ getYoungestAnimals(animalsArray));
			writer.println("Task4:\r\n"
					+ getOldestSumOfAges(mammalArray, reptileArray, fishArray));
			writer.println("Task5:\r\n"
					+ getYoungestBiologicalSumOfAges(animalsArray));
			writer.println("Task6:\r\n"
					+ getMostPopulationByBiological(animalsArray));
			writer.println("Task7:\r\n"
					+ getSmallesSumOfTypes(mammalArray, reptileArray, fishArray));
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

		System.out.println("Task1:\nPredators are more than the others? "
				+ arePredatorsMoreThanOthers(animalsArray));
		System.out.println("Task2:\nThe oldest animal/s is/are: "
				+ getOldestAnimals(animalsArray));
		System.out.println("Task3:\nThe youngest animals are: \n"
				+ getYoungestAnimals(animalsArray));
		System.out.println("Task4:\n"
				+ getOldestSumOfAges(mammalArray, reptileArray, fishArray));
		System.out.println("Task5:\n"
				+ getYoungestBiologicalSumOfAges(animalsArray));
		System.out.println("Task6:\n"
				+ getMostPopulationByBiological(animalsArray));
		System.out.println("Task7:\n"
				+ getSmallesSumOfTypes(mammalArray, reptileArray, fishArray));
	}

	static boolean firstRun;

	private static Animal getAnimalFromInput(String input)
			throws InvalidParameterException {

		String[] splitedInput = input.split(" ");
		if (firstRun) {
			StringBuilder builder = new StringBuilder(splitedInput[0]);
			builder.deleteCharAt(0);
			splitedInput[0] = builder.toString();
			firstRun = false;
		}
		if (splitedInput.length < 3 || splitedInput.length > 3) {
			throw new InvalidParameterException(
					"Your input format was invalid!\n" + input);
		}
		byte type = 0;
		try {
			type = Byte.parseByte(splitedInput[0]);
		} catch (NumberFormatException e) {
			throw e;
		}

		String name = splitedInput[1];
		int age = 0;
		try {
			age = Integer.parseInt(splitedInput[2]);
		} catch (NumberFormatException e) {
			throw e;
		}

		Animal newAnimal = null;
		switch (type) {
		case 1:
			newAnimal = new Lion(type, name, age);
			break;
		case 2:
			newAnimal = new Dog(type, name, age);
			break;
		case 3:
			newAnimal = new Snake(type, name, age);
			break;
		case 4:
			newAnimal = new Lizard(type, name, age);
			break;
		case 5:
			newAnimal = new Trout(type, name, age);
			break;
		case 6:
			newAnimal = new Shark(type, name, age);
			break;
		case 7:
			newAnimal = new Dolphin(type, name, age);
			break;
		case 8:
			newAnimal = new Turtle(type, name, age);
			break;

		default:
			throw new InvalidKeyException("No animal of type: " + type
					+ " found!");
		}
		return newAnimal;
	}

	// Task 1
	private static boolean arePredatorsMoreThanOthers(
			ArrayList<Animal> animalsArray) {
		int arrayLength = animalsArray.size();
		int predatorCount = 0;
		for (int i = 0; i < arrayLength; i++) {
			if (animalsArray.get(i).isPredator()) {
				predatorCount++;
			} else {
				predatorCount--;
			}
		}
		if (predatorCount >= 1) {
			return true;
		}
		return false;
	}

	// Task 2
	private static String getOldestAnimals(ArrayList<Animal> animalsArray) {
		int arrayLength = animalsArray.size();
		int oldestAge = Integer.MIN_VALUE;
		StringBuilder animalsNames = new StringBuilder();
		for (int i = 0; i < arrayLength; i++) {
			Animal currentAnimal = animalsArray.get(i);
			int animalAge = currentAnimal.getAge();
			if (animalAge > oldestAge) {
				oldestAge = animalAge;
				animalsNames = new StringBuilder();
				animalsNames.append(currentAnimal.getName());
			} else {
				if (animalAge == oldestAge) {
					animalsNames.append(", " + currentAnimal.getName());
				}
			}
		}
		return animalsNames.toString();
	}

	// Task 3
	private static String getYoungestAnimals(ArrayList<Animal> animalsArray) {
		int arrayLength = animalsArray.size();
		int youngestAge = Integer.MAX_VALUE;
		StringBuilder animalsNames = new StringBuilder();
		for (int i = 0; i < arrayLength; i++) {
			Animal currentAnimal = animalsArray.get(i);
			int animalAge = currentAnimal.getAge();
			if (animalAge < youngestAge) {
				youngestAge = animalAge;
				animalsNames = new StringBuilder();
				animalsNames.append(currentAnimal.getName());
			} else {
				if (animalAge == youngestAge) {
					animalsNames.append(", " + currentAnimal.getName());
				}
			}
		}
		return animalsNames.toString();
	}

	// Task 4
	private static String getOldestSumOfAges(ArrayList<Mammal> mammalArray,
			ArrayList<Reptile> reptileArray, ArrayList<Fish> fishArray) {
		int mammalAgeSum = 0;
		int reptileAgeSum = 0;
		int fishAgeSum = 0;
		for (int i = 0; i < mammalArray.size(); i++) {
			mammalAgeSum += mammalArray.get(i).getAge();
		}
		for (int i = 0; i < reptileArray.size(); i++) {
			reptileAgeSum += reptileArray.get(i).getAge();
		}
		for (int i = 0; i < fishArray.size(); i++) {
			fishAgeSum += fishArray.get(i).getAge();
		}
		StringBuilder animalsNames = new StringBuilder();
		if (mammalAgeSum > reptileAgeSum && mammalAgeSum > fishAgeSum) {
			animalsNames.append("Mammals have the largest sum in age: "
					+ mammalAgeSum);
		} else if (reptileAgeSum > mammalAgeSum && reptileAgeSum > fishAgeSum) {
			animalsNames.append("Reptiles have the largest sum in age: "
					+ reptileAgeSum);
		} else if (fishAgeSum > mammalAgeSum && fishAgeSum > reptileAgeSum) {
			animalsNames.append("Fish have the largest sum in age: "
					+ fishAgeSum);
		} else {
			animalsNames.append("There are two or more equal ages -  Mammals: "
					+ mammalAgeSum + "Reptiles: " + reptileAgeSum + "Fish: "
					+ fishAgeSum);
		}

		return animalsNames.toString();
	}

	// Task 5
	private static String getYoungestBiologicalSumOfAges(
			ArrayList<Animal> animalsArray) {
		int arrayLength = animalsArray.size();
		int land = 0;
		int landAndWater = 0;
		int water = 0;
		StringBuilder animalsNames = new StringBuilder();
		for (int i = 0; i < arrayLength; i++) {

			Animal currentAnimal = animalsArray.get(i);

			switch (currentAnimal.getBiologicalSigns()) {
			case Water:
				water += currentAnimal.getAge();
				break;
			case Land:
				land += currentAnimal.getAge();
				break;
			case LandAndWater:
				landAndWater += currentAnimal.getAge();
				break;

			default:
				break;
			}
		}
		if (land < landAndWater && land < water) {
			animalsNames.append("Land have the smallest sum in age: " + land);
		} else if (landAndWater < land && landAndWater < water) {
			animalsNames.append("Land and Water have the smallest sum in age: "
					+ landAndWater);
		} else if (water < land && water < landAndWater) {
			animalsNames.append("Water have the smallest sum in age: " + water);
		} else {
			animalsNames.append("There are two or more equal ages -  Land: "
					+ land + "Land and Water: " + landAndWater + "Water: "
					+ water);
		}

		return animalsNames.toString();
	}

	// Task 6
	private static String getSmallesSumOfTypes(ArrayList<Mammal> mammalArray,
			ArrayList<Reptile> reptileArray, ArrayList<Fish> fishArray) {
		StringBuilder animalsNames = new StringBuilder();
		int mammalSum = mammalArray.size();
		int fishSum = fishArray.size();
		int reptileSum = reptileArray.size();
		if (mammalSum < reptileSum && mammalSum < fishSum) {
			animalsNames.append("Mammals have the smallest sum: " + mammalSum);
			animalsNames.append("\r\nTheir names are: ");
			for (int i = 0; i < mammalSum; i++) {
				Animal currentAnimal = mammalArray.get(i);

				animalsNames.append(currentAnimal.getName() + ", ");

			}
		} else if (reptileSum < mammalSum && reptileSum < fishSum) {
			animalsNames
					.append("Reptiles have the smallest sum: " + reptileSum);
			animalsNames.append("\r\nTheir names are: ");
			for (int i = 0; i < reptileSum; i++) {
				Animal currentAnimal = reptileArray.get(i);

				animalsNames.append(currentAnimal.getName() + ", ");

			}
		} else if (fishSum < mammalSum && fishSum < reptileSum) {
			animalsNames.append("Fish have the smallest sum: " + fishSum);
			animalsNames.append("\r\nTheir names are: ");
			for (int i = 0; i < fishSum; i++) {
				Animal currentAnimal = fishArray.get(i);

				animalsNames.append(currentAnimal.getName() + ", ");

			}
		} else {
			animalsNames.append("There are two or more equal -  Mammals: "
					+ mammalSum + "Reptiles: " + reptileSum + "Fish: "
					+ fishSum);
		}
		animalsNames.delete(animalsNames.length() - 2, animalsNames.length());
		return animalsNames.toString();
	}

	// Task 7
	private static String getMostPopulationByBiological(
			ArrayList<Animal> animalsArray) {
		int arrayLength = animalsArray.size();
		int land = 0;
		int landAndWater = 0;
		int water = 0;
		StringBuilder animalsNames = new StringBuilder();
		for (int i = 0; i < arrayLength; i++) {

			Animal currentAnimal = animalsArray.get(i);

			switch (currentAnimal.getBiologicalSigns()) {
			case Water:
				water++;
				break;
			case Land:
				land++;
				break;
			case LandAndWater:
				landAndWater++;
				break;

			default:
				break;
			}
		}
		if (land > landAndWater && land > water) {
			animalsNames.append("Land have the biggest sum: " + land);
		} else if (landAndWater > land && landAndWater > water) {
			animalsNames.append("Land and Water  have the biggest sum: "
					+ landAndWater);
		} else if (water > land && water > landAndWater) {
			animalsNames.append("Water have the biggest sum: " + water);
		} else {
			animalsNames.append("There are two or more equal -  Land: " + land
					+ "Land and Water: " + landAndWater + "Water: " + water);
		}

		return animalsNames.toString();
	}
}
