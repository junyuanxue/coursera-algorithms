import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        String inputString = StdIn.readString();
        String[] strings = inputString.split("\\s+");
        print(size, strings);
    }

    private static void print(int size, String[] strings) {
        strings = shuffle(strings);
        for (int i = 0; i < size; i++) {
            StdOut.println(strings[i]);
        }
    }

    private static String[] shuffle(String[] strings) {
        for (int i = 1; i < strings.length; i++) {
            int randomIndex = StdRandom.uniform(i + 1);
            String tempItem = strings[i];
            strings[i] = strings[randomIndex];
            strings[randomIndex] = tempItem;
        }
        return strings;
    }
}
