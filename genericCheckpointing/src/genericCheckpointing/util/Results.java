package genericCheckpointing.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Results class that stores the results of each tree in its object
 *
 * @author tushitjain
 */
public class Results {
    private PrintWriter out;
    private StringBuilder sb;

    /**
     * Results constructor that creates a stringbuilder object to be used.
     */
    public Results() {
        sb = new StringBuilder();
}

    /**
     * This function created the whole string to write to the file
     *
     * @param s: the string value to append to already created String
     */
    public void storeNewResult(String s) {
        sb.append(s);
    }

    /**
     * this function writes the String value to console.
     */
    public void writeToStdout(String s) {
        System.out.println(sb);
    }

    /**
     * Implemented function from FileDisplayInterface that writes the String to the File
     */
    public void writeToFile(String s) {
        File file;
        file = new File(s);
        if (!file.isDirectory() && file.exists()) {
            file.delete();
        }
        try {
            out = new PrintWriter(new FileWriter(s, true), true);
            out.write(sb.toString());
        } catch (IOException e) {
            System.out.println("There was some error in creating the file");
            e.printStackTrace();
        } finally {
            out.close();
            
        }
    }

    /**
     * Overridden method toString to give class description
     */
    @Override
    public String toString() {
        return sb.toString();
    }


}