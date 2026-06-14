package Dao;
import java.sql.*;
import Model.Matricula;
import java.util.*;

public class MatriculaDao {
    private Connection c;

    public MatriculaDao(Connection c) {
        this.c = c;
    }

    public void InsertMatricula(Matricula m) throws SQLException {
        String sql = "INSERT INTO matricula(id_matricula , status , aluno_id , disciplina_id) values(? , ? , ? , ?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1 , m.getIdMatricula());
            ps.setString(2 , m.getStatus());
            ps.setInt(3 , m.getAlunofk());
            ps.setInt(4 , m.getDisciplinafk());
            ps.executeUpdate();
        }
    }

    public List<Matricula> ListarMatricula() throws SQLException {
        List<Matricula> ma = new ArrayList<>();
        String sql = "SELECT id_matricula , status , aluno_id , disciplina_id FROM matricula";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            Matricula m = new Matricula(
                    rs.getInt("id_matricula"),
                    rs.getString("status"),
                    rs.getInt("aluno_id"),
                    rs.getInt("disciplina_id")
            );
            ma.add(m);
        }
        return ma;
    }

    public void UpdateMatricula(Matricula m) throws SQLException {
        String sql = "UPDATE matricula SET status = ? , aluno_id = ? , disciplina_id = ? WHERE id_matricula = ?";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1 , m.getStatus());
            ps.setInt(2 , m.getAlunofk());
            ps.setInt(3 , m.getDisciplinafk());
            ps.setInt(4 , m.getIdMatricula());
            ps.executeUpdate();
        }
    }

    public void DeleteMatricula(int id) throws SQLException {
        String sql = "DELETE FROM matricula WHERE id_matricula = ?";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1 , id);
            ps.executeUpdate();
        }
    }

    public Matricula Busca(int id) throws SQLException {
        String sql = "SELECT id_matricula , status , aluno_id , disciplina_id FROM matricula WHERE id_matricula = ?";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1 , id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return new Matricula(
                            rs.getInt("id_matricula"),
                            rs.getString("status"),
                            rs.getInt("aluno_id"),
                            rs.getInt("disciplina_id")
                    );
                }
                return null;
            }
        }
    }
}
