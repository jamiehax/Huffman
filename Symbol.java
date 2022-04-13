/*
* Symbol class to hold each symbol's value and appearances
*/

package Huffman;

public class Symbol {

     Double appearances;
     Character value;
     String code;

     public Symbol(char value) {

          this.appearances = 0.0;
          this.value = value;

     }

     public void addAppearance() {
          this.appearances++;
     }

     /*
     * override equals() to compare two symbols
     * From: https://www.geeksforgeeks.org/overriding-equals-method-in-java/
     */
     @Override
     public boolean equals(Object o) {

          if (o == this) {
               return true;
          }

          if (!(o instanceof Symbol)) {
               return false;
          }

          Symbol s = (Symbol) o;

          return (this.value == s.value);
     }

}
