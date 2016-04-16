package by.iba.gomel;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(final String pathFile) {
        this.pathFile = pathFile;
    }

}
