package learn;

import java.sql.*;

public class PostgresConn {

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "postgres";

    public static void main(String args[]) {

        PostgresConn pgcon = new PostgresConn();
        pgcon.connect();

    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public long insertUserDetails(RegisterUser registerUser) {

        String query = "insert into \"registerUser\" (\"clientUserIdStr\",\"inviteCode\",\"status\",\"inviteUrl\",\"userId\")"
                + "values(?,?,?,?,?)";

        int affectedRows = 0;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, registerUser.getClientUserIdStr());
            pstmt.setString(2, registerUser.getInviteCode());
            pstmt.setString(3, registerUser.getStatus());
            pstmt.setString(4, registerUser.getInviteUrl());
            pstmt.setInt(5, registerUser.getUserId());

            affectedRows = pstmt.executeUpdate();
            System.out.println("Im here!");
            if (affectedRows == 0) {
                System.out.println("Nothing Affected");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return affectedRows;
    }

    public int getUserCount() {

        String query = "Select count(*) from \"registerUser\"";
        int count = 0;

        try (Connection connect = connect();
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count;

    }

    public void getUsers() {

        String query = "Select * from \"registerUser\"";

        try (Connection connect = connect();
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            displayUsers(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void displayUsers(ResultSet rs) throws SQLException {

        while (rs.next()) {
            System.out.println("Client ID : " + rs.getString("clientUserIdStr") + "\t" + "\tInvite Code : "
                    + rs.getString("inviteCode") + "\t" + "\tStatus : " + rs.getString("status"));
        }
    }

}