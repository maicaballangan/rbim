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
public class PopulationByBrgy {
    private String barangay;
    private int count;
    private static final String QUERY = "select barangay, count(*) as count from Resident GROUP by barangay order by barangay";

    public static List<PopulationByBrgy> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
                .unwrap(SQLQuery.class)
                .addScalar("barangay", StringType.INSTANCE)
                .addScalar("count", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PopulationByBrgy.class))
                .getResultList();
    }

    public String getBarangay() {
        return barangay.replaceAll("_", " ");
    }
}
