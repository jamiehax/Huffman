/*
* Implements a (limited functionality) binary tree
* Inspired by: https://www.baeldung.com/java-binary-tree
*/

package Huffman;

public class BinaryTree implements Comparable<BinaryTree> {

     Double weight;
     Node root;

     public BinaryTree(Node root) {
          this.root = root;
          this.weight = root.weight;
     }

     @Override
     public int compareTo(BinaryTree t) {
          return this.weight.compareTo(t.weight);
     }

     // traverse tree to set codes
     public static void setCodes(Node node, String code) {

          // if not at a "character" node
          if (node.symbol == null) {

               // add 0 for every left turn
               setCodes(node.left, code.concat("0"));

               // set child node codes if not null
               if (node.left.symbol != null) {
                    node.left.symbol.code = code.concat("0");
               }
               if (node.right.symbol != null) {
                    node.right.symbol.code = code.concat("1");
               }

               // add 1 for every right turn
               setCodes(node.right, code.concat("1"));
          }

     }

}
