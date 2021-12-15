package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import enums.Barangay;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
public class Residency extends Model {

    enum Status {
        Current, Previous
    }

    enum Type {
        NonMigrant(1),
        Migrant(2),
        Transient(3);

        private final int code;

        Type(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum ReasonForLeaving {
        Lack_of_Employment(1),
        Perception_of_better_income_in_other_place(2),
        Schooling(3),
        Presence_of_relatives_and_friends_in_other_place(4),
        Employment_or_Job_Relocation(5),
        Disaster_related_Relocation(6),
        Retirement(7),
        To_live_with_parents(8),
        To_live_with_children(9),
        Marriage(10),
        Annulment_Divorce_Separation(11),
        Commuting_related_Reasons(12),
        Health_related_Reasons(13),
        Peacde_and_Security(14),
        Others(15);

        private final int code;

        ReasonForLeaving(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum ReasonForTransfer {
        Availability_of_Jobs(1),
        Higher_wage(2),
        Presence_of_schools_or_Universities(3),
        Presence_of_relatives_and_friends_in_other_place(4),
        Housing(5);

        private final int code;

        ReasonForTransfer(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    @Required
    @Enumerated(EnumType.STRING)
    private Status status;

    @Required
    @Enumerated(EnumType.STRING)
    private Type type;

    @Required
    @Enumerated(EnumType.STRING)
    private Barangay barangay;

    @Required
    private int yearsOfStay;

    @Required
    private int monthsOfStay;

    @ElementCollection(targetClass= ReasonForLeaving.class)
    private List<ReasonForLeaving> reasonForLeaving;
    private boolean intentOfReturning;

    @ElementCollection(targetClass= ReasonForTransfer.class)
    private List<ReasonForTransfer> reasonForTransfer;
    private int durationOfStay;

    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Resident resident;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
