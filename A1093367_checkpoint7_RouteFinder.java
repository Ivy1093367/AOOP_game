import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
public class A1093367_checkpoint7_RouteFinder {
    //Description : The target block.
    private A1093367_checkpoint7_Block target;
    private A1093367_checkpoint7_Block root;
    //Description : The hashmap that records the parent block.
    private HashMap<A1093367_checkpoint7_Block, A1093367_checkpoint7_Block> ParentBlock;
    //Description : Record which block has been visited.
    private boolean[][] visited ;
    // Description : The root frame.
    private A1093367_checkpoint7_GameFrame parentFrame;
    //Description : the map with all blocks.
    //You can get the location block you want with typing map[x][y].
    private A1093367_checkpoint7_Block[][] map;
    //Description : record the cost if you go on the block.
    private HashMap<A1093367_checkpoint7_Block, Integer> accumulatedCost;
    // Description : The route searching algorithm.
    private int algorithm;
    private A1093367_checkpoint7_Fringe fringe;
    private static final int DFS = 0;
    private static final int BFS = 1;
    private static final int UCS = 2;
    public boolean b=false;
    public A1093367_checkpoint7_RouteFinder(A1093367_checkpoint7_GameFrame parentFrame, A1093367_checkpoint7_Block target, A1093367_checkpoint7_Block root,int algorithm, A1093367_checkpoint7_Block[][] map){
        /**********************************The TODO This Time (Checkpoint7)**************************
         * 
         * TODO(1): For the TODO here, you have to implement fringe according "algorithm".
         * 
         * Hint(1): The BFS algorithm needs to use the queue to work, so we make a object named BlockQueue for BFS.
         * Hint(2): The DFS algorithm needs to use the stack to work, so we make a object named BlockStack for DFS.
         * Hint(3): The UCS algorithm needs to use the priority queue  to work, so we make a object named PriorityQueue for UCS.
         * Hint(4): These three objects all implement the fringe, and the detail description can be found 
         *          in the code of Fringe.java, BlockQueue.java, BlockStack.java, BlockPriorityQueue.java.
         * Hint(5): You have to add the root (the player current location) into fringe.
         * Hint(6): To calculate the priority, you have to implement a Comparator<block> object and make 
         *          it as an input in the constructor of BlockPriorityQueue.
         * Hint(7): Before starting the searching, you need to initialize the accumulatedCost and set the root with
         *          its cost.
         **********************************The End of the TODO**************************************/
        this.target = target;
        this.root = root;
        this.ParentBlock = new HashMap<A1093367_checkpoint7_Block, A1093367_checkpoint7_Block>();
        this.parentFrame = parentFrame;
        this.visited = new boolean[4096 / 256][4096 / 256];
        this.accumulatedCost = new HashMap<A1093367_checkpoint7_Block, Integer>();
        this.algorithm = algorithm;
        this.map = map;
        for(int x = 0 ; x < 4096 / 256; x++ ){
            for(int y = 0 ; y < 4096 / 256; y++ ){
                visited[x][y] = false;
            }
        }
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        if(algorithm==DFS){
            fringe=new A1093367_checkpoint7_BlockStack();
        }else if(algorithm==BFS){
            fringe=new A1093367_checkpoint7_BlockQueue();
        }else if(algorithm==UCS){
            fringe=new A1093367_checkpoint7_BlockPriorityQueue(new Comparator<A1093367_checkpoint7_Block>(){
                @Override
                public int compare(A1093367_checkpoint7_Block a, A1093367_checkpoint7_Block b){
                    if(accumulatedCost.get(a)>accumulatedCost.get(b)){
                        return 1;
                    }else if(accumulatedCost.get(a)==accumulatedCost.get(b)){
                        return 0;
                    }else{
                        return -1;
                    }
                }
            });
        }
        fringe.add(root);
        accumulatedCost.put(root,root.getCost());
        if(search()==null){
            b=true;
            return;
        }
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
    private A1093367_checkpoint7_Block search(){
        /*********************************The TODO (Checkpoint7)********************************
         * 
         * TODO(14.1): For the TODO here, you have to implement the searching funciton;
         * TODO(14.2): You MUST print the message of "Searching at (x, y)" in order to let us check if you sucessfully do it.
         * TODO(14.3): After you find the target, you just need to return the target block.
         * //System.out.println("Searching at ("+Integer.toString(YOURBLOCK.getX())+", "+Integer.toString(YOURBLOCK.getY())+")");
         * 
         * Hint(1): If the target can not be search you should return null(that means failure).
         * 
         * pseudo code is provided here: 
         *   1. get the block from fringe.
         *   2. print the message
         *   3. if that block equals target return it.
         *   4. if not, expand the block and insert then into fringe.
         *   5. return to 1. until the fringe does not have anyting in it.
         * 
         **********************************The End of the TODO**************************************/
        
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        while(!fringe.isEmpty()) {
            A1093367_checkpoint7_Block t=fringe.remove();
            System.out.println("Searching at ("+Integer.toString(t.getX())+", "+Integer.toString(t.getY())+")");
            //System.out.println("target"+Integer.toString(target.getX())+", "+Integer.toString(target.getY()));
            if(t.equals(target)){
                //System.out.println("down!");
                return t;
            }else{
                ArrayList<A1093367_checkpoint7_Block> in=expand(t, ParentBlock, visited);
                for(A1093367_checkpoint7_Block element : in){
                    fringe.add(element);
                }
            }
        }
        return null;
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    private ArrayList<A1093367_checkpoint7_Block> expand(A1093367_checkpoint7_Block currentBlock,HashMap<A1093367_checkpoint7_Block, A1093367_checkpoint7_Block> ParentBlock, boolean[][] visited){
        /*********************************The TODO This Time (Checkpoint7)*****************************
         * 
         * TODO(15.1): For the TODO here, you have to implement the expand funciton and return the Blocks(successor);
         * TODO(15.2): the order that you expand is North(Up) West(Left) South(Down) East(Right).
         * TODO(15.3): before adding the block into successor, you have to check if it is valid by checkBlock().
         * TODO(15.4): For the TODO here, you have to calculate the cost of the path that the player walked from 
         * root to new blocks and set it into the HashMap accumulatedCost.
         * 
         * Hint(1): While the block is valid, before you add the block into successor, 
         *        you should set its ParentBlock (We prepare a HashMap to implement this).
         *        And you should also set it is visited. (We prepare 2D boolean array for you) (the (x,y) block <--> visited[x][y] )
         **********************************The End of the TODO**************************************/

        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        visited[currentBlock.getX()][currentBlock.getY()]=true;
        ArrayList<A1093367_checkpoint7_Block> blockList = new ArrayList<>();
        if(!parentFrame.ClickCheckGridLocation(currentBlock.getX(), currentBlock.getY()-1, false)&&!visited[currentBlock.getX()][currentBlock.getY()-1]){
            visited[currentBlock.getX()][currentBlock.getY()-1] = true;
            ParentBlock.put(map[currentBlock.getX()][currentBlock.getY()-1],map[currentBlock.getX()][currentBlock.getY()]);
            blockList.add(map[currentBlock.getX()][currentBlock.getY()-1]);
            accumulatedCost.put(map[currentBlock.getX()][currentBlock.getY()-1],map[currentBlock.getX()][currentBlock.getY()-1].getCost()+accumulatedCost.get(currentBlock));
        }
        if(!parentFrame.ClickCheckGridLocation(currentBlock.getX()-1, currentBlock.getY(), false)&&!visited[currentBlock.getX()-1][currentBlock.getY()]){
            visited[currentBlock.getX()-1][currentBlock.getY()] = true;
            ParentBlock.put(map[currentBlock.getX()-1][currentBlock.getY()],map[currentBlock.getX()][currentBlock.getY()]);
            blockList.add(map[currentBlock.getX()-1][currentBlock.getY()]);
            accumulatedCost.put(map[currentBlock.getX()-1][currentBlock.getY()],map[currentBlock.getX()-1][currentBlock.getY()].getCost()+accumulatedCost.get(currentBlock));
        }
        if(!parentFrame.ClickCheckGridLocation(currentBlock.getX(), currentBlock.getY()+1, false)&&!visited[currentBlock.getX()][currentBlock.getY()+1]){
            visited[currentBlock.getX()][currentBlock.getY()+1] = true;
            ParentBlock.put(map[currentBlock.getX()][currentBlock.getY()+1],map[currentBlock.getX()][currentBlock.getY()]);
            blockList.add(map[currentBlock.getX()][currentBlock.getY()+1]);
            accumulatedCost.put(map[currentBlock.getX()][currentBlock.getY()+1],map[currentBlock.getX()][currentBlock.getY()+1].getCost()+accumulatedCost.get(currentBlock));
            //System.out.println("targetput:("+Integer.toString(currentBlock.getX())+","+Integer.toString(currentBlock.getY()+1)+")"+"("+Integer.toString(currentBlock.getX())+","+Integer.toString(currentBlock.getY())+")");
        }
        if(!parentFrame.ClickCheckGridLocation(currentBlock.getX()+1, currentBlock.getY(), false)&&!visited[currentBlock.getX()+1][currentBlock.getY()]){
            visited[currentBlock.getX()+1][currentBlock.getY()] = true;
            ParentBlock.put(map[currentBlock.getX()+1][currentBlock.getY()],map[currentBlock.getX()][currentBlock.getY()]);
            blockList.add(map[currentBlock.getX()+1][currentBlock.getY()]);
            accumulatedCost.put(map[currentBlock.getX()+1][currentBlock.getY()],map[currentBlock.getX()+1][currentBlock.getY()].getCost()+accumulatedCost.get(currentBlock));
        }        
        return blockList;
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }

    public A1093367_checkpoint7_RouteLinkedList createRoute(){
        /******************************The TODO This Time (Checkpoint7)*****************************
         * 
         * TODO(16): For the TODO here, you have to trace back the route and return the route;
         * 
         * Hint1: You can get the parent block of target by HashMap ParentBlock, thus you can calculate
         * the last step of the route. And then you get the parent block of  target, 
         * you can calculate the backward step and so on. 
         * 
         * presudo code is provided here:
         *      1. get parent block
         *      2. calculate the delta location
         *      3. insert into head
         *      4. make the target equals its parent block and so on.
         * 
         **********************************The End of the TODO**************************************/

        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1093367_checkpoint7_RouteLinkedList a=new A1093367_checkpoint7_RouteLinkedList();
        A1093367_checkpoint7_Block t=ParentBlock.get(target);
        int axis=0,des=0;
        //System.out.println("root:("+Integer.toString(root.getX())+","+Integer.toString(root.getY())+")");
        if(target.getY()-t.getY()!=0){
            axis=1;
            if(target.getY()-t.getY()==1){
                des=1;
            }else{
                des=-1;
            }
        }else{
            axis=0;
            if(target.getX()-t.getX()==1){
                des=1;
            }else{
                des=-1;
            }
        }
        a.append(axis,des);
        int axis2=0,des2=0;
        A1093367_checkpoint7_Block t2;
        while(true){
            if(t.equals(root)){
                break;
            }
            t2=ParentBlock.get(t);
            if(t.getY()-t2.getY()!=0){
                axis2=1;
                if(t.getY()-t2.getY()==1){
                    des2=1;
                }else{
                    des2=-1;
                }
            }else{
                axis2=0;
                if(t.getX()-t2.getX()==1){
                    des2=1;
                }else{
                    des2=-1;
                }
            }
            a.insert(axis, des, axis2, des2);
            if(t2.equals(root)){
                break;
            }
            axis=axis2;
            des=des2;
            t=t2;
        }
        return a;
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
}
