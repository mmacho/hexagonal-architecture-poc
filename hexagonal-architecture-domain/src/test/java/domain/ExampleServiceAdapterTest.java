package domain;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hexagonal.domain.example.Example;
import com.hexagonal.domain.example.ExamplePortRepository;
import com.hexagonal.domain.example.ExampleServiceAdapter;

@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceAdapterTest {

	private static final int ANY_EXAMPLE_ID = 1;
    
	@Mock
	ExamplePortRepository repo;

	@InjectMocks
	ExampleServiceAdapter underTest;

	@Test
	public void findExample() {
		Example exampleSheet = new Example(ANY_EXAMPLE_ID);
        when(repo.findById(ANY_EXAMPLE_ID)).thenReturn(Optional.of(exampleSheet));

        Optional<Example> sheet = underTest.findById(ANY_EXAMPLE_ID);

        assertEquals(Optional.of(exampleSheet), sheet);
	}
	
    @Test
    public void doesntExample() {
        when(repo.findById(ANY_EXAMPLE_ID)).thenReturn(empty());

        Optional<Example> sheet = underTest.findById(ANY_EXAMPLE_ID);

        assertThat(sheet, is(empty()));
    }
}
