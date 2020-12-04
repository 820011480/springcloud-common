package com.mady.common.spring.lists;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/11/24 14:41
 * @description
 */
public class TestLinkedList {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        MyLinkedList.Node add1 = myLinkedList.add("1");
        MyLinkedList.Node add2 = myLinkedList.add("2");
        MyLinkedList.Node add3 = myLinkedList.add("3");
        MyLinkedList.Node add4 = myLinkedList.add("4");
        MyLinkedList.Node add5 = myLinkedList.add("5");
        MyLinkedList.Node add6 =myLinkedList.add("6");
        System.out.println(myLinkedList);
        myLinkedList.diffLinkedNode(add4, add5);
        System.out.println(myLinkedList);


//        LinkedList linkedList= new LinkedList();
//        linkedList.add("1");
//        linkedList.add("2");
//        System.out.println(linkedList);
    }
}
