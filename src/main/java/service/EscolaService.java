package service;

import connection.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static connection.Conexao.fazerConexao;

public class EscolaService {
    private Statement statement;
    private Conexao conexao;
    public EscolaService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarAlunoPorCurso(int id) {
        String sql = "SELECT alunos.Nome " +
                "FROM alunos " +
                "INNER JOIN Matriculas ON alunos.ID = Matriculas.ID_aluno " +
                "INNER JOIN Cursos ON Matriculas.ID_Curso = Cursos.ID " +
                "WHERE Cursos.ID = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("=====ALUNOS MATRICULADOS NO CURSO=====\n");
            while (resultSet.next()) {
                System.out.println("Nome: " + resultSet.getString("Nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarCursosPorProfessor(int id) {
        String sql = "SELECT cursos.NomeCurso " +
                "FROM cursos " +
                "INNER JOIN professores ON cursos.ProfessorResponsavel = professores.ID " +
                "WHERE Professores.ID = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("=====DISCIPLINAS LECIONADAS POR ESSE PROFESSOR=====\n");
            while (resultSet.next()) {
                System.out.println("Nome do Curso: " + resultSet.getString("NomeCurso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarAlunosSemMatricula() {
        String sql = "SELECT alunos.Nome " +
                "FROM alunos " +
                "LEFT JOIN Matriculas ON alunos.ID = Matriculas.ID_Aluno " +
                "WHERE matriculas.ID_Curso IS NULL";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("=====ALUNOS SEM MATRÍCULA EM NENHUM CURSO=====\n");
            while (resultSet.next()) {
                System.out.println("Nome do Aluno: " + resultSet.getString("Nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarCursosSemAlunos() {
        String sql = "SELECT cursos.NomeCurso " +
                "FROM cursos " +
                "LEFT JOIN matriculas ON cursos.ID = matriculas.ID_Curso " +
                "WHERE matriculas.ID_Aluno IS NULL";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("=====CURSOS SEM MATRÍCULA DE ALUNOS=====\n");
            while (resultSet.next()) {
                System.out.println("Nome do Curso: " + resultSet.getString("NomeCurso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarAlunosComMaisDeUmCurso() {
        String sql = "SELECT alunos.Nome " +
                "FROM alunos " +
                "INNER JOIN Matriculas ON alunos.ID = Matriculas.ID_Aluno " +
                "GROUP BY alunos.ID, alunos.Nome " +
                "HAVING COUNT(Matriculas.ID_Curso) > 1";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            boolean alunosEncontrados = false;

            System.out.println("=====ALUNOS MATRICULADOS EM MAIS DE UM CURSO=====\n");
            while (resultSet.next()) {
                alunosEncontrados = true;
                System.out.println("Nome do Aluno: " + resultSet.getString("Nome"));
            }
            if (!alunosEncontrados) {
                System.out.println("Por enquanto não há nenhum aluno cadastrado em mais de um curso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
