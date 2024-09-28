import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Criar alunos
        Aluno aluno1 = new Aluno("Alice", 20, "Feminino", "alice@email.com", "A123", true);
        Aluno aluno2 = new Aluno("Bob",22,"Masculino","bob@email.com","B456", false);


        // Criar curso
        Curso curso = criarCurso("Ciência da Computação", "CC123", 8);
        curso.salvarDados("dados.dat");
        Curso cursoCarregado = new Curso().carregarDados("dados.dat");

        // Menu principal
        int opcao;
        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Adicionar Aluno");
            System.out.println("2. Remover Aluno");
            System.out.println("3. Lançar Nota");
            System.out.println("4. Exibir Informações do Curso");
            System.out.println("5. Mostrar Alunos no Banco de Dados");
            System.out.println("6. Mostrar Professores no Banco de Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("=== Adicionar Aluno ===");
                    Aluno novoAluno = criarNovoAluno(scanner);
                    curso.adicionarAluno(novoAluno);
                    break;
                case 2:
                    System.out.println("=== Remover Aluno ===");
                    removerAluno(scanner, curso);
                    break;
                case 3:
                    System.out.println("=== Lançar Nota ===");
                    lançarNota(scanner, curso);
                    break;
                case 4:
                    System.out.println("=== Exibir Informações do Curso ===");
                    exibirInformacoesCurso(curso);
                    break;
                case 5:
                    System.out.println("=== Mostrar Alunos no Banco de Dados ===");
                    exibirAlunos(curso);
                    break;
                case 6:
                    System.out.println("=== Mostrar Professores no Banco de Dados ===");
                    exibirProfessores(curso);
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }
    private static void removerAluno(Scanner scanner, Curso curso) {
        System.out.println("Informe a matrícula do aluno a ser removido:");
        String matricula = scanner.nextLine();
        Aluno alunoRemover = encontrarAlunoPorMatricula(curso, matricula);

        if (alunoRemover != null) {
            curso.removerAluno(alunoRemover);
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
    private static void lançarNota(Scanner scanner, Curso curso) {
        System.out.println("Informe a matrícula do aluno:");
        String matricula = scanner.nextLine();
        Aluno aluno = encontrarAlunoPorMatricula(curso, matricula);

        if (aluno != null) {
            System.out.println("Informe a nota para o aluno " + aluno.getNome() + ":");
            double nota = scanner.nextDouble();
            curso.lançarNota(aluno, nota);
            System.out.println("Nota lançada com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
    private static Aluno encontrarAlunoPorMatricula(Curso curso, String matricula) {
        for (Aluno aluno : curso.getAlunosInscritos()) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }


    private static Aluno criarNovoAluno(Scanner scanner) {
        System.out.println("Informe o nome do aluno:");
        String nome = scanner.nextLine();

        System.out.println("Informe a idade do aluno:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe o gênero do aluno:");
        String genero = scanner.nextLine();

        System.out.println("Informe o e-mail do aluno:");
        String email = scanner.nextLine();

        System.out.println("Informe a matrícula do aluno:");
        String matricula = scanner.nextLine();

        System.out.println("O aluno já pagou a mensalidade? (Digite 'true' se sim, 'false' se não):");
        boolean pagouMensalidade = scanner.nextBoolean();
        scanner.nextLine();


        return new Aluno(nome, idade, genero, email, matricula, pagouMensalidade);

    }

    private static Curso criarCurso(String nome, String codigo, int duracaoEmSemestres, Aluno... alunos) {
        Curso curso = new Curso(nome, codigo, duracaoEmSemestres);

        for (Aluno aluno : alunos) {
            curso.adicionarAluno(aluno);
        }


        Professor professor = criarProfessor();


        curso.adicionarProfessor(professor);

        return curso;
    }

    private static Professor criarProfessor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do professor:");
        String nome = scanner.nextLine();

        System.out.println("Informe a idade do professor:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe o gênero do professor:");
        String genero = scanner.nextLine();

        System.out.println("Informe o salário do professor:");
        float salario = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Informe o ID do professor:");
        String id = scanner.nextLine();

        System.out.println("Informe o e-mail do professor:");
        String email = scanner.nextLine();

        System.out.println("Informe a quantidade de turmas do professor:");
        int qtdTurmas = scanner.nextInt();
        scanner.nextLine();

        return new ProfessorConcreto(nome, idade, genero, salario, id, email, qtdTurmas);
    }


    private static void exibirInformacoesCurso(Curso curso) {
        if (curso != null) {
            System.out.println("Informações do Curso:");
            System.out.println(curso);
        } else {
            System.out.println("Curso não carregado.");
        }
    }
    private static void exibirAlunos(Curso curso) {
        System.out.println("Alunos no Banco de Dados:");
        for (Aluno aluno : curso.getAlunosInscritos()) {
            System.out.println(aluno.getNome() + " - Matrícula: " + aluno.getMatricula());
        }
    }

    private static void exibirProfessores(Curso curso) {
        System.out.println("Professores no Banco de Dados:");
        for (Professor professor : curso.getProfessoresInscritos()) {
            System.out.println(professor.getNome() + " - ID: " + professor.getId());
        }
    }

}
