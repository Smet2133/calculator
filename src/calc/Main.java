package calc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("hi");
        File f = new File(".");
        System.out.println(f.getAbsolutePath());
        String  fileString;
        fileString = Utilities.fileToString("index.html");
        fileString = fileString.replaceAll("\\$\\{greetings}", "Hi, bro");
        fileString = fileString.replaceAll("\"", "\\\"");
        PrintWriter localOut = new PrintWriter(new FileWriter("localOut.txt"));
        localOut.print(fileString);
        localOut.close();
        System.out.println(fileString);
    }
}
