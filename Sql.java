import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Created by forch on 31-May-17.
 */
public class Sql {
    private static Connection connection = null;
    private static PGConnectionPoolDataSource source = null;

    private static void openConnection() {
        try {
            source = new PGConnectionPoolDataSource();
            source.setUser("postgres");
            source.setPassword("12345");
            source.setUrl("jdbc:postgresql://localhost:5432/UDP");
            connection = source.getConnection();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    private static void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    protected static LinkedList<Baby> getValues() {
        openConnection();
        LinkedList<Baby> answer = new LinkedList<Baby>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("select * from babies;");
            while (result.next()) {
                Baby baby = new Baby(result.getString(2), result.getBoolean(3), result.getString(4), result.getInt(5));
                answer.add(baby);
            }
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        closeConnection();
        return answer;
    }

    protected static void setValues(LinkedList<Baby> list) {
        openConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("delete from babies;");
            PreparedStatement stmt2 = connection.prepareStatement(new String("insert into babies values ( ?, ? , ? , ? , ?);"));
            for (int i = 0; i < list.size(); i++) {
                stmt2.setInt(1, i);
                stmt2.setString(2, list.get(i).getName());
                stmt2.setBoolean(3, Boolean.parseBoolean(list.get(i).getFree()));
                stmt2.setString(4, list.get(i).getSex());
                stmt2.setInt(5, Integer.parseInt(list.get(i).getAge()));
                stmt2.executeUpdate();
            }
            stmt2.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        closeConnection();
    }
}
