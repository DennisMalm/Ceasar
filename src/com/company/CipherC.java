package com.company;

public class CipherC {

    char letters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char specialChar[] = {' ', '!', '.', '?', ','};
    int randomShift[] = {12, 2, 3, 4, 5};

    char[] charArray;
    char[] decodeArray;


    CipherC(String input) {

        charArray = input.toCharArray();
        int shiftIndex = 0;

        String output = "";

        int count = 0;
        System.out.println("Randomiserad." +
                " \n -----------");
        System.out.println(input);

        for (int i = 0; i != charArray.length; i++) {

            if(shiftIndex != randomShift.length){

                if (Character.isUpperCase(charArray[i])) {
                    output += Character.toUpperCase(calcShift(getIndex(charArray[i]), randomShift[shiftIndex]));
                } else {
                    output += calcShift(getIndex(charArray[i]), randomShift[shiftIndex]);
                }
            } else{
                shiftIndex = 0;
                i--;
            }
            shiftIndex++;
            count++;

        }
        System.out.println(output);
        shiftIndex = 0;
        decodeArray = output.toCharArray();
        String decoded= "";
        for (int i = 0; i != decodeArray.length; i++) {

            if(shiftIndex != randomShift.length){
                if (Character.isUpperCase(decodeArray[i])) {

                    decoded += Character.toUpperCase(calcShift(getIndex(decodeArray[i]), -randomShift[shiftIndex]));
                } else {
                    decoded += calcShift(getIndex(decodeArray[i]), -randomShift[shiftIndex]);
                }
            } else {
                shiftIndex = 0;
                i--;
            }
            shiftIndex++;

        }
        System.out.println(decoded);

    }
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
    int isSpecialChar(char isSpecial) {
        for (int i = 0; i != specialChar.length; i++) {
            if (specialChar[i] == isSpecial) {
                return i;
            }
        }
        return 999;
    }
}
