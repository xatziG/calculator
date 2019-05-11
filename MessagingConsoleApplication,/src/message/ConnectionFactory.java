package message;

import java.sql.*;

public class ConnectionFactory {
    private static Connection connection=null;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "1234";
    private Statement statement;
    private PreparedStatement preparedStatement;
    public ConnectionFactory() {
       try {
         Class.forName(JDBC_DRIVER);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
     }

 public  static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            return connection;


        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);

        }

    }
    public void closeConnection() throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }
    public ResultSet executeQuery(String sql) throws Exception{
        try {
            this.setPreparedStatement(getConnection().prepareStatement(sql));
            // this.getStatement().close();
            return this.getPreparedStatement().executeQuery(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new Exception();
    }
    public  void update(String sql)throws SQLException{
        this.setPreparedStatement(getConnection().prepareStatement(sql));
        this.getPreparedStatement().executeUpdate(sql);
    }

    public void insert(String sql) throws SQLException{
        this.setPreparedStatement(getConnection().prepareStatement(sql));
        this.getPreparedStatement().executeUpdate(sql);
    }
    public ResultSet delete (String sql) throws SQLException{
        this.setPreparedStatement(getConnection().prepareStatement(sql));
        this.getPreparedStatement().executeUpdate(sql);
        return null;
    }



    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }



}


