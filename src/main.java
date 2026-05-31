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

            try {
                while (true) {
                    System.out.println("\n=== Menu Principal ===");
                    System.out.println("1 -> Gerenciar Aluno");
                    System.out.println("2 -> Gerenciar Professor");
                    System.out.println("3 -> Gerenciar Curso");
                    System.out.println("4 -> Gerenciar Disciplina");
                    System.out.println("5 -> Sair");
                    System.out.print("Digite opção desejada: ");
                    int opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1 -> as.MenuAluno();
                        case 2 -> pf.MenuProf();
                        case 3 -> cr.MenuCurso();
                        case 4 -> ds.MenuDisciplina();
                        case 5 -> {
                            System.out.println("Encerrando programa...");
                            return;
                        }
                        default -> System.out.println("Opção incorreta!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            } finally {
                conexao.fechar();
                sc.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
