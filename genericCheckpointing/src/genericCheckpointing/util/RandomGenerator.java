package genericCheckpointing.util;

import java.util.Random;

public class RandomGenerator {
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
	public static boolean randomBool() {
		return rand.nextBoolean();
	}
	public static long randomLong() {
		return rand.nextLong();
	}
	public static float randomFloat() {
		return rand.nextFloat();
	}
	public static double randomDouble() {
		return rand.nextDouble();
	}
	public static short randomShort() {
		return (short) rand.nextInt(Short.MAX_VALUE + 1);
	}
	public static char randomChar() {
		String alphas = "abcdefghijklmnopqrstuvwxyz";
		int index = rand.nextInt(26);
		return alphas.charAt(index);
	}
}
