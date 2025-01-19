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


