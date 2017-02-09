
public abstract class Ticket {
	
	private int number;
	public Ticket(int number) {
		this.number = number;
	}
	public abstract double getPrice();
	public String toString() {
		return "Number: " + number + ", Price: " + getPrice();
	}
}


