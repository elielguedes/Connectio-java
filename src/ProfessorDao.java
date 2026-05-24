import java.util.*;
import java.sql.*;

public class ProfessorDao {
    private Connection c;

    public ProfessorDao(Connection c){
        this.c = c;
    }

    public List<Professor> ListarProfessor() throws SQLException{
        List<Professor> professor = new ArrayList<>();
        String sql = "SELECT id_professor , nome_professor , titulacao FROM Professor";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            Professor p = new Professor(
                    rs.getInt("id_professor"),
                    rs.getString("nome_professor"),
                    rs.getString("titulacao")
            );
            professor.add(p);
        }
        return professor;
    }
}
