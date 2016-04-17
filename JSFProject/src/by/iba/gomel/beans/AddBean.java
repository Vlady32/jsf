package by.iba.gomel.beans;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.Record;

@ManagedBean(name = "addPage")
@RequestScoped
public class AddBean implements Serializable {

    private static final Logger LOGGER           = LoggerFactory.getLogger(AddBean.class);
    private static final long   serialVersionUID = 1L;
    private Record              record;
    private Part                uploadedFile;

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(final Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(final Record record) {
        this.record = record;
    }

    public void upload() {
        String fileContent;
        final String path = Constants.PATH_VALUE_PHOTOS + File.separator + new Date().getTime()
                + uploadedFile.getName();
        PrintWriter out = null;
        try {
            fileContent = new Scanner(uploadedFile.getInputStream()).useDelimiter("\\A").next();
            out = new PrintWriter(path);
            out.write(fileContent);
            record.setPathFile(path);
        } catch (final IOException e) {
            AddBean.LOGGER.error(Constants.IO_EXCEPTION, e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
