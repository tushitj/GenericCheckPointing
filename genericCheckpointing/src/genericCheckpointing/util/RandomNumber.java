package genericCheckpointing.util;

import java.util.Random;

public class RandomNumber {
	static Random rand = new Random();
	public static int randomInt() {
		return rand.nextInt(100000) + 10;
	}
	public static int randomInt(int index) {
		return rand.nextInt(index)+1;
	}	
	public static String randomString() {
		String alphas = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder randomString = new StringBuilder();
		int loop = randomInt(10);
		while(randomString.length() < loop) {
			int index = randomInt(alphas.length())-1;
			randomString.append(alphas.charAt(index));
		}
		return randomString.toString();
	}
}
