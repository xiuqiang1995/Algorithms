package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list = " + list);
        list.offer(4);
        list.offer(5);
        list.offer(6);
        System.out.println("list = " + list);
        Integer pop = list.pop();
        System.out.println("pop = " + pop);
        System.out.println("list = " + list);
        Integer poll = list.poll();
        System.out.println("poll = " + poll);
        System.out.println("list = " + list);
    }
}
