
public class ClientCode {
	public static void main(String[] args)
	    {
	        Ticket[] tickets = new Ticket[3];
	        tickets[0] = new WalkupTicket(0);
	        tickets[1] = new AdvanceTicket(1,10);
	        tickets[2] = new StudentAdvanceTicket(2,7);
	        for (int i = 0; i<3; i++)
	        {
	            System.out.println(tickets[i]);
	        }
	    }
}
