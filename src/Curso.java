public class Curso {
    private int id_curso;
    private String NomeCurso;
    private int Duracao;

    public Curso(int id_curso , String NomeCurso , int Duracao){
        this.id_curso = id_curso;
        this.NomeCurso = NomeCurso;
        this.Duracao = Duracao;
    }

    public int getId_curso(){
        return id_curso;
    }

    public void setId_curso(int id_curso){
        this.id_curso = id_curso;
    }

    public String getNome(){
        return NomeCurso;
    }

    public void setNome(String NomeCurso){
        this.NomeCurso = NomeCurso;
    }

    public int getDuracao(){
        return Duracao;
    }

    public void setDuracao(int Duracao){
        this.Duracao = Duracao;
    }

    @Override
    public String toString(){
        return "Curso {" +
                "id: " + id_curso +
                " nome: " + NomeCurso +
                " Duração: " + Duracao +
                '}';
    }

}
