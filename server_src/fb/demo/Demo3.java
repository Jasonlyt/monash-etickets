package fb.demo;

import java.util.Random;

public class Demo3 {
	public static void main(String[] args) {
		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			int j = r.nextInt(6);
			System.out.println(j);
		}
	}
}
