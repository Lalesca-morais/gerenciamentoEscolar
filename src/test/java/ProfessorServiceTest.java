import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.EscolaService;
import service.InputService;
import service.ProfessorService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.mockito.Mockito.*;

public class ProfessorServiceTest {
    @Mock
    public ProfessorService professorService;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        InputService inputService = mock(InputService.class);
        EscolaService escolaService = mock(EscolaService.class);

        professorService = new ProfessorService(connection, inputService, escolaService);
    }

    @Test
    public void testInserirProfessor() throws SQLException {
        String nome = "Professor Teste";
        String disciplina = "Matemática";
        when(statement.executeUpdate(anyString())).thenReturn(1);
        professorService.inserirProfessor(nome, disciplina);

        verify(statement).executeUpdate(anyString());
    }

    @Test
    public void testConsultarTodosProfessores() throws SQLException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("Nome")).thenReturn("Professor Teste");
        when(resultSet.getString("Disciplina")).thenReturn("Matemática");
        professorService.consultarTodosProfessores();

        verify(statement).executeQuery(anyString());
    }

    @Test
    public void testAlterarProfessor() throws SQLException {
        int idProfessor = 1;
        String novaDisciplina = "Física";
        when(statement.executeUpdate(anyString())).thenReturn(1);
        professorService.alterarProfessor(idProfessor, novaDisciplina);

        verify(statement).executeUpdate(anyString());
    }

    @Test
    public void testDeletarProfessor() throws SQLException {
        int idProfessor = 1;
        when(statement.executeUpdate(anyString())).thenReturn(1);
        professorService.deletarProfessor(idProfessor);

        verify(statement).executeUpdate(anyString());
    }
}

