package org.gala.demo.sjjg.group1;

/**
 * @author yuan.li
 *
 */
public class Node {
	
	int value;
	Node leftChild;
	Node rightChild;

	Node(int value) {
		this.value = value;
	}

	public void display() {
		System.out.print(this.value + "\t");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
}
