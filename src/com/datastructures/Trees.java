package com.datastructures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Trees {

  public static void main(String[] args) {

//    BinaryTree bt = new BinaryTree();
//    Node rootNode = bt.populateBinaryTree();
//    System.out.println("In order traversal");
//    bt.inOrderTraversal(rootNode);
//    System.out.println("Pre order traversal");
//    bt.preOrderTraversal(rootNode);
//    System.out.println("Level trevarsal");
//    bt.LevelTraversal(rootNode);

    BinarySearchTree bst = new BinarySearchTree();

    ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(4, 2, 3, 1, 5));

    Node rootNode = null;

    for (Integer data : arrayList){
      rootNode = bst.populateBinarySearchTree(data, rootNode);
    }

//    System.out.println(bst.searchElementInBST(rootNode, 5));
    rootNode = bst.insertNodeInBST(rootNode, 6);
    System.out.println(bst.checkIfBstOrNot(rootNode));
    System.out.println(bst.inorderData);
    System.out.println(bst.returnMaxFromBST(rootNode));
    System.out.println(bst.returnMinFromBST(rootNode));
  }

}

class BinarySearchTree{

  ArrayList<Integer> inorderData = new ArrayList<>();
  Node prevNode;
  Integer maxInteger = Integer.MIN_VALUE;
  Integer minInteger = Integer.MAX_VALUE;

  public Node populateBinarySearchTree(Integer data, Node rootNode){

     if (rootNode == null){
       rootNode = new Node(data);
       return rootNode;
     }

     if (data > rootNode.getData()){
        rootNode.setRightNode(populateBinarySearchTree(data, rootNode.getRightNode()));
     }else {
        rootNode.setLeftNode(populateBinarySearchTree(data, rootNode.getLeftNode()));
     }

     return rootNode;
  }

  public Node searchElementInBST(Node rootNode, Integer element){

    if (rootNode == null){
      return null;
    }

    if (rootNode.getData() == element){
      return rootNode;
    }

    if (rootNode.getData() > element){
      return searchElementInBST(rootNode.getLeftNode(), element);
    }else {
      return  searchElementInBST(rootNode.getLeftNode(), element);
    }
  }

  public Node insertNodeInBST(Node insertNode){

    return null;
  }

  public Boolean checkIfBstOrNot(Node rootNode){

    if (rootNode == null){
      return true;
    }

    if (!checkIfBstOrNot(rootNode.getLeftNode())) return false;

    inorderData.add(rootNode.getData());

    if (prevNode != null && rootNode.getData() <= prevNode.getData())
      return false;

    prevNode = rootNode;

    return checkIfBstOrNot(rootNode.getRightNode());
  }

  public Node insertNodeInBST(Node rootNode, Integer element){

    if (rootNode == null){
      return new Node(element);
    }

    if(element > rootNode.getData()){
      rootNode.setRightNode(insertNodeInBST(rootNode.getRightNode(), element));
    }else{
      rootNode.setLeftNode(insertNodeInBST(rootNode.getLeftNode(), element));
    }

    return rootNode;
  }

  public Integer returnMaxFromBST(Node rootNode){

    if (rootNode == null){
      return null;
    }

    Integer tempInt = returnMaxFromBST(rootNode.getRightNode());

    if (tempInt == null){
      maxInteger = rootNode.getData();
    }

    if ( tempInt != null && tempInt > maxInteger){
      maxInteger = rootNode.getData();
    }

    return maxInteger;
  }

  public Integer returnMinFromBST(Node rootNode){

    if (rootNode == null){
      return null;
    }

    Integer tempInt = returnMinFromBST(rootNode.getLeftNode());

    if (tempInt == null){
      minInteger = rootNode.getData();
    }

    if ( tempInt != null && tempInt < minInteger){
      minInteger = rootNode.getData();
    }

    return minInteger;
  }

  public Node deleteNodeFromBST(Node rootNode, Integer element){

    rootNode = searchElementInBST(rootNode, element);

    return null;
  }
}

class BinaryTree{


  public Node populateBinaryTree(){

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Data");
    Integer data = sc.nextInt();

    if (data == -1) return null;

    Node rootNode = new Node(data);
    rootNode.setLeftNode(populateBinaryTree());
    rootNode.setRightNode(populateBinaryTree());

    return  rootNode;
  }

  public void inOrderTraversal(Node rootNode){
    if (rootNode == null){
      return;
    }
    inOrderTraversal(rootNode.getLeftNode());
    System.out.println(rootNode.getData());
    inOrderTraversal(rootNode.getRightNode());

  }

  public void preOrderTraversal(Node rootNode){
    if(rootNode == null){
      return;
    }

    System.out.println(rootNode.getData());
    preOrderTraversal(rootNode.getLeftNode());
    preOrderTraversal(rootNode.getRightNode());
  }

  public void LevelTraversal(Node rootNode){

    Queue<Node> nodesQeue = new LinkedList<>();

    nodesQeue.add(rootNode);

    while(rootNode != null || !nodesQeue.isEmpty()){

      rootNode = nodesQeue.remove();
      if(rootNode != null){
        nodesQeue.add(rootNode.getLeftNode());
        nodesQeue.add(rootNode.getRightNode());

        System.out.println(rootNode.getData());
      }else{
        rootNode = nodesQeue.remove();
      }
    }
  }

  public Integer heightOfBinaryTree(Node rootNode){

    if (rootNode == null){
      return 0;
    }

    Integer maxHeight = Math.max(heightOfBinaryTree(rootNode.getLeftNode()),
        heightOfBinaryTree(rootNode.getRightNode())) + 1;

    return maxHeight;
  }
}




class Node{

  private Integer data;
  private Node leftNode;
  private Node rightNode;

  public Node(Integer data){
    this.data = data;
  }

  public Node getLeftNode() {
    return leftNode;
  }

  public Node getRightNode() {
    return rightNode;
  }

  public Integer getData() {
    return data;
  }

  public void setLeftNode(Node leftNode) {
    this.leftNode = leftNode;
  }

  public void setRightNode(Node rightNode) {
    this.rightNode = rightNode;
  }
}
