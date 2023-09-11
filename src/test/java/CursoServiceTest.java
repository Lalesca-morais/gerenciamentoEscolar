
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import service.CursoService;
import service.InputService;
import service.ProfessorService;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.mockito.Mockito.*;

public class CursoServiceTest {
    @Mock
    private CursoService cursoService;
    @Mock
    private Statement statement;
    @Mock
    private InputService inputService;
    @Mock
    private ProfessorService professorService;

    @Before
    public void setUp() throws SQLException {
        statement = mock(Statement.class);
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        inputService = mock(InputService.class);
        professorService = mock(ProfessorService.class);
        cursoService = new CursoService(connection, inputService, professorService);
    }

    @Test
    public void testInserirCurso() throws SQLException {
        String nomeCurso = "Odontologia";
        int professorResponsavel = 1;
        when(statement.executeUpdate(anyString())).thenReturn(1);
        cursoService.inserirCurso(nomeCurso, professorResponsavel);

        verify(statement).executeUpdate(anyString());
    }

    @Test
    public void testDeletarCurso() throws SQLException {
        int idCurso = 1;
        when(statement.executeUpdate(anyString())).thenReturn(1);
        cursoService.deletarCurso(idCurso);

        verify(statement).executeUpdate(anyString());
    }

    @Test(expected = SQLException.class)
    public void testConsultarTodosCursos() throws SQLException {
        when(statement.executeQuery(anyString())).thenThrow(SQLException.class);

        cursoService.consultarTodosCursos();
    }
}

