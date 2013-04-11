Data Structures HW #2
Justin Zhao
jxz2101
4/7/2013

This program implements an expression tree that takes in a single command line
argument in postfix. The program will then print the tree in prefix, infix, and postfix order
and calculate the result of the expression. Operations supported include:
+
-
/
*

In order to do this, I use my own stack class and expression tree class.
The main program is in the program2.java class.

java program2 "5 3 4 + *"

which will output:
Infix:
(5  /  (3  -  4  ))
Postfix:
5  3  4  -  /  
Prefix:
/  5  -  3  4  
Result: -5.0

