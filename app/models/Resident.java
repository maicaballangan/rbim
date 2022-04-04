/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import controllers.CRUD;
import enums.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.Model;
import utils.StringSequenceIdGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={
                "lastName",
                "firstName",
                "middleName",
                "placeOfBirthBrgy",
                "placeOfBirthMunicipality",
                "yearOfBirth",
                "monthOfBirth",
                "sex"})
)
@Entity
@Cacheable
@Builder
@Getter
@Setter
public class Resident extends AbstractModel {

    public enum Status {
        ALIVE, DECEASED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resident_seq")
    @GenericGenerator(
        name = "resident_seq",
        strategy = "utils.StringSequenceIdGenerator",
        parameters = {
            @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "R"),
            @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%07d") })
    public String id;

    @Required
    private String lastName;

    @Required
    private String firstName;

    @MaxSize(1)
    private String middleName;

    @Required
    @Enumerated(EnumType.STRING)
    private Relation relationshipToHead;

    @Required
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @CRUD.Hidden
    private Integer age;

    private Month monthOfBirth;

    private Integer yearOfBirth;

    @Required
    @Enumerated(EnumType.STRING)
    private Barangay placeOfBirthBrgy;

    @Required
    @Enumerated(EnumType.STRING)
    private Municipality placeOfBirthMunicipality;

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

    private Integer monthlyIncome;

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

    @Enumerated(EnumType.STRING)
    private enums.FPMethod FPMethod;

    @Enumerated(EnumType.STRING)
    private SourceOfFP sourceOfFP;

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

    @Enumerated(EnumType.STRING)
    private YesOrNo disability;

    // Economic Status
    @Enumerated(EnumType.STRING)
    private YesOrNo registeredBirth;

    @Enumerated(EnumType.STRING)
    private RegisteredSoloParent registeredSoloParent;

    @Enumerated(EnumType.STRING)
    private YesOrNo registeredSeniorCitizen;

    @Enumerated(EnumType.STRING)
    private Barangay votingArea;

    // Residency Info
    @Enumerated(EnumType.STRING)
    private Barangay previousBarangayFiveYr;

    @Enumerated(EnumType.STRING)
    private Municipality previousMunicipalityFiveYr;

    @Enumerated(EnumType.STRING)
    private Barangay previousBarangaySixMo;

    @Enumerated(EnumType.STRING)
    private Municipality previousMunicipalitySixMo;

    private Integer yearsOfStay;

    private Integer monthsOfStay;

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
    private YesOrNo intentOfReturning;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferA;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferB;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferC;

    private Integer durationOfStay;

    @Enumerated(EnumType.STRING)
    private YesOrNo CTCIssued;

    @Enumerated(EnumType.STRING)
    private YesOrNo CTCIssuedInBarangay;

    private String skillTraining;

    @Enumerated(EnumType.STRING)
    private Skill skillA;

    @Enumerated(EnumType.STRING)
    private Skill skillB;

    @Enumerated(EnumType.STRING)
    private Skill skillC;

    @Required
    @Column(insertable = false, updatable = false)
    private String household_id;

    @CRUD.Hidden
    @ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Household household;

    @CRUD.Hidden
    private String barangay;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return id + ": " + lastName + ", " + firstName + (middleName != null ? " " + middleName : "");
    }

    public Resident getExisting() {
        StringBuilder sb = new StringBuilder("lastName = :lastName and firstName = :firstName");
        if (middleName != null) sb.append(" and middleName = :middleName");
        if (placeOfBirthBrgy != null) sb.append(" and placeOfBirthBrgy = :placeOfBirthBrgy");
        if (placeOfBirthMunicipality != null) sb.append(" and placeOfBirthMunicipality = :placeOfBirthMunicipality");
        if (yearOfBirth != null) sb.append(" and yearOfBirth = :yearOfBirth");

        JPAQuery query = Resident.find(sb.toString())
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);

        if (middleName != null) query.setParameter("middleName", middleName);
        if (placeOfBirthBrgy != null) query.setParameter("placeOfBirthBrgy", placeOfBirthBrgy);
        if (placeOfBirthMunicipality != null) query.setParameter("placeOfBirthMunicipality", placeOfBirthMunicipality);
        if (yearOfBirth != null) query.setParameter("yearOfBirth", yearOfBirth);
        return query.first();
    }

    public static List<Model> findByHouseholdId(String household_id) {
        JPAQuery query = Resident.find("household_id = :household_id").setParameter("household_id", household_id);
        return query.fetch();
    }

    public Integer getAge() {
        return yearOfBirth != null ?
                Period.between(
                    LocalDate.of(yearOfBirth, monthOfBirth != null ? monthOfBirth : Month.JANUARY, 1),
                    LocalDate.now()).getYears() :
                null;
    }

    public String getSkillsString() {
        return (skillA != null ? skillA : "") + (skillB != null ? ", " + skillB : "") + (skillC != null ? ", " + skillC : "");
    }

    public String getSkillsCode() {
        return (skillA != null ? skillA.getCode() : "") + (skillB != null ? ", " + skillB.getCode() : "") + (skillC != null ? ", " + skillC.getCode() : "");
    }

    public String getHouseholdId() {
        return household_id == null && household != null ? household.getId() : household_id;
    }

    public Household getHousehold() {
        if ((household == null && StringUtils.isNotBlank(household_id))
                ||  (StringUtils.isNotBlank(household_id) && household != null && !household.getId().equals(household_id))) {
            this.household = Household.findById(household_id);
            this.household_id = null;
        }

        return household;
    }

    @Override
    public void _save() {
        this.barangay = getHousehold().getBarangay().name();
        this.age = getAge();
        if (status == null) {
            this.status = Status.ALIVE;
        }
        super._save();
    }
}