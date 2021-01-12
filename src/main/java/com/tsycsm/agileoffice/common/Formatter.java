package com.tsycsm.agileoffice.common;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

public class Formatter {

	public static String getCurrency(long number) {
		String currency = Currency.getInstance(Locale.KOREA).getSymbol();
		DecimalFormat df= new DecimalFormat("###,###.###");
		String result = df.format(number);
		return currency+ result;
	}
	/*
	 * public static void main(String[] args) {
	 * System.out.println(getCurrency(200000)); }
	 */
	
}
