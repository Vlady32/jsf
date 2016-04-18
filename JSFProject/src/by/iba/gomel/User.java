package by.iba.gomel;

import java.io.Serializable;

/**
 * This class contains fields and methods for working with user.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            userName;
    private String            type;

    /**
     * 
     * @param userName
     *            username.
     * @param type
     *            user's type.
     */
    public User(final String userName, final String type) {
        this.userName = userName;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
