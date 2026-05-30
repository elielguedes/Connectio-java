package Util;

import java.sql.Connection;
import java.sql.DriverManager; // cria a conecção
import java.sql.SQLException; //erro relacional do sql
import java.sql.Statement; // execulta o sql

public class Conneccao {

    private Connection c; // a variavel c guarda a conecxão aberta

    public void conectar() throws ClassNotFoundException, SQLException {  // pode gerar erros drive nao encontrado e erro no banco
        Class.forName("com.mysql.cj.jdbc.Driver");// carrega o drive

        // drivemanager recebe a url || getConne -> pega a conecção
        c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistemaoff?useSSL=false&serverTimezone=UTC",
                "root",
                ""
        );
        System.out.println("Conectado com sucesso!");
    }

    public void executar() { // metodo tenta executar e trata dos erros
        try {  // tenta exxultar algo que pode dar erro
            conectar();
        } catch (ClassNotFoundException e) { // se der erro no drive cai aqui
            System.out.println("Erro: " + e.getMessage());
        } catch (SQLException e) { // aqui erro em sql ex: senha errada
            System.out.println("Erro sql: " + e.getMessage());
        } finally { // executa sempre
            try {
                if (c != null) { // se c tiver null fecha o banco
                    c.close();
                    System.out.println("Conexão fechada");
                }
            } catch (SQLException e) { // tentou fachar o banco e deu erro
                System.out.println("Erro ao fechar: " + e.getMessage());
            }
        }
    }

    public Connection getConnection() { // devolve connecção a outra classe
        return c;
    }

    public void fechar() throws SQLException { // fecha manualmente o banco
        if (c != null) {
            c.close();
            System.out.println("Conexão fechada");
        }
    }
}