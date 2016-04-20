package by.iba.gomel.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.connectiondb.ConnectionDB2;
import by.iba.gomel.interfaces.IDB2;
import by.iba.gomel.managers.RequestManager;

@ManagedBean(name = "deleteUserPage")
@ViewScoped
/**
 * This bean uses for deleting user from database.
 */
public class DeleteUserBean implements Serializable, IDB2 {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(DeleteUserBean.class);
    private String              userName;

    public void deleteUser(final String userName) {
        this.userName = userName;
        perform();
    }

    @Override
    public String perform() {
        Statement st = null;
        PreparedStatement pr = null;
        Connection cn = null;
        try {
            cn = ConnectionDB2.getConnection();
            st = cn.createStatement();
            final String gotRequest = RequestManager.getProperty(Constants.PROPERTY_DELETE_USER);
            final String readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2)
                    .toString();
            pr = cn.prepareStatement(readyRequest);
            pr.setString(Constants.INDEX_COLUMN_FULLNAME_SQL, userName);
            pr.executeUpdate();
        } catch (final SQLException e) {
            DeleteUserBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
            return Constants.RESULT_FAIL;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    DeleteUserBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    DeleteUserBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (pr != null) {
                try {
                    pr.close();
                } catch (final SQLException e) {
                    DeleteUserBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        return Constants.RESULT_SUCCESS;
    }

}
