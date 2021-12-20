import java.io.*;
import java.util.ArrayList;

public class Read {
    public static void main(String[] args) {
        ArrayList<String> employees = new ArrayList<>();
        readFile(employees);
        displayEmployees(employees);
    }// end main

    static void displayEmployees(ArrayList<String> employees){
        for(String employee: employees)
            System.out.println(employee);

    }// end displayEmployees

    static void readFile(ArrayList<String> employees){
        String line = "";
        BufferedReader fileInput = null;

        try {
            fileInput = new BufferedReader(new FileReader(
                    new File("C:/George/names/names.txt")));
            line = fileInput.readLine();
            while (line != null){
                employees.add(line);
                line = fileInput.readLine();
            }// end while
            fileInput.close();
        }//end try
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }//end catch1
        catch (EOFException eofe){
            System.out.println("no more lines to read.");
        }// end catch 2
        catch (IOException ioe){
            System.out.println("Error reading file.");
        }// end catch 3 ( it seems that this one is the one that controls the file error thing. or it isn't. Who knows? )
    }// end readfile

    static void getName(ArrayList<String> employees){
        // this will just be made in findword for convenience
    }


}

