package com.company;

public class CipherC {

    char letters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    char specialChar[] = {' ', '!', '.', '?', ','};
    int randomShift[] = {1, 2, 3};

    char[] charArray;
    int shiftKey;

    CipherC(String input) {

        charArray = input.toCharArray();
        shiftKey = 0;
        String output = "";

        System.out.println(input);

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isUpperCase(charArray[i])) {
                output += Character.toUpperCase(calcShift(getIndex(charArray[i]), randomShift[shiftKey]));
            } else {
                output += calcShift(getIndex(charArray[i]), randomShift[shiftKey]);
            }
            shiftKey++;
            if (shiftKey == randomShift.length)
        }
        System.out.println(output);


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
