import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *  The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of generic items.
 *  It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 *  for peeking at the top item, testing if the stack is empty, and iterating through
 *  the items in LIFO order.
 *  <p>
 *  All stack operations except iteration are constant time.
 *  <p>
 *  For additional documentation, see <a href="/algs4/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Stack<Item> implements Iterable<Item> {
    private int N;          // size of the stack
    private StackNode first;     // top of stack

    // helper linked list class
    private class StackNode {
        private Item item;
        private StackNode next;
    }

   /**
     * Create an empty stack.
     */
    public Stack() {
        first = null;
        N = 0;
        assert check();
    }

   /**
     * Is the stack empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the stack.
     */
    public int size() {
        return N;
    }

   /**
     * Add the item to the stack.
     */
    public void push(Item item) {
        StackNode oldfirst = first;
        first = new StackNode();
        first.item = item;
        first.next = oldfirst;
        N++;
        assert check();
    }

   /**
     * Delete and return the item most recently added to the stack.
     * @throws java.util.NoSuchElementException if stack is empty.
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        assert check();
        return item;                   // return the saved item
    }

   /**
     * Return the item most recently added to the stack.
     * @throws java.util.NoSuchElementException if stack is empty.
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

   /**
     * Return string representation.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }
       

    // check internal invariants
    private boolean check() {
        if (N == 0) {
            if (first != null) return false;
        }
        else if (N == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable N
        int numberOfNodes = 0;
        for (StackNode x = first; x != null; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N) return false;

        return true;
    } 

   /**
     * Return an iterator to the stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator()  { return new ListIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private StackNode current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

   /**
     * A test client.
     */
    public static void main(String[] args) {
        Stack<BinaryTree> stack = new Stack<BinaryTree>(); //stack for building the tree
        Stack<Integer> calc = new Stack<Integer>(); //stack for calculating postfix input
        String[] input = args[0].split(" "); //split the arguments by space 
        
        for (int i = 0; i < input.length; i++) {
        	
        	if (!input[i].equals("+") && !input[i].equals("-") && !input[i].equals("*") && !input[i].equals("/")) {
            	/*
            	 * if its a number
            	 * create a tree of that node and push it onto the stack
            	 */
        		BinaryTree tree = new BinaryTree();
        		tree.insert(input[i]);
        		stack.push(tree);
        		
        		calc.push(Integer.parseInt(input[i]));
        	} else {
        		/*
        		 * otherwise its an expression
        		 * pop the first two trees of the stack and merge them
        		 */
        		BinaryTree t1 = stack.pop();
        		BinaryTree t2 = stack.pop();
        		stack.push(t2.mergeWith(t1, input[i]));
        		
        		int n1 = calc.pop();
        		int n2 = calc.pop();
        		int ans;
        		switch (input[i]) {
        			case "+":
        				ans = n1+n2;
        				break;
        			case "-":
        				ans = n1-n2;
        				break;
        			case "*":
        				ans = n1*n2;
        				break;
        			case "/":
        				ans = n1/n2;
        				break;
        			default: ans = 0; 
        		}
        		calc.push(ans);
        	}
        }
        
        //popping the final tree
        BinaryTree finalTree = stack.pop();
        
        //printing out inorder, postorder, and preorder traversals
        System.out.println("Infix:");
        finalTree.printTree();
        System.out.println("Postfix:");
        finalTree.printPostorder();
        System.out.println("Prefix:");
        finalTree.printPreorder();
        
        System.out.println("Result: " + calc.pop());
        
    }
}