import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    public void input(){



    }// end input

}// end Create
