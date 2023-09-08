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
                "INNER JOIN Matriculas ON alunos.ID = Matriculas.IDaluno " +
                "INNER JOIN Cursos ON Matriculas.IDCurso = Cursos.ID " +
                "WHERE Cursos.ID = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("=====ALUNOS MATRICULADOS NO CURSO=====\n");
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
            while (resultSet.next()) {
                System.out.println("=====DISCIPLINAS LECIONADAS POR ESSE PROFESSOR=====\n");
                System.out.println("Nome do Curso: " + resultSet.getString("NomeCurso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarAlunosSemMatricula() {
        String sql = "SELECT alunos.Nome " +
                "FROM alunos " +
                "LEFT JOIN Matriculas ON alunos.ID = Matriculas.IDaluno " +
                "WHERE matriculas.IDCurso IS NULL";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("=====ALUNOS SEM MATRICULA EM NENHUM CURSO=====\n");
                System.out.println("Nome do Aluno: " + resultSet.getString("Nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarCursosSemAlunos() {
        String sql = "SELECT cursos.NomeCurso " +
                "FROM cursos " +
                "LEFT JOIN matriculas ON Cursos.ID = matriculas.IDCurso " +
                "WHERE matriculas.IDaluno IS NULL";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("=====CURSOS SEM MATRICULA DE ALUNOS=====");
                System.out.println("Nome do Curso: " + resultSet.getString("NomeCurso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarAlunosComMaisDeUmCurso() {
        String sql = "SELECT alunos.Nome " +
                "FROM alunos " +
                "INNER JOIN Matriculas ON Alunos.ID = Matriculas.IDAluno " +
                "GROUP BY Alunos.ID, Alunos.Nome " +
                "HAVING COUNT(Matriculas.IDCurso) > 1";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("=====ALUNOS MATRICULADOS EM MAIS DE UM CURSO=====\n");
                System.out.println("Nome do Aluno: " + resultSet.getString("Nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
