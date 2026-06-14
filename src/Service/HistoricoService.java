package Service;
import java.util.*;
import Dao.HistoricoDao;
import java.sql.*;
import Model.Historico;
import Service.MatriculaService;

public class HistoricoService {
    private Connection c;
    private Scanner sc;
    private HistoricoDao hi;
    private MatriculaService m;
    private Historico h;

    public HistoricoService(Scanner sc , Connection c, MatriculaService m , HistoricoDao hi){
        this.sc = sc;
        this.c = c;
        this.m = m;
        this.hi = hi;
    }

    public void MenuHistorico() throws SQLException{
        while(true){
            System.out.println("\n -= Historico =- \n");
            System.out.println("1 -- Cadastro de historico ");
            System.out.println("2 -- Listar ");
            System.out.println("3 -- Atualizar historico");
            System.out.println("4 -- Deletar ");
            System.out.println("5 -- Sair");

            System.out.println("Digite opção desejada: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            try{
                switch(opcao){
                    case 1 -> CadastrarHistorico();
                    case 2 -> listaHistorico();
                    case 3 -> Update();
                    case 4 -> Delete();
                    case 5 -> {System.out.println("Saindo...."); return;}
                    default -> System.out.println("Opção incorreta !");
                }
            }catch(Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }

    public void ListMatricula() throws SQLException {
        m.ListMatricula();
    }

    public void CadastrarHistorico() throws SQLException{
        System.out.println("Informe a quantidade de cadastro: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        for(int i = 0;i < qtd; i++){
            System.out.println("Digite a quantidade de notas: ");
            int qtd_nota = sc.nextInt();
            sc.nextLine();

            float copia = 0;
            for(int j = 0;j < qtd_nota; j++){
                System.out.println("Informe a nota do aluno "+ (i + 1) +" : ");
                float nota = Float.parseFloat(sc.nextLine());
                sc.nextLine();
                copia = nota;
            }

            System.out.println("Informe a quantidade de frequencia: ");
            int frequencia = sc.nextInt();
            sc.nextLine();

            System.out.println("\n =- Matricula disponíveis -= \n");
            ListMatricula();

            System.out.println("Digite o id da matricula: ");
            int id_matricula = sc.nextInt();
            sc.nextLine();

            Historico historico = new Historico(0 , copia , frequencia , id_matricula);
            hi.InsertHistorico(historico);

            System.out.println("Cadastro realizado com sucesso !");
        }
    }

    public void listaHistorico() throws SQLException {
        List<Historico> hs = hi.ListarHistorico();
        for(Historico his : hs){
            System.out.println(his);
        }
    }

    public void Update() throws SQLException {
        Historico Hist = null;
        String opcao;
        do{
            listaHistorico();
            System.out.println("\n Informe o id que deseja alterar: ");
            int id = sc.nextInt();
            sc.nextLine();

            Historico his = hi.Busca_id(id);
            if(his != null){
                System.out.println("Cadastro não encontrado !");
            }

            System.out.println("Digite nova nota: ");
            Hist.setNota(sc.nextFloat());
            sc.nextLine();

            System.out.println("Informe a frequencia: ");
            Hist.setFrequencia(sc.nextInt());
            sc.nextLine();

            System.out.println("\n Matriculas \n");
            ListMatricula();
            System.out.println("Infome o id da matricula: ");
            Hist.setMatricula(sc.nextInt());
            sc.nextLine();

            hi.UpdateHistorico(his);
            System.out.println("Deseja alterar mais algum cadastro(sim/não)? ");
            opcao = sc.nextLine();
        }while(opcao.equalsIgnoreCase("sim"));
    }

    public void Delete() throws SQLException {
        String opcao;
        do{
            listaHistorico();
            System.out.println("Digite o id que deseja apaguar: ");
            int id = sc.nextInt();
            sc.nextLine();

            hi.DeleteHistorico(id);

            System.out.println("Cadastro deletado com sucesso !");

            System.out.println("Deseja apaguar mais algum cadastro(sim/não): ");
            opcao = sc.nextLine();
        }while(opcao.equalsIgnoreCase("sim"));
    }
}