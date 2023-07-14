package com.hc.toolrental;

import java.time.LocalDate;
import com.hc.toolrental.model.RentalAgreement;
import com.hc.toolrental.service.RentalStore;

public class Application {
	
	public static void main(String[] args) {
        String toolCode = "LADW";
        int rentalDays = 8;
        double discountPercent = 20;
        LocalDate checkoutDate = LocalDate.now();
        RentalStore rentalStore = new RentalStore();
        try {
            RentalAgreement agreement = rentalStore.createRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);
            agreement.printRentalAgreement();
        } catch (IllegalArgumentException e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

}
