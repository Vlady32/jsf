package by.iba.gomel.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
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

@ManagedBean(name = "login")
@RequestScoped
/**
 * This bean uses for working with loginning users.
 */
public class LoginBean implements Serializable, IDB2 {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(LoginBean.class);

    private String              userName;
    private String              password;

    public String validateUser() {
        return perform();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String perform() {
        Statement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String typeUser = null;
        try {
            cn = ConnectionDB2.getConnection();
            st = cn.createStatement();
            final String gotRequest = RequestManager.getProperty(Constants.PROPERTY_CHECK_USER);
            final String readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2,
                    userName, password).toString();
            rs = st.executeQuery(readyRequest);
            if (rs.next()) {
                typeUser = rs.getString(Constants.INDEX_COLUMN_TYPE_SQL);
            }
            if (typeUser != null) {
                SessionBean.setAttributesSession(Constants.ATTRIBUTE_NAME_LOGIN, userName);
                SessionBean.setAttributesSession(Constants.ATTRIBUTE_NAME_TYPE, typeUser);
                return Constants.RESULT_SUCCESS;
            } else {
                SessionBean.addErrorMessage(Constants.MESSAGE_LOGIN_ERROR,
                        Constants.TAG_ERROR_MESSAGE_PASSWORD);
                return null;
            }
        } catch (final SQLException e) {
            LoginBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    LoginBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    LoginBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException e) {
                    LoginBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        return Constants.RESULT_FAIL;
    }

}
