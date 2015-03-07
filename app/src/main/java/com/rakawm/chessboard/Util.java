package com.rakawm.chessboard;

/**
 * Created by raka on 06/03/2015.
 */
public class Util {
    public static int getIntegerFromChar(String str) {
        int i = 0;
        switch (str) {
            case "a":
                i = 0;
                break;
            case "b":
                i = 1;
                break;
            case "c":
                i = 2;
                break;
            case "d":
                i = 3;
                break;
            case "e":
                i = 4;
                break;
            case "f":
                i = 5;
                break;
            case "g":
                i = 6;
                break;
            case "h":
                i = 7;
                break;
        }
        return i;
    }
}
