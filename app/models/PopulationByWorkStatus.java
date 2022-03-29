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
public class PopulationByWorkStatus {
    private String workStatus;
    private int female;
    private double femalePercent;
    private int male;
    private double malePercent;

    public static List<PopulationByWorkStatus> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery("select * from" +
                        " (select id, sex, case " +
                        " when workStatus is null then 'OTHER/UNDEFINED' else workStatus" +
                        " end as workStatus" +
                        " from Resident" +
                        " ) as a" +
                        " pivot (count(id) for sex in (FEMALE,MALE)) as b" +
                        " order by workStatus")
                .unwrap(SQLQuery.class)
                .addScalar("workStatus", StringType.INSTANCE)
                .addScalar("female", IntegerType.INSTANCE)
                .addScalar("male", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PopulationByWorkStatus.class))
                .getResultList();
    }

    public int getTotal() {
        return female + male;
    }
}
