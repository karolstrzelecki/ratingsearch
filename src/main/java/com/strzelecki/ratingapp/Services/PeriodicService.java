package com.strzelecki.ratingapp.Services;


import com.strzelecki.ratingapp.Model.Category;
import com.strzelecki.ratingapp.Model.Periodic;
import com.strzelecki.ratingapp.Model.PeriodicWithData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PeriodicService {

    List<Periodic> getByCategory(Set<Category> categories);

    List<PeriodicWithData> retrieved(List<Periodic> periodics);

    List<PeriodicWithData>example();

}
