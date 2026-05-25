import java.util.*;
import java.sql.*;

public class ProfessorDao {
    private Connection c; // connecção aberta

    public ProfessorDao(Connection c){
        this.c = c;
    }

    public List<Professor> ListarProfessor() throws SQLException{ // throws se der erro volta um sql error
        List<Professor> professor = new ArrayList<>();
        String sql = "SELECT id_professor , nome_professor , titulacao FROM Professor";
        Statement stmt = c.createStatement(); // envia comandos sql para o banco quem execulta o sql
        ResultSet rs = stmt.executeQuery(sql); // executa o comando sql

        while(rs.next()){ // enquanto percorrer o sql o next() -> seria proximo ex: linha 1 , linha 2 e linha 3
            Professor p = new Professor(
                    // pego os dados usando get
                    rs.getInt("id_professor"),
                    rs.getString("nome_professor"),
                    rs.getString("titulacao")
            );
            professor.add(p); // Objeto criado e colocando na lista
        }
        return professor; // retorna o objeto
    }
}
