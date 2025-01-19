
import java.io.*;

/**
 * The LZWencode class implements an LZW compression algorithm specifically designed
 * to encode hexadecimal strings ('0'-'9', 'A'-'F'). It uses a trie structure to manage
 * dictionary entries efficiently during the encoding process.
 */
public class LZWencode {
    private static final int MAX_CODES = 4500;  // Maximum number of entries in the dictionary
    private Node root;  // Root node of the trie
    private int nextCode;  // Next available code index for new dictionary entries

    /**
     * Constructs an LZWencode instance by initializing the trie with predefined entries.
     */
    public LZWencode() {
        root = new Node(-1); // Root node does not correspond to any character
        initializeTrie();
    }

    /**
     * Initializes the trie with single character entries for hexadecimal digits.
     */
    private void initializeTrie() {
        nextCode = 0; // Start indexing from 0 for dictionary codes
        // Add entries for '0' to '9'
        for (char c = '0'; c <= '9'; c++) {
            int idx = c - '0';
            root.children[idx] = new Node(nextCode++);
        }
        // Add entries for 'A' to 'F'
        for (char c = 'A'; c <= 'F'; c++) {
            int idx = 10 + (c - 'A');
            root.children[idx] = new Node(nextCode++);
        }
    }

    /**
     * Encodes input read from an InputStream using LZW encoding and writes output to both
     * a file and the console.
     *
     * @param input InputStream from which to read the raw hexadecimal data.
     * @param fileOutput OutputStream connected to the output file.
     * @param consoleOutput OutputStream connected to the console.
     * @throws IOException If an I/O error occurs.
     */
    public void encode(InputStream input, OutputStream fileOutput, OutputStream consoleOutput) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutput));
        BufferedWriter consoleWriter = new BufferedWriter(new OutputStreamWriter(consoleOutput));
        
        int read;
        Node current = root;

        while ((read = input.read()) != -1) {
            char c = (char) read;
            if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                continue; // Skip non-hex characters
            }
            int idx = (c <= '9') ? c - '0' : 10 + (c - 'A');

            if (current.children[idx] == null) {
                fileWriter.write(current.index + "\n");
                consoleWriter.write(current.index + "\n");
                if (nextCode < MAX_CODES) {
                    current.children[idx] = new Node(nextCode++);
                }
                current = root.children[idx];
            } else {
                current = current.children[idx];
            }
        }

        if (current != root && current.index != -1) {
            fileWriter.write(current.index + "\n");
            consoleWriter.write(current.index + "\n");
        }
        fileWriter.flush();
        consoleWriter.flush();
        fileWriter.close();
        consoleWriter.close();
    }

    /**
     * Main method to execute the encoding process. Takes input from standard input and writes
     * the encoded output to both the console and a specified file.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("encodedfile.txt")) {
            new LZWencode().encode(System.in, fos, System.out);
        }
    }
}

/**
 * Represents a node in the trie used in the LZWencode class. Each node can have up to 16 children
 * corresponding to hexadecimal characters.
 */
class Node {
    Node[] children = new Node[16]; // Children nodes for each hexadecimal character
    int index; // Dictionary index associated with the node

    public Node(int idx) {
        this.index = idx;
    }
}
