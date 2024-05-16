package com.localspeak;

public class PointerQueue {
    private NextPointer front;
    private NextPointer rear;

    /**
     * Constructs a new PointerQueue object that is empty.
     */
    public PointerQueue() {
        front = null;
        rear = null;
    }

    /**
     * Adds another pointer to the queue.
     * @param pointer Integer describing the next available pointer.
     */
    public void enqueue(int pointer) {
        NextPointer pointerIterator = front;    // start from top of queue
        // iterate through queue until we find the end node
        while (pointerIterator != rear) {
            pointerIterator = pointerIterator.getNext();
        }
        // create a new node with desired pointer
        NextPointer newPointer = new NextPointer(pointer);
        // make old rear point to the new node
        rear.setNext(newPointer);
        // set new rear as what the old rear is pointing to (new node)
        rear = rear.getNext();
    }

    /**
     * Return the value of the front node, and removes the node from the queue.
     * @return Integer describing the next available pointer.
     */
    public int dequeue() {
        int frontPointer = front.value();
        front = front.getNext();
        return frontPointer;
    }

    /**
     * Attempts to clear PointerQueue object by nullifying front and rear.
     */
    public void clear() {
        front = null;
        rear = null;
    }

    class NextPointer {
        private NextPointer next;
        private int pointer;

        public NextPointer(int value) {
            pointer = value;
            next = null;
        }

        /**
         * Gets next pointer.
         * @return  NextPointer object containing the next node.
         */
        public NextPointer getNext() {
            return next;
        }

        /**
         * Sets next node to specified NextPointer object.
         * @param next NextPointer object containing the node to be set as next.
         */
        public void setNext(NextPointer next) {
            this.next = next;
        }

        /**
         * Returns an integer value containing the pointer associated with the current node.
         * @return Integer describing the pointer associated with the current node.
         */
        public int value() {
            return pointer;
        }
    }
}
