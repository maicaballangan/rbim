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
        Philhealth_Paying_Member(1),
        Philhealth_Dependent_of_Paying_Member(2),
        Philhealth_Indigent_Member(3),
        Philhealth_Dependent_of_Indigent_Member(4),
        GSIS(5),
        SSS(6),
        Private_or_HMO(7);

        private final int code;

        HealthInsurance(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum DeliveryPlace {
        Public_Hospital(1),
        Private_Hospital(2),
        Lying_in_Clinic(3),
        Home(4);

        private final int code;

        DeliveryPlace(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Attendant {
        Doctor(1),
        Nurse(2),
        Midwife(3),
        Hilot(4);

        private final int code;

        Attendant(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum FamilyPlanningMethod {
        Female_Sterilization_or_Ligation(1),
        Male_Sterilization_or_Vasectomy(2),
        IUD(3),
        InjectAbles(4),
        Implants(5),
        Pill(6),
        Condom(7),
        Modern_Natural_FP(8),
        Lactational_Amenorrhea_Method(9),
        Traditional(10),
        None(00);

        private final int code;

        FamilyPlanningMethod(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum SourceOfFP {
        Government_Hospital(1),
        RHU_or_Health_Center(2),
        Brgy_Health_Station(3),
        Private_Hospital(4),
        Pharmacy(5);

        private final int code;

        SourceOfFP(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Facility {
        Government_Hospital(1),
        RHU_or_Health_Center(2),
        Brgy_Health_Station(3),
        Private_Hospital(4),
        Private_Clinic(5),
        Pharmacy(6),
        Hilot_or_Herbalist(7);

        private final int code;

        Facility(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum ReasonOfVisit {
        Sick_or_Injured(1),
        Prenatal_or_Postnatal(2),
        Gave_Birth(3),
        Dental(4),
        Medical_Checkup(5),
        Medical_Requirement(6),
        NHTS_CCT_4Ps_Requirement(7);

        private final int code;

        ReasonOfVisit(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
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
