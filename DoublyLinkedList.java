package com.chetan.stackoverflow;

import androidx.annotation.NonNull;

public class DoublyLinkedList {

    private int size = 0;
    private Node head = null, tail = null;

    private class Node{
        int data;
        Node prev, next;

        public Node(int data, Node prev, Node next){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @NonNull
        @Override
        public String toString() {
            return "" + data;
        }
    }

    public void clear(){
        Node trav = head;
        while(trav != null){
            Node next = trav.next;
            trav.next = trav.prev = null;
            trav = next;
        }
        head = tail = trav = null;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void add(int data){
        addLast(data);
    }

    public void addLast(int data) {
        if(isEmpty()){
            head = tail = new Node(data, null, null);
        }else{
            tail.next = new Node(data, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(int data){
        if(isEmpty()){
            head = tail = new Node(data, null, null);
        }else{
            head.prev = new Node(data, null, head);
            head = head.prev;
        }
        size++;
    }

    public int peekFirst(){
        if(isEmpty()) throw new RuntimeException("List is Empty!");
        return head.data;
    }

    public int peekLast(){
        if(isEmpty()) throw new RuntimeException("List is Empty!");
        return tail.data;
    }

    public int removeFirst(){
        if(isEmpty()) throw new RuntimeException("List is Empty!");
        int data = head.data;
        head = head.next;
        size--;

        if(isEmpty()) tail = null;
        else{
            head.prev = null;
        }
        return data;
    }

    public int removeLast(){
        if(isEmpty()) throw new RuntimeException("List is Empty!");
        int data = tail.data;
        tail = tail.prev;
        size--;

        if(isEmpty()) head = null;
        else{
            tail.next = null;
        }
        return data;
    }

    public int remove(Node node){
        if(isEmpty()) throw  new RuntimeException("List is Empty");

        if(node.prev == null) removeLast();
        if(node.next == null) removeFirst();

        int data = node.data;
        node.next.prev = node.prev;
        node.prev.next = node.next;

        node.prev = node.next = null;
        size--;

        return data;
    }

    public int removeAt(int index){
        if(index < 0 || index >= size) throw new Error();

        int i =0;
        Node trav;
        if(index < size/2){
            for(i=0, trav = head; i!= index; i++){
                trav = trav.next;
            }
        }else for(i = size-1 , trav = tail; i!= index; i--) trav = trav.prev;

        return remove(trav);
    }

    public boolean remove(int data){
        Node trav = head;

        for(; trav != null; trav = trav.next){
            if(trav.data == data){
                remove(trav);
                return  true;
            }
        }
        return false;
    }

    public int indexOf(int data){
        Node trav = head;


        for(int index = 0; trav != null; trav = trav.next , index++ ){
            if(trav.data == data){
                return index;
            }
        }
        return -1;
    }

    public boolean contains(int data){
        return indexOf(data) != -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node trav = head;
        while (trav != null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    public DoublyLinkedList(){
        add(1);
        add(4);
        add(5);
        add(1);
        add(0);
        add(15);
        add(7);
        add(6);
        add(3);
        add(1);
        add(2);
        System.out.print(toString());
    }

    public static void main(String[] args){
        new DoublyLinkedList();
    }
}
