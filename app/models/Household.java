/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import enums.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import play.data.validation.Required;
import utils.StringSequenceIdGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
@Cacheable
@Builder
@Getter
@Setter
public class Household extends AbstractModel {

    public enum Status {
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "household_seq")
    @GenericGenerator(
        name = "household_seq",
        strategy = "utils.StringSequenceIdGenerator",
        parameters = {
            @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "H"),
            @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
    public String id;

    @Required
    @Enumerated(EnumType.STRING)
    private Province province=Province.KALINGA;

    @Required
    @Enumerated(EnumType.STRING)
    private Municipality municipality=Municipality.TABUK_CITY;

    @Required
    @Enumerated(EnumType.STRING)
    private Barangay barangay;

    private String houseNo;

    private String blockNo;

    private String street;

    private String respondent;

    @Required
    private String head;

    private Integer totalNo;

    private Date interviewDate;

    @Enumerated(EnumType.STRING)
    private Ownership houseOwnership;

    @Enumerated(EnumType.STRING)
    private Ownership lotOwnership;

    @Enumerated(EnumType.STRING)
    private Lighting lighting;

    @Enumerated(EnumType.STRING)
    private Cooking cooking;

    @Enumerated(EnumType.STRING)
    private WaterSource waterSource;

    @Enumerated(EnumType.STRING)
    private GarbageDisposal garbageDisposal;

    @Enumerated(EnumType.STRING)
    private YesOrNo hasTrashSegregation;

    @Enumerated(EnumType.STRING)
    private ToiletFacility toiletFacility;

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Enumerated(EnumType.STRING)
    private BuildingMaterial buildingMaterial;

    @Required
    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    private Barangay intentToStayFiveYrsBrgy;

    @Enumerated(EnumType.STRING)
    private Municipality intentToStayFiveYrsMunicipality;

    @Enumerated(EnumType.STRING)
    private Province intentToStayFiveYrsProvince;

    @Override
    public String toString() {
        return id + ": " + head;
    }

    public Household getExisting() {
        StringBuilder sb = new StringBuilder("head = :head and barangay = :barangay");
        if (houseNo != null) sb.append(" and houseNo = :houseNo");
        if (blockNo != null) sb.append(" and blockNo = :blockNo");
        if (street != null) sb.append(" and street = :street");

        JPAQuery query = Household.find(sb.toString())
                .setParameter("head", head)
                .setParameter("barangay", barangay);

        if (street != null) query.setParameter("street", street);
        if (blockNo != null) query.setParameter("blockNo", blockNo);
        if (houseNo != null) query.setParameter("houseNo", houseNo);
        return query.first();
    }

    @Override
    public void _save() {
        if (status == null) {
            this.status = Status.ACTIVE;
        }
        super._save();
    }

    @Override
    public boolean create() {
        return super.create();
    }
}
