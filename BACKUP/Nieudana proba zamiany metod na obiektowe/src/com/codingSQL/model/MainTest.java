package com.codingSQL.model;

public class MainTest {

	public static void main(String[] args) {
		
		try{
			
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/codingSQL?useSSL=false","root","coderslab");
			//Connection conn = DbUtil.getConnection();
			
//			Exercise ex3 = new Exercise("DAO", "Split classes to DAO");
//			ExerciseDAO.saveToDB(ex3);
//			
//			Exercise[] exercises = ExerciseDAO.loadAllExercises();
//			for (Exercise each:exercises){
//				System.out.println(each);
//			}
			
//			User[] uList=UserDAO.loadAllByGroupId(1);
//			
//			for(int i=0;i<uList.length;i++) {
//				System.out.println(i + " " + uList[i]);
//			}
			
			Solution[] solutions = SolutionDAO.loadAllSolutions(5);
			for (Solution each: solutions) {
				System.out.println(each);
			}
			
			
			
			//----------------------------------------saveToDB
//			User u1=new User("Krzysiek", "Krzysiek@gmail.com", "ajsdfhn",2);
//			u1.saveToDB(conn);
			
			//----------------------------------------loadUserById
//			User u2 = User.loadUserById((conn), 4);
//			System.out.println(u2);
			
			//----------------------------------------loadAllUsers
//			User[] u = User.loadAllUsers(conn);
//			for (User eachUser : u) {
//				System.out.println(eachUser);
//			}
			
			//----------------------------------------modifyUsers
//			u[0].setUsername("Michal");
//			u[0].saveToDB(conn);
			
			
			
//			Exercise e = new Exercise("Servlety", "Wstep do technologii webowej");
//			e.saveToDB(conn);
			
//			Exercise e1=Exercise.loadExerciseById(conn, 2);
//			System.out.println(e1);
//			
//			Exercise[] e2 = Exercise.loadAllExercises(conn);
//			for (Exercise eachEx : e2) {
//				System.out.println(eachEx);
//			}
//			
//			e2[2].delete(conn);
			
//			Solution s = new Solution("2011-05-01", "2017-08-04", "Solution to Java exercise");
//			s.saveToDB(conn);
			
//			Solution s1 = Solution.loadSolutionById(conn, 2);
//			System.out.println(s1);
//			
//			s1.setDescription("Soluton to VERY hardcore Exercise");
//			s1.saveToDB(conn);
//			
//			s1 = Solution.loadSolutionById(conn, 2);
//			System.out.println(s1);

//			Solution[] sl=Solution.loadAllSolutions(conn);
//			for (Solution eachSol : sl) {
//				System.out.println(eachSol);
//			}
			
			//sl[2].delete(conn);
			
			
//			Solution s2= new Solution("2011-05-01", "2017-08-04", "Solution to Java exercise", 11 , 8);
//			s2.saveToDB(conn);
			
//			Solution[] sList = Solution.loadAllByExerciseId(conn, 3);
//			for ( Solution eachSol : sList) {
//				System.out.println(eachSol);
//			}
			
//			User[] uList = User.loadAllByGroupId(conn, 1);
//			for (User eachUser : uList) {
//				System.out.println(eachUser);
//			}
			
//			Solution[] solList = Solution.loadAllSolutions(conn,2);
//			for (Solution each:solList) {
//				System.out.println(each);
//			}
		
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
