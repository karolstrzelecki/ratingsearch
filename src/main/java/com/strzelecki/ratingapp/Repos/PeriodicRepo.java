package com.strzelecki.ratingapp.Repos;


import com.strzelecki.ratingapp.Model.Category;
import com.strzelecki.ratingapp.Model.Periodic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PeriodicRepo extends JpaRepository<Periodic, Long> {

    Optional<List<Periodic>> findAllByCategoriesIn(Set<Category> categories);



}
