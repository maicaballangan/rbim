package models;

import org.hibernate.annotations.Where;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
        Head,
        Spouse,
        Son,
        Daughter,
        Stepson,
        Stepdaughter,
        SonInLaw,
        DaughterInLaw,
        Grandson,
        Granddaughter,
        Father,
        Mother,
        Brother,
        Sister,
        Uncle,
        Aunt,
        Nephew,
        Niece,
        OtherRelative,
        NonRelative,
        Boarder,
        DomesticHelper
    }

    enum Sex {
        // TODO Q3
        test
    }

    enum Nationality {
        // TODO Q7
        test
    }

    enum MaritalStatus {
        // TODO Q8
        test
    }

    enum Education {
        // TODO Q11
        test
    }

    enum Enrollment {
        ub("Public"), priv("Private"), unenrolled("Unenrolled");

        private String description;

        Enrollment(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    enum SchoolLevel {
        // TODO Q13
        test
    }

    enum ParentalStatus {
        // TODO Q30
        test
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
