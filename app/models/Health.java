package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import enums.DeliveryPlace;
import enums.FPMethod;
import enums.Facility;
import enums.HealthInsurance;
import enums.ReasonOfVisit;
import enums.SourceOfFP;
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
public class Health extends Model {

    public enum Status {
        Alive, Deceased, Missing
    }

    @Required
    private Status status;

    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    private String disability;
    private String immunization;
    private DeliveryPlace placeOfDelivery;
    private String birthAttendant;
    private boolean FPUsage;
    private Integer livingChildren;
    private Integer livingChildrenSub; // What??
    private String causeOfDeath;

    @Enumerated(EnumType.STRING)
    private SourceOfFP sourceOfFP;

    @Enumerated(EnumType.STRING)
    private FPMethod FPMethod;

    @Enumerated(EnumType.STRING)
    private YesOrNo intentToUseFP;

    @Enumerated(EnumType.STRING)
    private FPMethod intentToUseFPSub;

    // Is this important? If so, create separate Health History Table
    @Enumerated(EnumType.STRING)
    private Facility facility;

    @Enumerated(EnumType.STRING)
    private ReasonOfVisit reasonOfVisit;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private Resident resident;
}
