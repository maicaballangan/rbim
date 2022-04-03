/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;

import java.util.List;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Getter
@Setter
@AllArgsConstructor
public class EducationalLevelByAge {
    private String age;
    private int none;
    private int preschool;
    private int elementary_grad;
    private int highschool_grad;
    private int junior_hs_grad;
    private int senior_hs_grad;
    private int vocational;
    private int college_grad;
    private int post_grad;
    private int undefined;

    private static final String QUERY = "select a.age, " +
            " case when a.NONE is not null then a.NONE else 0 end as NONE, " +
            " case when a.PRESCHOOL is not null then a.PRESCHOOL else 0 end as PRESCHOOL," +
            " case when a.ELEMENTARY_GRAD is not null then a.ELEMENTARY_GRAD else 0 end as ELEMENTARY_GRAD, " +
            " case when a.HIGHSCHOOL_GRAD is not null then a.HIGHSCHOOL_GRAD else 0 end as HIGHSCHOOL_GRAD," +
            " case when a.JUNIOR_HS_GRAD is not null then a.JUNIOR_HS_GRAD else 0 end as JUNIOR_HS_GRAD, " +
            " case when a.SENIOR_HS_GRAD is not null then a.SENIOR_HS_GRAD else 0 end as SENIOR_HS_GRAD, " +
            " case when a.VOCATIONAL is not null then a.VOCATIONAL else 0 end as VOCATIONAL," +
            " case when a.COLLEGE_GRAD is not null then a.COLLEGE_GRAD else 0 end as COLLEGE_GRAD, " +
            " case when a.POST_GRAD is not null then a.POST_GRAD else 0 end as POST_GRAD," +
            " case when a.UNDEFINED is not null then a.UNDEFINED else 0 end as UNDEFINED" +
            " from crosstab(" +
            " $$select case" +
            "   when age between 15 and 19 then '15-19'" +
            "   when age between 20 and 24 then '20-24'" +
            "   when age between 25 and 29 then '25-29'" +
            "   when age between 30 and 34 then '30-34'" +
            "   when age between 35 and 39 then '35-39'" +
            "   when age between 40 and 44 then '40-44'" +
            "   when age between 45 and 49 then '45-49'" +
            "   when age between 50 and 54 then '50-54'" +
            "   when age between 55 and 59 then '55-59'" +
            "   when age between 60 and 64 then '60-64' end as age," +
            " case " +
            "   when educationalAttainment is null then 'UNDEFINED'" +
            "   when educationalAttainment = 'ELEMENTARY' then 'PRESCHOOL'" +
            "   when educationalAttainment = 'HIGHSCHOOL' then 'ELEMENTARY_GRAD'" +
            "   when educationalAttainment = 'JUNIOR_HS' then 'HIGHSCHOOL_GRAD'" +
            "   when educationalAttainment = 'SENIOR_HS' then 'HIGHSCHOOL_GRAD'" +
            "   when educationalAttainment = 'COLLEGE' then 'SENIOR_HS_GRAD'" +
            " else educationalAttainment end as educationalAttainment, " +
            " count(*) as total" +
            " from Resident where age between 15 and 64" +
            "   group by age, educationalAttainment" +
            "   order by age$$," +
            " $$select 'NONE' union all" +
            "   select 'PRESCHOOL' union all" +
            "   select 'ELEMENTARY_GRAD' union all" +
            "   select 'HIGHSCHOOL_GRAD' union all" +
            "   select 'JUNIOR_HS_GRAD' union all" +
            "   select 'SENIOR_HS_GRAD' union all" +
            "   select 'VOCATIONAL' union all" +
            "   select 'COLLEGE_GRAD' union all" +
            "   select 'POST_GRAD' union all" +
            "   select 'UNDEFINED'$$" +
            " ) as a(age varchar, NONE int, PRESCHOOL int, ELEMENTARY_GRAD int, HIGHSCHOOL_GRAD int, JUNIOR_HS_GRAD int, SENIOR_HS_GRAD int, VOCATIONAL int, COLLEGE_GRAD int, POST_GRAD int, UNDEFINED int)";

    public static List<EducationalLevelByAge> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(EducationalLevelByAge.class))
                .getResultList();
    }
}
