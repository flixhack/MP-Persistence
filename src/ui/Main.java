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
        main.tryCreateCustomer();
    }

    private void tryCreateCustomer() {
        try {
            Customer s = cc.createPrivateCustomer("123", "Gibe");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
