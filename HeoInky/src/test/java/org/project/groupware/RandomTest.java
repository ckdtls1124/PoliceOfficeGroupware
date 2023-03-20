package org.project.groupware;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class RandomTest {

	public static void main(String[] args) {
		int ranNum = (int)Math.random();

		Random ranNum2 = new Random();

		System.out.println(ranNum);
		System.out.println(ranNum2.nextInt());
		System.out.println(ranNum2.nextLong());
	}

}
