package models;

import enums.Relation;
import lombok.Builder;
import lombok.Getter;
import play.Logger;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Getter
@Builder
public class Record {
    private int row;
    private Resident resident;
    private Household household;
    private Survey survey;

    public void create() {
        try {
            // Fetch existing using resident details
            if (resident.getExisting() != null) {
                Logger.warn("Record on row %s with resident name %s, %s already exists",
                        row, resident.getLastName(), resident.getFirstName());
                return; // Don't save existing record
            }

            // Save household, and survey information
            if (Relation.Head.equals(resident.getRelationshipToHead())) {
                household.create();
                survey.setHousehold(household);
                survey.create();
            } else {
                String head = household.getHead();
                household = household.getExisting();
                if (household == null) {
                    Logger.error("Can't find household for row %s with head name %s", row, head);
                    return;
                }
            }

            resident.setHousehold(household);
            resident.create();
            //Logger.info("Successfully saved record on row %s with name %s", row, health.getDescription());
        } catch(Exception e) {
            Logger.error("Failed to save record on row %s [Error: %s]", getRow(), e.getMessage());
        }
    }
}
