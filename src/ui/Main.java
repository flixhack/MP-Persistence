package ui;

import control.CustomerController;
import dal.DataAccessException;
import model.Customer;

public class Main {
    private CustomerController cc;

    public Main() {
        try {
            cc = new CustomerController();
        } catch (DataAccessException e) {
            e.printStackTrace();
            // handle exception as needed
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.tryCreatePrivateCustomer();
        main.tryCreateBusinessCustomer();
    }
    private void tryCreatePrivateCustomer() {
		try {
			Customer c = cc.createPrivateCustomer("Gibe", "Sofiendalsvej", 9000, "Ålleren", 112, "awd@awdk.dk");
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
    }

    private void tryCreateBusinessCustomer() {
        try {
            Customer s = cc.createBusinessCustomer("gibe", "Sofiendalsvej", 9000, "Ålleren", 112, 12345678);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
