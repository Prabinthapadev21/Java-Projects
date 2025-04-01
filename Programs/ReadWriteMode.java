// doing reading and writing mode through file reader and file writer
package Programs;
import java.io.*;
public class ReadWriteMode {
    public static void main(String[] args) {
        String desktop_path = System.getProperty("user.home")+"/Desktop/";
        String sourcefile = desktop_path +"abc.txt";
        String destinationfile = desktop_path + "xyz.txt";
        try(FileReader reader = new FileReader(sourcefile); FileWriter writer = new FileWriter(destinationfile);){
            int character;
            while((character=reader.read())!=-1)
            {
                writer.write(character);
            }
            System.out.println("Filecopied from "+sourcefile + "to" + destinationfile);
        }
        catch (IOException e)
        {
            System.out.println("Error coping the file");
            e.printStackTrace();
        }
    }
}