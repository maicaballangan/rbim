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
public class PopulationByResidence {
    private String residentType;
    private int count;
    private double percent;

    public static List<PopulationByResidence> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery("select case \n" +
                        "    when residentType is null then 'OTHER/UNDEFINED' \n" +
                        "    else residentType \n" +
                        "  end as residentType, count(id) as count from Resident GROUP by residentType order by residentType")
                .unwrap(SQLQuery.class)
                .addScalar("residentType", StringType.INSTANCE)
                .addScalar("count", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PopulationByResidence.class))
                .getResultList();
    }
}
