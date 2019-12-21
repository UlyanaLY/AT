package ru.stqa.at.sandbox;

public class Point {
	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Point otherPoint) {
		double distance = Math.sqrt(Math.pow((otherPoint.x - this.x), 2.0) + Math.pow((otherPoint.y - this.y), 2.0));
		return distance;
	}
}