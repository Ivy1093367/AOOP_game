
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class A1093367_checkpoint7_GamePanel extends JPanel {
    // Description : the obstacle location set in GUI index version.
    private ArrayList<Integer[]> obstacleList;
    // Description : the obstacle images set. bar_id -> obstacle image
    private HashMap<Integer, Image> obstacleImg = new HashMap<>();
    // Description : the image object of the map.
    private Image mapImg = new ImageIcon("Resource/map.png").getImage();
    // Description : the displaysize of the map
    private int scaler;
    private ArrayList<A1093367_checkpoint7_House> houseList = new ArrayList<A1093367_checkpoint7_House>();
    private ArrayList<A1093367_checkpoint7_Barrack> barrackList = new ArrayList<A1093367_checkpoint7_Barrack>();
    private ArrayList<A1093367_checkpoint7_Pyramid> pyramidList = new ArrayList<A1093367_checkpoint7_Pyramid>();
    private ArrayList<A1093367_checkpoint7_Soldier> soldierList = new ArrayList<A1093367_checkpoint7_Soldier>();
    // Description : the normal image size.
    // Hint : while the mapsize is not normal size, you have to think of the
    // displaysize.
    private int originalGridLen = 256;
    // Description : the image displaysize.
    private int gridLen;
    // Description : the map center point x-axis location.
    // Hint : While dragging the map, you may need to set the map location via this.
    private Integer centerX = 0;
    // Description : the map center point y-axis location.
    // Hint : While dragging the map, you may need to set the map location via this.
    private Integer centerY = 0;

    public A1093367_checkpoint7_GamePanel(ArrayList<A1093367_checkpoint7_House> houseList, ArrayList<A1093367_checkpoint7_Barrack> barrackList,
            ArrayList<A1093367_checkpoint7_Pyramid> pyramidList,ArrayList<Integer[]> obstacleList, HashMap<Integer, Image> obstacleImg, int scaler) {
        this.obstacleList = obstacleList;
        this.scaler = scaler;
        this.obstacleImg = obstacleImg;
        this.houseList = houseList;
        this.barrackList = barrackList;
        this.pyramidList = pyramidList;
        gridLen = originalGridLen / scaler;
        centerX = (4096 / scaler) / 2;
        centerY = (4096 / scaler) / 2;
    }

    // Description : While painting this JPanel, we draw map on the given location and other obstacles.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int mapX = this.getWidth() / 2 - centerX;
        int mapY = this.getHeight() / 2 - centerY;
        g.drawImage(this.mapImg, mapX, mapY, this.mapImg.getWidth(null) / scaler, this.mapImg.getHeight(null) / scaler,
                null);
        for (Integer[] x : obstacleList) {
            g.drawImage(this.obstacleImg.get(x[2]), mapX + x[0] * gridLen, mapY + x[1] * gridLen, gridLen, gridLen,
                    null);
        }
        if (houseList.size() != 0) {
            for (A1093367_checkpoint7_House house : houseList) {
                house.setLocation(mapX + house.getlocationX() * gridLen, mapY + house.getlocationY() * gridLen);
            }
        }
        if (barrackList.size() != 0) {
            for (A1093367_checkpoint7_Barrack barrack : barrackList) {
                barrack.setLocation(mapX + barrack.getlocationX() * gridLen, mapY + barrack.getlocationY() * gridLen);
            }
        }
        if (pyramidList.size() != 0) {
            for (A1093367_checkpoint7_Pyramid pyramid : pyramidList) {
                pyramid.setLocation(mapX + pyramid.getlocationX() * gridLen, mapY + pyramid.getlocationY() * gridLen);
            }
        }
        if (soldierList.size() != 0) {
            for (A1093367_checkpoint7_Soldier soldier : this.soldierList) {
                soldier.setLocation(mapX + soldier.getlocationX() * gridLen, mapY + soldier.getlocationY() * gridLen);
            }
        }
    }

    public Integer getCenterX() {
        return this.centerX;
    }

    public void setCenterX(Integer centerX) {
        this.centerX = centerX;
    }

    public Integer getCenterY() {
        return this.centerY;
    }

    public void setCenterY(Integer centerY) {
        this.centerY = centerY;
    }

    public Integer getGridLen() {
        return this.gridLen;
    }

    public void setHouseList(ArrayList<A1093367_checkpoint7_House> houseList) {
        this.houseList = houseList;
    }

    public void setBarrackList(ArrayList<A1093367_checkpoint7_Barrack> barrackList) {
        this.barrackList = barrackList;
    }

    public void setPyramidList(ArrayList<A1093367_checkpoint7_Pyramid> pyramidList) {
        this.pyramidList = pyramidList;
    }

    public void addToSoldierList(A1093367_checkpoint7_Soldier soldier){
        this.soldierList.add(soldier);
    }
    public ArrayList<A1093367_checkpoint7_Soldier> getSoldierList(){
        return soldierList;
    }

}
