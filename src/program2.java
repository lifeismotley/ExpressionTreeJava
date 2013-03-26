/**
 * Tester class for an expression tree implemented through a stack
 * @author Jing
 * 3/26/2013
 * COMS 3134, Programming HW #2
 *
 */
public class program2 {
	/**
     * A test client.
     */
    public static void main(String[] args) {
        Stack<BinaryTree> stack = new Stack<BinaryTree>(); //stack for building the tree
        Stack<Double> calc = new Stack<Double>(); //stack for calculating postfix input
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
        		
        		calc.push(Double.parseDouble(input[i]));
        	} else {
        		/*
        		 * otherwise its an expression
        		 * pop the first two trees of the stack and merge them
        		 */
        		BinaryTree t2 = stack.pop();
        		BinaryTree t1 = stack.pop();
        		stack.push(t1.mergeWith(t2, input[i]));
        		
        		double n2 = calc.pop();
        		double n1 = calc.pop();
        		double ans;
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