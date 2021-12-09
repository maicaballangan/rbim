package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enums.Barangay;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
public class House extends Model {

    private enum Type {
        Single("Single"),
        Duplex("Duplex"),
        MultiUnitResidential("Multi-unit Residential"),
        CommercialIndustrialAgricultural("Commercial/Unit/Agricultural"),
        InstitutionalLivingQuarter("Institutional Living Quarter (Hotel, Hospital)"),
        Other("Other housing units (boat, cave, others)");

        private String description;

        Type(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    private enum Material {
        NoWalls("No walls"),
        MakeshiftSalvagedImprovised("Makeshift/Salvaged/Improvised"),
        Glass("Glass"),
        Asbestos("Asbestos"),
        BambooSawaliCogonNipa("Bamboo/Sawali/Cogon/Nipa"),
        GalvanizedIronAluminum("Galvanized Iron/Aluminum"),
        HalfConcreteBrickStoneHalfWood("Half Concrete/Brick/Stone and Half Wood"),
        Wood("Wood"),
        ConcreteBrickStone("Concrete/Brick/Stone"),
        Other("Other");

        private String description;

        Material(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    @Required
    private String address;

    @Required
    private String street;

    @Required
    private Type type;

    @Required
    @Enumerated(EnumType.STRING)
    private Material material;

    @Required
    @Enumerated(EnumType.STRING)
    private Barangay barangay;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Barangay getBarangay() {
        return barangay;
    }

    public void setBarangay(Barangay barangay) {
        this.barangay = barangay;
    }

    @Override
    public String toString() {
        return address + ", " + street + ", " + barangay;
    }
}
