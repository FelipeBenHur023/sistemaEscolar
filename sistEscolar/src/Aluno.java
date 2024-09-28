import java.util.ArrayList;

public class Aluno extends Pessoa implements Avaliacoes, Pagamentos, Notificacoes{
    private String email;
    private String matricula;
    private boolean pagouMensalidade;

    public Aluno(String nome, int idade, String genero, String email, String matricula, boolean pagouMensalidade) {
        super(nome, idade, genero);
        this.email = email;
        this.matricula = matricula;
        this.pagouMensalidade = pagouMensalidade;
    }

    public boolean isPagouMensalidade() {
        return pagouMensalidade;
    }

    public void setPagouMensalidade(boolean pagouMensalidade) {
        this.pagouMensalidade = pagouMensalidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }


    private ArrayList<Double> notas;

    public Aluno(String nome, int idade, String genero, ArrayList<Double> notas) {
        super(nome, idade, genero);
        this.notas = notas;
    }

    @java.lang.Override
    public double calcularMedia() throws AvaliacaoVaziaException{
        if (notas.isEmpty()) {
            throw new AvaliacaoVaziaException("Lista de notas se encontra vazia para o aluno " +getNome());
        }

        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.size();
    }

    @java.lang.Override
    public void notificar(String mensagem) {
        System.out.println("Notificação enviada para o aluno " + getNome() + " via email: " + email);
        System.out.println("Mensagem: " + mensagem);
    }
    @java.lang.Override
    public void pagarMensalidade() throws PagamentoException {
        boolean pagamentoBemSucedido = verificarPagamento();

        if (!pagamentoBemSucedido) {
            throw new PagamentoException("Falha ao processar o pagamento para o aluno " + getNome());
        }

        System.out.println("Mensalidade paga pelo aluno: " + getNome());
    }

    private boolean verificarPagamento() {


        boolean pagamentoBemSucedido = false;

        if (!pagamentoBemSucedido) {
            System.out.println("Falha na verificação de pagamento para o aluno " + getNome());
        }

        return pagamentoBemSucedido;
    }

}
