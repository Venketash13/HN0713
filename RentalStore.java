package com.hc.toolrental.service;


import java.time.DayOfWeek;
import java.time.LocalDate;

import com.hc.toolrental.model.RentalAgreement;
import com.hc.toolrental.model.Tool;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class RentalStore {
	
	public RentalAgreement createRentalAgreement(String toolCode, int rentalDays, double discountPercent,
			LocalDate checkoutDate) {
		validateRentalDays(rentalDays);
		validateDiscount(discountPercent);
		Tool tool = getToolByCode(toolCode);
		LocalDate dueDate = calculateDueDate(checkoutDate, rentalDays);

		double dailyRentalCharge = getDailyCharge(tool);
		
		int chargeDays = calculateChargeDays(checkoutDate, dueDate, tool);
		
		
		double preDiscountCharge = calculatePreDiscountCharge(dailyRentalCharge, chargeDays);
		double discountAmount = calculateDiscountAmount(preDiscountCharge, discountPercent / 100.0);
		double finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);
		RentalAgreement agreement = new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate,
		        dueDate, dailyRentalCharge, chargeDays, preDiscountCharge, discountAmount, finalCharge);
		agreement.printRentalAgreement();
		return agreement;
	}
	
	private void validateRentalDays(int rentalDays) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
    }

    private void validateDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 1.");
        }
    }
    
    private static LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
        return checkoutDate.plusDays(rentalDays);
    }

    private static Tool getToolByCode(String toolCode) {
        // Retrieve the tool based on the tool code from a database or configuration
        if (toolCode.equalsIgnoreCase("CHNS")) {
            return new Tool("CHNS", "Chainsaw", "Stihl");
        } else if (toolCode.equalsIgnoreCase("LADW")) {
            return new Tool("LADW", "Ladder", "Werner");
        } else if (toolCode.equalsIgnoreCase("JAKD")) {
            return new Tool("JAKD", "Jackhammer", "DeWalt");
        } else if (toolCode.equalsIgnoreCase("JAKR")) {
            return new Tool("JAKR", "Jackhammer", "Ridgid");
        } else {
            throw new IllegalArgumentException("Tool not found for the specified tool code: " + toolCode);
        }
    }
    
    

    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1); // Start from the day after checkout

        while (!currentDate.isAfter(dueDate)) {
            if (isChargeableDay(currentDate, tool)) {
                chargeDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return chargeDays;
    }

    private boolean isChargeableDay(LocalDate date, Tool tool) {
        String toolType = tool.getToolType();
        boolean isWeekday = !isWeekend(date);
        boolean isWeekend = isWeekend(date);
        boolean isHoliday = isHoliday(date);
        if(toolType.equalsIgnoreCase("Ladder")) {
        	return ((isWeekday && !isHoliday || isWeekend));
        } else if(toolType.equalsIgnoreCase("Chainsaw")) {
        	return (isWeekday || isHoliday) && !isWeekend;
        } else if(toolType.equalsIgnoreCase("Jackhammer")) {
        	return isWeekday && !isWeekend && !isHoliday;
        }
       return false;
    }
  
    private  double calculatePreDiscountCharge(double dailyRentalCharge, int chargeDays) {
    	BigDecimal dailyRentalChargeBigDecimal = BigDecimal.valueOf(dailyRentalCharge);
        BigDecimal chargeDaysBigDecimal = BigDecimal.valueOf(chargeDays);

        BigDecimal preDiscountCharge = dailyRentalChargeBigDecimal.multiply(chargeDaysBigDecimal);
        return preDiscountCharge.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private  double calculateDiscountAmount(double preDiscountCharge, double discountPercent) {
    	if(discountPercent > 0) {
    	BigDecimal preDiscountChargeBigDecimal = BigDecimal.valueOf(preDiscountCharge);
        BigDecimal discountPercentBigDecimal = BigDecimal.valueOf(discountPercent);

        BigDecimal discountAmountBigDecimal = preDiscountChargeBigDecimal.multiply(discountPercentBigDecimal);
        return discountAmountBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    	} else {
    		return 0;
    	}
    }

    private  double calculateFinalCharge(double preDiscountCharge, double discountAmount) {
    	if(discountAmount > 0) {
    	BigDecimal preDiscountChargeBigDecimal = BigDecimal.valueOf(preDiscountCharge);
        BigDecimal discountAmountBigDecimal = BigDecimal.valueOf(discountAmount);

        BigDecimal finalChargeBigDecimal = preDiscountChargeBigDecimal.subtract(discountAmountBigDecimal);
        return finalChargeBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    	} else {
    		return preDiscountCharge;
    	}
    }
    
    private double getDailyCharge(Tool tool) {
        String toolType = tool.getToolType();
        if (toolType.equalsIgnoreCase("Ladder")) {
            return 1.99;
        } else if (toolType.equalsIgnoreCase("Chainsaw")) {
            return 1.49;
        } else if (toolType.equalsIgnoreCase("Jackhammer")) {
            return 2.99;
        } else {
            throw new IllegalArgumentException("Invalid tool type: " + toolType);
        }
    }
    
    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public  boolean isHoliday(LocalDate date) {
    	if(isLaborDay(date) || isIndependenceDay(date)) {
    		return true;
    	}
        return false;
    }

    private  boolean isIndependenceDay(LocalDate date) {
    	if(date.getMonthValue() == 7) {
    		if(date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == 3) {
    			return true;
    		} else if(date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() == 5) {
    			return true;
    		} else if(date.getDayOfMonth() == 4) {
    			return true;
    		}
    	}
        return false;
    }

    private  boolean isLaborDay(LocalDate date) {
        if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }
        return false;
    }
    

}
