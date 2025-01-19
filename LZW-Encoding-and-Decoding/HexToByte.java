
import java.io.*;

/**
 * The HexToByte class reads hexadecimal digits from standard input, converts them into binary bytes,
 * and writes these bytes to a specified output file.
 */
public class HexToByte {

    /**
     * Main method which handles the conversion process from hexadecimal to byte.
     * It reads from standard input until it reaches the end of input (EOF),
     * and writes the converted bytes to 'byteOutput.txt'.
     *
     * @param args Command-line arguments, not used in this program.
     */
    public static void main(String[] args) {
        try {
            // Open input stream for reading hexadecimal digits from standard input
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

            // Open output stream for writing bytes to 'byteOutput.txt'
            FileOutputStream outputStream = new FileOutputStream("byteOutput.txt");

            String hexString;
            // Read each line of hexadecimal digits from standard input
            while ((hexString = inputStream.readLine()) != null) {
                // Process each line in 2-character groups representing one byte
                for (int i = 0; i < hexString.length(); i += 2) {
                    // Ensure the substring does not exceed the length of the string
                    String hexByte = hexString.substring(i, Math.min(i + 2, hexString.length()));
                    // Convert the hexadecimal string to a byte
                    byte b = (byte) Integer.parseInt(hexByte, 16);
                    // Write the byte to the output file
                    outputStream.write(b);
                }
            }

            // Close streams to free system resources
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.err.println("Error processing input or output: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
