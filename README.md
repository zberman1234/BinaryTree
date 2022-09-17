# Binary Tree
### Introduction
This Binary Tree implementation, originally forked from my Linked List implementation (https://www.github.com/zberman1234/LinkedList), bases its unit structure on the Node class, and follows the Binary Tree specification. That is, every node "to the left" has a value less than that node's value, and every node "to the right" of that node has a value greater than or equal to that node's value. 


### Current Errors
There are currently no errors or failed test cases in this implementation, however, issues regarding memory are not neccessarily addressed, as there are no limitations or requested parameters for the max size of the binary tree. 

### Overview
BinaryTree class is made up of nodes each with pointers to their parent, left child, and right child nodes. 
The major (and standard) methods implemented in the BinaryTree class are search, insertion, and deletion.
Deletion is handled by three important helper methods: deletion by removing parent-child connection, deletion by splicing, and deletion by rotation. 
Testing involves a mix of automated and manual tests, the majority of which concern the functionality of deletion and its various helper methods.

### Acknowledgements
Authored by Zach Berman, adapted, and sometimes directly copied from Mr. Kuszmaul's youtube (https://www.youtube.com/playlist?list=PLxcs8YW55pqo808FdMdSOTi5-JGJrr2iS).