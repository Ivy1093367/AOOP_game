import java.util.*;

public class A1093367_checkpoint7_BlockStack implements A1093367_checkpoint7_Fringe {
    Stack<A1093367_checkpoint7_Block> stack;
    //Description : the constuctor of BlockQueue.
    public A1093367_checkpoint7_BlockStack(){
        //The TODO(3) This Time (Checkpoint7) : Initialize the stack.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        stack=new Stack<A1093367_checkpoint7_Block>();
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public void add(A1093367_checkpoint7_Block block){
        //The TODO(3) This Time (Checkpoint7) : add block into the stack.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        stack.push(block); 
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public A1093367_checkpoint7_Block remove(){
        //The TODO(3) This Time (Checkpoint7) :First check the stack is empty or not and return and remove the object from the stack.
        // If stack is empty return null.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        if(stack.isEmpty()){
            return null;
        }else{
            return stack.pop();
        }
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
    public boolean isEmpty(){
        //The TODO(3) This Time (Checkpoint7) :Check the stack is empty or not.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        return stack.empty();
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
}