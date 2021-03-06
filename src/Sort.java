import java.util.Arrays;

/**
 * Created by Maciej Procyk on 30.03.2018.
 */
public class Sort {

    public static int[] BubbleSort(int[] tableSort){
        int[] tableToSort = tableSort.clone();
        for (int i=0; i < tableToSort.length; i++){
            for(int j=1; j < tableToSort.length; j++){
                if (tableToSort[j] < tableToSort[j-1]){
                    tableToSort[j] ^= tableToSort[j-1];
                    tableToSort[j-1] ^= tableToSort[j];
                    tableToSort[j] ^= tableToSort[j-1];
                }
            }
        }
        return tableToSort;
    }

    public static  int[] SelectionSort(int[]  tableSort){
        int[] tableToSort = tableSort.clone();
        for(int i = 0; i < tableToSort.length; i++){
            int indexOfMin = i;
            for(int j = i+1; j < tableToSort.length; j++){
                if(tableToSort[j] < tableToSort[indexOfMin])
                    indexOfMin = j;
            }
            if(indexOfMin != i){
                tableToSort[i] ^= tableToSort[indexOfMin];
                tableToSort[indexOfMin] ^= tableToSort[i];
                tableToSort[i] ^= tableToSort[indexOfMin];
            }
        }
        return  tableToSort;
    }

    public static int[] InsertionSort(int[] tableSort){
        int[] tableToSort = tableSort.clone();
        for(int i = 1; i < tableToSort.length; i++){
            int j = i-1;
            int actVal = tableToSort[i];
            while (j>=0 && tableToSort[j] > actVal){
                tableToSort[j+1] = tableToSort[j];
                j--;
                tableToSort[j+1] = actVal;
            }
        }
        return tableToSort;
    }

    public static int[] InsertionBinarySort(int[] tableSort){
        int[] tableToSort = tableSort.clone();
        for(int i = 1; i < tableToSort.length; i++){
            int actVal = tableSort[i];
            int indexShift = BinarySearchForSortingActive(0, i, tableToSort, actVal);
            int j = i-1;
            while (j >= indexShift){
                tableToSort[j+1] = tableToSort[j];
                j--;
            }
            tableToSort[j+1] = actVal;
        }
        return tableToSort;
    }

    private static int BinarySearchActive(int di, int ui, int[] table, int value){
        if(ui >= di){
            int mi = di + (ui-di)/2;
            if(table[mi] == value) {
                return mi;
            }
            else if(table[mi] > value){
                return BinarySearchActive(di, mi-1, table, value);
            }
            else{
                return BinarySearchActive(mi+1, ui, table, value);
            }
        }
        return -1;
    }

    public static int BinarySearch(int[] tableToFind, int valueToFind){
        return BinarySearchActive(0, tableToFind.length-1, tableToFind, valueToFind);
    }

    private static int BinarySearchForSortingActive(int di, int ui, int[] table, int value){
        int mi = di + (ui-di)/2;

        if (di == ui)
            return di;
        else if(value > table[mi]){
            return BinarySearchForSortingActive(mi+1, ui, table, value);
        }
        else if(value < table[mi]){
            return BinarySearchForSortingActive(di, mi, table, value);
        }

        return mi;
    }

    public static int[] InsertionBinarySortInline(int[] tableSort){ 
        int[] tableToSort = tableSort.clone();
        for(int i = 1; i < tableToSort.length; i++){
            int actValue = tableToSort[i];
            int l = -1;
            int p = i;
            while (p-l > 1){
                int s = (l+p)/2;
                if (actValue < tableToSort[s]){
                    p = s;
                }
                else{
                    l = s;
                }
            }
            int j = l+1;
            for (int k = i; k > j; k--){
                tableToSort[k] = tableToSort[k-1];
            }
            tableToSort[j] = actValue;
        }
        return tableToSort;
    }

    private static void Merge(int[] tableMerge, int p, int q, int r){
        int num1 = q-p+1;
        int num2 = r-q;

        int[] L = new int[num1];
        int[] R = new int[num2];

        for(int i = 0; i < num1; i++){
            L[i] = tableMerge[p+i];
        }
        for(int i = 0; i < num2; i++){
            R[i] = tableMerge[q+1+i];
        }

        int i1 = 0, i2 = 0;
        int mi = p;
        while (i1 < num1 && i2 < num2){
            if(L[i1] <= R[i2]){
                tableMerge[mi] = L[i1];
                i1++;
            }
            else{
                tableMerge[mi] = R[i2];
                i2++;
            }
            mi++;
        }
        while(i1 < num1){
            tableMerge[mi] = L[i1];
            i1++;
            mi++;
        }
        while(i2 < num2) {
            tableMerge[mi] = R[i2];
            i2++;
            mi++;
        }
    }

