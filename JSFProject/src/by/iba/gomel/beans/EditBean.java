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
import by.iba.gomel.Record;
import by.iba.gomel.connectiondb.ConnectionDB2;
import by.iba.gomel.interfaces.IDB2;
import by.iba.gomel.managers.RequestManager;

@ManagedBean(name = "editPage")
@ViewScoped
public class EditBean implements Serializable, IDB2 {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(EditBean.class);
    private final ViewBean      viewBean;
    private Record              changedRecord;

    public Record getChangedRecord() {
        return changedRecord;
    }

    public void setChangedRecord(final Record changedRecord) {
        this.changedRecord = changedRecord;
    }

    public EditBean() {
        viewBean = new ViewBean();
    }

    public ViewBean getViewBean() {
        return viewBean;
    }

    public void changeRecord() {
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
            final String gotRequest = RequestManager.getProperty(Constants.PROPERTY_CHANGE_RECORD);
            final String readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2)
                    .toString();
            pr = cn.prepareStatement(readyRequest);
            pr.setString(Constants.INDEX_COLUMN_FULLNAME_SQL, changedRecord.getFullName());
            pr.setString(Constants.INDEX_COLUMN_ADDRESS_SQL, changedRecord.getAddress());
            pr.setString(Constants.INDEX_COLUMN_PHONE_NUMBER_SQL, changedRecord.getPhoneNumber());
            pr.setString(Constants.INDEX_COLUMN_MAIL_SQL, changedRecord.getMail());
            pr.setDate(Constants.INDEX_COLUMN_DATE_SQL, new java.sql.Date(changedRecord
                    .getBirthDate().getTime()));
            pr.setString(Constants.INDEX_COLUMN_IMAGE_SQL, changedRecord.getPathFile());
            pr.setInt(Constants.INDEX_COLUMN_ITEM_SQL, changedRecord.getItem());
            pr.executeUpdate();
        } catch (final SQLException e) {
            EditBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
            return Constants.RESULT_FAIL;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    EditBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    EditBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (pr != null) {
                try {
                    pr.close();
                } catch (final SQLException e) {
                    EditBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        return Constants.RESULT_SUCCESS;
    }

}
