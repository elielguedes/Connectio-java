package Dao;
import Model.Aluno;
import java.util.*;
import java.sql.*;

public class AlunoDao {
    private Connection c;

    public AlunoDao(Connection c){
        this.c = c;
    }

    public void InsertAlunos(Aluno a) throws SQLException {
        String sql = "INSERT INTO Aluno(nome_aluno , email , idade) VALUES (? , ? , ?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1 , a.getNomeAluno());
            ps.setString(2 , a.getEmail());
            ps.setInt(3 , a.getIdade());
            ps.executeUpdate();
        }
    }


    public List<Aluno> listarAlunos() throws SQLException{
        List<Aluno> Alunos = new ArrayList<>();
        String sql = "SELECT id_aluno, nome_aluno , email , idade FROM Aluno";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            Aluno aluno = new Aluno(
                    rs.getInt("id_aluno"),
                    rs.getString("nome_aluno"),
                    rs.getString("email"),
                    rs.getInt("idade")
            );
            Alunos.add(aluno);
        }
        return Alunos;
    }

    public void UpdateAlunos(Aluno a) throws SQLException{
        String sql = "UPDATE aluno set nome_aluno = ? , email = ? , idade = ? WHERE id_aluno = ?";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1 , a.getNomeAluno());
            ps.setString(2 , a.getEmail());
            ps.setInt(3 , a.getIdade());
            ps.setInt(4 , a.getIdAluno());
            ps.executeUpdate();
        }
    }

    public void DeletarAluno(int id) throws SQLException{
        String sqlD = "DELETE FROM Aluno WHERE id_aluno = ?";
        try(PreparedStatement stmt = c.prepareStatement(sqlD)){
            stmt.setInt(1 , id);
            stmt.executeUpdate();
        }
    }

}
