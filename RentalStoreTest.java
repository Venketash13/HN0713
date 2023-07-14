package com.hc.toolrental.service.test;

import com.hc.toolrental.model.RentalAgreement;
import com.hc.toolrental.service.RentalStore;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RentalStoreTest {
    private RentalStore rentalStore = new RentalStore();

    @Test
    public void testScenario1() {
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 5;
        double discount = 101;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            rentalStore.createRentalAgreement(toolCode, rentalDays, discount, checkoutDate);
        });
    }

 
    @Test
    public void testScenario2() {
        
        String toolCode = "LADW";
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 3;
        double discount = 10;

        RentalAgreement agreement = rentalStore.createRentalAgreement(toolCode, rentalDays, discount, checkoutDate);
        Assertions.assertEquals("LADW", agreement.getTool().getToolCode());
        Assertions.assertEquals(1.99, agreement.getDailyRentalCharge());
        Assertions.assertEquals(2, agreement.getChargeDays());
        Assertions.assertEquals(3.98, agreement.getPreDiscountCharge());
        Assertions.assertEquals(10, agreement.getDiscount());
        Assertions.assertEquals(0.4, agreement.getDiscountAmount());
        Assertions.assertEquals(3.58, agreement.getFinalCharge());
    }

    @Test
    public void testScenario3() {
     
        String toolCode = "CHNS";
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        int rentalDays = 5;
        double discount = 25;

        RentalAgreement agreement = rentalStore.createRentalAgreement(toolCode, rentalDays, discount, checkoutDate);
        
        Assertions.assertEquals("CHNS", agreement.getTool().getToolCode());
        Assertions.assertEquals(1.49, agreement.getDailyRentalCharge());
        Assertions.assertEquals(3, agreement.getChargeDays());
        Assertions.assertEquals(4.47, agreement.getPreDiscountCharge());
        Assertions.assertEquals(25, agreement.getDiscount());
        Assertions.assertEquals(1.12, agreement.getDiscountAmount());
        Assertions.assertEquals(3.35, agreement.getFinalCharge());
    }

    @Test
    public void testScenario4() {
        
        String toolCode = "JAKD";
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 6;
        double discount = 0;

        RentalAgreement agreement = rentalStore.createRentalAgreement(toolCode, rentalDays, discount, checkoutDate);
        Assertions.assertEquals("JAKD", agreement.getTool().getToolCode());
        Assertions.assertEquals(2.99, agreement.getDailyRentalCharge());
        Assertions.assertEquals(3, agreement.getChargeDays());
        Assertions.assertEquals(8.97, agreement.getPreDiscountCharge());
        Assertions.assertEquals(0, agreement.getDiscount());
        Assertions.assertEquals(0, agreement.getDiscountAmount());
        Assertions.assertEquals(8.97, agreement.getFinalCharge());
    }

    @Test
    public void testScenario5() {
       
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        int rentalDays = 9;
        double discount = 0;

        RentalAgreement agreement = rentalStore.createRentalAgreement(toolCode, rentalDays, discount, checkoutDate);
        Assertions.assertEquals("JAKR", agreement.getTool().getToolCode());
        Assertions.assertEquals(2.99, agreement.getDailyRentalCharge());
        Assertions.assertEquals(5, agreement.getChargeDays());
        Assertions.assertEquals(14.95, agreement.getPreDiscountCharge());
        Assertions.assertEquals(0, agreement.getDiscount());
        Assertions.assertEquals(0, agreement.getDiscountAmount());
        Assertions.assertEquals(14.95, agreement.getFinalCharge());
    }

    @Test
    public void testScenario6() {
        
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 4;
        double discount = 50;

        RentalAgreement agreement = rentalStore.createRentalAgreement(toolCode, rentalDays, discount, checkoutDate);
        Assertions.assertEquals("JAKR", agreement.getTool().getToolCode());
        Assertions.assertEquals(2.99, agreement.getDailyRentalCharge());
        Assertions.assertEquals(1, agreement.getChargeDays());
        Assertions.assertEquals(2.99, agreement.getPreDiscountCharge());
        Assertions.assertEquals(50, agreement.getDiscount());
        Assertions.assertEquals(1.5, agreement.getDiscountAmount());
        Assertions.assertEquals(1.49, agreement.getFinalCharge());
    }
    
    
}
