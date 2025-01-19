# LZW-Encoding-and-Decoding
This project implements a complete pipeline for encoding and decoding data using the LZW (Lempel-Ziv-Welch) algorithm, with data represented as hexadecimal digits. The system consists of four programs: ByteToHex, HexToByte, LZWencode, and LZWdecode, each contributing to the encoding, decoding, compression, and decompression process.

ByteToHex:

Converts an input stream of bytes into an output stream of hexadecimal digits (base-16 characters 0-F). This step encodes the byte data into a readable and compact hexadecimal format.

HexToByte:

Reverses the operation performed by ByteToHex. It decodes a stream of hexadecimal digits back into the original byte stream, ensuring no loss of data integrity.

LZWencode:

Implements the LZW encoding algorithm, which compresses the hexadecimal stream from ByteToHex by encoding repeated patterns into a sequence of phrase numbers. This process reduces redundancy while creating a compact representation of the input data.

LZWdecode:

Complements LZWencode by decoding the sequence of phrase numbers back into the original hexadecimal stream. This step decompresses the data, ensuring that the exact input to LZWencode can be reconstructed.

# Key Features:

- Hexadecimal Conversion: Allows seamless conversion between bytes and hexadecimal digits for compatibility with various data formats and clear representation of processed data.

- LZW Algorithm Implementation: Achieves efficient compression during encoding and accurate decompression during decoding through dynamic dictionary-based pattern recognition.
  
- Data Integrity: The pipeline ensures that the final output matches the initial input, verifying the correctness of the encoding and decoding processes.


# Instructions to Run the Files

Before running the programs, use the cd command to navigate to the directory containing java files. For example:

cd path/to/your/java/files

Ensure that you have compiled the Java files into class files by executing the following commands in your terminal or command prompt:

javac ByteToHex.java

javac HexToByte.java

javac LZWencode.java

javac LZWdecode.java

You can now use the following commands to run the programs. The provided example uses MobyDick.txt as the input file. Replace inputfile_name with MobyDick.txt or any other file you want to process.

# 1. Convert Binary Data to Hexadecimal
   
To convert binary data from a file into hexadecimal and output both to the console and to a file named hex_output.txt (automatically generated), run:


java ByteToHex MobyDick.txt

# 2. Convert Hexadecimal Digits Back to Bytes
   
To convert the hexadecimal digits back to bytes and save the result in byteOutput.txt (automatically generated), pipe the output of ByteToHex into HexToByte:

java ByteToHex MobyDick.txt | java HexToByte

# 3. Perform LZW Encoding
   
To perform LZW encoding on the hexadecimal output and save the encoded data into encodedfile.txt (automatically generated), use:

java ByteToHex MobyDick.txt | java LZWencode

# 4. Decode the Encoded Data
   
To decode the LZW-encoded data back to its original format and save the result into decodedfile.txt (automatically generated), use the full pipeline command:

java ByteToHex MobyDick.txt | java LZWencode | java LZWdecode

# Notes:

Replace MobyDick.txt with the path to your desired input file if using a different file.

The output files (hex_output.txt, byteOutput.txt, encodedfile.txt, and decodedfile.txt) are automatically generated in the same directory where you run the commands.

Ensure all .java files and the input file are in the same directory as your terminal session or set the appropriate file paths.


