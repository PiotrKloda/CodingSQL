package com.codingSQL.model;

public class MainTest {

	public static void main(String[] args) {

		try {

			User[] uList = UserDAO.loadAllByGroupId(1);

			for (int i = 0; i < uList.length; i++) {
				System.out.println(i + " " + uList[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
