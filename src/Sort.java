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
            int l = 0;
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
}