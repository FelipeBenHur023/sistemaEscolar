public abstract class Funcionario extends Pessoa {
    private float salario;
    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Funcionario(String nome, int idade, String genero, float salario, String id, String email) {
        super(nome, idade, genero);
        this.salario = salario;
        this.id = id;
        this.email = email;
    }
}
