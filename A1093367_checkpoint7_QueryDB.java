import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class A1093367_checkpoint7_QueryDB {
    // //Description : the driver description of mysql
    // private static final String driver = "com.mysql.cj.jdbc.Driver";
    // //Description : the protocol description of mysql
    // private static final String protocol = "jdbc:mysql://140.127.220.220:3306/";
    // Description : the obstacle set queried from database.
    private static ArrayList<Integer[]> data = new ArrayList<Integer[]>();
    // Description : the filename of obstacle image queried from database.
    private static HashMap<Integer, String> typeChar = new HashMap<Integer, String>();
    // Description : the primary key of map in database.
    private static String mapID = "0";

    public static void queryData(ArrayList<Integer[]> data, HashMap<Integer, String> typeChar) {
        // TODO(Past): Querying the barrier location from the server, and set it into
        // Arraylist data.
        // TODO(Past): Querying the bar_type and the corresponding file_name from the
        // server, and set it into HashMap.
        /********************************************************************************************
         * START OF YOUR CODE
         ********************************************************************************************/
        final String DATABASE_URL="jdbc:postgresql://140.127.220.226:5432/oopiickp";
        final String SELECT_QUERY_ObInfo="SELECT obstacle_id,x_coordinate,y_coordinate,obstacle_type,map_id FROM obstacle_info";
        final String SELECT_QUERY_ObStyle="SELECT obstacle_type,display,filename FROM obstacle_style";
        final String driver="org.postgresql.Driver";
        try{
            Class.forName(driver);
            Connection con=DriverManager.getConnection(DATABASE_URL,"fallckp","2021OOPIIpwd");
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY_ObInfo);
            
            Integer[] obstacle=new Integer[3];
    
            while(resultSet.next()){
                if(resultSet.getString(5).equals(mapID)){
                    //obstacle[0]=resultSet.getInt(2);
                    //obstacle[1]=resultSet.getInt(3);
                    obstacle[0]=resultSet.getInt(3);
                    obstacle[1]=resultSet.getInt(2);
                    obstacle[2]=resultSet.getInt(4);
                    data.add(obstacle);                    
                    obstacle=new Integer[3];
                }
            }
            resultSet=statement.executeQuery(SELECT_QUERY_ObStyle);
    
            while(resultSet.next()){
                typeChar.put(resultSet.getInt(1),resultSet.getString(3));
            }
        }catch(ClassNotFoundException err){
            err.printStackTrace();
            System.exit(0);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        /********************************************************************************************
         * END OF YOUR CODE
         ********************************************************************************************/

    }

    public ArrayList getObstacle() {
        return this.data;
    }

    public void setObstacle(ArrayList<Integer[]> data) {
        this.data = data;
    }

    public String getMapID() {
        return this.mapID;
    }

    public void setMapID(String mapID) {
        this.mapID = mapID;
    }

    public HashMap getObstacleImg() {
        return this.typeChar;
    }

    public void setObstacleImg(HashMap<Integer, String> typeChar) {
        this.typeChar = typeChar;
    }
}
