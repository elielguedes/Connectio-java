import java.sql.*;
import java.util.*;

public class DisciplinaDao {
    private Connection c;

    public DisciplinaDao(Connection c){
        this.c = c;
    }

    public void InserirDisciplina(Disciplina d) throws SQLException{
        String sqlU = "INSERT INTO disciplina(nome_disciplina ,CargaHoraria  , professor_id , curso_id) VALUES(? , ? , ? , ?)";
        try(PreparedStatement stmt = c.prepareStatement(sqlU)){
            stmt.setString(1 , d.getNome());
            stmt.setInt(2 , d.getCargaHoraria());
            stmt.setInt(3 , d.getProf());
            stmt.setInt(4 , d.getCurso_id());
            stmt.executeUpdate();
        }
    }

    public List<Disciplina> ListarDisciplina() throws SQLException {
        List<Disciplina> d = new ArrayList<>();
        String sql = "SELECT id_disciplina , nome_disciplina , CargaHoraria , professor_id , curso_id FROM disciplina";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            Disciplina dis = new Disciplina(
                    rs.getInt("id_disciplina"),
                    rs.getString("nome_disciplina"),
                    rs.getInt("CargaHoraria"),
                    rs.getInt("professor_id"),
                    rs.getInt("curso_id")
            );
                    d.add(dis);
        }
        return d;
    }

    public void UpdateDisciplina(Disciplina disciplina) throws SQLException {
        String sqlUp = "UPDATE disciplina SET nome_disciplina = ? , CargaHoraria = ? , professor_id = ? , curso_id WHERE id_disciplina";
        try(PreparedStatement stmt = c.prepareStatement(sqlUp)){
            stmt.setString(1 , disciplina.getNome());
            stmt.setInt(2 , disciplina.getCargaHoraria());
            stmt.setInt(3 , disciplina.getProf());
            stmt.setInt(4 , disciplina.getCurso_id());
            stmt.executeUpdate();
        }
    }

    public void DelDisciplina(int id) throws SQLException{
        String sqlDl = "DELETE FROM disciplina WHERE id_disciplina = ?";
        try(PreparedStatement stmt = c.prepareStatement(sqlDl)){
            stmt.setInt(1 , id);
            stmt.executeUpdate();
        }
    }

}
