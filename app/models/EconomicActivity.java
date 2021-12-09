package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
public class EconomicActivity extends Model {

    enum IncomeSource {
        // TODO q16
    }

    enum WorkStatus {
        // TODO q17
    }

    enum Skill {
        // TODO q44
    }

    @Required
    private String monthlyIncome;

    @Required
    private IncomeSource sourceOfIncome;

    @Required
    private WorkStatus workStatus;
    private String placeOfWork;
    private boolean hasCTC;
    private boolean CTCIssuedInBarangay;

    @ElementCollection(targetClass=Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Resident resident;

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
