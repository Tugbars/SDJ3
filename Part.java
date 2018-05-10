//(String part_name, int part_number, int CarID, String databasename)
public class Part {
	
	public String part_name;
	public int part_number;
	public int CarID;
	
	public Part(String part_name, int part_number, int CarID)
	{
		this.part_name = part_name;
		this.part_number = part_number;
		this.CarID = CarID;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public int getPart_number() {
		return part_number;
	}

	public void setPart_number(int part_number) {
		this.part_number = part_number;
	}

	public int getCarID() {
		return CarID;
	}

	public void setCarID(int carID) {
		CarID = carID;
	}

}
