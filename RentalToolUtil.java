package com.hc.toolrental.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalToolUtil {
	   public static String formatDate(LocalDate date) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	        return date.format(formatter);
	    }

	   public static String formatCurrency(double amount) {
	        return String.format("$%.2f", amount);
	    }

	   public static String formatPercent(double percent) {
	        return String.format("%.0f%%", percent * 100);
	    }
}
