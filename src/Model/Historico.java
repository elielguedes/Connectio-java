package Model;

public class Historico {
    private int id;
    private float nota;
    private int frequencia;
    private int matricula_id;

    public Historico(int id , float nota , int frequencia , int matricula_id){
        this.id = id;
        this.nota = nota;
        this.frequencia = frequencia;
        this.matricula_id = matricula_id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public float getNota(){
        return nota;
    }

    public void setNota(float nota){
        this.nota = nota;
    }

    public int getFrequencia(){
        return frequencia;
    }

    public void setFrequencia(int frequencia){
        this.frequencia = frequencia;
    }

    public int getMatricula(){
        return matricula_id;
    }

    public void setMatricula(int matricula_id){
        this.matricula_id = matricula_id;
    }

    @Override
    public String toString(){
        return "Historico{" +
                "id: "+ getId() +
                " nota: " + getNota() +
                " frequencia: "+ getFrequencia() +
                " Matricula: " + getMatricula() +
                " }";
    }

}
