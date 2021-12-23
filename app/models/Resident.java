package models;

import java.util.Date;

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

import enums.Barangay;
import enums.CivilStatus;
import enums.Education;
import enums.Enrollment;
import enums.Nationality;
import enums.ParentalStatus;
import enums.Relation;
import enums.SchoolLevel;
import enums.Sex;
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
@SequenceGenerator(initialValue = 10000000, name = "idgen", sequenceName = "residentSeq")
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

    private int age;
    private Date dateOfBirth;

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

    @Enumerated(EnumType.STRING)
    private ParentalStatus parentalStatus;

    @Enumerated(EnumType.STRING)
    private YesOrNo registeredSeniorCitizen;

    @Enumerated(EnumType.STRING)
    private Education educationalAttainment;

    @Enumerated(EnumType.STRING)
    private Enrollment enrollmentStatus;

    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    private String placeOfSchool;

    @Enumerated(EnumType.STRING)
    private Barangay votingArea;

    private String email;

    @ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Household household;

    @Override
    public String toString() {
        return id + ": " + lastName + ", " + firstName + " " + middleName;
    }

    public Resident getExisting() {
        return Resident.find("lastName = ?1 and firstName = ?2 and middleName = ?3 " +
                "and placeOfBirthBrgy = ?4 and dateOfBirth = ?5", lastName, firstName, middleName, placeOfBirthBrgy, dateOfBirth).first();
    }
}
