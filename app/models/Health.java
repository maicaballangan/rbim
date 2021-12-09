package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
public class Health extends Model {

    enum HealthInsurance {
        // TODO Q26
    }

    enum DeliveryPlace {
        // TODO Q19
    }

    enum Attendant {
        // TODO Q20
    }

    enum FamilyPlanningMethod {
        // TODO Q23/25
    }

    enum SourceOfFP {
        // TODO Q24
    }

    enum Facility {
        // TODO Q27
    }

    enum ReasonOfVisit {
        // TODO Q28
    }

    private String healthInsurance;
    private String disability;
    private String immunization;
    private DeliveryPlace placeOfDelivery;
    private String birthAttendant;
    private boolean FPUsage;

    @Enumerated(EnumType.STRING)
    private SourceOfFP sourceOfFP;

    @Enumerated(EnumType.STRING)
    private FamilyPlanningMethod familyPlanningMethod;
    private boolean intentToUseFP;

    // Is this important? If so, create separate Health History Table
    @Enumerated(EnumType.STRING)
    private Facility facility;
    private String reasonOfVisit;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Resident resident;

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }
}
