package net.sf.navigator.digester.test;

public class Child extends Father{
	String name = "child";

	public static void main(String[] args) {
		System.out.println(new Child().getName());
	}
}
