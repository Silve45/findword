// I used this old project as a base for creating the directories in the beginning
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FilesCopyDemo {
    public static void main(String[] args) {
        Path p = Paths.get("C:/JavaProgramming/gameData");
        Path p1 = Paths.get("scores");
        Path p2 = Paths.get("backup");
        Path p3 = Paths.get("Highscores.txt");
        //create path for working directory ( folder )
        Path woD = p.resolve(p1);
        // create path for working file ( highscores in this case )
        Path woF = p.resolve(p1.resolve(p3));
        //create path for backup directory
        Path buD = p.resolve(p2);
        //create path for the backup file
        Path buF = p.resolve(p2.resolve(p3));

        try {
            if (Files.exists(woF)) {
                if (Files.notExists(buD)){
                    Files.createDirectories(buD);
                }

                Files.copy(woF,buF, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            }//end if
            if (Files.notExists(woD)){
                Files.createDirectories(woD);
            }//end if

            if (Files.notExists(woF)){
                Files.createFile(woF);
            }//end if


        }//end try
        catch (IOException e){
            System.err.println(e);
        }
    }
}
