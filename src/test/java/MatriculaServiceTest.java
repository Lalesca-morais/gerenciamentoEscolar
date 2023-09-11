import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.AlunoService;
import service.CursoService;
import service.InputService;
import service.MatriculaService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

public class MatriculaServiceTest {
    @Mock
    public MatriculaService matriculaService;
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
        AlunoService alunoService = mock(AlunoService.class);
        CursoService cursoService = mock(CursoService.class);

        matriculaService = new MatriculaService(connection, inputService, alunoService, cursoService);
    }

    @Test
    public void testInserirMatricula() throws SQLException {
        int idAluno = 1;
        int idCurso = 2;
        LocalDate dataMatricula = LocalDate.now();
        when(statement.executeUpdate(anyString())).thenReturn(1);
        matriculaService.inserirMatricula(idAluno, idCurso, dataMatricula);

        verify(statement).executeUpdate(anyString());
    }

    @Test
    public void testConsultarTodasMatriculas() throws SQLException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("NomeAluno")).thenReturn("João");
        when(resultSet.getString("NomeCurso")).thenReturn("Matemática");
        when(resultSet.getString("DataMatricula")).thenReturn(LocalDate.now().toString());

        matriculaService.consultarTodasMatriculas();

        verify(statement).executeQuery(anyString());
    }

    @Test
    public void testDeletarMatricula() throws SQLException {
        int idMatricula = 1;
        when(statement.executeUpdate(anyString())).thenReturn(1);

        matriculaService.deletarMatricula(idMatricula);

        verify(statement).executeUpdate(anyString());
    }
}

