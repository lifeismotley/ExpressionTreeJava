public class BinaryTree {
	// Root node pointer. Will be null for an empty tree.
	private Node root;
	
	public BinaryTree(Node root) {
		this.root = root;
	}
	
	/**
	 * Creates an empty binary tree -- a null root pointer.
	 */
	public BinaryTree() {
		root = null;
	}

	/**
	 * Inserts the given data into the binary tree. Uses a recursive helper.
	 */
	public void insert(String data) {
		root = insert(root, data);
	}

	/**
	 * Recursive insert -- given a node pointer, recur down and insert the given
	 * data into the tree. Returns the new node pointer (the standard way to
	 * communicate a changed pointer back to the caller).
	 */
	private Node insert(Node node, String data) {
		if (node == null) {
			node = new Node(data);
		} else {
			node.left = insert(node.left, data); 
			/*
			if (data <= node.data) {
				node.left = insert(node.left, data);
			} else {
				node.right = insert(node.right, data);
			}
			*/
		}

		return (node); // in any case, return the new pointer to the caller
	}

	/**
	 * Returns the number of nodes in the tree. Uses a recursive helper that
	 * recurs down the tree and counts the nodes.
	 */
	public int size() {
		return (size(root));
	}

	private int size(Node node) {
		if (node == null)
			return (0);
		else {
			return (size(node.left) + 1 + size(node.right));
		}
	}

	/**
	 * Prints the node values in the "inorder" order. Uses a recursive helper to
	 * do the traversal.
	 */
	public void printTree() {
		printTree(root);
		System.out.println();
	}

	private void printTree(Node node) {
		if (node == null)
			return;

		// left, node itself, right
		printTree(node.left);
		System.out.print(node.data + "  ");
		printTree(node.right);
	}

	/**
	 * Prints the node values in the "postorder" order. Uses a recursive helper
	 * to do the traversal.
	 */
	public void printPostorder() {
		printPostorder(root);
		System.out.println();
	}

	public void printPostorder(Node node) {
		if (node == null)
			return;

		// first recur on both subtrees
		printPostorder(node.left);
		printPostorder(node.right);

		// then deal with the node
		System.out.print(node.data + "  ");
	}
	
	/**
	 * Prints the node values in the "preorder" order. Uses a recursive helper
	 * to do the traversal.
	 */
	public void printPreorder() {
		printPreorder(root);
		System.out.println();
	}

	public void printPreorder(Node node) {
		if (node == null)
			return;

		// first deal with the node
		System.out.print(node.data + "  ");
		
		// then recur on both subtrees
		printPreorder(node.left);
		printPreorder(node.right);
	}
	
	public BinaryTree mergeWith(BinaryTree t, String expression) {
		Node newRoot = new Node(expression);
		newRoot.left = this.root;
		newRoot.right = t.root;
		
		BinaryTree newTree = new BinaryTree(newRoot);
		
		return newTree;
	}
}