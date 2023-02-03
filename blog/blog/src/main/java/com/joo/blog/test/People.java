package com.joo.blog.test;

public class People {
	private int hungryState = 50; // full = 100

	public void eat() {
		hungryState += 10;
	}

	public static void main(String[] args) {
		People p = new People();
		p.eat();

	}
}
