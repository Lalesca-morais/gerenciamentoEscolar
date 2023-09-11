import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import service.EscolaService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class EscolaServiceTest {
    @Mock
    private EscolaService escolaService;
    @Mock
    private Statement statement;

    @Before
    public void setUp() throws SQLException {
        statement = mock(Statement.class);
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);

        escolaService = new escolaService(connection);
    }

    @Test
    public void testConsultarAlunoPorCurso() throws SQLException {
        int idCurso = 1;
        when(statement.executeQuery(anyString())).thenReturn(mock(ResultSet.class));
        escolaService.consultarAlunoPorCurso(idCurso);

        verify(statement).executeQuery(anyString());
    }

    @Test
    public void testConsultarCursosPorProfessor() throws SQLException {
        int idProfessor = 1;
        when(statement.executeQuery(anyString())).thenReturn(mock(ResultSet.class));
        escolaService.consultarCursosPorProfessor(idProfessor);

        verify(statement).executeQuery(anyString());
    }

    @Test
    public void testConsultarAlunosSemMatricula() throws SQLException {
        when(statement.executeQuery(anyString())).thenReturn(mock(ResultSet.class));
        escolaService.consultarAlunosSemMatricula();

        verify(statement).executeQuery(anyString());
    }

    @Test
    public void testConsultarCursosSemAlunos() throws SQLException {
        when(statement.executeQuery(anyString())).thenReturn(mock(ResultSet.class));
        escolaService.consultarCursosSemAlunos();

        verify(statement).executeQuery(anyString());
    }

    @Test
    public void testConsultarAlunosComMaisDeUmCurso() throws SQLException {
        when(statement.executeQuery(anyString())).thenReturn(mock(ResultSet.class));
        escolaService.consultarAlunosComMaisDeUmCurso();

        verify(statement).executeQuery(anyString());
    }
}

