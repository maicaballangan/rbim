package models;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import utils.EncryptionUtils;

/**
 * @author Maica Ballangan
 * @since v1
 */
@Entity
@Builder
@Getter
@Setter
public class Staff extends GenericModel {

    public enum Status {
        Active,
        Inactive,
        Suspended,
        Removed
    }

    private enum Role {
        Encoder,
        Supervisor,
        Interviewer
    }

    @Id
    @Required
    private String id;

    @Required
    private String name;

    @Required
    @MinSize(8)
    private String password;

    @Required
    @Enumerated(EnumType.STRING)
    private Status status;

    @Required
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(EncryptionUtils.hash(password), BCrypt.gensalt());
    }

    @Override
    public String toString() {
        return name;
    }

    public final boolean checkPassword(final String compare) {
        return BCrypt.checkpw(EncryptionUtils.hash(compare), this.password);
    }
}
