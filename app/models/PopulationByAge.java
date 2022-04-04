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
public class PopulationByAge {
    private String age;
    private int count;
    private double percent;
    private static final String QUERY = "select cast(count(*) as int) as count, * from (select case" +
            "   when age between 0 and 4 then '00-04'" +
            "   when age between 5 and 9 then '05-09'" +
            "   when age between 10 and 14 then '10-14'" +
            "   when age between 15 and 19 then '15-19'" +
            "   when age between 20 and 24 then '20-24'" +
            "   when age between 25 and 29 then '25-29'" +
            "   when age between 30 and 34 then '30-34'" +
            "   when age between 35 and 39 then '35-39'" +
            "   when age between 40 and 44 then '40-44'" +
            "   when age between 45 and 49 then '45-49'" +
            "   when age between 50 and 54 then '50-54'" +
            "   when age between 55 and 59 then '55-59'" +
            "   when age between 60 and 64 then '60-64'" +
            "   when age between 65 and 69 then '65-69'" +
            "   when age between 70 and 74 then '70-74'" +
            "   when age between 75 and 79 then '75-79'" +
            "   when age > 79 then '80 Above'" +
            "   when age is null then 'UNDEFINED'" +
            " end as age" +
            " from Resident) as a" +
            "   group by age" +
            "   order by age";

    public static List<PopulationByAge> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(PopulationByAge.class))
                .getResultList();
    }
}
