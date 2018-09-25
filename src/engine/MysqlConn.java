package engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de conexão com o banco de dados Mysql
 * @author Juan Galvão
 */
public class MysqlConn {
    //Nome do usuário do mysql
    private static final String USERNAME = "root";

    //Senha do mysql
    private static final String PASSWORD = "";

    //Dados de caminho, porta e nome da base de dados que irá ser feita a conexão
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/db_fisiobase";

    // Objeto de Conexão
    private static Connection connection;
    
    /**
     * Cria uma conexão com o banco de dados MySQL utilizando o nome de usuário
     * e senha fornecidos
     *
     * @return uma conexão com o banco de dados
     * @throws Exception
     */
    public static Connection createConnectionToMySQL() throws Exception {
        Class.forName("com.mysql.jdbc.Driver"); //Faz com que a classe seja carregada pela JVM

        //Cria a conexão com o banco de dados
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }
    
    /**
     * Fecha a conexão com o banco de dados
     * @throws SQLException 
     */
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
