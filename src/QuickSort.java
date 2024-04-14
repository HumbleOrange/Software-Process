import java.util.Scanner;
public class QuickSort {
    static int[] a = new int[100005];
    static int n;
    public static void qsort(int L, int R) {
        int i = L, j = R;
        int key = a[(L + R) / 2];
        while (i <= j) {
            while (a[i] < key) i++;
            while (a[j] > key) j--;
            if (i <= j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }
        if (j > L)   qsort(L, j);
        if (i < R)   qsort(i, R);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        for (int i = 0; i < n; i++)    a[i] = input.nextInt();
        qsort(0, n - 1);
        for (int i = 0; i < n; i++)    System.out.print(a[i] + " ");
    }
}