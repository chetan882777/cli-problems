package com.chetan.dsa.data_structure.maps;

public class Entry <K ,V> {
    int hash;
    K key;
    V value;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
        hash = key.hashCode();
    }

    public boolean equals(Entry<K,V> other){
        if(hash != other.hash)  return false;
        return key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + " => " + value;
    }
}
