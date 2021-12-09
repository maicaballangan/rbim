package models;


import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
public class Survey extends Model {

    private Date interviewDate;
    private LocalTime interviewStart;
    private LocalTime interviewEnd;
    private Date encodeDate;

    @Required
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Staff interviewer;

    @Required
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Staff interviewerSupervisor;

    @Required
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Staff encoder;

    @Required
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Staff encoderSupervisor;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Household household;

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }
}
