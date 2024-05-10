package com.notepad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDateTime now = LocalDateTime.now();

        // Format the time as "hr:min:sec"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(timeFormatter);
        System.out.println("Time: " + formattedTime);

        // Format the date as "dd/mm/yy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String formattedDate = now.format(dateFormatter);
        System.out.println("Date: " + formattedDate);
	}

}
