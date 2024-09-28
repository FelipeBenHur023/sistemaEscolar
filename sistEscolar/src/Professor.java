import java.util.ArrayList;

public abstract class Professor extends Funcionario implements Avaliacoes, Notificacoes {
    private int qtdTurmas;

    public Professor(String nome, int idade, String genero, float salario, String id, String email, int qtdTurmas) {
        super(nome, idade, genero, salario, id, email);
        this.qtdTurmas = qtdTurmas;
    }
    private ArrayList<Double> avaliacoes;

    @java.lang.Override
    public double calcularMedia() throws AvaliacaoVaziaException {
        if (avaliacoes.isEmpty()) {
            throw new AvaliacaoVaziaException("Lista de avaliações se encontra vazia para o professor " +getNome());
        }

        double soma = 0;
        for (double avaliacao : avaliacoes) {
            soma += avaliacao;
        }

        return soma / avaliacoes.size();
    }



    @java.lang.Override
    public void notificar(String mensagem) {

    }
}
