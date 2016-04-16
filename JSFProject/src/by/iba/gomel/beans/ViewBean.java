package by.iba.gomel.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.Record;
import by.iba.gomel.connectiondb.ConnectionDB2;
import by.iba.gomel.interfaces.IDB2;
import by.iba.gomel.managers.RequestManager;

@ManagedBean(name = "viewPage")
@ViewScoped
public class ViewBean implements Serializable, IDB2 {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(ViewBean.class);
    private List<Record>        listRecords;
    private int                 currentPage      = Constants.DEFAULT_PAGE;
    private int                 qualityPages;

    public void update() {
        final Map<String, String> mapParameters = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        final String page = mapParameters.get(Constants.ATTRIBUTE_PAGE_TYPE);
        currentPage = Integer.parseInt(page);
        perform();
    }

    public int getQualityPages() {
        return qualityPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<Integer> getValuesPages() {
        final List<Integer> values = new ArrayList<Integer>();
        for (int i = 1; i <= qualityPages; i++) {
            values.add(i);
        }
        return values;
    }

    public List<Record> getListRecords() {
        final String resultOperation = perform();
        if ((resultOperation != null) && resultOperation.equals(Constants.RESULT_SUCCESS)) {
            return listRecords;
        } else {
            SessionBean.addErrorMessage(Constants.MESSAGE_WRONG_VIEW);
            return null;
        }
    }

    @Override
    public String perform() {
        Statement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = ConnectionDB2.getConnection();
            listRecords = new ArrayList<Record>();
            st = cn.createStatement();
            final int start = (int) ((currentPage - Constants.NUMBER_ONE) * Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE);
            final String getAllRecordsRequest = MessageFormat.format(
                    RequestManager.getProperty(Constants.PROPERTY_GET_ALL_RECORDS),
                    Constants.USER_DB2, start);
            final String qualityRecordsRequest = MessageFormat.format(
                    RequestManager.getProperty(Constants.PROPERTY_GET_QUALITY_RECORDS),
                    Constants.USER_DB2);
            rs = st.executeQuery(qualityRecordsRequest);
            if (rs.next()) {
                qualityPages = (int) Math
                        .ceil((rs.getInt(Constants.INDEX_COLUMN_FULLNAME_SQL) / Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE));
            }
            rs.close();
            rs = st.executeQuery(getAllRecordsRequest);
            while (rs.next()) {
                listRecords.add(new Record(rs.getInt(Constants.INDEX_COLUMN_ITEM_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_FULLNAME_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_ADDRESS_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_PHONE_NUMER_VIEW_SQL), rs
                        .getDate(Constants.INDEX_COLUMN_CREATION_DATE_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_MAIL_VIEW_SQL), rs
                        .getDate(Constants.INDEX_COLUMN_BIRTHDATE_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_IMAGE_VIEW_SQL)));
            }
        } catch (final SQLException e) {
            ViewBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
            return null;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    ViewBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    ViewBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException e) {
                    ViewBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        return Constants.RESULT_SUCCESS;
    }
}
