package com.chetan.dsa.sorting;

public class MergeSort {


    private void merge(int[] arr, int l, int mid, int r) {
        int n1 = mid;
        int n2 = r - mid;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for(int i =0; i< n1; i++){
            L[i] = arr[l + i];
        }
        for(int i =0; i< n2; i++){
            L[i] = arr[mid + i];
        }

        int i =0, j = 0;

        int k = l;
        while(i < n1 && j < n2){
            if(L[i] < R[j]){
                arr[k++] = L[i++];
            }else{
                arr[k++] = L[j++];
            }
        }

        while(i< n1){
            arr[k++] = L[i++];
        }
        while(j< n2){
            arr[k++] = L[j++];
        }
    }

    private void mergeSort(int arr[], int l, int r){
        if(l<r){
            int mid = l+r/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr, l, mid ,r);
        }
    }

    public int[] sort(int[] a){
        mergeSort(a, 0, a.length);
        return a;
    }
}
