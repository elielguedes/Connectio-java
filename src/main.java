import java.sql.*;
import java.util.*;
import Util.Conneccao;
import Service.AlunoService;
import Service.CursoService;
import Service.DisciplinaService;
import Service.ProfessorService;
import Dao.AlunoDao;
import Dao.ProfessorDao;
import Dao.CursoDao;
import Dao.DisciplinaDao;
import Service.HistoricoService;
import Service.MatriculaService;
import Dao.MatriculaDao;
import Dao.HistoricoDao;

public class main {
    public static void main(String[] args) {
        try {
            Conneccao conexao = new Conneccao();
            conexao.conectar();
            Connection conn = conexao.getConnection();

            Scanner sc = new Scanner(System.in);
            AlunoService as = new AlunoService(new AlunoDao(conn), sc);
            ProfessorService pf = new ProfessorService(new ProfessorDao(conn), sc);
            CursoService cr = new CursoService(new CursoDao(conn), sc);
            DisciplinaService ds = new DisciplinaService(
                    new DisciplinaDao(conn),
                    new ProfessorDao(conn),
                    new CursoDao(conn),
                    sc
            );
            MatriculaService m = new MatriculaService(conn , sc ,new AlunoDao(conn) , new DisciplinaDao(conn), new MatriculaDao(conn));
            HistoricoService h = new HistoricoService(sc, conn, new MatriculaService(conn , sc ,new AlunoDao(conn) , new DisciplinaDao(conn), new MatriculaDao(conn)), new HistoricoDao(conn));

            try {
                while (true) {
                    System.out.println("\n=== Menu Principal ===");
                    System.out.println("1 -> Gerenciar Aluno");
                    System.out.println("2 -> Gerenciar Professor");
                    System.out.println("3 -> Gerenciar Curso");
                    System.out.println("4 -> Gerenciar Disciplina");
                    System.out.println("5 -> Gerenciar Matricula");
                    System.out.println("6 -> Gerenciar Historico");
                    System.out.println("7 -> Sair");
                    System.out.print("Digite opção desejada: ");
                    int opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1 -> as.MenuAluno();
                        case 2 -> pf.MenuProf();
                        case 3 -> cr.MenuCurso();
                        case 4 -> ds.MenuDisciplina();
                        case 5 -> m.Menu();
                        case 6 -> h.MenuHistorico();
                        case 7 -> {
                            System.out.println("Encerrando programa...");
                            return;
                        }
                        default -> System.out.println("Opção incorreta!");
                    }
                }
            } catch (Exception e) { // qualquer erro
                System.out.println("Erro: " + e.getMessage());
            } finally { // sempre cai aqui
                conexao.fechar();
                sc.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
