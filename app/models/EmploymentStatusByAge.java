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
public class EmploymentStatusByAge {
    private String age;
    private int permanent;
    private int casual;
    private int contractual;
    private int owned_business;
    private int partnership_business;
    private int corporate_business;
    private int other;

    private static final String QUERY = "select a.age, " +
            " case when a.PERMANENT is not null then a.PERMANENT else 0 end as PERMANENT, " +
            " case when a.CASUAL is not null then a.CASUAL else 0 end as CASUAL," +
            " case when a.CONTRACTUAL is not null then a.CONTRACTUAL else 0 end as CONTRACTUAL, " +
            " case when a.OWNED_BUSINESS is not null then a.OWNED_BUSINESS else 0 end as OWNED_BUSINESS," +
            " case when a.PARTNERSHIP_BUSINESS is not null then a.PARTNERSHIP_BUSINESS else 0 end as PARTNERSHIP_BUSINESS, " +
            " case when a.CORPORATE_BUSINESS is not null then a.CORPORATE_BUSINESS else 0 end as CORPORATE_BUSINESS, " +
            " case when a.OTHER is not null then a.OTHER else 0 end as OTHER" +
            " from crosstab(" +
            " $$select case" +
            " when age < 20 then '20-'" +
            " when age between 20 and 24 then '20-24'" +
            " when age between 25 and 29 then '25-29'" +
            " when age between 30 and 34 then '30-34'" +
            " when age between 35 and 39 then '35-39'" +
            " when age between 40 and 44 then '40-44'" +
            " when age between 45 and 49 then '45-49'" +
            " when age between 50 and 54 then '50-54'" +
            " when age between 55 and 59 then '55-59'" +
            " when age between 60 and 64 then '60-64'" +
            " when age > 64 then '65+' end as age," +
            " case when workStatus is null then 'OTHER' else workStatus end as workStatus, " +
            " count(*) " +
            " from Resident" +
            " group by age, workStatus" +
            " order by age$$," +
            " $$select 'PERMANENT' union all" +
            "   select 'CASUAL' union all" +
            "   select 'CONTRACTUAL' union all" +
            "   select 'OWNED_BUSINESS' union all" +
            "   select 'PARTNERSHIP_BUSINESS' union all" +
            "   select 'CORPORATE_BUSINESS' union all" +
            "   select 'OTHER'$$" +
            " ) as a (age varchar, PERMANENT integer, CASUAL integer, CONTRACTUAL integer, OWNED_BUSINESS integer, PARTNERSHIP_BUSINESS integer, CORPORATE_BUSINESS integer, OTHER integer)";

    public static List<EmploymentStatusByAge> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(EmploymentStatusByAge.class))
                .getResultList();
    }

    public int getTotal() {
        return permanent + casual + contractual + owned_business + partnership_business + corporate_business + other;
    }
}
