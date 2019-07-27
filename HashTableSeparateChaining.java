package com.chetan.dsa.data_structure.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HashTableSeparateChaining<K,V>{

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double loadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K,V>>[] table;

    public HashTableSeparateChaining(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity){
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity, double maxLoadFactor){
        if(capacity < 0) capacity = DEFAULT_CAPACITY;
        if(maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)){
            maxLoadFactor = DEFAULT_LOAD_FACTOR;
        }

        this.loadFactor = maxLoadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        threshold = (int) (capacity * loadFactor);

        table = new LinkedList[this.capacity];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    private int normalizeIndex(int keyHash){
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear(){
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(K key){
        return hasKey(key);
    }

    private boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    public V put(K key, V value){
        return insert(key, value);
    }

    public V add(K key, V value){
        return insert(key, value);
    }

    public V insert(K key , V value){
        if(key == null){ throw  new RuntimeException();}
        Entry<K,V> newEntry = new Entry<>(key, value);

        int bucketIndex = normalizeIndex(newEntry.hash);

        return  bucketInsertEntry(bucketIndex, newEntry);
    }

    public V get(K key){
        if(key == null) return null;

        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K,V> entry = bucketSeekEntry(bucketIndex, key);

        if(entry!= null) return entry.value;
        return null;
    }


    public V remove(K key){
        if(key == null) return null;

        int bucketIndex = normalizeIndex(key.hashCode());

        return bucketRemoveEntry(bucketIndex, key);
    }

    private V bucketRemoveEntry(int bucketIndex, K key) {
        Entry<K,V> entry = bucketSeekEntry(bucketIndex, key);

        if(entry != null){
            LinkedList<Entry<K,V>> bucket = table[bucketIndex];
            bucket.remove(entry);
            --size;
            return entry.value;
        }else return null;
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K, V> newEntry) {

        LinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if(bucket == null) table[bucketIndex] = bucket = new LinkedList<>();

        Entry<K,V> existentEntry = bucketSeekEntry(bucketIndex, newEntry.key);

        if(existentEntry == null){
            bucket.add(newEntry);
            if(++size > threshold) resizeTable();
            return null;
        }else{
            V  oldVal = existentEntry.value;
            existentEntry.value = newEntry.value;
            return oldVal;
        }
    }


    private Entry<K,V> bucketSeekEntry(int bucketIndex, K key){
        if(key == null){
            return null;
        }
        LinkedList<Entry<K,V>> bucket = table[bucketIndex];

        if(bucket == null) bucket = table[bucketIndex] = new LinkedList<>();

        for(Entry<K,V> entry : bucket){
            if(entry.key.equals(key)) return entry;
        }
        return null;
    }


    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * loadFactor);

        LinkedList<Entry<K,V>>[] newTable = new LinkedList[capacity];

        for(int i =0; i< table.length; i++){
            if(table[i] != null){
                for(Entry<K,V> entry: table[i]){
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K,V>> bucket = newTable[i];
                    if(bucket == null){
                        newTable[bucketIndex] = bucket = new LinkedList<>();
                    }
                    bucket.add(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }

    public List<K> keys(){

        List<K> keys = new ArrayList<>(size());
        for(LinkedList<Entry<K,V>> bucket : table){
            if(bucket != null) for(Entry<K,V> entry: bucket) keys.add(entry.key);
        }
        return keys;
    }

    public List<V> values(){
        List<V> values = new ArrayList<>(size());
        for(LinkedList<Entry<K,V>> bucket : table){
            if(bucket != null) for(Entry<K,V> entry: bucket) values.add(entry.value);
        }
        return values;
    }
    
}
