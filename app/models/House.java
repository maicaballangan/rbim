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
        Single(1, "Single"),
        Duplex(2, "Duplex"),
        MultiUnitResidential(3, "Multi-unit Residential"),
        CommercialIndustrialAgricultural(4, "Commercial/Unit/Agricultural"),
        InstitutionalLivingQuarter(5, "Institutional Living Quarter (Hotel, Hospital)"),
        Other(6, "Other housing units (boat, cave, others)");

        private final int code;
        private final String description;

        Type(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    private enum Material {
        NoWalls(0, "No walls"),
        MakeshiftSalvagedImprovised(1, "Makeshift/Salvaged/Improvised"),
        Glass(2, "Glass"),
        Asbestos(3, "Asbestos"),
        BambooSawaliCogonNipa(4, "Bamboo/Sawali/Cogon/Nipa"),
        GalvanizedIronAluminum(5, "Galvanized Iron/Aluminum"),
        HalfConcreteBrickStoneHalfWood(6, "Half Concrete/Brick/Stone and Half Wood"),
        Wood(7, "Wood"),
        ConcreteBrickStone(8, "Concrete/Brick/Stone"),
        Other(9, "Other");

        private final int code;
        private final String description;

        Material(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
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
