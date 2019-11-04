package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {


    char letters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char specialChar[] = {' ', '!', '.', '?', ','};
    int randomShift[] = {1, 2, 3};

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        System.out.print("Skriv in den text du vill kryptera: ");
        Scanner read = new Scanner(System.in);
        String message = read.nextLine();
        System.out.println("Skriv ditt krypteringsvärde: ");
        int krypteringstal = read.nextInt();
        new Main(message, krypteringstal, 2);

        new CipherC(message);
    }

    /**
     *
     * @param input Meddelandet som ska krypteras.
     * @param shift Hur många steg krypteringen ska gå i arrayn som byter ut bokstäver.
     * @param timeout Hur lång tid innan meddelandet visas för använderan.
     * @throws InterruptedException
     */
    public Main(String input, int shift, int timeout) throws InterruptedException {
        timeout *= 1000;
        String output = cipherThis(input, shift);
        System.out.println(output);
        System.out.println("");
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            System.exit(-1);
        }
        System.out.println(cipherThis(output, -shift));
    }

    /**
     * Metod för kryptering.
     * @param input Texten som ska krypteras. Omvandlas till en array av chars.
     * @param shift Vilket värde krypteringen ska använda.
     * @return Returerar krypterad text.
     */
    String cipherThis(String input, int shift) {
        char[] charArray = input.toCharArray();
        String output = "";
        for (int i = 0; i != charArray.length; i++) {


            if (Character.isUpperCase(charArray[i])) {
                output += Character.toUpperCase(calcShift(getIndex(charArray[i]), shift));
            } else {
                output += calcShift(getIndex(charArray[i]), shift);
            }
        }
        return output;
    }

    /**
     * Metod som söker index för varje char i meddelandet,
     * samt checkar om någon annan char än bokstäver finns i meddelandet.
     * @param input
     * @return
     */
    int getIndex(char input) {

        if (isSpecialChar(input) != 999) {
            return (isSpecialChar(input) + 50);
        }
        if (Character.isUpperCase(input)) {
            input = Character.toLowerCase(input);
        }
        for (int i = 0; i != letters.length; i++) {
            if (letters[i] == input) {
                return i;
            }
        }
        return 999;
    }
    //int randomizeShift(int i){
        // get index
     //   int shift;
     //   if(randomShift[i] > randomShift.length){
         //   shift = randomShift[i];
       // }
       // else if (randomShift[i] < randomShift.length){
       //     shift = randomShift[0 + i];
       // }
       // shift = randomShift[i];
       // return shift;
    //}

    /**
     *
     * @param index Index för att avgöra vart char finns i array samt vilken bokstav som ska avnändas för kryptering enligt shift.
     * @param shift Hur långt ifrån den krypteraded char ska vara ifrån original.
     * @return Returerar beroende på if sats.
     */
    char calcShift(int index, int shift) {

        // Använd randomshift
        if (index >= 50 && index < 999) {
            return specialChar[index - 50];
        }
        if (index + shift < 0) {
            int newIndex = 26 + (index + shift);
            return letters[newIndex];
        }
        if (index + shift > 25) {
            int newIndex = (index + shift) - 26;
            return letters[newIndex];
        }
        if (index + shift < 25 && index + shift > 0) {
            return letters[index + shift];
        }
        if (index + shift == 0) {
            return letters[index + shift];
        }

        return 'X';
    }

    /**
     * Hanterar specialkaraktärer.
     * @param isSpecial
     * @return
     */
    int isSpecialChar(char isSpecial) {
        for (int i = 0; i != specialChar.length; i++) {
            if (specialChar[i] == isSpecial) {
                return i;
            }
        }
        return 999;
    }
}
