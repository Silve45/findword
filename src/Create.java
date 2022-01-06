import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Create {


    Path p = Paths.get("C:/");
    Path p1 = Paths.get("George");
    Path p2 = Paths.get("names");
    Path p3 = Paths.get("names.txt");
    Path p4 = Paths.get("ban");
    Path p5 = Paths.get("ban.txt");
    //load files
    Path p6 = Paths.get("load");
    Path p7 = Paths.get("load.txt");


    Path g1 = p.resolve(p1);//C:/George
    Path g2 = g1.resolve(p2);//George/names
    Path g3 = g2.resolve(p3);//names/names.txt
    Path g4 = g1.resolve(p4);//George/ban
    Path g5 = g4.resolve(p5);//ban/ban.txt
    //save and loaders
    Path g6 = g1.resolve(p6);//George/load
    Path g7 = g6.resolve(p7);//load/load.txt

    public void createfolder(){
        try {
            //creates george folder if not already created
            if (Files.notExists(g1)){
                Files.createDirectories(g1);
                System.out.println("Created George folder");
            }//end if
            //creates names folder if not already created
            if (Files.notExists(g2)){
                Files.createDirectories(g2);
                System.out.println("Created names folder");
            }//end if
            //creates names file if not already created
            if (Files.notExists(g3)){
                Files.createFile(g3);
                System.out.println("Created names.txt. To add names you want to scan through, please write them in this document");
            }//end if
            //creates ban folder if not already created
            if (Files.notExists(g4)){
                Files.createDirectories(g4);
                System.out.println("Created ban folder");
            }//end if
            //creates ban file if not already created
            if (Files.notExists(g5)){
                Files.createFile(g5);
                System.out.println("Created ban.txt");
            }//end if
            //creates ban folder if not already created
            if (Files.notExists(g6)){
                Files.createDirectories(g6);
                System.out.println("Created load folder");
            }//end if
            //creates ban file if not already created
            if (Files.notExists(g7)){
                Files.createFile(g7);
                System.out.println("Created load.txt");
            }//end if
        }//end try
        catch (IOException e){
            System.err.println(e);
        }


    }// end create folder

    public void ArrayOutput(String path, ArrayList<String>info, String bmessage, String amessage){
        try(FileWriter fw = new FileWriter(path,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < info.size(); i++){
                pw.println(bmessage + info.get(i) + amessage);
            }

        }catch (IOException i){
//            i.printStackTrace();
        }


    }// end output

    //this reads a file and adds all of its contents into an arraylist, so in essence a loader
    public void PrintInput(String path, String O,ArrayList<String> Add) throws FileNotFoundException {
        Scanner sc1 = new Scanner(new FileInputStream(path));

        if (O.equalsIgnoreCase("o")){
           while (sc1.hasNext()){
            System.out.println(sc1.nextLine());
           }// end while
        }
        else {
            //nothing to see here
        }//end check

        try {
        if (Add.equals(null)){

        }
        else {
            while (sc1.hasNext()){
                String line = (sc1.nextLine());

                if (Add.contains(line)){
                    // based on old lines in findword
                }
                else {
                    Add.add(line);
                }
            }
        }//end add
        }//end try
        catch (NullPointerException e){
            //if Add is null, nothing will happen
        }//end catch

    }// end input

    public void ClearDocument (String path) throws FileNotFoundException {
        //these 3 lines wipe the old blacklist, so it can be fully overwritten
        PrintWriter w1 = new PrintWriter(String.valueOf(path));
        w1.print("");
        w1.close();
    }
    public void remove(String path) throws FileNotFoundException {
        while (true) {// begin big while
            ArrayList<String> gather = new ArrayList<>();
            ArrayList<String> bean = new ArrayList<>();
            Scanner sc1 = new Scanner(System.in);
            PrintInput(path, "n", bean);
            System.out.println(bean);
            boolean flag = false;
            int count = 0;
//        System.out.println("Contents of the line"); // part of the sout(line) thing below
            //Reading the contents of the file
            System.out.println("Print a word to be deleted (-1 to exit, press enter to include all the words)");
            String word = sc1.nextLine();
            Scanner sc2 = new Scanner(new FileInputStream(path));

            //exits if word equals -1
            if (word.equals("-1")){
                break;
            }

            // if it doesn't equal -1 we can keep going
            String line = "lll";
            while (sc2.hasNextLine()) {
                line = sc2.nextLine();
//            System.out.println(line); // removing, because it is too cumbersome to keep using that over and over again
                if (line.contains(word)) {
                    flag = true;

                    if (bean.contains(line)) {
                        count = count + 1;
                    } else {
                        continue;
                    }
                }// end line .contains
            }//end small while

            if (flag) {
                System.out.println("File contains the specified word");
                System.out.println("Number of new occurrences is: " + count);
                System.out.println("Do you want to remove all occurrences from ban list?");


                while (true) {
                    String check = sc1.next();
                    if (check.equalsIgnoreCase("y")) {
                        System.out.println("Removing");
                        for (int i = 0; i < bean.size(); i++) {
                            if (bean.get(i).contains(word)) {// it will be if bean contains line then write it in get array list, and then I will rewrite file using an arrayoutputmethod
                            }//end if
                            else {
                                gather.add(bean.get(i));
                            }// end else
                        }

                        //clears the document and then outputs the new list
                        ClearDocument(path);
                        ArrayOutput(path, gather, "", "");
                        break;
                    } else if (check.equalsIgnoreCase("n")) {
                        System.out.println("Okay not removing occurrences");
                        break;
                    } else {
                        System.out.println("please use Y or N");
                    }

                }

            } else if (!flag) {
                System.out.println("File does not contain word");
            }

        }// end big while
        System.out.println("Goodbye!");
    }//end remove

}// end Create
