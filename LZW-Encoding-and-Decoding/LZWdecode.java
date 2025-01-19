
import java.io.*;
import java.util.*;

/**
 * The LZWdecode class implements an LZW decompression algorithm specifically designed
 * to decode a stream of LZW-encoded indices (assuming the dictionary was initialized with 
 * hexadecimal characters '0'-'9' and 'A'-'F'). It rebuilds the dictionary dynamically
 * during the decoding process.
 */
public class LZWdecode {
    private List<String> dictionary = new ArrayList<>();

    /**
     * Constructs an LZWdecode instance by initializing the dictionary with predefined entries
     * for hexadecimal characters.
     */
    public LZWdecode() {
        // Initialize the dictionary with single hexadecimal characters
        for (char ch = '0'; ch <= '9'; ch++) {
            dictionary.add(String.valueOf(ch));
        }
        for (char ch = 'A'; ch <= 'F'; ch++) {
            dictionary.add(String.valueOf(ch));
        }
    }

    /**
     * Decodes input read from an InputStream using LZW decoding and writes the output to a file.
     *
     * @param input InputStream from which to read the encoded LZW indices.
     * @param output OutputStream connected to the output file.
     * @throws IOException If an I/O error occurs.
     */
    public void decode(InputStream input, OutputStream output) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        int prevCode = -1; // Previous code read from the input

        String line;
        while ((line = reader.readLine()) != null) {
            int code = Integer.parseInt(line.trim());
            String entry;

            if (code < dictionary.size()) {
                entry = dictionary.get(code);
            } else if (code == dictionary.size() && prevCode != -1) {
                // This handles the special case where the new string is the last string + its first character
                entry = dictionary.get(prevCode) + dictionary.get(prevCode).charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid LZW code: " + code);
            }

            writer.write(entry);
            
            // Expand the dictionary with new entries based on the decoded strings
            if (prevCode != -1 && dictionary.size() < 4500) { // Ensures within dictionary size limits
                dictionary.add(dictionary.get(prevCode) + entry.charAt(0));
            }
            prevCode = code; // Update prevCode to the current code after processing
        }

        writer.flush();
        writer.close();
    }

    /**
     * Main method to execute the decoding process. Takes encoded input from standard input and writes
     * the decoded output to a specified file.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("decodedfile.txt")) {
            new LZWdecode().decode(System.in, fos);
        }
    }
}
