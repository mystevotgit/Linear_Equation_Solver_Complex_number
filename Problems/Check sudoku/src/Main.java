import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int limit = n * n;
        int[][] sudoko = new int[limit][limit];
        int[] row = new int[n * n];
        int[] col = new int[n * n];
        int[] square = new int[n * n];
        int input;
        int counter = 0;

        for (int i = 0; i < limit; i++) {

            for (int j = 0; j < limit; j++) {
                input = scanner.nextInt();
                sudoko[i][j] = input;
            }

        }

        for (int i = 0; i < limit; i++) {

            for (int j = 0; j < limit; j++) {
                row[j] = sudoko[i][j];
                col[j] = sudoko[j][i];
                //check if inner square is made of unique values
                if (i % n == 0) {
                    for (int k = 0; k < n; k++) {
                        square[counter] = sudoko[k][j];
                        counter++;
                    }
                }

                if (counter == limit && !checkUnique(square)) {
                    System.out.println("NO");
                    return;
                } else if (counter == limit) {
                    counter = 0;
                }

            }
            if (!checkUnique(row) || !checkUnique(col)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static boolean checkUnique(int[] array) {
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != i + 1) {
                return false;
            }
        }
        return true;
    }
}
