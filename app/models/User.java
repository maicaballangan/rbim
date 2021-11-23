package models;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.Id;

import constants.Constants;
import interfaces.Retainable;
import play.data.validation.Check;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import utils.EncryptionUtils;
import utils.RegexMatcher;
import utils.SerializationUtils;

/**
 * User
 *
 * @author Maica Ballangan
 * @since v1
 */
@Entity
public class User extends GenericModel implements Retainable {

    public enum Status {
        Active,
        Inactive,
        Suspended,
        Removed
    }

    @Id
    @Required
    @MinSize(8)
    private String id;

    @Required
    private String name;

    @Required
    @MinSize(8)
    private String password;

    @Required
    private Status status;

    // TODO: v2
    // private String role;
    // private String email;
    // private String mobile;

    /**
     * Empty constructor - This is necessary for polymorphism
     */
    public User() {
        // Required for polymorphism, default constructor for deserializers
    }

    public User(final String id) {
        this.id = id.trim().toLowerCase();
    }

    /**
     * Constructor using the Builder class
     *
     * @param builder builder class
     */
    private User(final Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        //this.email = builder.email;
        this.status = builder.status;
        //this.role = builder.role;
        //this.mobile = builder.mobile;
    }

    public String getId() {
        return id;
    }

    public boolean cannotLogin() {
        return isSuspended() || isInActive();
    }

    public boolean isActive() {
        return getStatus() == Status.Active;
    }

    public boolean isInActive() {
        return getStatus() == Status.Inactive;
    }

    public boolean isRemoved() {
        return getStatus() == Status.Removed;
    }

    public boolean isSuspended() {
        return getStatus() == Status.Suspended;
    }

    @Override
    public Status getRemoveStatus() {
        return Status.Removed;
    }

    public String getPassword() {
        return password;
    }

    public final boolean checkPassword(final String compare) {
        return BCrypt.checkpw(EncryptionUtils.hash(compare), this.password);
    }

    protected static String hashPassword(final String plaintext) {
        return BCrypt.hashpw(EncryptionUtils.hash(plaintext), BCrypt.gensalt());
    }

    public String getName() {
        return name;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(final Enum<?> status) {
        this.status = (Status) status;
    }

    /**
     * Populate record from the json representation - deserialize json to record
     *
     * @param json list the documents to be deserialized
     * @return list of the populated records
     */
    public User populate(final String json) {
        return SerializationUtils.deserialize(json, User.class);
    }

    @Override
    public String toString() {
        return SerializationUtils.serialize(this);
    }

    public static class PasswordCheck extends Check {

        public boolean isSatisfied(Object user, Object password) {
            setMessage(Constants.PASSWORD_VALIDATION_ERROR);
            return RegexMatcher.matches(Constants.PASSWORD_MATCHER).matches(password);
        }
    }

    /**
     * BUILDERS
     */
    public static class Builder {

        @Required
        private String username;
        @Required
        private String name;
        @Required
        private Status status;
        private String password;

        public Builder(final User user) {
            this.username = user.id;
            this.name = user.name;
            this.password = user.password;
            this.status = user.status;
        }

        public Builder(final String username) {
            this.username = username.toLowerCase();
        }

        public Builder name(final String name) {
            if (name != null) this.name = name.trim();
            return this;
        }

        public Builder password(final String password) {
            if (password != null) this.password = hashPassword(password);
            return this;
        }

        public Builder status(final Status status) {
            if (status != null) this.status = status;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}