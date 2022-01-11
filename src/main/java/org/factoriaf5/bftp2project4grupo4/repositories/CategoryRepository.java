package org.factoriaf5.bftp2project4grupo4.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    public CategoryRepository() {
    }

    public List<Category> findAll() {
        return List.of(
                new Category("Simulation"),
                new Category("Role-Playing"),
                new Category("Sports"),
                new Category("Racing"),
                new Category("Misc"),
                new Category("Action"),
                new Category("Platform")
        );
    }
}

