package com.chetan.dsa.sorting;

public class HeapSort {

    private void maxHeapify(int[] a, int n, int i){
        int largest = i;
        int l = i*2 + 1;
        int r = i*2 + 2;

        if(l < n && a[l] > a[largest]){
            largest = l;
        }
        if(r < n && a[r] > a[largest]){
            largest = r;
        }

        if(largest != i){
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            maxHeapify(a, n, largest);
        }
    }

    private void maxHeapSort(int[] a){
        int n = a.length;

        for(int i = n/2 -1; i>= 0; i--){
            maxHeapify(a, n, i);
        }

        for(int i =n-1; i>= 0; i++){
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            maxHeapify(a, i, 0);
        }
    }

    private void minHeapify(int[] a, int n, int i){
        int largest = i;
        int l = i*2 + 1;
        int r = i*2 + 2;

        if(l < n && a[l] < a[largest]){
            largest = l;
        }
        if(r < n && a[r] < a[largest]){
            largest = r;
        }

        if(largest != i){
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            maxHeapify(a, n, largest);
        }
    }
    
    private void minHeapSort(int[] a){
        int n = a.length;

        for(int i = n/2 -1; i>= 0; i--){
            minHeapify(a, n, i);
        }

        for(int i =n-1; i>= 0; i++){
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            minHeapify(a, i, 0);
        }
    }

    public int[] maxSort(int[] a){
        maxHeapSort(a);
        return a;
    }


    public int[] minSort(int[] a){
        return a;
    }
}
