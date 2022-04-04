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
    private int undefined;

    private static final String QUERY = "select a.age, " +
            " coalesce(a.PERMANENT, 0) as PERMANENT, " +
            " coalesce(a.CASUAL, 0) as CASUAL," +
            " coalesce(a.CONTRACTUAL, 0) as CONTRACTUAL, " +
            " coalesce(a.OWNED_BUSINESS, 0) as OWNED_BUSINESS," +
            " coalesce(a.PARTNERSHIP_BUSINESS, 0) as PARTNERSHIP_BUSINESS, " +
            " coalesce(a.CORPORATE_BUSINESS, 0) as CORPORATE_BUSINESS, " +
            " coalesce(a.UNDEFINED, 0) as UNDEFINED" +
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
            " when age > 64 then '65+'" +
            " when age is null then 'UNDEFINED' end as age," +
            " case when workStatus is null then 'UNDEFINED' else workStatus end as workStatus, " +
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
            " ) as a (age varchar, PERMANENT integer, CASUAL integer, CONTRACTUAL integer, OWNED_BUSINESS integer, PARTNERSHIP_BUSINESS integer, CORPORATE_BUSINESS integer, UNDEFINED integer)";

    public static List<EmploymentStatusByAge> getReport() {
        return play.db.jpa.JPA.em()
                .createNativeQuery(QUERY)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(EmploymentStatusByAge.class))
                .getResultList();
    }

    public int getTotal() {
        return permanent + casual + contractual + owned_business + partnership_business + corporate_business + undefined;
    }
}
