package com.strzelecki.ratingapp.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Periodic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    private String title;
    private String issn;
    private String eIssn;
    private String alternativeTitle;
    private String alternativeIssn;
    private String alternativeEIssn;
    private Long credit;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Category", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Category> categories;









}
