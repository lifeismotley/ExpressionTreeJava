/**
 * This class is used as nodes for the Binary Tree class
 * @author Jing
 *
 */
/*
 * --Node-- The binary tree is built using this nested node class. Each node
 * stores one data element, and has left and right sub-tree pointer which
 * may be null. The node is a "dumb" nested class -- we just use it for
 * storage; it does not have any methods.
 */
public class Node {
	Node left;
	Node right;
	String data;

	Node(String newData) {
		left = null;
		right = null;
		data = newData;
	}
}
