package application;

public class Test {

	public static void main(String[] args) {
		Register reg = new Register();
		Customer c1 = new Customer("Bosse", "123");
		Customer c2 = new Customer("Mats", "456");
		
		reg.addCustomer(c1);
		reg.addCustomer(c2);
		
		for (Customer c: reg.getCustomers()) {
			System.out.println(c.getName());
		}

	}

}
