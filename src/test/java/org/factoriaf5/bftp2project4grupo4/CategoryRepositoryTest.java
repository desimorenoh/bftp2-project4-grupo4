package org.factoriaf5.bftp2project4grupo4;


import org.factoriaf5.bftp2project4grupo4.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CategoryRepositoryTest {
    @Test
    void providesTheValidCategories() {
        CategoryRepository categoryRepository = new CategoryRepository();

        assertThat(categoryRepository.findAll(), hasItems(
                hasProperty("name", is("Simulation")),
                hasProperty("name", is("Role-Playing")),
                hasProperty("name", is("Sports")),
                hasProperty("name", is("Racing")),
                hasProperty("name", is("Misc")),
                hasProperty("name", is("Action")),
                hasProperty("name", is("Platform"))
        ));
    }
}
