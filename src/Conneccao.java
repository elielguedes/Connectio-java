import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conneccao {

    private Connection c;

    public void conectar() throws ClassNotFoundException, SQLException {
        // Carregar driver do MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Conectar ao banco sistemaoff
        c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistemaoff?useSSL=false&serverTimezone=UTC",
                "root",
                "" // coloque sua senha aqui se tiver
        );
        System.out.println("Conectado com sucesso!");
    }

    public void executar() {
        try {
            conectar();
            // aqui você pode chamar métodos como criar(), inserir(), mostrar()
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (c != null) {
                    c.close();
                    System.out.println("Conexão fechada");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return c;
    }

    public void fechar() throws SQLException {
        if (c != null) {
            c.close();
            System.out.println("Conexão fechada");
        }
    }
}