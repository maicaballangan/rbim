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
    private static final String QUERY = "select " +
            " a.workStatus, " +
            " case when a.female is not null then a.female else 0 end as female, " +
            " case when a.male is not null then a.male else 0 end as male" +
            " from crosstab(" +
            " $$select case" +
            "   when workStatus is null then 'OTHER/UNDEFINED' else workStatus" +
            " end as workStatus," +
            " sex, count(*) " +
            " from Resident" +
            "   group by workStatus, sex" +
            "   order by workStatus$$," +
            " $$select 'FEMALE' union all" +
            "   select 'MALE'$$"  +
            " ) as a(workStatus varchar, FEMALE numeric, MALE numeric)";

    public static List<PopulationByWorkStatus> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
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
