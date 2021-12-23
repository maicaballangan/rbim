package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
@Builder
@Getter
@Setter
public class Household extends Model {

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

    private YesOrNo hasTrashSegregation;

    @Required
    private String head;

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Enumerated(EnumType.STRING)
    private BuildingMaterial buildingMaterial;

    /*@Required
    @OneToMany(cascade= CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Resident> resident;*/

    @Override
    public String toString() {
        return id + ": " + head.toString();
    }

    public static Household lookup(String head, String placeOfBirthBrgy, String barangay) {
        return Household.find(
                "select h " +
                        "from Household h " +
                        "join Resident r on h.id = r.household " +
                        "join Residency ry on r.id = ry.resident" +
                        " where h.head = ?1 and r.placeOfBirthBrgy = ?2 and ry.barangay = ?3",
                        head, placeOfBirthBrgy, barangay)
                .first();
        /*return JPA.em().createQuery("select h.* " +
                "from Household h " +
                "join Resident r on h.resident_id = r.id " +
                "where h.head = ? " +
                "and r.placeOfBirthBrgy = ? " +
                "and ")*/
    }
}