    private static void MergeSortActive(int[] tableSort, int p, int r){
        if(p < r){
            int q = p +((r-p)/2);
            Sort.MergeSortActive(tableSort, p, q);
            Sort.MergeSortActive(tableSort, q+1, r);
            Sort.Merge(tableSort, p, q, r);
        }
    }

    private static final int MAX_MERGE_SORT = 5;

    private static void InsertionBinarySortInlineForMerge(int[] tableSort, int m, int w){
        int[] tableToSort = Arrays.copyOfRange(tableSort, m, w+1); // Pay attention at indexes
        for(int i = 1; i < tableToSort.length; i++){                  // The second one (w+1) will NOT be copied
            int actValue = tableToSort[i];
            int l = -1;
            int p = i;
            while (p-l > 1){
                int s = (l+p)/2;
                if (actValue < tableToSort[s]){
                    p = s;
                }
                else{
                    l = s;
                }
            }
            int j = l+1;
            for (int k = i; k > j; k--){
                tableToSort[k] = tableToSort[k-1];
            }
            tableToSort[j] = actValue;
        }
        // Copying the mini-sorted table to the orginal one
        // which is going to be merged

        for(int i = 0; i < w-m+1; i++){
            tableSort[m+i] = tableToSort[i];
        }
    }

    private static void MergeAndInsertionSortActive(int[] tableSort, int p, int r){
        if(p < r){
            if (r - p > MAX_MERGE_SORT){
                int q = p +((r-p)/2);
                Sort.MergeAndInsertionSortActive(tableSort, p, q);
                Sort.MergeAndInsertionSortActive(tableSort, q+1, r);
                Sort.Merge(tableSort, p, q, r);
            }
            else{
                Sort.InsertionBinarySortInlineForMerge(tableSort, p, r);
            }
        }
    }

    public static int[] MergeSort(int[] tableSort){
        int[] tableToSort = tableSort.clone();
        Sort.MergeSortActive(tableToSort, 0, tableToSort.length-1);
        return tableToSort;
    }

    public static int[] MergeAndInsertionSort(int[] tableSort){
        int[] tableToSort = tableSort.clone();
        Sort.MergeAndInsertionSortActive(tableToSort, 0, tableToSort.length-1);
        return tableToSort;
    }

    /**
     * Allows sorting by using quicksort when table and indexes of
     * the begining and end are given
     * @param tableSort is a table to sort by the method
     * @param l is the left index that WILL be sorted too
     * @param r is the right index that WILL be sorted too
     * @return nothing to return, changes the orginal table
     */

    private static void QuickSortActive(int[] tableSort, int l, int r){
        int i = l;
        int j = r;
        int qValue = tableSort[l + ((r-l)/2)];

        while(i <= j){
            while (tableSort[i] < qValue){
                i++;
            }
            while(tableSort[j] > qValue){
                j--;
            }
            if (i < j){
                tableSort[i] ^= tableSort[j];
                tableSort[j] ^= tableSort[i];
                tableSort[i] ^= tableSort[j];
                i++;
                j--;
            }
            else if(i == j){
                i++; // Can't swap in tricky way when i == j
                j--;
            }
        }
        if (i < r){
            Sort.QuickSortActive(tableSort, i, r);
        }
        if(j > l){
            Sort.QuickSortActive(tableSort, l, j);
        }
    }

    public static  int[] QuickSort(int[] tableSort){
        int[] tableToSort = tableSort.clone();
        Sort.QuickSortActive(tableToSort, 0, tableToSort.length-1);
        return  tableToSort;
    }

    public static int findMaxInt(int... values){
        int largest = values[0];
        for (int i = 1; i < values.length; i++){
            if (values[i] > largest){
                largest = values[i];
            }
        }
        return largest;
    }

    public static int findMinInt(int... values){
        int smallest = values[0];
        for(int i = 0; i < values.length; i++){
            if (values[i] < smallest){
                smallest = values[i];
            }
        }
        return smallest;
    }

    public static int[] findMinAndMaxInt(int... values){
        int[] mini = new int[values.length/2];
        int[] maxi = new int[values.length/2];
        int index = 0;

        int i = 0;
        while((i + 1) < values.length){
            if(values[i] < values[i+1]){
                mini[index] = values[i];
                maxi[index] = values[i+1];
            }
            else{
                mini[index] = values[i+1];
                maxi[index] = values[i];
            }
            index++;
            i += 2;
        }

        int[] result = new int[2];
        result[0] = Sort.findMinInt(mini);
        result[1] = Sort.findMaxInt(maxi);
        if (i < values.length){
            if(values[i] < result[0]){
                result[0] = values[i];
            }
            if(values[i] > result[1]){
                result[1] = values[i];
            }
        }
        return result;
    }
}