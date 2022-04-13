/*
* Author: Jamie Hackney
* Implements Huffman's binary encoding algorithm
*/

package Huffman;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.ArrayList;

public class Huffman {

	public static void main(String[] args) throws IOException {

		String str = "Hello World!";
		System.out.println("string encoding: "+encode(str));

	}

	public static String encode(String message) {

		// parse string to get symbols
		char[] messageChars = message.toCharArray();
		Double numSymbols = (double) messageChars.length;
		ArrayList<Symbol> symbols = new ArrayList<Symbol>(messageChars.length);

		// count appearances of each symbol
		for (int sym = 0; sym < messageChars.length; sym++) {
			Symbol s = new Symbol(messageChars[sym]);
			int index = symbols.indexOf(s);

			// symbol not in list, add to list
			if (index == -1) {
				symbols.add(s);
				s.addAppearance();
			}

			// symbol is in list, appearance++
			else {
				symbols.get(index).addAppearance();
			}
		}

		// initialize priority queue
		PriorityQueue<BinaryTree> pq = new PriorityQueue<BinaryTree>(60);

		// create tree object for each symbol and add to pq
		for (int sym = 0; sym < symbols.size(); sym++) {
			double symProb = symbols.get(sym).appearances / numSymbols;
			Node r = new Node(symbols.get(sym), symProb);
			BinaryTree t = new BinaryTree(r);
			pq.add(t);
		}

		// merge the two min weight trees
		while (pq.size() > 1) {
			BinaryTree t1 = pq.poll();
			BinaryTree t2 = pq.poll();
			BinaryTree t3 = mergeTrees(t1, t2);
			pq.add(t3);
		}
		BinaryTree codeTree = pq.remove();

		// traverse tree and set codes
		BinaryTree.setCodes(codeTree.root, "");

		// print out dictionary
		for (int s = 0; s < symbols.size(); s++) {
			Double prob = (symbols.get(s).appearances / numSymbols);
			System.out.println("symbol: '"+symbols.get(s).value+"' | code: '"+symbols.get(s).code+"' | probability: "+Math.round(prob * 100.0) / 100.0);
		}

		// return string code
		String code = "";
		for (int c = 0; c < messageChars.length; c++) {
			Symbol checkSym = new Symbol(messageChars[c]);
			String codeBit = symbols.get(symbols.indexOf(checkSym)).code;
			code = code.concat(codeBit);
		}
		return code;
	}

	// return new tree from merging t1 and t2, with weight t1 + t2
	public static BinaryTree mergeTrees(BinaryTree t1, BinaryTree t2) {
		// create parent "null" node of two trees
		Node parent = new Node(null, null);
		parent.left = t1.root;
		parent.right = t2.root;

		// create new merged tree
		BinaryTree t3 = new BinaryTree(parent);
		t3.weight = t1.weight + t2.weight;
		return t3;
	}

}
