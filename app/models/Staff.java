/* Copyright (C) 2022 Jamaica Ballangan - All Rights Reserved
 * Clients may use and modify this code under the
 * terms and agreement only. Selling or distribution is prohibited
 * without the consent of the author
 */
package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import utils.EncryptionUtils;

import javax.persistence.*;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
@Cacheable
@Builder
@Getter
@Setter
public class Staff extends AbstractModel {

    public enum Status {
        ACTIVE,
        SUSPENDED,
        REMOVED
    }

    private enum Role {
        ADMIN,
        ENCODER,
        VIEWER
    }

    @Id
    private String username;

    @Required
    private String name;

    @Required
    @MinSize(8)
    @Password
    private String password;

    @Required
    @Enumerated(EnumType.STRING)
    private Status status;

    @Required
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setPassword(String password) {
        this.password = encrypt(password);
    }

    @Override
    public String toString() {
        return name;
    }

    public static String encrypt(String password) {
        return BCrypt.hashpw(EncryptionUtils.hash(password), BCrypt.gensalt());
    }

    public final boolean checkPassword(final String compare) {
        return BCrypt.checkpw(EncryptionUtils.hash(compare), this.password);
    }
}
