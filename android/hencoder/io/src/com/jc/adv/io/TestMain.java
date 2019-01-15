package com.jc.adv.io;

import java.io.*;
import java.nio.CharBuffer;

public class TestMain {
    public static void main(String[] args) {
//        outPutSteam();
        inputSteam();
    }

    private static void inputSteam() {
        try {
            FileReader fileReader = new FileReader("./text.txt");
//            CharBuffer charBuffer = new StB;
//            fileReader.read()
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void outPutSteam() {
        try {
            FileWriter writer = new FileWriter("./text.txt");
            writer.write("sdfasfsdf");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
