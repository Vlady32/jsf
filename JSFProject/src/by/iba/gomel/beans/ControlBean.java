package by.iba.gomel.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.User;
import by.iba.gomel.connectiondb.ConnectionDB2;
import by.iba.gomel.interfaces.IDB2;
import by.iba.gomel.managers.RequestManager;

@ManagedBean(name = "controlPage")
@ViewScoped
public class ControlBean implements Serializable, IDB2 {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(ControlBean.class);
    private List<User>          listUsers;

    public List<User> getListUsers() {
        perform();
        return listUsers;
    }

    public void setListUsers(final List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public String perform() {
        Statement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = ConnectionDB2.getConnection();
            listUsers = new ArrayList<User>();
            st = cn.createStatement();
            final String gotRequest = RequestManager.getProperty(Constants.PROPERTY_GET_USERS);
            final String readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2)
                    .toString();
            rs = st.executeQuery(readyRequest);
            while (rs.next()) {
                listUsers.add(new User(rs.getString(Constants.INDEX_COLUMN_NAME_USER_SQL), rs
                        .getString(Constants.INDEX_COLUMN_TYPE_USER_CONTROL_SQL)));
            }
        } catch (final SQLException e) {
            ControlBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
            return Constants.RESULT_FAIL;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    ControlBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    ControlBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException e) {
                    ControlBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        return Constants.RESULT_SUCCESS;
    }

}
