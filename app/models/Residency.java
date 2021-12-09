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
        // TODO Q36
    }

    enum ReasonForLeaving {
        // TODO Q38
    }

    enum ReasonForTransfer {
        // TODO Q40
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
