/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import controllers.CRUD;
import enums.Barangay;
import enums.CivilStatus;
import enums.DeliveryPlace;
import enums.Education;
import enums.Enrollment;
import enums.FPMethod;
import enums.Facility;
import enums.HealthInsurance;
import enums.IncomeSource;
import enums.Municipality;
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
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

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
@SequenceGenerator(initialValue = 1000000, name = "resident", sequenceName = "residentSeq")
@Builder
@Getter
@Setter
public class Resident extends GenericModel {

    public enum Status {
        ALIVE, DECEASED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resident")
    public Long id;

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

    private Integer dateOfBirth;

    private Integer yearOfBirth;

    private Month monthOfBirth;

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

    private String disability;

    // Economic Status
    @Enumerated(EnumType.STRING)
    private ParentalStatus parentalStatus;

    @Enumerated(EnumType.STRING)
    private YesOrNo registeredSeniorCitizen;

    @Enumerated(EnumType.STRING)
    private YesOrNo registeredBirth;

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
    private ReasonForTransfer reasonForTransferA;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferB;

    @Enumerated(EnumType.STRING)
    private ReasonForTransfer reasonForTransferC;

    @Enumerated(EnumType.STRING)
    private YesOrNo intentOfReturning;
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
    @ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Household household;

    @CRUD.Hidden
    @Enumerated(EnumType.STRING)
    private Barangay barangay;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ALIVE;

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

    public Integer getAge() {
        return yearOfBirth != null ?
                Period.between(
                    LocalDate.of(yearOfBirth,
                            monthOfBirth != null ? monthOfBirth : Month.JANUARY,
                            dateOfBirth != null ? dateOfBirth : 1),
                    LocalDate.now()).getYears() :
                null;
    }

    public String getSkillsString() {
        return (skillA != null ? skillA : "") + (skillB != null ? ", " + skillB : "") + (skillC != null ? ", " + skillC : "");
    }

    public String getSkillsCode() {
        return (skillA != null ? skillA.getCode() : "") + (skillB != null ? ", " + skillB.getCode() : "") + (skillC != null ? ", " + skillC.getCode() : "");
    }

    @Override
    public void _save() {
        this.barangay = household.getBarangay();
        this.age = getAge();
        if (status == null) {
            this.status = Status.ALIVE;
        }
        super._save();
    }
}