public class Disciplina {
    private int id_disciplina;
    private String Nome;
    private int CargaHoraria;
    private int prof_id;
    private int curso_id;

    public Disciplina(int id_disciplina , String Nome , int CargaHoraria , int prof_id , int curso_id){
        this.id_disciplina = id_disciplina;
        this.Nome = Nome;
        this.CargaHoraria = CargaHoraria;
        this.prof_id = prof_id;
        this.curso_id = curso_id;
    }

    public int getId_disciplina(){
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina){
        this.id_disciplina = id_disciplina;
    }

    public String getNome(){
        return Nome;
    }

    public void setNome(String Nome){
        this.Nome = Nome;
    }

    public int getCargaHoraria(){
        return CargaHoraria;
    }

    public void setCargaHoraria(int CargaHoraria){
        this.CargaHoraria = CargaHoraria;
    }

    public int getProf(){
        return prof_id;
    }

    public void setProf(int prof_id){
        this.prof_id = prof_id;
    }

    public int getCurso_id(){
        return curso_id;
    }

    public void setCurso_id(int curso_id){
        this.curso_id = curso_id;
    }

    @Override
    public String toString(){
        return "Disciplina{" +
                "id: " + id_disciplina +
                "nome: "+ Nome +
                "Carga-Horaria: " + CargaHoraria +
                "professor_id: " + prof_id +
                "Curso_id: " + curso_id +
                '}';
    }
}