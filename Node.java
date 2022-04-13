/*
* Implements a Node to use for a binary tree in Java
* Backed by the Symbol object
* Inspired by: https://www.baeldung.com/java-binary-tree
*/

package Huffman;

public class Node {

     Symbol symbol;
     Double weight;
     Node left;
     Node right;

     public Node(Symbol s, Double weight) {
          this.symbol = s;
          this.weight = weight;
          this.right = null;
          this.left = null;
     }

}
