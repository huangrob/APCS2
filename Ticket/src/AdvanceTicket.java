
public class AdvanceTicket extends Ticket {
	private double price;
	public AdvanceTicket(int number, int numberDays){
		super(number);
		if (numberDays < 10) {
			this.price = 40.0;
		}
		else {
			this.price = 30.0;
		}
	}
    public double getPrice() {
        return this.price;
    }
    public String toString(){
        return super.toString();
    }

}
