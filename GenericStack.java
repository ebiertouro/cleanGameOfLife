package gameOfLife;
import java.util.*;

public class GenericStack<T> {
	
	public class Node<T>{
	
		T value;
		Node<T> next;

		Node(T value) {
			this.value = value;
			next = null;
	}}

	Node<T> top;
	int size;

	GenericStack() {

		this.top = null;
		this.size = 0;
	}

	GenericStack(T value) {

		this.size = 0;
		Node<T> n = new Node<T>(value);
		this.top = n;
	}
	 	 
	    // this method adds an element to the top of the stack
	    
	public void push (T value) {
		
		 Node<T> node = new Node<>(value);
		 node.next = top;
		 top = node;
		 size++;
		 }
	  
	//this method returns and removes the top element
	
	public T pop () {
		 
		 if (size==0) 
			 throw new EmptyStackException();
		 
		 else {
			Node popped = top;
			top = top.next;
			size --;
			return (T) popped.value;
		 }}
	
		// this method returns the value of the top element
	
		public T peek () {
		 
		 if (size==0) {
			 throw new EmptyStackException();	 	
		 }
		 
		 else {
			return (T) top.value;
			
			
	}}
	    public boolean isEmpty() {
	        return size == 0;
	    }
	    
	    public int size() {
	        return size;
	    }}