package Model;

public class Matricula {
    private int idmatricula;
    private String status;
    private int alunofk;
    private int disciplinafk;

    public Matricula(int idmatricula ,String status ,int alunofk , int disciplinafk){
        this.idmatricula = idmatricula;
        this.status = status;
        this.alunofk = alunofk;
        this.disciplinafk = disciplinafk;
    }

    public int getIdMatricula(){
        return idmatricula;
    }

    public void setIdMatricula(int idmatricula){
        this.idmatricula = idmatricula;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getAlunofk(){
        return alunofk;
    }

    public void setAlunofk(int alunofk){
        this.alunofk = alunofk;
    }

    public int getDisciplinafk(){
        return disciplinafk;
    }

    public void setDisciplinafk(int disciplinafk){
        this.disciplinafk = disciplinafk;
    }

}