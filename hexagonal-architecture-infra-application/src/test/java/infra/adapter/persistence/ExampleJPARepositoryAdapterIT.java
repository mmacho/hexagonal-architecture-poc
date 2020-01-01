package infra.adapter.persistence;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hexagonal.domain.example.Example;
import com.hexagonal.infra.DataSourceInitializerConfiguration;
import com.hexagonal.infra.DatabaseConfiguration;
import com.hexagonal.infra.RepositoryConfiguration.DataJPAConfiguration;
import com.hexagonal.infra.adapter.persistence.ExampleJPARepositoryAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:config/persistence.properties")
@ContextConfiguration(classes = {DatabaseConfiguration.class, DataJPAConfiguration.class})
public class ExampleJPARepositoryAdapterIT {

	@Autowired
	private ExampleJPARepositoryAdapter underTest;

	@Test
	public void findsExample() {
		Example expectedExample = new Example(1);

		Optional<Example> example = underTest.findById(1);

		assertEquals(Optional.of(expectedExample), example);
	}
	
    @Test
    public void notFindsExample() {
        Optional<Example> shirt = underTest.findById(1);

        assertThat(shirt, is(empty()));
    }
}
