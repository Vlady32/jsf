package by.iba.gomel.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.iba.gomel.Record;

@ManagedBean(name = "profilePage")
@SessionScoped
/**
 * This bean uses for getting record.
 */
public class ProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Record            record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(final Record record) {
        this.record = record;
    }
}
