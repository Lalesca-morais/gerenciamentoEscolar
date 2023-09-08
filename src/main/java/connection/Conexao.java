package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection fazerConexao () {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/escola",
                            "postgres", "1234");
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
