package models;


import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
@Builder
@Getter
@Setter
public class Survey extends Model {

    private Date interviewDate;
    private LocalTime interviewStart;
    private LocalTime interviewEnd;
    private Date encodeDate;
    private String respondent;

    @Required
    private String interviewer;
    @Required
    private String interviewerSupervisor;
    @Required
    private String encoder;
    @Required
    private String encoderSupervisor;

    @Required
    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Household household;
}
