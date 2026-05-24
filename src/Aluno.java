public class Aluno {
    private int IdAluno;
    private String NomeAluno;
    private String Email;
    private int Idade;

    public Aluno(int IdAluno , String NomeAluno , String Email , int Idade){
        this.IdAluno = IdAluno;
        this.NomeAluno = NomeAluno;
        this.Email = Email;
        this.Idade = Idade;
    }

    public int getIdAluno(){
        return IdAluno;
    }

    public void setIdAluno(int IdAluno){
        this.IdAluno = IdAluno;
    }

    public String getNomeAluno(){
        return NomeAluno;
    }

    public void setNomeAluno(String NomeAluno){
        this.NomeAluno = NomeAluno;
    }

    public String getEmail(){
        return Email;
    }

    public void setEmail(){
        this.Email = Email;
    }

    public int getIdade(){
        return Idade;
    }

    public void setIdade(int Idade){
        this.Idade = Idade;
    }

    @Override
    public String toString(){
        return "Aluno{" +
                "id = "+ IdAluno +
                ", Nome: "+ NomeAluno +
                " , email: "+ Email +
                " , Idade: " + Idade +
                '}';
    }
}
