package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import enums.IncomeSource;
import enums.Skill;
import enums.WorkStatus;
import enums.YesOrNo;
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
public class EconomicActivity extends Model {

    @Required
    private Double monthlyIncome;

    @Required
    private IncomeSource sourceOfIncome;

    @Required
    private WorkStatus workStatus;
    private String placeOfWork;
    private YesOrNo hasCTC;
    private YesOrNo CTCIssuedInBarangay;

    @ElementCollection(targetClass= Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private Resident resident;
}
