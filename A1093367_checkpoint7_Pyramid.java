import javax.swing.*;
import java.awt.*;

public class A1093367_checkpoint7_Pyramid extends JLabel implements Runnable {
    // Description : the grid location X of player.
    private int locationX;
    // Description : the grid location Y of player.
    private int locationY;
    // Description : identify the player is onclick or not.
    private int originalGridLen;
    // Description : the Image the player is.
    private ImageIcon icon;
    private boolean understructure;
    private int jfScaler;
    private int maximum;
    // Description : The number of this type of building.
    private String produced_num;
    // Description : The image of under constructing.
    private ImageIcon constructingIcon;

    /********************************** The TODO (Past) ********************************
     * 
     * TODO(Past): Upon a pyramid is built, it needs to first wait for 5 seconds, then
     * it will change its photo from "constructing.png" to "pyramid.png".Also, there
     * will be a counter to show the progress, thus you have to refresh the text of
     * this building every 0.5 sec. Hint : The text of counter will be like-> "0%"
     * -> wait 0.5sec -> "10%" -> wait 0.5sec -> "20%" -> wait 0.5sec -> "30%" ->
     * wait 0.5sec -> "40%" -> wait 0.5sec -> "50%" -> wait 0.5sec -> "60%" -> wait
     * 0.5sec -> "70%" -> wait 0.5sec -> "80%" -> wait 0.5sec -> "90%" -> wait
     * 0.5sec -> "100%" -> produced_num(the number of this type of building).
     * 
     ********************************** The End of the TODO**************************************/
    @Override
    public void run() {
        /********************************************************************************************
         * START OF YOUR CODE
         ********************************************************************************************/
        for(int i=0;i<10;i++){
            this.setText(10*i+"%");
            doNothing(500);
        }
        this.setText("100%");
        this.setIcon(icon);
        this.setText(produced_num);
        /********************************************************************************************
         * END OF YOUR CODE
         ********************************************************************************************/
    }

    // Description : Stop the thread in milliseconds.
    private static void doNothing(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Unexpected interruption");
            System.exit(0);
        }
    }
    /********************************** The TODO(Past) ********************************
     * 
     * TODO(Past): Aside from the "pyramid.png", you also need to read in the
     * "constructing.png".You need to set the text of this building to "0%" when it
     * is built. Hint : The size of "constructing.png" has to be the same size of
     * "pyramid.png".
     * 
     ********************************** The End of the TODO**************************************/

    // Description: this is the player's constructor, we set icon of player here and
    // calculate the distance every step.
    // Hint text : "player", horizontalAlignment : SwingConstants.CENTER
    public A1093367_checkpoint7_Pyramid(int locationX, int locationY, String text, int scaler, int horizontalAlignment) {
        super(text, horizontalAlignment);
        this.locationX = locationX;
        this.locationY = locationY;
        this.understructure = true;
        this.jfScaler = scaler;
        this.originalGridLen = 256;
        this.icon = new ImageIcon("Resource/pyramid.png");
        this.maximum = 5;
        this.produced_num = text;
        Image img = icon.getImage();
        img = img.getScaledInstance(originalGridLen / scaler * 2, originalGridLen / scaler * 2, Image.SCALE_DEFAULT);
        icon.setImage(img);
        /********************************************************************************************
         * START OF YOUR CODE
         ********************************************************************************************/
        this.setText("0%");
        constructingIcon=new ImageIcon("Resource/constructing.png");
        Image imgCon=constructingIcon.getImage();
        imgCon=imgCon.getScaledInstance(originalGridLen / scaler * 2, originalGridLen / scaler * 2, Image.SCALE_DEFAULT);
        constructingIcon.setImage(imgCon);
        this.setIcon(constructingIcon);
        /********************************************************************************************
         * END OF YOUR CODE
         ********************************************************************************************/

        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);

    }

    public void produce_people(int maximum, int produced_num) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public int getlocationX() {
        return this.locationX;
    }

    public void setlocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getlocationY() {
        return this.locationY;
    }

    public void setlocationY(int locationY) {
        this.locationY = locationY;
    }

    public Image getImage() {
        return this.icon.getImage();
    }

}
