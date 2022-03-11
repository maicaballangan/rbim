/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    public static List<EducationalLevelByAge> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery("select * from" +
                        " (select id, case" +
                        "  when age between 15 and 19 then '15-19'" +
                        "  when age between 20 and 24 then '20-24'" +
                        "  when age between 25 and 29 then '25-29'" +
                        "  when age between 30 and 34 then '30-34'" +
                        "  when age between 35 and 39 then '35-39'" +
                        "  when age between 40 and 44 then '40-44'" +
                        "  when age between 45 and 49 then '45-49'" +
                        "  when age between 50 and 54 then '50-54'" +
                        "  when age between 55 and 59 then '55-59'" +
                        "  when age between 60 and 64 then '60-64' end as age," +
                        " case " +
                        "  when educationalAttainment is null then 'UNDEFINED'" +
                        "  when educationalAttainment = 'ELEMENTARY' then 'PRESCHOOL'" +
                        "  when educationalAttainment = 'HIGHSCHOOL' then 'ELEMENTARY_GRAD'" +
                        "  when educationalAttainment = 'JUNIOR_HS' then 'HIGHSCHOOL_GRAD'" +
                        "  when educationalAttainment = 'SENIOR_HS' then 'HIGHSCHOOL_GRAD'" +
                        "  when educationalAttainment = 'COLLEGE' then 'SENIOR_HS_GRAD'" +
                        " else educationalAttainment end as educationalAttainment " +
                        " from Resident where age between 15 and 64" +
                        " ) as a" +
                        " pivot (count(id) for educationalAttainment in (NONE,PRESCHOOL,ELEMENTARY_GRAD,HIGHSCHOOL_GRAD,JUNIOR_HS_GRAD,SENIOR_HS_GRAD,VOCATIONAL,COLLEGE_GRAD,POST_GRAD,UNDEFINED)) AS b" +
                        " order by age")
                .unwrap(SQLQuery.class)
                .addScalar("age", StringType.INSTANCE)
                .addScalar("none", IntegerType.INSTANCE)
                .addScalar("preschool", IntegerType.INSTANCE)
                .addScalar("elementary_grad", IntegerType.INSTANCE)
                .addScalar("highschool_grad", IntegerType.INSTANCE)
                .addScalar("junior_hs_grad", IntegerType.INSTANCE)
                .addScalar("senior_hs_grad", IntegerType.INSTANCE)
                .addScalar("vocational", IntegerType.INSTANCE)
                .addScalar("college_grad", IntegerType.INSTANCE)
                .addScalar("post_grad", IntegerType.INSTANCE)
                .addScalar("undefined", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(EducationalLevelByAge.class))
                .getResultList();
    }
}
