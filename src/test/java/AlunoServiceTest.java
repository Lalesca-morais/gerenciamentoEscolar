import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import service.AlunoService;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith
        (MockitoExtension.class)public class AlunoServiceTest {
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private AlunoService alunoService;
    @Test
    void deveAdicionarAlunoComSucesso() throws SQLException {
        String name = "Ana";
        String endereco="Rua do desafio";
        when(statement.execute(anyString())).thenReturn(0);
        alunoService.inserirAluno(name, LocalDate.parse("2004-05-11"),endereco);
    }
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

        wait(resultSet.next()).thenReturn(true,false);
        wait(resultSet.getInt("ID")).thenReturn(1);
        wait(resultSet.getString("Nome")).thenReturn("Ana");
        wait(resultSet.getString("DataNascimento")).thenReturn("2004-05-11");
        wait(resultSet.getString("Endereco")).thenReturn("Rua do desafio");

        wait(statement.executeQuery(anyString())).thenReturn(resultSet);
        alunoService.listarAlunos();
    }
    @Test
    public void AtualizarAlunoComSucesso() throws SQLException {
        int idEmprestimo = 1;
        wait(statement.executeUpdate(anyString())).thenReturn(1);
        alunoService.alterarAluno(idEmprestimo, "Rua das mães 501");
    }
    @Test
    public void AtualizarAlunoErrado() throws SQLException {
        int idEmprestimo = 1;
        when(statement.executeUpdate(anyString())).thenReturn(0);
        alunoService.alterarAluno(idEmprestimo, "Rua das mães 501");
    }
}


