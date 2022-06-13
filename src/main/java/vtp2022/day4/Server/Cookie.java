package vtp2022.day4.Server;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cookie {

    public static String getRandomCookie(String path){
        List<String> cookies = new LinkedList<>();
        String randomCookie = "";
            try {

            // use scanner on a file
            Scanner scanner = new Scanner(new File(path));

            while(scanner.hasNextLine())

            //add each line of file to cookies array list
            cookies.add(scanner.nextLine());
            scanner.close();

            System.out.println(cookies);
            Random random = new Random();

            //randomCookie is an index of cookies list
            randomCookie = cookies.get(random.nextInt(cookies.size()));
            System.out.println(randomCookie);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
            return randomCookie;
        }
}
