package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

/**
 * @author Maica Ballangan
 * @since v1
 */
@Table(
    uniqueConstraints=
    @UniqueConstraint(columnNames={"lastName", "firstName", "middleName", "placeOfBirthBrgy", "dateOfBirth"})
)
@Entity
@SequenceGenerator(initialValue = 10000000, name = "idgen", sequenceName = "residentSeq")
@Builder
@Getter
@Setter
public class Resident extends Model {

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
    private int age;

    @Required
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

    @Required
    private String religion;

    @Required
    private String ethnicity;

    @Required
    @Enumerated(EnumType.STRING)
    private ParentalStatus parentalStatus;

    @Required
    @Enumerated(EnumType.STRING)
    private YesOrNo registeredSeniorCitizen;

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
    @JoinColumn
    private EconomicActivity economicActivity;

    @OneToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Health health;

    @OneToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Residency residency;

    @ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Household household;

    @Override
    public String toString() {
        return lastName + ", " + firstName + " " + middleName;
    }

    public void findByName() {
        /*Criteria criteria = session.createCriteria(this);
        String head = cellIterator.next().getStringCellValue();
        List<Resident> list = criteria.add(Restrictions.eq("yourField", yourFieldValue)).list();*/
    }
}
