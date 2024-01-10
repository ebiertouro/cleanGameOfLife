package gameOfLife;

public class CircularQ<T> {
    private Node<T> rear;
    private int size;

    public CircularQ() {
        rear = null;
        size = 0;
    }

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(T data) {
    	
        Node<T> newNode = new Node<>(data);
        
        if (isEmpty()) {
            newNode.next = newNode;       // Point to itself for the first node        
        }
        
        else {
            newNode.next = rear.next;      // New node points to the head
            rear.next = newNode;            // Rear now points to the new node
            
        }
        
        rear = newNode;                  // The new node is now the rear
       
        size++;
    }

    public T dequeue() {
        if (!isEmpty()) {
        	
        	// Get the data of the head
        	T data = rear.next.data;     
           
        	if (rear.next == rear) {
        		 // If only one node, make the queue empty
                rear = null;
                
            } else {
            	 // Skip the head in the circular linked list
            	rear.next = rear.next.next;     
            }
            size--;
            
            return data;
            
        }
        return null;
    }

    
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue is empty";
        }

        StringBuilder result = new StringBuilder();
        Node<T> current = rear.next;
        do {
            result.append(current.data).append(" ");
            current = current.next;
        } while (current != rear.next);

        return result.toString().trim();
    }
}