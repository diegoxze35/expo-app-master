package com.expo.expoapp.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static List<String> parseNameAndSurname(String input) {
		List<String> list = new ArrayList<>(2);
		String[] fullName = input.split(" ");
		//[Diego, Alejandro, Moreno, Martinez]
		if (fullName.length == 4) {
			list.add(fullName[0] + " " + fullName[1]);
			list.add(fullName[2] + " " + fullName[3]);
		} else {
			list.add(fullName[0]);
			list.add(fullName[1] + " " + fullName[2]);
		}
		return list;
	}
}
