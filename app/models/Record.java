/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import enums.Relation;
import lombok.Builder;
import lombok.Getter;
import play.Logger;
import play.data.validation.Validation;

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

    public void create(Validation validation) {
        // Fetch existing using resident details
        if (resident.getExisting() != null) {
            Logger.warn("Record on row %s with resident name %s, %s already exists",
                    row, resident.getLastName(), resident.getFirstName());
            validation.addError(
                    ""+row,
                    "Resident with name " + resident.getLastName()+ ", " + resident.getFirstName() + " already exists");
            return; // Don't save existing record
        }

        // Save household, and survey information
        Household householdExisting = household.getExisting();
        if (householdExisting != null) {
            resident.setHousehold(householdExisting);
        } else {
            household.create();
            resident.setHousehold(household);
        }

        resident.create();
        //Logger.info("Successfully saved record on row %s with name %s", row, health.getDescription());
    }
}
