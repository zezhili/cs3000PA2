// RandomStrings.java:  generate N random strings, one per line

import java.util.Random;

public class RandomStrings {
    
    public static int STRING_LENGTH = 80;

    public static void main (String[] args) {
        Random rng = new Random();
        int N = Integer.parseInt(args[0]);
        for (int i = 0; i < N; i++) {
            String nextString = "";
            for (int c = 0; c < STRING_LENGTH; c++) {
                int next_char = rng.nextInt(127-33) + 33;
                nextString += (char) next_char;
            }
            System.out.println(nextString);
        }
    }
}
