package models;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enums.Barangay;
import enums.BuildingMaterial;
import enums.BuildingType;
import enums.CookingFuel;
import enums.GarbageDisposal;
import enums.Lighting;
import enums.Ownership;
import enums.ToiletFacility;
import enums.WaterSource;
import enums.YesOrNo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
@Cacheable
@Builder
@Getter
@Setter
public class Household extends Model {

    private Integer houseNo;

    @Required
    private String address;

    @Required
    private String street;

    @Required
    @Enumerated(EnumType.STRING)
    private Barangay barangay;

    @Enumerated(EnumType.STRING)
    private BuildingType type;

    @Enumerated(EnumType.STRING)
    private BuildingMaterial material;

    @Enumerated(EnumType.STRING)
    private Ownership houseOwnership;

    @Enumerated(EnumType.STRING)
    private Ownership lotOwnership;

    @Enumerated(EnumType.STRING)
    private Lighting lighting;

    @Enumerated(EnumType.STRING)
    private CookingFuel cookingFuel;

    @Enumerated(EnumType.STRING)
    private WaterSource waterSource;

    @Enumerated(EnumType.STRING)
    private GarbageDisposal garbageDisposal;

    @Enumerated(EnumType.STRING)
    private ToiletFacility toiletFacility;

    @Enumerated(EnumType.STRING)
    private YesOrNo hasTrashSegregation;

    @Required
    private String head;

    private Integer totalNumber;

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Enumerated(EnumType.STRING)
    private BuildingMaterial buildingMaterial;

    @Override
    public String toString() {
        return id + ": " + head;
    }

    public Household getExisting() {
        StringBuilder sb = new StringBuilder("head = :head and barangay = :barangay");
        if (address != null) sb.append(" and address = :address");
        if (street != null) sb.append(" and street = :street");
        if (houseNo != null) sb.append(" and houseNo = :houseNo");

        JPAQuery query = Household.find(sb.toString())
                .setParameter("head", head)
                .setParameter("barangay", barangay);

        if (address != null) query.setParameter("address", address);
        if (street != null) query.setParameter("street", street);
        if (houseNo != null) query.setParameter("houseNo", houseNo);
        return query.first();
    }
}
