package models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import enums.IncomeSource;
import enums.Skill;
import enums.WorkStatus;
import enums.YesOrNo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import play.data.validation.Required;

/**
 * @author Maica Ballangan
 * @since v1
 */
/*@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"resident_id"})
)*/
@Entity
@Builder
@Getter
@Setter
public class EconomicActivity extends Model {

    private Double monthlyIncome;
    private IncomeSource sourceOfIncome;
    private WorkStatus workStatus;
    private String placeOfWork;
    private YesOrNo hasCTC;
    private YesOrNo CTCIssuedInBarangay;

    @ElementCollection(targetClass= Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    @OneToOne
    @JoinColumn
    @Required
    private Resident resident;
}
