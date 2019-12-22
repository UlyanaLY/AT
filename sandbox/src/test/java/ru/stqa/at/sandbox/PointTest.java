package ru.stqa.at.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

	@Test
	public void testPoint(){
		Point p1 = new Point(1.0,5.0);
		Point p2 = new Point(5.0, 5.0);

		Assert.assertEquals(p1.distance(p2), 4.0);
	}

	@Test
	public void testDistanceIsEqual(){
		Point p1 = new Point(2.0,5.0);
		Point p2 = new Point(5.0, 5.0);

		Assert.assertEquals(p1.distance(p2), p2.distance(p1));
	}
}