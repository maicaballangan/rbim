/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author Maica Ballangan
 * @since v1
 */
@With(Secure.class)
public class Reports extends Controller {

    public static void list() {
        render();
    }

    public static void populationByAge() {
        List<PopulationByAge> records = PopulationByAge.getReport();
        int total = records.stream().mapToInt(PopulationByAge::getCount).sum();
        for (PopulationByAge record: records) {
            record.setPercent(BigDecimal.valueOf(((double)record.getCount()/total)*100d).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        }
        records.add(new PopulationByAge("TOTAL", total, 100.0d));
        render(records);
    }

    public static void populationByBrgy() {
        List<PopulationByBrgy> records = PopulationByBrgy.getReport();
        records.add(new PopulationByBrgy("TOTAL", records.stream().mapToInt(PopulationByBrgy::getCount).sum()));
        render(records);
    }

    public static void populationBySex() {
        List<PopulationBySex> records = PopulationBySex.getReport();
        int total = records.stream().mapToInt(PopulationBySex::getCount).sum();
        for (PopulationBySex record: records) {
            record.setPercent(BigDecimal.valueOf(((double)record.getCount()/total)*100d).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        }
        records.add(new PopulationBySex("TOTAL", total, 100.0d));
        render(records);
    }

    public static void populationByCivilStatus() {
        List<PopulationByCivilStatus> records = PopulationByCivilStatus.getReport();
        render(records);
    }

    public static void populationBySeniorCitizen() {
        List<PopulationBySeniorCitizen> records = PopulationBySeniorCitizen.getReport();
        int femaleTotal = records.stream().mapToInt(PopulationBySeniorCitizen::getFemale).sum();
        int maleTotal = records.stream().mapToInt(PopulationBySeniorCitizen::getMale).sum();
        records.add(new PopulationBySeniorCitizen("TOTAL", femaleTotal, maleTotal));
        render(records);
    }

    public static void populationByResidence() {
        List<PopulationByResidency> records = PopulationByResidency.getReport();
        int total = records.stream().mapToInt(PopulationByResidency::getCount).sum();
        for (PopulationByResidency record: records) {
            record.setPercent(BigDecimal.valueOf(((double)record.getCount()/total)*100d).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        }
        records.add(new PopulationByResidency("TOTAL", total, 100.0d));
        render(records);
    }

    public static void populationByWorkStatus() {
        List<PopulationByWorkStatus> records = PopulationByWorkStatus.getReport();
        int femaleTotal = records.stream().mapToInt(PopulationByWorkStatus::getFemale).sum();
        int maleTotal = records.stream().mapToInt(PopulationByWorkStatus::getMale).sum();
        int total = femaleTotal + maleTotal;
        for (PopulationByWorkStatus record: records) {
            record.setFemalePercent(BigDecimal.valueOf(((double) record.getFemale()/total)*100d).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
            record.setMalePercent(BigDecimal.valueOf(((double) record.getMale()/total)*100d).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        }
        render(records);
    }

    public static void employmentStatusByAge() {
        List<EmploymentStatusByAge> records = EmploymentStatusByAge.getReport();
        int permanent = records.stream().mapToInt(EmploymentStatusByAge::getPermanent).sum();
        int casual = records.stream().mapToInt(EmploymentStatusByAge::getCasual).sum();
        int contractual = records.stream().mapToInt(EmploymentStatusByAge::getContractual).sum();
        int owned = records.stream().mapToInt(EmploymentStatusByAge::getOwned_business).sum();
        int partnership = records.stream().mapToInt(EmploymentStatusByAge::getPartnership_business).sum();
        int corporate = records.stream().mapToInt(EmploymentStatusByAge::getCorporate_business).sum();
        int other = records.stream().mapToInt(EmploymentStatusByAge::getOther).sum();
        records.add(new EmploymentStatusByAge("TOTAL", permanent, casual, contractual, owned, partnership, corporate, other));
        render(records);
    }

    public static void educationalLevelByAge() {
        List<EducationalLevelByAge> records = EducationalLevelByAge.getReport();
        int none = records.stream().mapToInt(EducationalLevelByAge::getNone).sum();
        int preSchool = records.stream().mapToInt(EducationalLevelByAge::getPreschool).sum();
        int elementary = records.stream().mapToInt(EducationalLevelByAge::getElementary_grad).sum();
        int highschool = records.stream().mapToInt(EducationalLevelByAge::getHighschool_grad).sum();
        int juniorHS = records.stream().mapToInt(EducationalLevelByAge::getJunior_hs_grad).sum();
        int seniorHS = records.stream().mapToInt(EducationalLevelByAge::getSenior_hs_grad).sum();
        int vocational = records.stream().mapToInt(EducationalLevelByAge::getVocational).sum();
        int college = records.stream().mapToInt(EducationalLevelByAge::getCollege_grad).sum();
        int postGrad = records.stream().mapToInt(EducationalLevelByAge::getPost_grad).sum();
        int undefined = records.stream().mapToInt(EducationalLevelByAge::getUndefined).sum();
        records.add(new EducationalLevelByAge("TOTAL", none, preSchool, elementary, highschool, juniorHS,
                seniorHS, vocational, college, postGrad, undefined));
        render(records);
    }
}