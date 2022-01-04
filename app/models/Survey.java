package models;


import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Cacheable;
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
@Cacheable
@Builder
@Getter
@Setter
public class Survey extends Model {

    private Date interviewDate;
    private String respondent;

    @Required
    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Household household;
}
