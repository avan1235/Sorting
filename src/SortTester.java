import java.util.Scanner;

/**
 * Created by Maciej Procyk on 30.03.2018.
 */
public class SortTester {
    public static void main(String[] args){
        Scanner inputKeyboard = new Scanner(System.in);
        int tableSize = 20;//inputKeyboard.nextInt();

        int[] tableSorting = new int[tableSize];

        for(int i = 0; i < tableSorting.length; i++){
            tableSorting[i] = (int) (Math.random()*tableSize);
            System.out.printf("%3d", tableSorting[i]);
        }
        System.out.println();

        int[] BubbleSorted = Sort.BubbleSort(tableSorting);
        for (int item : BubbleSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        int[] SelectionSorted = Sort.SelectionSort(tableSorting);
        for (int item : SelectionSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        int[] InsertionSorted = Sort.InsertionSort(tableSorting);
        for (int item : InsertionSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        int[] BinaryInsertionSorted = Sort.InsertionBinarySort(tableSorting);
        for (int item : BinaryInsertionSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

    }
}