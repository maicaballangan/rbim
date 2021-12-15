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
        Employment(1),
        Business(2),
        Remittance(3),
        Investments(4),
        Others(5);

        private final int code;

        IncomeSource(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum WorkStatus {
        Permanent(1),
        Casual(2),
        Contractual(3),
        Individually_Owned(4),
        Business(4),
        Shared_or_Partnership_Business(5),
        Corporate_Business(6);

        private final int code;

        WorkStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    enum Skill {
        Refrigeration_and_Airconditioning(1),
        Automotive_or_Heavy_Equipment_Servicing(2),
        Metal_Worker(3),
        Building_Wiring_Installation(4),
        Heavy_Equipment_Operation(5),
        Plumbing(6),
        Welding(7),
        Carpentry(8),
        Baking(9),
        Dressmaking(10),
        Linguist(11),
        Computer_Graphics(12),
        Painting(13),
        Beauty_Care(14),
        Commercial_Cooking(15),
        Housekeeping(16),
        Massage_Therapy(17),
        Others(18);

        private final int code;

        Skill(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
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
