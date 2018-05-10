
public class Car {
//	(int i, String model,  int weight)
	
	public int CHASSIS_ID;
	public String MODEL;
	public int weight;
	public Car(int CHASSIS_ID, String MODEL, int weight)
	{
		this.CHASSIS_ID = CHASSIS_ID;
		this.MODEL = MODEL;
		this.weight = weight;
	}
	public int getCHASSIS_ID() {
		return CHASSIS_ID;
	}
	public void setCHASSIS_ID(int cHASSIS_ID) {
		CHASSIS_ID = cHASSIS_ID;
	}
	public String getMODEL() {
		return MODEL;
	}
	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	
//	(SELECT Title Movie WHERE Title = 'Bourne Ultimatum'),
//	 1995,
//	 (SELECT username User_Profile WHERE username = 'Jensgoestocinema'));
//	 
//	 	INSERT INTO Review (ReviewID,
//          text,
//          stars,
//		   movie_Title,
//		   Production_Year,
//		   reviewer
//		   )
//VALUES ('1290',
//'Matt Damon is so handsome!',
//    '5',
//    (SELECT Title Movie WHERE Title = 'Jason Bourne'),
//	 2016,
//	  (SELECT username User_Profile WHERE username = 'Mettewatchesmovies'));
//	 
//	 	INSERT INTO Review (ReviewID,
//          text,
//          stars,
//		   movie_Title,
//		   Production_Year,
//		   reviewer
//		   )

}
