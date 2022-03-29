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
public class PopulationBySex {
    private String sex;
    private int count;
    private double percent;

    public static List<PopulationBySex> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery("select sex, count(id) as count from Resident GROUP by sex order by sex")
                .unwrap(SQLQuery.class)
                .addScalar("sex", StringType.INSTANCE)
                .addScalar("count", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PopulationBySex.class))
                .getResultList();
    }
}
