package main.iterator;

import java.util.Iterator;

class Node<T>{
    public T value;
    public Node<T> left, right, parent;

    public Node(T value){ this.value = value; }

    public Node(T value, Node<T> left, Node<T> right){
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }
}
class InOrderIterator<T> implements Iterator<T>{

    private Node<T> current, root;
    private boolean yieldedStart;

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public T next() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }
    
}
public class TreeTraversal {
    
}
