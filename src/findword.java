//I pulled this from online and then I edited it, the first git is the original
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class findword {
    public static void main(String args[]) throws FileNotFoundException {
        // going to try and create a way to create directories
        Path p = Paths.get("C:/");
        Path p1 = Paths.get("George");
        Path p2 = Paths.get("names");
        Path p3 = Paths.get("names.txt");
        Path p4 = Paths.get("ban");
        Path p5 = Paths.get("ban.txt");

        Path g1 = p.resolve(p1);
        Path g2 = g1.resolve(p2);
        Path g3 = g2.resolve(p3);
        Path g4 = g1.resolve(p4);
        Path g5 = g4.resolve(p5);


        try {
            if (Files.notExists(g1)){
                Files.createDirectories(g1);
                System.out.println("Created George folder");
            }//end if
            if (Files.notExists(g2)){
                Files.createDirectories(g2);
                System.out.println("Created names folder");
            }//end if
            if (Files.notExists(g3)){
                Files.createFile(g3);
                System.out.println("Created names.txt. To add names you want to scan through, please write them in this document");
            }//end if
            if (Files.notExists(g4)){
                Files.createDirectories(g4);
                System.out.println("Created ban folder");
            }//end if
            if (Files.notExists(g5)){
                Files.createFile(g5);
                System.out.println("Created ban.txt");
            }//end if

        }//end try
//        catch (NoSuchFileException i){
//            System.out.println(i);} I don't need this anymore
         catch (IOException e){
            System.err.println(e);
        }


        //Reading the word to be found from the user
        String check;
        String Y = "Y";
        String N = "N";
        ArrayList<String> name = new ArrayList<String>();

        //starts the while true stuff
        while (true){

        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter the word to be found");
        String word = sc1.next();

        boolean flag = false;
        int count = 0;
        System.out.println("Contents of the line");
        //Reading the contents of the file, it seems this person used a scanner for all the occurrences of the words
        Scanner sc2 = new Scanner(new FileInputStream("C:/George/names/names.txt"));
        while(sc2.hasNextLine()) {
            String line = sc2.nextLine();
            System.out.println(line);
            if(line.contains(word)) {
                flag = true;
                count = count+1;
                if(name.isEmpty()){
                    name.add(line);
                }//end if
                else {name.add(0,line);
                }//end else

            }// end line .contains
        }//end small while
        if(flag) {
            System.out.println("File contains the specified word");
            System.out.println("Number of occurrences is: "+count);
            System.out.println("Add occurrence(s) to ban list?");
           while (true){
            check = sc1.nextLine();
            if (check.equalsIgnoreCase(Y)){
                System.out.println("Adding occurrence(s)");
                break;
            }
            else if (check.equalsIgnoreCase(N)){
                System.out.println("Occurrence(s) not added");
                name.remove(0);
                break;
            }
            else{
                System.out.println("Please use Y or N");
                continue;
            }
           }// end small while
        } else {
            System.out.println("File does not contain the specified word");
        }
            // justs prints out elements in arraylist
            System.out.println(name);
        }
    }// end psvm


}
// for next time add all the found words into a text file with .ban at the beginning :)