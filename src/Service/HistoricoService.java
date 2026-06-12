package Service;
import java.util.*;
import Dao.HistoricoDao;
import java.sql.*;

public class HistoricoService {
    private Scanner sc;
    private HistoricoDao h;

    public HistoricoService(Scanner sc , HistoricoDao h){
        this.sc = sc;
        this.h = h;
    }

    public void MenuHistoric() throws SQLException{
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
                }
            }catch(Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }

    public void CadastrarHistorico() throws SQLException{
        System.out.println("Digite a quantidade de notas: ");
        int qtdnota = sc.nextInt();
        sc.nextLine();

        for(int i = 0;i < qtdnota; i++){
            System.out.println("Informe a nota do aluno: ");
            float nota = sc.nextFloat();
            sc.nextLine();
        }

        System.out.println("Informe a quantidade de frequencia: ");
        int frequencia = sc.nextInt();
        sc.nextLine();

    }
}
