package by.iba.gomel.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.Record;
import by.iba.gomel.connectiondb.ConnectionDB2;
import by.iba.gomel.interfaces.IDB2;
import by.iba.gomel.managers.RequestManager;

@ManagedBean(name = "addPage")
@ViewScoped
public class AddBean implements Serializable, IDB2 {

    private static final Logger LOGGER           = LoggerFactory.getLogger(AddBean.class);
    private static final long   serialVersionUID = 1L;
    private Record              record;

    public AddBean() {
        record = new Record();
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(final Record record) {
        this.record = record;
    }

    public void listener(final FileUploadEvent event) {
        record = new Record();
        final UploadedFile uploadedFile = event.getUploadedFile();
        final String path = Constants.PATH_VALUE_PHOTOS + File.separator + new Date().getTime()
                + uploadedFile.getName();
        final File file = new File(path);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = uploadedFile.getInputStream();
            out = new FileOutputStream(file);
            int read = 0;
            final byte[] bytes = new byte[Constants.ONE_KB];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            record.setPathFile(path);
        } catch (final FileNotFoundException e) {
            AddBean.LOGGER.error(Constants.FILE_NOT_FOUND_EXCEPTION, e);
        } catch (final IOException e) {
            AddBean.LOGGER.error(Constants.IO_EXCEPTION, e);
        } finally {
            try {
                in.close();
                out.flush();
                out.close();
            } catch (final IOException e) {
                AddBean.LOGGER.error(Constants.IO_EXCEPTION, e);
            }
        }
    }

    public void addRecord() {
        perform();
        record = new Record();
    }

    @Override
    public String perform() {
        System.err.println(record);
        Statement st = null;
        PreparedStatement pr = null;
        Connection cn = null;
        try {
            cn = ConnectionDB2.getConnection();
            st = cn.createStatement();
            final String gotRequest = RequestManager.getProperty(Constants.PROPERTY_ADD_RECORD);
            final String readyRequest = MessageFormat.format(gotRequest, Constants.USER_DB2)
                    .toString();
            pr = cn.prepareStatement(readyRequest);
            pr.setString(Constants.INDEX_COLUMN_FULLNAME_SQL, record.getFullName());
            pr.setString(Constants.INDEX_COLUMN_ADDRESS_SQL, record.getAddress());
            pr.setString(Constants.INDEX_COLUMN_PHONE_NUMBER_SQL, record.getPhoneNumber());
            pr.setString(Constants.INDEX_COLUMN_MAIL_SQL, record.getMail());
            pr.setDate(Constants.INDEX_COLUMN_DATE_SQL, new java.sql.Date(record.getBirthDate()
                    .getTime()));
            pr.setString(Constants.INDEX_COLUMN_IMAGE_SQL, record.getPathFile());
            pr.executeUpdate();
        } catch (final SQLException e) {
            AddBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
            return Constants.RESULT_FAIL;
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (final SQLException e) {
                    AddBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (final SQLException e) {
                    AddBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
            if (pr != null) {
                try {
                    pr.close();
                } catch (final SQLException e) {
                    AddBean.LOGGER.error(Constants.EXCEPTION_SQL, e);
                }
            }
        }
        return Constants.RESULT_SUCCESS;
    }

}
