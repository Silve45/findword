//I pulled this from online and then I edited it, the first git is the original
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class findword {
    public static void main(String[] args) throws FileNotFoundException {
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
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> wu = new ArrayList<>();
        ArrayList<String> ga = new ArrayList<>();

        //starts the while true stuff
        while (true){

        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter the word to be found (-1 to exit, -2 to re-go over blacklist words)");
        String word = sc1.next();
        if (wu.contains(word)){
            System.out.println("Word already on blacklist");
            continue;
        }



        //adding so that I can exit out of while loop
        if (word.equalsIgnoreCase("-1")){
            System.out.println("exiting");
            break;
        }

        if (word.equalsIgnoreCase("-2")){
            System.out.println("rechecking document for new occurrences of blacklist words");
            for(int i = 0; i < wu.size(); i++) {
                word = String.valueOf(wu.get(i));// trying to get all the words instead of just one
                System.out.println(word);

                boolean flag = false;
                int count = 0;
                Scanner sc2 = new Scanner(new FileInputStream("C:/George/big/big.txt"));

                while(sc2.hasNextLine()) {
                    String line = sc2.nextLine();
//                    System.out.println(line);
                    if(line.contains(word)) {
                        flag = true;
                        count = count+1;
                        if(name.isEmpty()){
                            ga.add(line);
                        }//end if
                        else if (name.contains(line)){
                            ga.remove(ga);
                        }
                        else {
                            ga.add(line);
                        }//end else
                    }// end line .contains
                }//end small while

                if(flag) {
                    System.out.println("File contains the specified word");
                    System.out.println("Number of occurrences is: "+count);
                    System.out.println("Although File contains the word, it may be looking at previously added words. This program will only add new occurrences, so even if you press y, no dupilicates will be added");
                    System.out.println("Add occurrence(s) to ban list?");
                    while (true){
                        check = sc1.nextLine();
                        if (check.equalsIgnoreCase(Y)){
                            System.out.println("Adding occurrence(s)");
                            name.addAll(ga);// replaced for loop, because it said I could


                            //this code is supposed to add a new banned word and ignore old (FIXED!!!)
                            if(wu.contains(word)){
                                ga.clear();
                                break;
                            }
                            else {
                                wu.add(word);
                                ga.clear();
                                break;
                            }
                        }
                        else if (check.equalsIgnoreCase(N)){
                            System.out.println("Occurrence(s) not added");
                            ga.clear();//this needs to be line or an equivalent
                            break;
                        }
                        else{
                            System.out.println("Please use Y or N");
                            //took out continue, because it said I could
                        }
                    }// end small while
                } else {
                    System.out.println("File does not contain the specified word");
                    ga.clear();
                }
                // justs prints out elements in arraylist
                System.out.println("Words in ban list");
                System.out.println(name);
                //prints out elements in blacklist
                System.out.println("Blacklisted words");
                System.out.println(wu);
            }//end giant for
            continue;
        }//end -2 if

        boolean flag = false;
        int count = 0;
        System.out.println("Contents of the line");
        //Reading the contents of the file
        Scanner sc2 = new Scanner(new FileInputStream("C:/George/big/big.txt"));

        while(sc2.hasNextLine()) {
            String line = sc2.nextLine();
//            System.out.println(line);
            if(line.contains(word)) {
                flag = true;
                count = count+1;
                if(name.isEmpty()){
                    ga.add(line);
                }//end if
                else if (name.contains(line)){
                    ga.remove(ga);
                }
                else {
                    ga.add(line);
//                    System.out.println("burger");
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
                name.addAll(ga);// replaced for loop, because it said I could


                //this code is supposed to add a new banned word and ignore old (FIXED!!!)
                if(wu.contains(word)){
                    ga.clear();
                    break;
                }
                else {
                    wu.add(word);
                    ga.clear();
                    break;
                }
            }
            else if (check.equalsIgnoreCase(N)){
                System.out.println("Occurrence(s) not added");
                ga.clear();//this needs to be line or an equivalent
                break;
            }
            else{
                System.out.println("Please use Y or N");
                //took out continue, because it said I could
            }
           }// end small while
        } else {
            System.out.println("File does not contain the specified word");
            ga.clear();
        }
            // justs prints out elements in arraylist
            System.out.println("Words in ban list");
            System.out.println(name);
            //prints out elements in blacklist
            System.out.println("Blacklisted words");
            System.out.println(wu);
//            ga.clear();
        }//end giant while

        System.out.println("Words are: ");

        String names;
        for (int i = 0; i < name.size(); i++){
             names = name.get(i);
            System.out.println(names);
        }

        try(FileWriter fw = new FileWriter(String.valueOf(g5),true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < name.size(); i++){
                pw.println("/ban" + name.get(i));
            }

        }catch (IOException i){
//            i.printStackTrace();
        }

    }// end psvm
}
//problem 1 it only calls the first blacklist word
//problem 2 it re-puts the blacklist word into the thing and does it again