import java.util.Scanner;

class Barber implements Runnable {
	boolean customerArrived = false;
	Customer customer;
	int waitingSeats = 5;
	
	synchronized void occupyChair(Customer customer) throws InterruptedException {
		while(this.customerArrived)
			wait();
		this.customerArrived = true;
		this.customer = customer;
		notifyAll();
	}
	
	
	public void run() {
		synchronized(this) {
			while(true){
				try{
					while(!this.customerArrived)
						wait();
					System.out.println("Serving customer");
					Thread.sleep(3000);
					System.out.println("Customer served");
					this.customerArrived = false;
					this.customer = null;
					notifyAll();
				}
				catch(InterruptedException e){ e.printStackTrace(); }
			}
		}
	}
	
}

class Customer implements Runnable {
	
	Barber barber;
	boolean beingServed = false;
	Customer(Barber barber) { 
		this.barber = barber;
		SleepingBarber.newCustomerComes(this);
	}
	
	public void run() {
		synchronized(barber){
		try{
			while(this.barber.customerArrived)
				barber.wait();
			barber.occupyChair(this);
			barber.waitingSeats += 1;
			System.out.println("Customer on barber chair. Available Seats: " + barber.waitingSeats);
		}
		catch(InterruptedException e){ e.printStackTrace(); }
		}
	}
}

public class SleepingBarber {
	static int customers =0;
	static Barber barber;
	static boolean barberChair = false;
	
	public static void main(String args[]){
		barber = new Barber();
		new Thread(barber).start();
		int inp = 0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Print Command:\n1. New Customer\n2. Exit");
			inp = sc.nextInt();
			if(inp == 1)
				new Customer(barber);
			else {
				sc.close();
				System.exit(0);
			}
		}
	}
	
	synchronized static void newCustomerComes(Customer customer) {
		if(barber.waitingSeats > 0) {
			barber.waitingSeats -=1;
			new Thread(customer).start();
			System.out.println("Seats left: " + barber.waitingSeats);
		}
		else {
			System.out.println("No seat available for the customer");
		}
	}

}
