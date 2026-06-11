package Dao;
import java.util.*;
import java.sql.*;
import Model.Historico;

public class HistoricoDao {
    private Connection c;

    public HistoricoDao(Connection c){
        this.c = c;
    }

    public void InsertHistorico(Historico h) throws SQLException{
        String sql = "INSERT INTO historico(id_historico , nota , frequencia , matricula_id , FROM historico) VALUES(? , ? , ? , ?)";
        try(PreparedStatement ps = c.prepareStatement(sql)){
           ps.setInt(1 , h.getId());
           ps.setFloat(2 , h.getNota());
           ps.setInt(3 , h.getFrequencia());
           ps.setInt(4 , h.getMatricula());
           ps.executeUpdate();
        }
    }

    public List<Historico> ListarHistorico() throws SQLException{
        List<Historico> h = new ArrayList<>();
        String sql = "SELECT id_historico,nota,frequencia,matricula_id FROM historico";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Historico hs = new Historico(
                    rs.getInt("id_historico"),
                    rs.getFloat("nota"),
                    rs.getInt("frequancia"),
                    rs.getInt("matricula_id")
            );
            h.add(hs);
        }
        return h;
    }

}
