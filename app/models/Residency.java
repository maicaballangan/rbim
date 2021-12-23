package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enums.Barangay;
import enums.BuildingMaterial;
import enums.BuildingType;
import enums.ReasonForLeaving;
import enums.ReasonForTransfer;
import enums.ResidentType;
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
public class Residency extends Model {

    @Required
    private String address;

    @Required
    private String street;

    @Required
    private BuildingType type;

    @Required
    @Enumerated(EnumType.STRING)
    private BuildingMaterial material;

    @Required
    @Enumerated(EnumType.STRING)
    private Barangay barangay;

    @Required
    @Enumerated(EnumType.STRING)
    private ResidentType residentType;

    //@Enumerated(EnumType.STRING)
    private String previousBarangayFiveYr;

    //@Enumerated(EnumType.STRING)
    private String previousMunicipalityFiveYr;

    //@Enumerated(EnumType.STRING)
    private String previousBarangaySixMo;

    //@Enumerated(EnumType.STRING)
    private String previousMunicipalitySixMo;

    private Integer yearsOfStay;
    private Integer monthsOfStay;
    private Integer monthOfTransfer;
    private Integer yearOfTransfer;
    private ReasonForLeaving reasonForLeavingA;
    private ReasonForLeaving reasonForLeavingB;
    private ReasonForLeaving reasonForLeavingC;
    private ReasonForTransfer reasonForTransferA;
    private ReasonForTransfer reasonForTransferB;
    private ReasonForTransfer reasonForTransferC;
    private boolean intentOfReturning;
    private Integer durationOfStay;
}
