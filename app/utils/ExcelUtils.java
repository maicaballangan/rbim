/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import enums.Barangay;
import enums.BuildingMaterial;
import enums.BuildingType;
import enums.CivilStatus;
import enums.Cooking;
import enums.DeliveryPlace;
import enums.Education;
import enums.Enrollment;
import enums.FPMethod;
import enums.Facility;
import enums.GarbageDisposal;
import enums.HealthInsurance;
import enums.IncomeSource;
import enums.Lighting;
import enums.Municipality;
import enums.Nationality;
import enums.Ownership;
import enums.RegisteredSoloParent;
import enums.Province;
import enums.ReasonForLeaving;
import enums.ReasonForTransfer;
import enums.ReasonOfVisit;
import enums.Relation;
import enums.ResidentType;
import enums.SchoolLevel;
import enums.Sex;
import enums.Skill;
import enums.SourceOfFP;
import enums.ToiletFacility;
import enums.WaterSource;
import enums.WorkStatus;
import enums.YesOrNo;
import models.Household;
import models.Resident;
import play.Logger;
import play.data.validation.Validation;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL;

/**
 * @author Maica Ballangan
 * @since v1
 */
public class ExcelUtils {

    public static void importExcel(File file, boolean saveHead) throws IOException {
        // Make sure to sort using Relation field so that head will be processed first
        FileInputStream fis = new FileInputStream(file);
        String fileName = file.getName().replace(".xlsx", "");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> itr = sheet.iterator();
        itr.next(); // skip header

        while (itr.hasNext()) {
            Row row = itr.next();
            try {
                // Save head or nonhead only depending on saveHead value
                if (Relation.HEAD.equals(Relation.getByCode(getIntCellValue(row, 30))) != saveHead) {
                    continue;
                }

                // Skip if name is blank
                if (row.getCell(27, RETURN_BLANK_AS_NULL) == null && row.getCell(28, RETURN_BLANK_AS_NULL) == null) {
                    continue;
                }

                Household.HouseholdBuilder hhb = Household.builder();
                Resident.ResidentBuilder rb = Resident.builder();

                Relation relation = Relation.getByCode(getIntCellValue(row, 30));
                Barangay barangay = Barangay.getByDescription(getStringCellValue(row, 6));
                if (barangay == null || Barangay.OTHERS.equals(barangay)) {
                    barangay = Barangay.getByDescription(fileName.toUpperCase());
                }
                hhb.barangay(barangay);
                hhb.houseNo(getStringCellValue(row, 8));
                hhb.blockNo(getStringCellValue(row, 11));
                hhb.street(getStringCellValue(row, 12));
                hhb.respondent(getStringCellValue(row, 13));
                hhb.head(getStringCellValue(row, 14));
                hhb.totalNo(getIntCellValue(row, 15)); // Should it be computed?#
                hhb.municipality(Municipality.TABUK_CITY);
                hhb.province(Province.KALINGA);

                hhb.interviewDate(getDateCellValue(row, 17));
                hhb.houseOwnership(Ownership.getByCode(getIntCellValue(row, 87)));
                hhb.lotOwnership(Ownership.getByCode(getIntCellValue(row, 88)));
                hhb.lighting(Lighting.getByCode(getIntCellMaxValue(row, 89)));
                hhb.cooking(Cooking.getByCode(getIntCellMaxValue(row, 90)));
                WaterSource waterSource = WaterSource.getByCode(getIntCellMaxValue(row, 91));
                if (WaterSource.OTHERS == waterSource) {
                    String waterSourceS = getStringCellValue(row, 91);
                    if ("MINERAL".equals(waterSourceS) || "MINIRAL".equals(waterSourceS)
                            || "REFFILED".equals(waterSourceS) || "REFILLED".equals(waterSourceS)) waterSource = WaterSource.BOTTLED_WATER;
                    if ("PUMPWELL".equals(waterSourceS)) waterSource = WaterSource.OWN_USE_TUBED_OR_PIPED_DEEP_WELL;
                }
                hhb.waterSource(waterSource);
                hhb.garbageDisposal(GarbageDisposal.getByCode(getIntCellMaxValue(row, 92)));
                hhb.hasTrashSegregation(YesOrNo.getByCodeOrDescription(getStringCellValue(row, 93)));
                hhb.toiletFacility(ToiletFacility.getByCode(getIntCellMaxValue(row, 94)));
                hhb.buildingType(BuildingType.getByCode(getIntCellMaxValue(row, 95)));
                hhb.buildingMaterial(BuildingMaterial.getByCode(getIntCellMaxValue(row, 96)));

                hhb.intentToStayFiveYrsBrgy(Barangay.getByDescription(getStringCellValue(row, 108)));
                hhb.intentToStayFiveYrsMunicipality(Municipality.getByDescription(getStringCellValue(row, 109)));
                hhb.intentToStayFiveYrsProvince(Province.getByDescription(getStringCellValue(row, 110)));

                // Resident info
                rb.lastName(getStringCellValue(row, 27));
                rb.firstName(getStringCellValue(row, 28));
                String middleName = getStringCellValue(row, 29);
                if (middleName != null) {
                    rb.middleName(middleName.replace(".", "")); //30
                }
                rb.relationshipToHead(relation);
                rb.sex(Sex.getByCode(getIntCellValue(row, 31)));
                //rb.age(getIntCellValue(row, 32));
                rb.yearOfBirth(getIntCellValue(row, 33)); //34
                rb.monthOfBirth(getMonthCellValue(row, 34));
                rb.placeOfBirthBrgy(Barangay.getByDescription(getStringCellValue(row, 35)));
                rb.placeOfBirthMunicipality(Municipality.getByDescription(getStringCellValue(row, 36)));
                rb.nationality(Nationality.getByCode(getIntCellValue(row, 37)));
                rb.civilStatus(CivilStatus.getByCode(getIntCellValue(row, 38))); //40
                rb.religion(getStringCellValue(row, 39));
                rb.ethnicity(getStringCellValue(row, 40));
                rb.educationalAttainment(Education.getByCode(getIntCellValue(row, 41)));
                rb.enrollmentStatus(Enrollment.getByCode(getIntCellValue(row, 42)));
                rb.schoolLevel(SchoolLevel.getByCode(getIntCellValue(row, 43))); //45
                rb.placeOfSchool(getStringCellValue(row, 44));

                // Economic Activity
                rb.monthlyIncome(getIntCellValueRemoveString(row, 45));
                rb.sourceOfIncome(IncomeSource.getByCode(getIntCellValue(row, 46)));
                rb.workStatus(WorkStatus.getByCode(getIntCellValue(row, 47)));
                rb.placeOfWork(getStringCellValue(row, 48)); //50

                // Health Record
                rb.placeOfDelivery(DeliveryPlace.getByCode(getIntCellValue(row, 49)));
                rb.birthAttendant(getStringCellValue(row, 50));
                rb.immunization(getStringCellValue(row, 51));
                rb.livingChildren(getIntCellValue(row, 52));
                rb.livingChildrenSub(getIntCellValue(row, 53)); //55
                rb.FPMethod(FPMethod.getByCode(getIntCellMaxValue(row, 54)));
                rb.sourceOfFP(SourceOfFP.getByCode(getIntCellMaxValue(row, 55)));
                rb.intentToUseFP(YesOrNo.getByCode(getIntCellMaxValue(row, 56)));
                rb.intentToUseFPSub(FPMethod.getByCode(getIntCellMaxValue(row, 57)));
                rb.healthInsurance(HealthInsurance.getByCode(getIntCellMaxValue(row, 58))); //60
                rb.facility(Facility.getByCode(getIntCellMaxValue(row, 59)));
                rb.reasonOfVisit(ReasonOfVisit.getByCode(getIntCellMaxValue(row, 60))); // If non-numeric catch exception and set 99

                if ("NONE".equals(getStringCellValue(row, 61))) {
                    rb.disability(YesOrNo.NO);
                } else {
                    Integer disability = getIntCellValue(row, 61);
                    if (disability != null && disability == -1) {
                        rb.disability(YesOrNo.YES);
                    } else {
                        rb.disability(YesOrNo.getByCode(getIntCellValueRemoveString(row, 61)));
                    }
                }
                rb.registeredSoloParent(RegisteredSoloParent.getByCode(getIntCellValue(row, 62)));
                rb.registeredSeniorCitizen(YesOrNo.getByCodeOrDescription(getStringCellValue(row, 63))); //65
                rb.votingArea(Barangay.getByDescription(getStringCellValue(row, 64)));
                // Residency Info
                rb.previousBarangayFiveYr(Barangay.getByDescription(getStringCellValue(row, 65)));
                rb.previousMunicipalityFiveYr(Municipality.getByDescription(getStringCellValue(row, 66)));
                rb.previousBarangaySixMo(Barangay.getByDescription(getStringCellValue(row, 67)));
                rb.previousMunicipalitySixMo(Municipality.getByDescription(getStringCellValue(row, 68))); //70
                rb.yearsOfStay(getIntCellValueRemoveString(row, 69));
                rb.monthsOfStay(getIntCellValueRemoveString(row, 70));
                rb.residentType(ResidentType.getByCode(getIntCellValue(row, 71)));
                rb.monthOfTransfer(getMonthCellValue(row, 72));
                rb.yearOfTransfer(getIntCellValue(row, 73)); //75
                rb.reasonForLeavingA(ReasonForLeaving.getByCode(getIntCellMaxValue(row, 74)));
                rb.reasonForLeavingB(ReasonForLeaving.getByCode(getIntCellMaxValue(row, 75)));
                rb.reasonForLeavingC(ReasonForLeaving.getByCode(getIntCellMaxValue(row, 76)));
                //row, ; // Q39-returntopreviousresident
                //row, ; // Q39-returntopreviousresident-Response  //80
                rb.reasonForTransferA(ReasonForTransfer.getByCode(getIntCellMaxValue(row, 79)));
                rb.reasonForTransferB(ReasonForTransfer.getByCode(getIntCellMaxValue(row, 80)));
                rb.reasonForTransferC(ReasonForTransfer.getByCode(getIntCellMaxValue(row, 81)));
                rb.durationOfStay(getIntCellValueRemoveString(row, 82));
                rb.CTCIssued(YesOrNo.getByCodeOrDescription(getStringCellValue(row, 83))); //85
                rb.CTCIssuedInBarangay(YesOrNo.getByCodeOrDescription(getStringCellValue(row, 84)));
                //row, ; // "Q43-skill development training
                String skills = getStringCellValue(row, 85);
                if (!isUndefined(skills)) {
                    String[] values = skills.split("&|,|\\.|\\*|/");
                    if (values.length > 0) rb.skillA(Skill.getByCodeOrDescription(values[0]));
                    if (values.length > 1) rb.skillB(Skill.getByCodeOrDescription(values[1]));
                    if (values.length > 2) rb.skillC(Skill.getByCodeOrDescription(values[2]));
                }

                //cellIterator.next(); //Q58 brgy
                //cellIterator.next(); //Q58 municipality
                //cellIterator.next(); //Q58 province

                Household household = hhb.build();
                Resident resident = rb.build();

                // Fetch existing using resident details
                if (resident.getExisting() != null) {
                    Logger.warn("Record on row %s with resident name %s, %s already exists",
                            row.getRowNum()+1, resident.getLastName(), resident.getFirstName());
                    Validation.addError(
                            row.getRowNum()+1 + "",
                            "Resident with name " + resident.getLastName()+ ", " + resident.getFirstName() + " already exists");
                    continue; // Don't save existing record
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
            } catch(Exception e) {
                Logger.fatal("Failed to parse row %s [Error: %s]", row.getRowNum()+1, e.getMessage());
                Validation.addError(row.getRowNum()+1+"", "FATAL: Failed to parse row" + e.getMessage());
            }
        }
        //return records;
    }

    private static Integer getIntCellValue(Row row, int rowNum) {
        Cell cell = row.getCell(rowNum, RETURN_BLANK_AS_NULL);
        if (isUndefined(cell)) return null;
        if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
            return Double.valueOf(cell.getNumericCellValue()).intValue();
        } else if (CellType.STRING.equals(cell.getCellTypeEnum())) {
            return parseInt(cell.getStringCellValue());
        }
        return null;
    }

