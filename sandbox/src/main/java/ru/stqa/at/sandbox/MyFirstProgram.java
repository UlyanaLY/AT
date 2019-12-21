package ru.stqa.at.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		Point p1 = new Point(18.0, 1.0);
		Point p2 = new Point(19.0, 23.0);
		System.out.println("Расстояние между точкой p1(" + p1.x + ", " + p1.y+ ") и p2("+p2.x + ", " +p2.y+ ") " +
						           "составляет" + " " + p2.distance(p1));
	}
}