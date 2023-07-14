package com.hc.toolrental.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.hc.toolrental.util.RentalToolUtil;

public class RentalAgreement {
	private Tool tool;
    private int rentalDays;
    private double discount;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;
    
    public RentalAgreement(Tool tool, int rentalDays, double discount, LocalDate checkoutDate, LocalDate dueDate,
            double dailyRentalCharge, int chargeDays, double preDiscountCharge, double discountAmount,
            double finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discount = discount;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void printRentalAgreement() {
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + RentalToolUtil.formatDate(checkoutDate));
        System.out.println("Due date: " + RentalToolUtil.formatDate(dueDate));
        System.out.println("Daily rental charge: " + dailyRentalCharge);
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + preDiscountCharge);
        System.out.println("Discount percent: " + discount);
        System.out.println("Discount amount: " + discountAmount);
        System.out.println("Final charge: " + finalCharge);
    }

 
}