    private static String getStringCellValue(Row row, int rowNum) {
        Cell cell = row.getCell(rowNum, RETURN_BLANK_AS_NULL);
        if (isUndefined(cell)) return null;
        if (CellType.STRING.equals(cell.getCellTypeEnum())) {
            return cell.getStringCellValue().trim().toUpperCase();
        } else if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
            return "" + Double.valueOf(cell.getNumericCellValue()).intValue();
        }
        return null;
    }

    private static Integer getIntCellValueRemoveString(Row row, int rowNum) {
        Cell cell = row.getCell(rowNum, RETURN_BLANK_AS_NULL);
        if (isUndefined(cell)) return null;
        if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
            return Double.valueOf(cell.getNumericCellValue()).intValue();
        } else if (CellType.STRING.equals(cell.getCellTypeEnum())) {
            String number = cell.getStringCellValue().replaceAll("\\D+","");
            return parseInt(number);
        }
        return null;
    }

    private static Integer getIntCellMaxValue(Row row, int rowNum) {
        Cell cell = row.getCell(rowNum, RETURN_BLANK_AS_NULL);
        if (isUndefined(cell)) return null;
        if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
            return getIntCellValue(row, rowNum);
        } else if (CellType.STRING.equals(cell.getCellTypeEnum())) {
            String value = getStringCellValue(row, rowNum);
            if (isUndefined(value)) return null;
            return Arrays.stream(value.split("&|,|\\.|\\*|/"))
                    .filter(s -> !s.isBlank())
                    .map(r -> parseInt(r))
                    .max(Integer::compare)
                    .get();
        }
        return null;
    }

    private static Month getMonthCellValue(Row row, int rowNum) {
        Cell cell = row.getCell(rowNum, RETURN_BLANK_AS_NULL);
        if (isUndefined(cell)) return null;
        if (CellType.STRING.equals(cell.getCellTypeEnum())) {
            String value = cell.getStringCellValue();
            try {
                return Month.of(Integer.parseInt(value));
            } catch (NumberFormatException nfe) {
                if (Arrays.stream(Month.values()).anyMatch(m -> m.toString().equals(value.toUpperCase())))
                return Month.valueOf(value.toUpperCase());
            }
        } else if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
            int month = getIntCellValue(row, rowNum);
            if (month < 1 || month > 12) return null;
            return Month.of(month);
        }
        return null;
    }

    private static Date getDateCellValue(Row row, int rowNum) {
        Cell cell = row.getCell(rowNum, RETURN_BLANK_AS_NULL);
        if (isUndefined(cell)) return null;
        if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
            return cell.getDateCellValue();
        }
        return null;
    }

    private static boolean isUndefined(String value) {
        return value == null || value.isBlank() || "99" .equals(value) || "98" .equals(value);
    }

    private static boolean isUndefined(Double value) {
        return value == null || value == 99.0D || value == 98.0D;
    }

    private static boolean isUndefined(Cell cell) {
        if (cell == null) {
            return true;
        } else if (CellType.STRING.equals(cell.getCellTypeEnum())) {
            return isUndefined(cell.getStringCellValue());
        } else if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
            return isUndefined(cell.getNumericCellValue());
        } else {
            return false;
        }
    }

    public static Integer parseInt(String value) {
        try {
            if (value.isBlank()) return null;
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException nfe) {
            Logger.warn("Failed to parse column with value %s. Value will be set as Others.", value);
            return -1;
        }
    }
}
