import java.util.*;
import java.sql.*;

public class CursoDao {
    private Connection c;

    public CursoDao(Connection c){
        this.c = c;
    }

    public void InserirCurso(Curso cc) throws SQLException {
        String sql = "INSERT INTO curso(nome_curso , duracao) VALUES(? , ?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1 , cc.getNome());
            ps.setInt(2 , cc.getDuracao());
            ps.executeUpdate();
        }
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

    public void UpdateCurso(Curso curso) throws SQLException {
        String sqlUp = "UPDATE Curso SET nome_curso = ? , duracao = ? WHERE id_curso = ?";
        try(PreparedStatement stmt = c.prepareStatement(sqlUp)){
            stmt.setString(1 , curso.getNome());
            stmt.setInt(2 , curso.getDuracao());
            stmt.setInt(3 , curso.getId_curso());
            stmt.executeUpdate();
        }
    }
}
