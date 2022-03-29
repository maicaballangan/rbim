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
public class PopulationByCivilStatus {
    private String civilStatus;
    private int female;
    private int male;

    public static List<PopulationByCivilStatus> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery("select * from" +
                        " (select id, sex, case " +
                        " when civilStatus is null then 'OTHER/UNDEFINED' else civilStatus" +
                        " end as civilStatus" +
                        " from Resident" +
                        " ) as a" +
                        " pivot (count(id) for sex in (FEMALE,MALE)) as b" +
                        " order by civilStatus")
                .unwrap(SQLQuery.class)
                .addScalar("civilStatus", StringType.INSTANCE)
                .addScalar("female", IntegerType.INSTANCE)
                .addScalar("male", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PopulationByCivilStatus.class))
                .getResultList();
    }

    public int getTotal() {
        return female + male;
    }
}
