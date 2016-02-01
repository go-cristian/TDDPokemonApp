package com.example.iyubinest.pokemonapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by iyubinest on 1/30/16.
 */
public class TestJSON {
    public static String getJsonPage() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./src/test/java/com/example/iyubinest/pokemonapp/pokemon1.json"));
        String testJson = scanner.useDelimiter("\\A").next();
        scanner.close();
        return testJson;
    }
}
