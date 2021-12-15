package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import enums.Barangay;
import enums.Municipality;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
public class Resident extends Model {

    enum Relation {
        Head(1),
        Spouse(2),
        Son(3),
        Daughter(4),
        Stepson(5),
        Stepdaughter(6),
        Son_In_Law(7),
        Daughter_In_Law(8),
        Grandson(9),
        Granddaughter(10),
        Father(11),
        Mother(12),
        Brother(13),
        Sister(14),
        Uncle(15),
        Aunt(16),
        Nephew(17),
        Niece(18),
        Other_Relative(19),
        NonRelative(20),
        Boarder(21),
        Domestic_Helper(22);

        private final int code;

        Relation(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Sex {
        Male(1),
        Female(2);

        private final int code;

        Sex(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Nationality {
        Filipino(1),
        Non_Filipino(2);

        private final int code;

        Nationality(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum MaritalStatus {
        Single(1),
        Married(2),
        LivingIn(3),
        Widowed(4),
        Separated(5),
        Divorced(6),
        Unknown(7);

        private final int code;

        MaritalStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Education {
        None(00),
        PreSchool(1),
        Elementary(2),
        Elementary_Graduate(3),
        HighSchool(4),
        HighSchool_Graduate(5),
        Junior_HS(6),
        Junior_HS_graduate(7),
        Senior_HS(8),
        Senior_HS_graduate(9),
        Vocational_or_Tech(10),
        College(11),
        College_Graduate(12),
        Post_Graduate(13);

        private final int code;

        Education(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Enrollment {
        PUBLIC(1),
        PRIVATE(2),
        UNENROLLED(3);

        private final int code;

        Enrollment(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum SchoolLevel {
        Pre_School(0),
        Elementary(1),
        Junior_HS(2),
        Senior_HS(3),
        Vocational_or_Technical(4),
        College_or_University(5);

        private final int code;

        SchoolLevel(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum ParentalStatus {
        Registered_Solo_Parent(1),
        Non_Solo_Parent(2),
        Unregistered_Solo_Parent(3);

        private final int code;

        ParentalStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

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

    @Required
    private Date dateOfBirth;

    @Required
    @Enumerated(EnumType.STRING)
    private Municipality placeOfBirth;

    @Required
    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Required
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Required
    private String religion;

    @Required
    private String ethnicity;

    @Required
    @Enumerated(EnumType.STRING)
    private ParentalStatus parentalStatus;

    @Required
    private boolean registeredSeniorCitizen;

    @Required
    @Enumerated(EnumType.STRING)
    private Education educationalAttainment;

    @Required
    @Enumerated(EnumType.STRING)
    private Enrollment enrollmentStatus;

    @Required
    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    @Required
    private String placeOfSchool;

    @Enumerated(EnumType.STRING)
    private Barangay votingArea;

    private String email;

    @OneToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    //@JoinColumn(name = "id")
    @PrimaryKeyJoinColumn
    private Household household;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Relation getRelationshipToHead() {
        return relationshipToHead;
    }

    public void setRelationshipToHead(Relation relationshipToHead) {
        this.relationshipToHead = relationshipToHead;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Municipality getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(Municipality placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public ParentalStatus getParentalStatus() {
        return parentalStatus;
    }

    public void setParentalStatus(ParentalStatus parentalStatus) {
        this.parentalStatus = parentalStatus;
    }

    public boolean isRegisteredSeniorCitizen() {
        return registeredSeniorCitizen;
    }

    public void setRegisteredSeniorCitizen(boolean registeredSeniorCitizen) {
        this.registeredSeniorCitizen = registeredSeniorCitizen;
    }

    public Education getEducationalAttainment() {
        return educationalAttainment;
    }

    public void setEducationalAttainment(Education educationalAttainment) {
        this.educationalAttainment = educationalAttainment;
    }

    public Enrollment getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(Enrollment enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public SchoolLevel getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(SchoolLevel schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getPlaceOfSchool() {
        return placeOfSchool;
    }

    public void setPlaceOfSchool(String placeOfSchool) {
        this.placeOfSchool = placeOfSchool;
    }

    public Barangay getVotingArea() {
        return votingArea;
    }

    public void setVotingArea(Barangay votingArea) {
        this.votingArea = votingArea;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + " " + middleName;
    }
}
