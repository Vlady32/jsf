package by.iba.gomel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.ws.util.Base64;

/**
 * This class contains fields and methods for working with records.
 */
public class Record implements Serializable {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(Record.class);
    private int                 item             = Constants.DEFAULT_INDEX_ITEM;
    private String              fullName         = null;
    private String              address          = null;
    private String              phoneNumber      = null;
    private Date                creationDate     = null;
    private String              mail             = null;
    private Date                birthDate        = null;
    private String              pathFile         = null;

    public Record() {
        // Empty constructor.
    }

    /**
     * 
     * @param item
     *            key record.
     * @param fullName
     *            full name.
     * @param address
     *            address.
     * @param phoneNumber
     *            phone number.
     * @param creationDate
     *            date of creation.
     * @param mail
     *            mail.
     * @param birthDate
     *            birthday date.
     */
    public Record(final int item, final String fullName, final String address,
            final String phoneNumber, final Date creationDate, final String mail,
            final Date birthDate, final String pathFile) {
        this.item = item;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
        this.mail = mail;
        this.birthDate = birthDate;
        this.pathFile = pathFile;
    }

    public int getItem() {
        return item;
    }

    public void setItem(final int item) {
        this.item = item;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    private byte[] getByteFile(final String pathToFile) {
        FileInputStream fileInputStream = null;
        final File file = new File(pathToFile);
        final byte[] bFile = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (final IOException e) {
            Record.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
        return bFile;
    }

    public String getBase64Code() {
        if ((pathFile == null) || pathFile.isEmpty()) {
            pathFile = Constants.DEFAULT_PATH_NO_IMAGE;
        }
        return Base64.encode(getByteFile(pathFile));
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(final String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public String toString() {
        return "Record [item=" + item + ", fullName=" + fullName + ", address=" + address
                + ", phoneNumber=" + phoneNumber + ", creationDate=" + creationDate + ", mail="
                + mail + ", birthDate=" + birthDate + ", pathFile=" + pathFile + "]";
    }

}
