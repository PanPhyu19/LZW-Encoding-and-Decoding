
import java.io.*;

/**
 * The ByteToHex class reads bytes from a specified file, converts each byte to its hexadecimal representation,
 * and writes the hexadecimal digits to both the standard output and a text file.
 */
public class ByteToHex {

    /**
     * Main method which handles the conversion process from byte to hexadecimal.
     * It accepts one command-line argument specifying the input file name.
     *
     * @param args Command-line arguments, expects one argument which is the file path to read bytes from.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ByteToHex <input_file>");
            return;
        }

        try {
            // Open input stream for reading bytes from the specified file
            FileInputStream inputStream = new FileInputStream(args[0]);

            // Open output stream for writing hexadecimal digits to standard output
            PrintWriter systemOutput = new PrintWriter(System.out, true);

            // Open output stream for writing hexadecimal digits to a file named 'hex_output.txt'
            PrintWriter fileOutput = new PrintWriter(new FileWriter("hex_output.txt"));

            int byteRead;
            // Read each byte from the input stream until the end of the file
            while ((byteRead = inputStream.read()) != -1) {
                // Convert byte to a two-character hexadecimal string
                String hexString = String.format("%02X", byteRead);
                
                // Write the hexadecimal string to standard output
                systemOutput.print(hexString);
                // Write the hexadecimal string to the output file
                fileOutput.print(hexString);
            }

            // Flush output streams to ensure all data is written out
            systemOutput.flush();
            fileOutput.flush();

            // Close all streams to release system resources
            inputStream.close();
            fileOutput.close();
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
