package models;

import java.time.Month;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import enums.Barangay;
import enums.CivilStatus;
import enums.DeliveryPlace;
import enums.Education;
import enums.Enrollment;
import enums.FPMethod;
import enums.Facility;
import enums.HealthInsurance;
import enums.IncomeSource;
import enums.Nationality;
import enums.ParentalStatus;
import enums.ReasonForLeaving;
import enums.ReasonForTransfer;
import enums.ReasonOfVisit;
import enums.Relation;
import enums.ResidentType;
import enums.SchoolLevel;
import enums.Sex;
import enums.Skill;
import enums.SourceOfFP;
import enums.WorkStatus;
import enums.YesOrNo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

/**
 * @author Maica Ballangan
 * @since v1
 */
/*@Table(
    uniqueConstraints=
    @UniqueConstraint(columnNames={"lastName", "firstName", "middleName", "placeOfBirthBrgy", "dateOfBirth"})
)*/
@Entity
@Cacheable
@SequenceGenerator(initialValue = 1000000, name = "idgen", sequenceName = "residentSeq")
@Builder
@Getter
@Setter
public class Resident extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    public Long id;

    @Required
    private String lastName;

    @Required
    private String firstName;

    @Required
    private String middleName;

    @Required
    @Enumerated(EnumType.STRING)
    private Relation relationshipToHead;

    @Required
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Integer age;

    private Integer dateOfBirth;

    private Integer yearOfBirth;

    @Enumerated(EnumType.STRING)
    private Month monthOfBirth;

    @Required
    private String placeOfBirthBrgy;

    @Required
    private String placeOfBirthMunicipality;

    @Required
    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Required
    @Enumerated(EnumType.STRING)
    private CivilStatus civilStatus;

    private String religion;

    @Required
    private String ethnicity;

    // Education and Work
    @Enumerated(EnumType.STRING)
    private Education educationalAttainment;

    @Enumerated(EnumType.STRING)
    private Enrollment enrollmentStatus;

    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    private String placeOfSchool;

    private String monthlyIncome;

    @Enumerated(EnumType.STRING)
    private IncomeSource sourceOfIncome;

    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    private String placeOfWork;

    // Health info
    @Enumerated(EnumType.STRING)
    private DeliveryPlace placeOfDelivery;

    private String birthAttendant;
    private String immunization;
    private Integer livingChildren;
    private Integer livingChildrenSub; // What??

    private boolean FPUsage;

    @Enumerated(EnumType.STRING)
    private SourceOfFP sourceOfFP;

    @Enumerated(EnumType.STRING)
    private enums.FPMethod FPMethod;

    @Enumerated(EnumType.STRING)
    private YesOrNo intentToUseFP;

    @Enumerated(EnumType.STRING)
    private FPMethod intentToUseFPSub;

    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    @Enumerated(EnumType.STRING)
    private Facility facility;

    @Enumerated(EnumType.STRING)
    private ReasonOfVisit reasonOfVisit;

    private String disability;

    // Economic Status
    @Enumerated(EnumType.STRING)
    private ParentalStatus parentalStatus;

    @Enumerated(EnumType.STRING)
    private YesOrNo registeredSeniorCitizen;

    @Enumerated(EnumType.STRING)
    private Barangay votingArea;

    // Residency Info
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

    @Required
    @Enumerated(EnumType.STRING)
    private ResidentType residentType;
    @Enumerated(EnumType.STRING)
    private Month monthOfTransfer;

    private Integer yearOfTransfer;

    @Enumerated(EnumType.STRING)
    private ReasonForLeaving reasonForLeavingA;

    @Enumerated(EnumType.STRING)
    private ReasonForLeaving reasonForLeavingB;

    @Enumerated(EnumType.STRING)
    private ReasonForLeaving reasonForLeavingC;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferA;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferB;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferC;

    private boolean intentOfReturning;
    private Integer durationOfStay;

    @Enumerated(EnumType.STRING)
    private YesOrNo CTCIssued;

    private String CTCIssuedBarangay;

    @ElementCollection(targetClass= Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    @ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Household household;

    @Override
    public String toString() {
        return id + ": " + lastName + ", " + firstName + (middleName != null ? " " + middleName : "");
    }

    public Resident getExisting() {
        StringBuilder sb = new StringBuilder("lastName = :lastName and firstName = :firstName");
        if (middleName != null) sb.append(" and middleName = :middleName");
        if (placeOfBirthBrgy != null) sb.append(" and placeOfBirthBrgy = :placeOfBirthBrgy");
        if (placeOfBirthMunicipality != null) sb.append(" and placeOfBirthMunicipality = :placeOfBirthMunicipality");
        if (dateOfBirth != null) sb.append(" and dateOfBirth = :dateOfBirth");

        JPAQuery query = Resident.find(sb.toString())
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);

        if (middleName != null) query.setParameter("middleName", middleName);
        if (placeOfBirthBrgy != null) query.setParameter("placeOfBirthBrgy", placeOfBirthBrgy);
        if (placeOfBirthMunicipality != null) query.setParameter("placeOfBirthMunicipality", placeOfBirthMunicipality);
        if (dateOfBirth != null) query.setParameter("dateOfBirth", dateOfBirth);
        return query.first();
    }
}