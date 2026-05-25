import java.util.*;
import java.sql.*;

public class CursoDao {
    private Connection c;

    public CursoDao(Connection c){
        this.c = c;
    }

    public List<Curso> ListarCurso() throws SQLException {
        List<Curso> curso = new ArrayList<>();
        String sql = "SELECT id_curso , nome_curso , duracao FROM curso";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            Curso c = new Curso(
                    rs.getInt("id_curso"),
                    rs.getString("nome_curso"),
                    rs.getInt("duracao")
            );
            curso.add(c);
        }
        return curso;
    }
}
