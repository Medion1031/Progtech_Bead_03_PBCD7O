package Backend.Database;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseController {
    private static Connection dataConnection = null;
    private static PreparedStatement statement = null;

    public static void ConnectToDatabase()
    {
        try {
            String url = "jdbc:mysql://localhost:3306/progtech";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            dataConnection = DriverManager.getConnection(url, info);

            System.out.println("Connected!");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Float> GetData()
    {
        HashMap<String, Float> ret = new HashMap<>();
        try
        {
            String sql = ("SELECT * FROM player ORDER BY timePlayed DESC;");
            statement = dataConnection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
            {
                ret.put((String) rs.getObject(1), (Float) rs.getObject(2));
            }

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return ret;
    }

    public static void SetData(String name, float time)
    {
        try {
            statement = dataConnection.prepareStatement("insert into player(playerName, timePlayed) values(?,?)");
            statement.setString(1, name);
            statement.setString(2, String.valueOf(time));
            statement.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
