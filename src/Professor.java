public class Professor {
    private int id_professor;
    private String nome_professor;
    private String titulacao;

    public Professor(int id_professor , String nome_professor , String titulacao){
        this.id_professor = id_professor;
        this.nome_professor = nome_professor;
        this.titulacao = titulacao;
    }

    public int getIdProfessor(){
        return id_professor;
    }

    public void setIdprofessor(int id_professor){
        this.id_professor = id_professor;
    }

    public String getNome_professor(){
        return nome_professor;
    }

    public void setNome_professor(String nome_professor){
        this.nome_professor = nome_professor;
    }

    public String getTitulacao(){
        return titulacao;
    }

    public void setTitulacao(String titulacao){
        this.titulacao = titulacao;
    }

    @Override
    public String toString(){ // mando um objeto em formato string para o user
        return "Professor: {" +
                "id: " + id_professor +
                " nome: "+ nome_professor +
                " titulacao: "+ titulacao +
                '}';
    }
}
