package models;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import controllers.CRUD;
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
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"resident_id"})
)
@Entity
@Cacheable
@Builder
@Getter
@Setter
public class EconomicActivity extends Model<EconomicActivity> {

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    @Required
    private Resident resident;

    private String monthlyIncome;

    @Enumerated(EnumType.STRING)
    private IncomeSource sourceOfIncome;

    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    private String placeOfWork;

    private Integer CTCIssuedYear;

    @Enumerated(EnumType.STRING)
    private YesOrNo CTCIssuedInBarangay;

    @ElementCollection(targetClass= Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    @CRUD.Exclude
    private String description;

    public void setResident(Resident resident) {
        this.resident = resident;
        this.description = resident.toString();
    }

    @Override
    public String toString() {
        return description;
    }
}
