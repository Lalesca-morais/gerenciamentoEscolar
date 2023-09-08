package service;

import connection.Conexao;
import model.CursoModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static connection.Conexao.fazerConexao;

public class CursoService {
    private Statement statement;
    private Conexao conexao;
    ProfessorService professorService = new ProfessorService();
    CursoModel cursoModel = new CursoModel();
    InputService inputService = new InputService();
    public CursoService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirCurso(String nomeCurso, int professorResponsavel) {
        String sql = "INSERT INTO cursos (NomeCurso, ProfessorResponsavel) VALUES ('" +
                nomeCurso + "', " + professorResponsavel + ")";
        try {
            statement.executeUpdate(sql);
            System.out.println("Curso '" + nomeCurso + "' foi adicionado com sucesso no banco!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarTodosCursos() {
        String sql = "SELECT cursos.ID, Cursos.NomeCurso, Professores.Nome AS NomeProfessor " +
                "FROM Cursos " +
                "INNER JOIN Professores ON Cursos.ProfessorResponsavel = Professores.ID";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID do Curso: " + resultSet.getInt("ID") +
                        " | Nome do Curso: " + resultSet.getString("NomeCurso") +
                        " | Professor Responsável: " + resultSet.getString("NomeProfessor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterarCurso(int id, String nomeCurso) {
        String sql = "UPDATE cursos SET NomeCurso = '" + nomeCurso + "' WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Curso com ID " + id + " foi alterado com sucesso.");
            } else {
                System.out.println("Curso com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarCurso(int id) {
        String sql = "DELETE FROM cursos WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Curso com ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Curso com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void adicionarCurso() {
        professorService.consultarTodosProfessores();
        String nome = inputService.lerStringDoUsuario("Digite o nome do Curso: ");
        int idProf = inputService.lerIntDoUsuario("Digite o id do professor desse curso");
        cursoModel.setNomeCurso(nome);
        inserirCurso(cursoModel.getNomeCurso(), idProf);
    }
    public void deletarCursoComInput() {
        consultarTodosCursos();
        int id = inputService.lerIntDoUsuario("Digite o ID do Curso que deseja deletar: ");
        deletarCurso(id);
    }
    public void atualizarCurso() {
        int id = inputService.lerIntDoUsuario("Digite o nome do curso que deseja alterar: ");
        String novoNomeDisciplina = inputService.lerStringDoUsuario("Digite o novo nome da disciplina: ");
        cursoModel.setNomeCurso(novoNomeDisciplina);
        alterarCurso(id, novoNomeDisciplina);
    }
}
