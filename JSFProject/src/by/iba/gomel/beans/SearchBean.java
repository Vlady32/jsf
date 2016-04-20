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
import by.iba.gomel.Record;
import by.iba.gomel.connectiondb.ConnectionDB2;
import by.iba.gomel.interfaces.IDB2;
import by.iba.gomel.managers.RequestManager;

@ManagedBean(name = "searchPage")
@ViewScoped
/**
 * This bean uses for searching records in database.
 */
public class SearchBean implements Serializable, IDB2 {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(SearchBean.class);
    private String              searchPhrase;
    private String              category;
    private List<Record>        searchedRecords;
    private Record              record;

    public void clear() {
        searchPhrase = null;
        category = null;
        searchedRecords = new ArrayList<Record>();
        record = new Record();
    }

    public SearchBean() {
        searchedRecords = new ArrayList<Record>();
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(final Record record) {
        this.record = record;
    }

    public List<Record> getSearchedRecords() {
        return searchedRecords;
    }

    public void setSearchedRecords(final List<Record> searchedRecords) {
        this.searchedRecords = searchedRecords;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(final String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public void searchRecord() {
        perform();
    }

    @Override
    public String perform() {
        Statement st = null;
        ResultSet rs = null;
        Connection cn = null;
        searchedRecords = new ArrayList<Record>();
        try {
            cn = ConnectionDB2.getConnection();
            st = cn.createStatement();
            String readyRequest;
            if (category.equals(Constants.PARAMETER_ALL)) {
                final String gotRequest = RequestManager
                        .getProperty(Constants.PROPERTY_SEARCH_ALL_COLUMNS);
                readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2,
                        searchPhrase.toLowerCase()).toString();
            } else {
                final String gotRequest = RequestManager
                        .getProperty(Constants.PROPERTY_SEARCH_CERTAIN_COLUMN);
                readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2, category,
                        searchPhrase.toLowerCase()).toString();
            }
            rs = st.executeQuery(readyRequest);
            while (rs.next()) {
                searchedRecords.add(new Record(rs.getInt(Constants.INDEX_COLUMN_ITEM_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_FULLNAME_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_ADDRESS_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_PHONE_NUMER_VIEW_SQL), rs
                        .getDate(Constants.INDEX_COLUMN_CREATION_DATE_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_MAIL_VIEW_SQL), rs
                        .getDate(Constants.INDEX_COLUMN_BIRTHDATE_VIEW_SQL), rs
                        .getString(Constants.INDEX_COLUMN_IMAGE_VIEW_SQL)));
            }
        } catch (final SQLException e) {
            SearchBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
            return Constants.RESULT_FAIL;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    SearchBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    SearchBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException e) {
                    SearchBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        if (!(searchedRecords.size() > 0)) {
            SessionBean.addErrorMessage(Constants.MESSAGE_SEARCH_NOTHING, null);
        }
        return Constants.RESULT_SUCCESS;
    }
}
