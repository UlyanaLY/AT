package ru.stqa.at.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

	public static void main(String[] args) {
		String[] langs = {"Java", "C#", "Python", "PHP"};

		for (String l: langs) {
			System.out.println("Я хочу выучить " + l);
		}

		for (int i = 0; i < langs.length; i++) {
			System.out.println("Я хочу выучить " + langs[i]);
		}

		//List< > languages = new ArrayList<String>();
		List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
		languages.add("JS");

		for (int i = 0; i < languages.size(); i++) {
			System.out.println("Я хочу выучить " + languages.get(i));
		}
	}
}
