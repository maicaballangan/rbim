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

    public static List<PopulationBySeniorCitizen> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery("select * from" +
                        " (select id, sex, case " +
                        " when registeredSeniorCitizen is null then 'OTHER/UNDEFINED' else registeredSeniorCitizen" +
                        " end as registeredSeniorCitizen" +
                        " from Resident where registeredSeniorCitizen in('NO', 'YES')" +
                        " ) as a" +
                        " pivot (count(id) for sex in (FEMALE,MALE)) as b" +
                        " order by registeredSeniorCitizen")
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
