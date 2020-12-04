package com.mady.common.spring.lists;

import org.springframework.util.StringUtils;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/11/24 14:15
 * @description
 */
public class MyLinkedList {
    /**
     * 头节点
     */
    private Node head;
    /**
     * 尾节点
     */
    private Node tail;
    /**
     * 记录链表长度
     */
    private volatile int size = 0;


    public class Node{
        /**
         * 前继节点
         */
        Node pred;
        /**
         * 后继节点
         */
        Node next;
        /**
         * 存储数据变量
         */
        String str;

        public Node() {
        }

        public Node(Node pred, Node next, String str) {
            this.pred = pred;
            this.next = next;
            this.str = str;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }



    @Override
    public String toString() {
        if(size == 0){
            return "{" + "}";
        }
        if(size == 1){
            return "{" + head.str + "}";
        }
        Node nc = head;
        Node nt = nc.next;
        String str = "{";
        while(nt != null){
            str =  str + nc.str + ", ";
            //变更当前节点
            nc = nc.next;
            //设置下一节点
            nt = nc.next;
        }
        str = str + tail.str + "}";
        return str;
    }



    /**
     * 1. add时 链表没有初始化时，需要先初始化链表，则第一个添加的元素既是头元素也是尾元素
     *
     *
     * @param str
     */
    public Node add(String str){
        //判断添加的节点是否为空
        if(StringUtils.isEmpty(str)){
            throw new RuntimeException("add node is not null");
        }
        Node node = new Node(null, null, str);
        //初始化链表
        if (size == 0){
            initLinkedList(node);
            return node;
        }
        //添加节点
        addLinkedList(node);
        return node;
    }

    /**
     * 添加节点 添加的列表的队尾
     * @param node
     */
    private void addLinkedList(Node node) {
        //获取当前列表的尾节点
        Node nt = tail;
        //获取当前列表尾节点的前继节点
        Node np = tail.pred;
        Node nh = head;
        //添加第二个元素
        if(np == null){
            head.next = node;
            node.pred = nh;
            tail = node;
            size++;
            return;
        }
        //原尾节点的后继节点指向链表的当前节点
        nt.next = node;
        //当前节点的前继节点指向链表的原尾节点
        node.pred = nt;
        //设置当前节点为尾节点
        tail = node;
        size++;
        return;
    }

    /**
     * 初始化链表 头尾节点都为node size为1
     * @param node
     */
    private void initLinkedList(Node node) {
        head = node;
        tail = node;
        size++;
    }

    public void diffLinkedNode(Node prev, Node node){
        node.pred = prev = prev.pred;
        System.out.println(node.pred.str);
        System.out.println(node.str);
        System.out.println(node.next.str);
        prev.next = node;
    }
}
