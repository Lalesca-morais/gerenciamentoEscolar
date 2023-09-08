import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import service.AlunoService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)public class AlunoServiceTest {
    @Mock    private Statement statement;
    @Mock    private ResultSet resultSet;
    @InjectMocks    private AlunoService alunoService;
    @Test    void deveAdicionarAlunoComSucesso() throws SQLException {
        String name = "Ana";
        String endereco="Rua do desafio";
        when(statement.executeUpdate(anyString())).thenReturn(0);
        alunoService.inserirAluno(name, LocalDate.parse("2004-05-11"),endereco);}
    @Test
    void deletarAlunoComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        alunoService.deletarAluno(1);
    }    @Test
    void deletarAutorComIdErrado() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        alunoService.deletarAluno(1);
    }    @Test
    void listarAlunosComSucesso() throws SQLException {
        // Configure o comportamento do resultSet
        // when(resultSet.next()).thenReturn(true,false);
        // when(resultSet.getInt("ID")).thenReturn(1);
        // when(resultSet.getString("Nome")).thenReturn("Ana");
        // when(resultSet.getString("DataNascimento")).thenReturn("2004-05-11");
        // when(resultSet.getString("Endereco")).thenReturn("Rua do desafio");
        // Configure o statement para retornar o resultSet mockado
        // when(statement.executeQuery(anyString())).thenReturn(resultSet);
        // Chama o método listarAutores
        // alunoService.listarAlunos();
        // }    @Test
        // public void AtualizarAlunoComSucesso() throws SQLException {
        // int idEmprestimo = 1;
        // when(statement.executeUpdate(anyString())).thenReturn(1);
        // alunoService.alterarAluno(idEmprestimo, "Rua das mães 501");
        // }    @Test    public void AtualizarAlunoErrado() throws SQLException {
        // int idEmprestimo = 1;
        // when(statement.executeUpdate(anyString())).thenReturn(0);
        // alunoService.alterarAluno(idEmprestimo, "Rua das mães 501");
        // }
    }


