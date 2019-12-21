package ru.stqa.at.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		Point p1 = new Point(5.0, 5.0);
		Point p2 = new Point(5.0, 5.0);
		System.out.println("Расстояние между точкой p1 и p2 составляет" + " " + distance(p1, p2));
	}

	public static double distance(Point p1, Point p2) {
		double distance = Math.sqrt(Math.pow((p2.x - p1.x), 2.0) + Math.pow((p2.y - p1.y), 2.0));
		return distance;
	}
}