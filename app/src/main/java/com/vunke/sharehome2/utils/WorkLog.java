package com.vunke.sharehome2.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkLog {
	public static void e(String className, String content) {
		Log.e(className, "time:" + getDateTime() + ";" + "[e]" + ";"
				+ "sharehome:\n" + content);
	}

	public static void d(String className, String content) {
		Log.d(className, "time:" + getDateTime() + ";" + "[d]" + ";"
				+ "sharehome:\n" + content);
	}

	public static void i(String className, String content) {
		Log.i(className, "time:" + getDateTime() + ";" + "[i]" + ";"
				+ "sharehome:\n" + content);
	}

	public static void w(String className, String content) {
		Log.w(className, "time:" + getDateTime() + ";" + "[w]" + ";"
				+ "sharehome:\n" + content);
	}

	public static void v(String className, String content) {
		Log.v(className, "time:" + getDateTime() + ";" + "[v]" + ";"
				+ "sharehome:\n" + content);
	}

	
	
	
	public static void e(String className, String content, Throwable e) {
		Log.e(className, "time:" + getDateTime() + ";" + "[e]" + ";"
				+ "sharehome:\n" + content, e);
	}

	public static void d(String className, String content, Throwable e) {
		Log.d(className, "time:" + getDateTime() + ";" + "[d]" + ";"
				+ "sharehome:\n" + content, e);
	}

	public static void i(String className, String content, Throwable e) {
		Log.i(className, "time:" + getDateTime() + ";" + "[i]" + ";"
				+ "sharehome:\n" + content, e);
	}

	public static void w(String className, String content, Throwable e) {
		Log.w(className, "time:" + getDateTime() + ";" + "[w]" + ";"
				+ "sharehome:\n" + content, e);
	}

	public static void v(String className, String content, Throwable e) {
		Log.v(className, "time:" + getDateTime() + ";" + "[v]" + ";"
				+ "sharehome:\n" + content, e);
	}

	/*
	 * public static void main(String[] args) { System.out.println("class:" +
	 * "worleLog"); System.out.println("time:" + getDateTime() + ";" + "[d]" +
	 * ";"); System.out.println("content:"+"当前内容"); }
	 */

	/**
	 * 获取系统时间
	 * 
	 * @return String 2016-6-12 10:53:05:888
	 */
	public static String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SS");
		Date date = new Date(System.currentTimeMillis());
		String time = dateFormat.format(date);
		return time;
	}
}
