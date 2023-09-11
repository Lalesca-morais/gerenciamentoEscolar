import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import service.InputService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class InputServiceTest {
    @Mock
    private InputService inputService;

    @Before
    public void setUp() {
        inputService = new InputService();
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
    }

    @Test
    public void testLerIntDoUsuario() {
        String input = "42\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int resultado = inputService.lerIntDoUsuario("Digite um número inteiro: ");

        assertEquals(42, resultado);
        systemOut.checkThat("Digite um número inteiro: ", equalTo(systemOut.getLog()));
    }

    @Test
    public void testLerStringDoUsuario() {
        String input = "Teste\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String resultado = inputService.lerStringDoUsuario("Digite uma string: ");

        assertEquals("Teste", resultado);
        systemOut.checkThat("Digite uma string: ", equalTo(systemOut.getLog()));
    }
}

