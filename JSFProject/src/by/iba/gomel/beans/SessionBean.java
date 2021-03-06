package by.iba.gomel.beans;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.iba.gomel.Constants;
import by.iba.gomel.managers.MessageManager;

@ManagedBean(name = "sessionInformation")
@RequestScoped
/**
 * This bean uses for working with session.
 */
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getUserName() {
        final HttpSession session = SessionBean.getSession();
        if (session != null) {
            return (String) session.getAttribute(Constants.ATTRIBUTE_NAME_LOGIN);
        } else {
            return null;
        }
    }

    public Locale getCurrentLocale() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Locale currentLocale = context.getExternalContext().getRequestLocale();
        return currentLocale;
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
    }

    /**
     * 
     * @param name
     *            name attribute.
     * @param value
     *            value attribute.
     */
    public static void setAttributesSession(final String name, final Object value) {
        SessionBean.getSession().setAttribute(name, value);
    }

    public static String getUserType() {
        final HttpSession session = SessionBean.getSession();
        if (session != null) {
            return session.getAttribute(Constants.ATTRIBUTE_NAME_TYPE).toString().trim();
        } else {
            return null;
        }
    }

    /**
     * 
     * @param propertyMessage
     *            property name for getting value from property file.
     * @param tag
     *            html tag for setting message.
     */
    public static void addErrorMessage(final String propertyMessage, final String tag) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Locale currentLocale = new SessionBean().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        final String messageError = messageManager.getProperty(propertyMessage);
        context.addMessage(tag, new FacesMessage(messageError));
    }

}
