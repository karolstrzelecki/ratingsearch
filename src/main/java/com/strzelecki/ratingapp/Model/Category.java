package com.strzelecki.ratingapp.Model;

import java.util.HashMap;
import java.util.Map;

public enum Category {

    archeo("Archeologia"),
    philosophy("Filozofia"),
    historia("Historia"),
    linguistics("Językoznawstwo"),
    literaryStudies("Literaturoznawstwo"),
    cultureAndReligion("Nauki o kulturze i religii"),
    arts("Nauki o sztuce"),
    architectureAndUrbanPlanning("Architektura i Urbanistyka"),
    automatics("Automatyka, Elektronika i Elektrotechnika"),
    informatics("Informatyka techniczna i Telekomunikacja"),
    biomedicEngineering("Inżynieria Biomedyczna"),
    chemicalEngineering("Inżynieria Chemiczna"),
    transportation("Inżynieria lądowa i Transport"),
    materialScience("Inzynieria Materiałowa"),
    mechanics("inżynieria mechaniczna"),
    environmentalMiningAndEnergetics("Inżynieria Środowiska, Górnictwo i Energetyka"),
    pharmaceuticalStudies("Nauki farmaceutyczne"),
    medicalScience("Nauki Medyczne"),
    physicalCultureStudies("Nauki o kulturze fizycznej"),
    healthScience("Nauki o zdrowiu"),
    forestStudies("Nauki leśne"),
    agricultureAndGardening("Rolnictwo i Ogrodnictwo"),
    foodTechnology("Technologia żywności i żywienia"),
    veterinary("Weterynaria"),
    zootechnics("Zootechnika i Rybactwo"),
    economics("Ekonomia i Finanse"),
    geographyEconomicsSocial("Geografia społeczno-ekonomiczna"),
    securityStudies("nauki o bezpieczeństwi"),
    mediaAndSocialCommunicationStudies("Nauki o komunikacji społecznej i mediach"),
    politicsAndAdministration("Nauki o polityce i administracji"),
    managementAndQualityStudies("Nauki o Zarządzaniu i Jakości"),
    legalStudies("Nauki Prawne"),
    sociologicalStudies("Nauki Socjologiczne"),
    pedagogics("Pedagogika"),
    canonLaw("Prawo Kanoniczne"),
    psychology("Psychologia"),
    astronomy("Astronomia"),
    computerScience("Informatyka"),
    mathematics("Matematyka"),
    biologicalScience("Nauki Biologiczne"),
    chemicalScience("Nauki Chemiczne"),
    physicalStudies("Nauki Fizyczne"),
    earthAndEnvironmentStudies("Nauki o Ziemi i Środowisku"),
    theologicalStudies("Nauki Teologiczne");

    public final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, Category> lookup = new HashMap<>();

    static {
        for(Category p : Category.values()){
            lookup.put(p.getName(), p);
        }
    }
    public static Category get(String str){
        return lookup.get(str);
    }

}


