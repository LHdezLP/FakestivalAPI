package com.festival.tickets.entity.models;

import java.security.SecureRandom;

public class GeneradorDeSeriales {
	
	private static final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generarSerial(int length) {
        StringBuilder serial = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(caracteres.length());
            serial.append(caracteres.charAt(randomIndex));
        }
        return serial.toString();
    }
    
}
