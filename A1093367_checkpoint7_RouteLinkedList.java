public class A1093367_checkpoint7_RouteLinkedList{
    private A1093367_checkpoint7_Node head;
    //Description : the constructor of leading the head Node as null.
    public A1093367_checkpoint7_RouteLinkedList(){
        this.head = null;
    }
    //Description : the constructor of input a Node as the head node.
    public A1093367_checkpoint7_RouteLinkedList(A1093367_checkpoint7_Node head){
        this.head = head;
    }
    public void delete(int axis, int direction){ 
        /*********************************The TODO This Time (Checkpoint7)***************************
        //TODO(7):      Input value of Node as the reference Node,
        //              you have to delete the first Node that is same as the reference Node,
        //              and connect the following one and the previous one.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1093367_checkpoint7_Node prev=null;
        A1093367_checkpoint7_Node current=head;
        if(head==null){
            return;
        }else{
            //if(current.getAxis()==axis&&current.getDirection()==direction)
            while(current.getNext()!=null&&(current.getAxis()!=axis||current.getDirection()!=direction)){
                prev=current;
                current=current.getNext();
            }
            if(current.getAxis()==axis&&current.getDirection()==direction){
                if(prev==null){
                    head=current.getNext();
                }else{
                    prev.setNext(current.getNext());
                }
            }
        }
        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }

    public A1093367_checkpoint7_Node search(int axis, int direction){ 
        /*******************************The TODO This Time (Checkpoint7)*****************************
        //TODO(8):      Input value of Node as the reference Node,
        //              you have to find the first Node that is same as the reference Node,
        //              and return it.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1093367_checkpoint7_Node current= head;
        if (current==null){
            return null;
        }else{
            while(current.getNext() != null){
                if(current.getAxis()==axis&&current.getDirection()==direction)
                    break;
                current=current.getNext();
            }
            return current;
        }
        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }
    public void insert(int referenceAxis, int referenceDirection, int axis, int direction){ 
        /******************************The TODO This Time (Checkpoint7)******************************
        //TODO(9):      Input value of Node as the reference Node,
        //              and insert a Node BEFORE the first Node same as the reference Node,
        //              and connect the following one and the previous one.
        //Hint          The value of the Node is int variable axis and dirsction.
        //Hint2         If there is no reference node in linkedlist, print "Insertion null".
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1093367_checkpoint7_Node prev=null;
        A1093367_checkpoint7_Node current=head;
        A1093367_checkpoint7_Node search=search(referenceAxis,referenceDirection);
        //System.out.println(axis+"+"+direction);
        A1093367_checkpoint7_Node node=new A1093367_checkpoint7_Node(direction,axis);
        if(current==null){
            return;
        }else{
            while(current.getNext()!=null&&(!current.equals(search))){
                prev=current;
                current=current.getNext();
                if(current.getNext()==null){
                    System.out.println("Insertion null");
                    break;
                }
            }
            if(prev==null){
                node.setNext(head);
                head = node;
            }else{
                prev.setNext(node);
                node.setNext(current);
            }
        }
        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }
    public int length(){
        /******************************The TODO This Time (Checkpoint7)******************************
        //TODO(10):      return how long the LinkedList is.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        int count = 0;
        A1093367_checkpoint7_Node current= head;
        if(head==null){
            return 0;
        }else{
            count++;
            while(current.getNext()!=null){
                count++;
                current= current.getNext();
            }
        }
        return count;
        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }
    public void append(int axis, int direction){
        A1093367_checkpoint7_Node current = head;
        A1093367_checkpoint7_Node newNode = new A1093367_checkpoint7_Node(direction,axis);
        if(head == null){
            head = newNode;
        }else {
            while(current.getNext() != null){
                current=current.getNext();
            }
            current.setNext(newNode);
        }
    }
    public A1093367_checkpoint7_Node getHead(){
        return this.head;
    }
    public void setHead(A1093367_checkpoint7_Node head){
        this.head = head;
    }
}
    

