
public class StudentAdvanceTicket extends AdvanceTicket{

	public StudentAdvanceTicket(int number, int numberDays) {
		super(number, numberDays);
	}
	public double getPrice() {
		return (super.getPrice())/2;
	}
	public String toString(){
		return super.toString();
	}

}
