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
public class PopulationByCivilStatus {
    private String cs;
    private int female;
    private int male;

    private static final String QUERY = "select a.civilStatus as cs, " +
            " case when a.female is not null then a.female else 0 end as female, " +
            " case when a.male is not null then a.male else 0 end as male" +
            " from crosstab(" +
            " $$select case " +
            "   when civilStatus is null then 'OTHER/UNDEFINED' else civilStatus" +
            " end as civilStatus," +
            "   sex, count(*) " +
            " from Resident" +
            " group by civilStatus, sex" +
            " order by civilStatus$$," +
            " $$select 'FEMALE' union all" +
            " select 'MALE'$$" +
            " ) as a(civilStatus varchar, FEMALE int, MALE int)";

    public static List<PopulationByCivilStatus> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(PopulationByCivilStatus.class))
                .getResultList();
    }

    public int getTotal() {
        return female + male;
    }
}
