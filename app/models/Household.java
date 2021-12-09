package models;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Required;
import play.db.jpa.GenericModel;
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

    enum HouseOwnership {
        // TODO Q45
        test
    }

    enum LotOwnership {
        // TODO Q46
        test
    }

    enum Lighting {
        // TODO Q47
        test
    }

    enum CookingFuel {
        // TODO Q48
        test
    }

    enum WaterSource {
        // TODO Q49
        test
    }

    enum GarbageDisposal {
        // TODO Q50a
        test
    }

    enum ToiletFacility {
        // TODO Q50c
        test
    }

    @Enumerated(EnumType.STRING)
    private HouseOwnership houseOwnership;

    @Enumerated(EnumType.STRING)
    private LotOwnership lotOwnership;

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

    public HouseOwnership getHouseOwnership() {
        return houseOwnership;
    }

    public void setHouseOwnership(HouseOwnership houseOwnership) {
        this.houseOwnership = houseOwnership;
    }

    public LotOwnership getLotOwnership() {
        return lotOwnership;
    }

    public void setLotOwnership(LotOwnership lotOwnership) {
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
