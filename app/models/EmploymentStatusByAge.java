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
public class EmploymentStatusByAge {
    private String age;
    private int permanent;
    private int casual;
    private int contractual;
    private int owned_business;
    private int partnership_business;
    private int corporate_business;
    private int other;

    public static List<EmploymentStatusByAge> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery("select * from" +
                        " (select id, case" +
                        " when age < 20 then '20 below'" +
                        " when age between 20 and 24 then '20-24'" +
                        " when age between 25 and 29 then '25-29'" +
                        " when age between 30 and 34 then '30-34'" +
                        " when age between 35 and 39 then '35-39'" +
                        " when age between 40 and 44 then '40-44'" +
                        " when age between 45 and 49 then '45-49'" +
                        " when age between 50 and 54 then '50-54'" +
                        " when age between 55 and 59 then '55-59'" +
                        " when age between 60 and 64 then '60-64'" +
                        " when age > 64 then '65 Above' end as age," +
                        " case when workStatus is null then 'OTHER' else workStatus end as workStatus " +
                        " from Resident" +
                        " ) as a" +
                        " pivot (count(id) for workStatus in (PERMANENT,CASUAL,CONTRACTUAL,OWNED_BUSINESS,PARTNERSHIP_BUSINESS,CORPORATE_BUSINESS,OTHER)) as b" +
                        " ORDER by age")
                .unwrap(SQLQuery.class)
                .addScalar("age", StringType.INSTANCE)
                .addScalar("permanent", IntegerType.INSTANCE)
                .addScalar("casual", IntegerType.INSTANCE)
                .addScalar("contractual", IntegerType.INSTANCE)
                .addScalar("owned_business", IntegerType.INSTANCE)
                .addScalar("partnership_business", IntegerType.INSTANCE)
                .addScalar("corporate_business", IntegerType.INSTANCE)
                .addScalar("other", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(EmploymentStatusByAge.class))
                .getResultList();
    }

    public int getTotal() {
        return permanent + casual + contractual + owned_business + partnership_business + corporate_business + other;
    }
}
