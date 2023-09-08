package service;

import model.ProfessorModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static connection.Conexao.fazerConexao;

public class ProfessorService {
    private Statement statement;
    ProfessorModel professorModel = new ProfessorModel();
    InputService inputService = new InputService();
    EscolaService escolaService = new EscolaService();
    public ProfessorService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirProfessor(String nome, String disciplina) {
        String sql = "INSERT INTO professores (Nome, Disciplina) VALUES ('" +
                nome + "', '" + disciplina + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Professor '" + nome + "' adicionado com sucesso no banco!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarTodosProfessores() {
        String sql = "SELECT * FROM professores";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("ID") +
                        " | Nome: " + resultSet.getString("Nome") +
                        " | Disciplina: " + resultSet.getString("Disciplina"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterarProfessor(int id, String disciplina) {
        String sql = "UPDATE professores SET Disciplina = '" + disciplina +
                "' WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Professor com ID " + id + " alterado com sucesso.");
            } else {
                System.out.println("Professor com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarProfessor(int id) {
        String sql = "DELETE FROM professores WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Professor com ID " + id + " deletado com sucesso.");
            } else {
                System.out.println("Professor com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void adicionarProfessor() {
        String nome = inputService.lerStringDoUsuario("Digite o nome do Professor: ");
        String disciplina = inputService.lerStringDoUsuario("Digite a diciplina desse professor: ");
        professorModel.setNomeProf(nome);
        professorModel.setDisciplina(disciplina);
       inserirProfessor(professorModel.getNomeProf(),professorModel.getDisciplina());
    }
    public void deletarProfessor() {
        consultarTodosProfessores();
        int id = inputService.lerIntDoUsuario("Qual o ID do Aluno que deseja deletar: ");
        deletarProfessor(id);
    }
    public void alterarInformacoes() {
        consultarTodosProfessores();
        int id = inputService.lerIntDoUsuario("Digite o ID do professor que deseja alterar: ");
        String disciplina = inputService.lerStringDoUsuario("Digite a novo disciplina: ");
        professorModel.setDisciplina(disciplina);
       alterarProfessor(id,professorModel.getDisciplina());
    }
    public void consultarCursoPorProfessor() {
        consultarTodosProfessores();
        int id=inputService.lerIntDoUsuario("Qual o id do professor");
        escolaService.consultarCursosPorProfessor(id);
    }
}
