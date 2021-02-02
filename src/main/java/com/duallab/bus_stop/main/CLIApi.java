package com.duallab.bus_stop.main;

import com.duallab.bus_stop.exception.CLIApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIApi {

    private CLIApi() {
    }

    public static String prompt(String title) {
        System.out.println(title);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new CLIApiException(e);
        }
    }

    public static void printInfo(String info) {
        System.out.println(info);
    }
}
