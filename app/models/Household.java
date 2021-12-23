package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Enumerated(EnumType.STRING)
    private BuildingMaterial buildingMaterial;

    @Required
    @OneToMany(cascade= CascadeType.PERSIST, fetch = FetchType.EAGER)
    //@Where(clause = "relationshipToHead == Head")
    private List<Resident> resident;

    @Override
    public String toString() {
        return super.toString();//head.toString();
    }
}
