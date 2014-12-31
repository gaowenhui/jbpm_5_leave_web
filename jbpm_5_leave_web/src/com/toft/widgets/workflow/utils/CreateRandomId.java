package com.toft.widgets.workflow.utils;

import java.util.Calendar;

public class CreateRandomId {

	/**
	 * 
	 * @return
	 */
	public static String createExport() {
		Calendar calendar = Calendar.getInstance();
		String s = "EXPORT" + String.valueOf(calendar.getTimeInMillis());
		s = s + ((int) ((Math.random() + 1) * 100000));
		return s;
	}

	public static String createPD() {
		Calendar calendar = Calendar.getInstance();
		String str = "PD" + String.valueOf(calendar.getTimeInMillis())
				+ ((int) ((Math.random() + 1) * 10000));
		return str;
	}

	public static String createId_Twenty() {
		Calendar calendar = Calendar.getInstance();
		String s = String.valueOf(calendar.getTimeInMillis());
		s = s + ((int) ((Math.random() + 1) * 1000000));
		return s;
	}

	public static String createId_Thirty() {
		Calendar calendar = Calendar.getInstance();
		String s = String.valueOf(calendar.getTimeInMillis());
		s = s + (int) (Math.random() * 100000000)
				+ (int) (Math.random() * 1000000000);
		return s;
	}

	public static String createId_Forty() {
		Calendar calendar = Calendar.getInstance();
		String s = String.valueOf(calendar.getTimeInMillis());
		s = s + (int) (Math.random() * 1000000000)
				+ (int) (Math.random() * 1000000000)
				+ (int) (Math.random() * 1000000000);
		return s;
	}

}
