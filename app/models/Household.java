package models;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
/*@Table(name = "Household", uniqueConstraints = {
        @UniqueConstraint(columnNames = "head")
})*/
public class Household extends Model {

    enum Ownership {
        Rent_free_without_consent_of_owner(1),
        Rent_free_with_consent_of_owner(2),
        Rented(3),
        Owned_or_being_Amortized(4);

        private final int code;

        Ownership(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Lighting {
        None(0),
        Oil_from_animals_or_vegetables (1),
        LPG(2),
        Kerosene(3),
        Electricity(4),
        Others(5);

        private final int code;

        Lighting(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum CookingFuel {
        None(0),
        Wood(1),
        Charcoal(2),
        LPG(3),
        Kerosene(4),
        Electricity(5),
        Others(6);

        private final int code;

        CookingFuel(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum WaterSource {
        Lake_river_rain_others(1),
        Dug_well(2),
        Unprotected_spring(3),
        Protected_Spring(4),
        Peddler(5),
        Tubed_or_Piped_Shallow_Well(6),
        Shared_Tubed_or_Piped_Deep_Well(7),
        Own_Use_Tubed_or_Piped_Deep_Well(8),
        Shared_Use_Faucet_Community_Water_System(9),
        Own_Use_Faucet_Community_Water_System(10),
        Bottled_Water(11),
        Others(12);

        private final int code;

        WaterSource(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum GarbageDisposal {
        Feeding_to_animals(1),
        Burying(2),
        Composting(3),
        Burning(4),
        Dumping_individual_pit(5),
        Picked_up_by_garbage_truck(6);

        private final int code;

        GarbageDisposal(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum ToiletFacility {
        None(0),
        Open_pit(1),
        Close_pit(2),
        Water_sealed_shared_depository(3),
        Water_sealed_exclusive_depository(4),
        Water_sealed_shared_sewer_septic_tank(5),
        Water_sealed_exclusive_sewer_septic_tank(6),
        Others(7);

        private final int code;

        ToiletFacility(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

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

    @Required
    private boolean hasTrashSegregation;

    @Required
    @OneToOne(cascade= CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Where(clause = "relationshipToHead == Head")
    private Resident head;

    @OneToOne(cascade= CascadeType.PERSIST, fetch = FetchType.EAGER)
    private House house;

    public Ownership getHouseOwnership() {
        return houseOwnership;
    }

    public void setHouseOwnership(Ownership houseOwnership) {
        this.houseOwnership = houseOwnership;
    }

    public Ownership getLotOwnership() {
        return lotOwnership;
    }

    public void setLotOwnership(Ownership lotOwnership) {
        this.lotOwnership = lotOwnership;
    }

    public Lighting getLighting() {
        return lighting;
    }

    public void setLighting(Lighting lighting) {
        this.lighting = lighting;
    }

    public CookingFuel getCookingFuel() {
        return cookingFuel;
    }

    public void setCookingFuel(CookingFuel cookingFuel) {
        this.cookingFuel = cookingFuel;
    }

    public WaterSource getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(WaterSource waterSource) {
        this.waterSource = waterSource;
    }

    public GarbageDisposal getGarbageDisposal() {
        return garbageDisposal;
    }

    public void setGarbageDisposal(GarbageDisposal garbageDisposal) {
        this.garbageDisposal = garbageDisposal;
    }

    public ToiletFacility getToiletFacility() {
        return toiletFacility;
    }

    public void setToiletFacility(ToiletFacility toiletFacility) {
        this.toiletFacility = toiletFacility;
    }

    public boolean isHasTrashSegregation() {
        return hasTrashSegregation;
    }

    public void setHasTrashSegregation(boolean hasTrashSegregation) {
        this.hasTrashSegregation = hasTrashSegregation;
    }

    public Resident getHead() {
        return head;
    }

    public void setHead(Resident head) {
        this.head = head;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return super.toString();//head.toString();
    }
}
