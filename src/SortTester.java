import java.util.Scanner;

/**
 * Created by Maciej Procyk on 30.03.2018.
 */
public class SortTester {
    public static void main(String[] args){
        Scanner inputKeyboard = new Scanner(System.in);
        int tableSize = 7;//inputKeyboard.nextInt();

        int[] tableSorting = new int[tableSize];

        System.out.println("Data:");
        for(int i = 0; i < tableSorting.length; i++){
            tableSorting[i] = (int) (Math.random()*tableSize);
            System.out.printf("%3d", tableSorting[i]);
        }
        System.out.println();

        System.out.println("BubbleSort:");
        int[] BubbleSorted = Sort.BubbleSort(tableSorting);
        for (int item : BubbleSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        System.out.println("SelectionSort:");
        int[] SelectionSorted = Sort.SelectionSort(tableSorting);
        for (int item : SelectionSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        System.out.println("InsertionSort:");
        int[] InsertionSorted = Sort.InsertionSort(tableSorting);
        for (int item : InsertionSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        System.out.println("BinaryInserionSort:");
        int[] BinaryInsertionSorted = Sort.InsertionBinarySort(tableSorting);
        for (int item : BinaryInsertionSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        System.out.println("BinaryInserionInlineSort:");
        int[] BinaryInsertionSortedInline = Sort.InsertionBinarySortInline(tableSorting);
        for (int item : BinaryInsertionSortedInline){
            System.out.printf("%3d", item);
        }
        System.out.println();

        System.out.println("MergeSort:");
        int[] MergeSorted = Sort.MergeSort(tableSorting);
        for (int item : MergeSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        System.out.println("MergeAndInsertionSort:");
        int[] MergeInsertionSorted = Sort.MergeAndInsertionSort(tableSorting);
        for (int item : MergeInsertionSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();

        System.out.println("QuickSort:");
        int[] QuickSorted = Sort.QuickSort(tableSorting);
        for (int item : QuickSorted){
            System.out.printf("%3d", item);
        }
        System.out.println();
    }
}