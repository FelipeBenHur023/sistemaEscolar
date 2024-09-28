import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
    public class Curso implements Serializable  {
        private String nome;
        private String codigo;
        private int duracaoEmSemestres;
        private ArrayList<Aluno> alunosInscritos;
        private HashMap<String, Double> notasCurso;
        private ArrayList<Professor> professoresInscritos;



        public Curso() {
            this.alunosInscritos = new ArrayList<>();
        }

        public Curso(String nome, String codigo, int duracaoEmSemestres) {
            this();
            this.nome = nome;
            this.codigo = codigo;
            this.duracaoEmSemestres = duracaoEmSemestres;
            this.alunosInscritos = new ArrayList<>();
            this.notasCurso = new HashMap<>();
            this.professoresInscritos = new ArrayList<>();
        }



        public void adicionarAluno(Aluno aluno) {
            alunosInscritos.add(aluno);
        }

        public ArrayList<Aluno> getAlunosInscritos() {
            return alunosInscritos;
        }

        public void setAlunosInscritos(ArrayList<Aluno> alunosInscritos) {
            this.alunosInscritos = alunosInscritos;
        }

        public void removerAluno(Aluno aluno) {
            alunosInscritos.remove(aluno);
        }


        public void lançarNota(Aluno aluno, double nota) {
            notasCurso.put(aluno.getMatricula(), nota);
        }

        public double obterMediaAluno(Aluno aluno) {
            return notasCurso.getOrDefault(aluno.getMatricula(), 0.0);
        }

        public void salvarDados(String nomeArquivo) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
                writer.println(nome + "," + codigo + "," + duracaoEmSemestres);

                for (Aluno aluno : alunosInscritos) {
                    writer.println(aluno.getNome() + "," + aluno.getIdade() + "," + aluno.getGenero() + ","
                            + aluno.getEmail() + "," + aluno.getMatricula() + "," +aluno.isPagouMensalidade());
                }

                notasCurso.forEach((matricula, nota) -> writer.println(matricula + "," + nota));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public  Curso carregarDados(String nomeArquivo) {
            try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
                String linha;

                if ((linha = reader.readLine()) != null) {
                    String[] infoCurso = linha.split(",");
                    nome = infoCurso[0];
                    codigo = infoCurso[1];
                    duracaoEmSemestres = Integer.parseInt(infoCurso[2]);
                }

                while ((linha = reader.readLine()) != null) {
                    String[] infoAluno = linha.split(",");
                    boolean pagouMensalidade = Boolean.parseBoolean(infoAluno[5].trim());
                    Aluno aluno = new Aluno(infoAluno[0], Integer.parseInt(infoAluno[1]),
                            infoAluno[2], infoAluno[3], infoAluno[4], pagouMensalidade);
                    adicionarAluno(aluno);
                }

                while ((linha = reader.readLine()) != null) {
                    String[] infoNota = linha.split(",");
                    notasCurso.put(infoNota[0], Double.parseDouble(infoNota[1]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        public String toString() {
            return "Curso: " + nome + ", Código: " + codigo + ", Duração em semestres: " +
                    duracaoEmSemestres + ", Alunos Inscritos: " + alunosInscritos.size();
        }



        public void adicionarProfessor(Professor professor) {
            professoresInscritos.add(professor);
        }

        public ArrayList<Professor> getProfessoresInscritos() {
            return professoresInscritos;
        }

        public void setProfessoresInscritos(ArrayList<Professor> professoresInscritos) {
            this.professoresInscritos = professoresInscritos;
        }

    }


