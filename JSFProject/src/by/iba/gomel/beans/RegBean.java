package by.iba.gomel.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.connectiondb.ConnectionDB2;
import by.iba.gomel.interfaces.IDB2;
import by.iba.gomel.managers.RequestManager;

@ManagedBean(name = "reg")
@RequestScoped
public class RegBean implements Serializable, IDB2 {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(RegBean.class);
    private String              login;
    private String              password;
    private String              confirmedPassword;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setConfirmedPassword(final String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String registeredUser() {
        if (password.equals(confirmedPassword)) {
            return perform();
        } else {
            SessionBean.addErrorMessage(Constants.MESSAGE_REGISTRATION_PASSWORDS_ERROR);
            return null;
        }
    }

    @Override
    public String perform() {
        Statement st = null;
        PreparedStatement pr = null;
        Connection cn = null;
        try {
            cn = ConnectionDB2.getConnection();
            st = cn.createStatement();
            final String gotRequest = RequestManager
                    .getProperty(Constants.PROPERTY_REGISTERED_USER);
            final String readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2)
                    .toString();
            pr = cn.prepareStatement(readyRequest);
            pr.setString(Constants.INDEX_COLUMN_FULLNAME_SQL, login);
            pr.setString(Constants.INDEX_COLUMN_PASSWORD_SQL, password);
            pr.setString(Constants.INDEX_COLUMN_TYPE_USER_SQL, Constants.TYPE_USER);
            pr.executeUpdate();
        } catch (final SQLException e) {
            RegBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
            SessionBean.addErrorMessage(Constants.MESSAGE_REGISTRATION_LOGIN_ERROR);
            return null;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    RegBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    RegBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (pr != null) {
                try {
                    pr.close();
                } catch (final SQLException e) {
                    RegBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        return Constants.RESULT_SUCCESS;
    }

}
