public class Encryptor{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c) {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock() {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str) {
        /* to be implemented in part (a) */
        int i = 0;
        for (int row = 0; row < letterBlock.length; row++) {
            for (int column = 0; column < letterBlock[0].length; column++) {
                if (i < str.length()) {
                    letterBlock[row][column] = str.substring(i, i + 1);
                }
                else {
                    letterBlock[row][column] = "A";
                }
                i++;
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock() {
        String str = "";
        for (int column = 0; column < letterBlock[0].length; column++) {
            for (int row = 0; row < letterBlock.length; row++) {
                str += letterBlock[row][column];
            }
        }
        return str;

    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message) {
        /* to be implemented in part (c) */
        String s = "";
        int n = numCols * numRows;
        int x = message.length() / n;
        if(message.length() % n != 0){
            x++;
        }
        for(int i = 0; i < x; i++) {
            fillBlock(message);
            s += encryptBlock();
            if(message.length() > n) {
                message = message.substring(n);
            }
        }
        return s;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage) {
        /* to be implemented in part (d) */
        String[][] decrypt = new String[numRows][numCols];
        String s = "";
        int num = encryptedMessage.length() / (numRows * numCols);
        if((encryptedMessage.length() % (numRows * numCols)) != 0) {
            num ++;
        }
        int index = 0;
        for (int k = 0; k < num; k ++) {
            for (int i = 0; i < numCols; i++) {
                for (int j = 0; j < numRows; j++) {
                    decrypt[j][i] = encryptedMessage.substring(index, index + 1);
                    index++;
                }
            }

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    s= s + decrypt[i][j];
                }
            }
        }

        while (s.charAt(s.length() - 1) == 'A') {
           s = s.substring(0, s.length() - 1);
        }

        return s;


    }
}

