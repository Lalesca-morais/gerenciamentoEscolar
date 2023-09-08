package service;

import connection.Conexao;
import model.AlunosModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static connection.Conexao.fazerConexao;

public class AlunoService {
    private Statement statement;
    private Conexao conexao;
    AlunosModel alunosModel= new AlunosModel();
    InputService inputService = new InputService();
    CursoService cursoService = new CursoService();
    EscolaService escolaService = new EscolaService();
    public AlunoService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirAluno(String nome, LocalDate dataNascimento, String endereco) {
        String sql = "INSERT INTO alunos (Nome, DataNascimento, Endereco) VALUES ('" +
                nome + "', '" + dataNascimento + "', '" + endereco + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Aluno '" + nome + "' foi adicionado com sucesso no banco!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarAlunos() {
        String sql = "SELECT * FROM alunos";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("ID") +
                        " | Nome: " + resultSet.getString("Nome") +
                        " | Data de Nascimento: " + resultSet.getString("DataNascimento") +
                        " | Endereço: " + resultSet.getString("Endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterarAluno(int id, String endereco) {
        String sql = "UPDATE alunos SET Endereco = '" + endereco + "' WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Aluno com ID " + id + " foi alterado com sucesso.");
            } else {
                System.out.println("Aluno com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarAluno(int id) {
        String sql = "DELETE FROM alunos WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Aluno com ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Aluno com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void adicionarAluno() {
        String nome = inputService.lerStringDoUsuario("Digite o nome do aluno: ");
        String dataNascimento = inputService.lerStringDoUsuario("Digite a data de nascimento do aluno (yyyy-MM-dd): ");
        String endereco = inputService.lerStringDoUsuario("Digite o endereço do aluno: ");
        alunosModel.setNome(nome);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataNascimento, formatter);
        alunosModel.setDataNascimento(data);
        alunosModel.setEndereco(endereco);
        inserirAluno(alunosModel.getNome(),alunosModel.getDataNascimento(),alunosModel.getEndereco());
    }
    public void deletarAlunoComInput() {
        listarAlunos();
        int id = inputService.lerIntDoUsuario("Digite o ID do aluno que deseja deletar: ");
        deletarAluno(id);
    }
    public void atualizarAluno() {
       listarAlunos();
        int id = inputService.lerIntDoUsuario("Qual o ID do Aluno que deseja alterar: ");
        String endereco = inputService.lerStringDoUsuario("Qual o novo endereço: ");
        alunosModel.setEndereco(endereco);
        alterarAluno(id,alunosModel.getEndereco());
    }
    public void consultarAlunosPorCurso() {
        cursoService.consultarTodosCursos();
        int id=inputService.lerIntDoUsuario("Digite o id do curso");
        escolaService.consultarAlunoPorCurso(id);
    }
}
