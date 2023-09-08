package service;

import model.MatriculaModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static connection.Conexao.fazerConexao;

public class MatriculaService {
    private Statement statement;
    MatriculaModel matriculaModel = new MatriculaModel();
    AlunoService alunoService=new AlunoService();
    CursoService cursoService=new CursoService();
    InputService inputService =new InputService();
    public MatriculaService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirMatricula(int idAluno, int idCurso, LocalDate dataMatricula) {
        String sql = "INSERT INTO matriculas (IDAluno, IDCurso, DataMatricula) VALUES (" +
                idAluno + ", " + idCurso + ", '" + dataMatricula + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Matrícula do Aluno ID " + idAluno +
                    " no Curso ID " + idCurso + " foi adicionada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarTodasMatriculas() {
        String sql = "SELECT matriculas.ID, alunos.Nome AS NomeAluno, cursos.NomeCurso" +
                " AS NomeCurso, matriculas.DataMatricula " +
                "FROM matriculas " +
                "INNER JOIN alunos ON matriculas.IDAluno = Alunos.ID " +
                "INNER JOIN cursos ON matriculas.IDCurso = Cursos.ID";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID da Matrícula: " + resultSet.getInt("ID") +
                        " | Nome do Aluno: " + resultSet.getString("NomeAluno") +
                        " | Nome do Curso: " + resultSet.getString("NomeCurso") +
                        " | Data de Matrícula: " + resultSet.getString("DataMatricula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarMatricula(int id) {
        String sql = "DELETE FROM matriculas WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Matrícula com ID " + id + " deletada com sucesso.");
            } else {
                System.out.println("Matrícula com ID " + id + " não encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void adicionarMatricula() {
        System.out.println("Alunos: ");
        alunoService.listarAlunos();
        System.out.println("\nCursos");
        cursoService.consultarTodosCursos();
        int idAluno=inputService.lerIntDoUsuario("Qual o id do aluno");
        int idCurso=inputService.lerIntDoUsuario("Qual o id do curso");
        matriculaModel.setDataMatricula(LocalDate.now());
        inserirMatricula(idAluno,idCurso,matriculaModel.getDataMatricula());
    }
    public void deletarMatriculaComInput() {
        consultarTodasMatriculas();
        int id = inputService.lerIntDoUsuario("Qual o ID da Matricula que deseja deletar: ");
        deletarMatricula(id);
    }
}
