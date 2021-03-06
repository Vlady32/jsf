package by.iba.gomel.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import by.iba.gomel.Constants;
import by.iba.gomel.managers.MessageManager;

@ManagedBean(name = "main")
@ViewScoped
/**
 * This bean uses for working with main page.
 */
public class MainBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<String>      listItemsMenu;
    private String            linkPage;

    @ManagedProperty(value = "#{viewPage}")
    ViewBean                  viewBean;
    @ManagedProperty(value = "#{editPage}")
    EditBean                  editBean;
    @ManagedProperty(value = "#{searchPage}")
    SearchBean                searchBean;

    public void setEditBean(final EditBean editBean) {
        this.editBean = editBean;
    }

    public void setSearchBean(final SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    public void setViewBean(final ViewBean viewBean) {
        this.viewBean = viewBean;
    }

    /**
     * 
     * @param index
     *            index page.
     */
    public void clearBean(final int index) {
        switch (index) {
            case Constants.DEFAULT_VIEW_PAGE_INDEX:
                viewBean.clear();
                break;
            case Constants.DEFAULT_EDIT_PAGE_INDEX:
                editBean.clear();
                break;
            case Constants.DEFAULT_SEARCH_PAGE_INDEX:
                searchBean.clear();
                break;
            default:
                break;
        }
    }

    public String logOut() {
        SessionBean.getSession().invalidate();
        return Constants.RESULT_SUCCESS;
    }

    public List<String> getlistItemsMenu() {
        initList();
        return listItemsMenu;
    }

    public void setlistItemsMenu(final List<String> listItemsMenuMenu) {
        listItemsMenu = listItemsMenuMenu;
    }

    private void initList() {
        listItemsMenu = new ArrayList<String>();
        final Locale currentLocale = new SessionBean().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        if (SessionBean.getUserType().equals(Constants.TYPE_ADMIN)) {
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_ADDING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_EDITING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_CONTROL));
        } else if (SessionBean.getUserType().equals(Constants.TYPE_USER)) {
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING));
        }
    }

    public String getLinkPage() {
        return linkPage;
    }

    public void setLinkPage(final String linkPage) {
        this.linkPage = linkPage;
    }

    public void determineAction() {
        final Locale currentLocale = new SessionBean().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        final Map<String, String> mapParameters = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        final String linkType = mapParameters.get(Constants.ATTRIBUTE_LINK_TYPE);
        if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING))) {
            linkPage = Constants.PAGE_VIEW;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_ADDING))) {
            linkPage = Constants.PAGE_ADD;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_EDITING))) {
            linkPage = Constants.PAGE_EDIT;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING))) {
            linkPage = Constants.PAGE_SEARCH;
        } else if (linkType.equals(messageManager.getProperty(Constants.MESSAGE_MENU_CONTROL))) {
            linkPage = Constants.PAGE_CONTROL;
        }
    }

}
