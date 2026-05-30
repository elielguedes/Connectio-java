package Dao;
import Model.Professor;
import Util.Conneccao;
import Model.Professor;
import java.util.*;
import java.sql.*;

public class ProfessorDao{
    private Connection c; // connecção aberta

    public ProfessorDao(Connection c){
        this.c = c;
    }

    public void InsertProf(Professor p) throws SQLException{
        String sql = "INSERT INTO professor(nome_professor , titulacao) VALUES (? , ?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1 , p.getNome_professor());
            ps.setString(2, p.getTitulacao());
            ps.executeUpdate();
        }
    }

    public List<Professor> ListProf() throws SQLException{ // throws se der erro volta um sql error
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

    public void UpdateProfessor(Professor p) throws SQLException {
        String sqlUp = "UPDATE Professor SET nome_professor = ? , titulacao = ? WHERE id_professor = ?";
        try(PreparedStatement stmt = c.prepareStatement(sqlUp)){
            stmt.setString(1 , p.getNome_professor());
            stmt.setString(2 , p.getTitulacao());
            stmt.executeUpdate();
        }
    }

    public void DelProfessores(int id) throws SQLException {
        String sqlDl = "DELETE FROM Professor WHERE id_professor = ?";
        try(PreparedStatement stmt = c.prepareStatement(sqlDl)){
            stmt.setInt(1 , id);
            stmt.executeUpdate();
        }
    }
}
