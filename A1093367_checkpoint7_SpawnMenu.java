import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class A1093367_checkpoint7_SpawnMenu extends JPanel {
    // Description : The root frame.
    private A1093367_checkpoint7_GameFrame parentFrame;
    // Description : Base location for spawning the soldier.
    private int baseLocationX,baseLocationY;

    /*********************************** The TODO (Past) ********************************
     * 
     * TODO(Past): Upon the Soldier button being clicked, you need to first check if location is suitable for 
     * spawning a soldier. A suitable place is defined as one grid below the base is empty.
     * If the location is suitable for spawning soldier, you need to first create a object of checkpoint7_Soldier
     * and make the thread start. Also you need to update the soldier list in the gamePanel and add to
     * gamepanel. Last, after a soldier is spawned,close the spawn menu.
     * Hint1: You could use ClickCheckGridLocation() to check whether the location is suitable.
     ********************************** The End of the TODO**************************************/

    public A1093367_checkpoint7_SpawnMenu() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton soldierButton = new JButton("Soldier");
        soldierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /********************************************************************************************
                 * START OF YOUR CODE
                 ********************************************************************************************/
                if(!parentFrame.ClickCheckGridLocation(baseLocationX,baseLocationY+1,false)){
                    A1093367_checkpoint7_Soldier s=new A1093367_checkpoint7_Soldier(baseLocationX,baseLocationY+1,parentFrame.jfScaler,SwingConstants.CENTER,parentFrame.algorithm);
                    parentFrame.gamePanel.add(s);
                    Thread sT=new Thread(s);
                    sT.start();
                    parentFrame.gamePanel.addToSoldierList(s);
                }
                closeMenu();
                /********************************************************************************************
                 * END OF YOUR CODE
                 ********************************************************************************************/
            }
        });
        this.add(soldierButton);
        this.setFocusable(true);
        this.requestFocusInWindow();
        setVisible(false);


}
    // Description : To set the base location for spawning the soldier.
    public void setBase(int baseLocationX,int baseLocationY){
        this.baseLocationX=baseLocationX;
        this.baseLocationY=baseLocationY;
    }
    // Description : To set root frame.
    public void setParentFrame(){
        this.parentFrame =(A1093367_checkpoint7_GameFrame) SwingUtilities.getWindowAncestor(this);
    }
    // Description : Close the spawnMenu.
    private void closeMenu() {
        parentFrame.spawnMenu.setVisible(false);
    }
    // Description : Open the spawnMenu.
    private void showMenu() {
        parentFrame.spawnMenu.setVisible(true);
    }

}
