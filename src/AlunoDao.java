    import java.sql.*;
    import java.util.*;

    public class AlunoDao {

        private Connection c;

        public AlunoDao(Connection c){
            this.c = c;
        }

        public void InserirAluno(Aluno a) throws SQLException {
            String sql = "INSERT INTO Aluno(nome_aluno , email , idade) VALUES (? , ? , ?)";
            try(PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1 , a.getNomeAluno());
                ps.setString(2 , a.getEmail());
                ps.setInt(3 , a.getIdade());
                ps.executeUpdate();
            }
        }

        public List<Aluno> ListarAlunos() throws SQLException{
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

        public void AtualizarAluno(Aluno a) throws SQLException {
            String sqlUp = "UPDATE Aluno set nome_aluno = ? , email = ? , idade = ? where id_aluno = ?";
            try(PreparedStatement stmt = c.prepareStatement(sqlUp)){
                stmt.setString(1 , a.getNomeAluno());
                stmt.setString(2 , a.getEmail());
                stmt.setInt(3 , a.getIdade());
                stmt.setInt(4 , a.getIdAluno());
                stmt.executeUpdate();
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
