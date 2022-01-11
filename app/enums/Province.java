/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package enums;

/**
 * @author Maica Ballangan
 * @since v1
 */
public enum Province {
    KALINGA;

    @Override
    public String toString() {
        return name().replaceAll("_", " ");
    }
}
