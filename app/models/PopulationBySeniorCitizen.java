/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.util.List;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Getter
@Setter
@AllArgsConstructor
public class PopulationBySeniorCitizen {
    private String registeredSeniorCitizen;
    private int female;
    private int male;
    private static final String QUERY = "select a.registeredSeniorCitizen, " +
            " case when a.female is not null then a.female else 0 end as female, " +
            " case when a.male is not null then a.male else 0 end as male" +
            " from crosstab(" +
            " $$select case " +
            "   when registeredSeniorCitizen is null then 'OTHER/UNDEFINED' else registeredSeniorCitizen" +
            " end as registeredSeniorCitizen," +
            " sex, count(*)" +
            " from Resident where registeredSeniorCitizen in('NO', 'YES')" +
            "   group by registeredSeniorCitizen, sex" +
            "   order by registeredSeniorCitizen$$," +
            " $$select 'FEMALE' union all" +
            "   select 'MALE'$$" +
            " ) as a(registeredSeniorCitizen varchar, FEMALE numeric, MALE numeric)";

    public static List<PopulationBySeniorCitizen> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
                .unwrap(SQLQuery.class)
                .addScalar("registeredSeniorCitizen", StringType.INSTANCE)
                .addScalar("female", IntegerType.INSTANCE)
                .addScalar("male", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PopulationBySeniorCitizen.class))
                .getResultList();
    }

    public int getTotal() {
        return female + male;
    }
}
