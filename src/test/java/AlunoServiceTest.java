import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.AlunoService;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private AlunoService alunoService;

    @Test
    void deveAdicionarAlunoComSucesso() throws SQLException {
        String name = "Marina";
        String endereco = "Rua quinze";
        when(statement.execute(anyString())).thenReturn(false);
        alunoService.inserirAluno(name, LocalDate.parse("2000-10-07"), endereco);
    }

    @Test
    void deletarAlunoComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        alunoService.deletarAluno(1);
    }

    @Test
    void deletarAlunoComIdErrado() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        alunoService.deletarAluno(2);
    }

    @Test
    void listarAlunosComSucesso() throws SQLException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("Nome")).thenReturn("Ana");
        when(resultSet.getString("DataNascimento")).thenReturn("1999-07-10");
        when(resultSet.getString("Endereco")).thenReturn("Rua do desafio");

        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        alunoService.listarAlunos();
    }

    @Test
    public void AtualizarAlunoComSucesso() throws SQLException {
        int idEmprestimo = 1;
        when(statement.executeUpdate(anyString())).thenReturn(1);
        alunoService.alterarAluno(idEmprestimo, "Rua dez, 384");
    }

    @Test
    public void AtualizarAlunoComIdErrado() throws SQLException {
        int idEmprestimo = 2;
        when(statement.executeUpdate(anyString())).thenReturn(0);
        alunoService.alterarAluno(idEmprestimo, "Rua oito, 409");
    }
}


