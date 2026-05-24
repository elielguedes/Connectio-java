    import java.sql.*;
    import java.util.*;

    public class AlunoDao {

        private Connection c;

        public AlunoDao(Connection c){
            this.c = c;
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
    }
