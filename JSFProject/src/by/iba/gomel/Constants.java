package by.iba.gomel;

public class Constants {

    public static final int    INDEX_COLUMN_TYPE_SQL                     = 1;
    public static final int    INDEX_COLUMN_FULLNAME_SQL                 = 1;
    public static final int    INDEX_COLUMN_ITEM_VIEW_SQL                = 1;
    public static final int    INDEX_COLUMN_NAME_USER_SQL                = 1;
    public static final int    INDEX_COLUMN_TYPE_USER_CONTROL_SQL        = 2;
    public static final int    INDEX_COLUMN_ADDRESS_SQL                  = 2;
    public static final int    INDEX_COLUMN_PASSWORD_SQL                 = 2;
    public static final int    INDEX_COLUMN_FULLNAME_VIEW_SQL            = 2;
    public static final int    INDEX_COLUMN_PHONE_NUMBER_SQL             = 3;
    public static final int    INDEX_COLUMN_TYPE_USER_SQL                = 3;
    public static final int    INDEX_COLUMN_ADDRESS_VIEW_SQL             = 3;
    public static final int    INDEX_COLUMN_MAIL_SQL                     = 4;
    public static final int    INDEX_COLUMN_PHONE_NUMER_VIEW_SQL         = 4;
    public static final int    INDEX_COLUMN_DATE_SQL                     = 5;
    public static final int    INDEX_COLUMN_CREATION_DATE_VIEW_SQL       = 5;
    public static final int    INDEX_COLUMN_IMAGE_SQL                    = 6;
    public static final int    INDEX_COLUMN_MAIL_VIEW_SQL                = 6;
    public static final int    INDEX_COLUMN_BIRTHDATE_VIEW_SQL           = 7;
    public static final int    INDEX_COLUMN_ITEM_SQL                     = 7;
    public static final int    INDEX_COLUMN_IMAGE_VIEW_SQL               = 8;
    public static final int    DEFAULT_PAGE                              = 1;
    public static final int    NUMBER_ONE                                = 1;
    public static final int    DEFAULT_INDEX_ITEM                        = -1;
    public static final int    DEFAULT_VIEW_PAGE_INDEX                   = 0;
    public static final int    DEFAULT_EDIT_PAGE_INDEX                   = 2;
    public static final int    DEFAULT_SEARCH_PAGE_INDEX                 = 3;
    public static final int    ONE_KB                                    = 1024;
    public static final double DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE = 30.0;

    public static final String ATTRIBUTE_NAME_LOGIN                      = "login";
    public static final String ATTRIBUTE_NAME_TYPE                       = "type";
    public static final String ATTRIBUTE_LINK_TYPE                       = "link";
    public static final String ATTRIBUTE_PAGE_TYPE                       = "page";

    public static final String FILE_MESSAGES_PROPERTY                    = "resources.messages";
    public static final String FILE_REQUESTS_PROPERTY                    = "resources.requests_db2";
    public static final String FILE_CONFIG_PROPERTY                      = "resources.config";
    public static final String DEFAULT_PATH_IMAGE                        = "/files/no_picture.png";
    public static final String DEFAULT_PATH_NO_IMAGE                     = "C:\\photos\\no_picture.png";
    public static final String DEFAULT_DEFAULT_SEPARATOR                 = "/";
    public static final String PATH_VALUE_PHOTOS                         = "C:\\photos";
    public static final String DEFAULT_ENCODING                          = "UTF-8";
    public static final String PARAMETER_ALL                             = "all";
    public static final String TAG_ERROR_MESSAGE_REGISTR                 = "registrationForm:confirmPassword";
    public static final String TAG_ERROR_MESSAGE_PASSWORD                = "loginForm:passwordLogin";

    public static final String PROPERTY_PATH_LOGIN_PAGE                  = "path.page.login";
    public static final String PROPERTY_PATH_MAIN_PAGE                   = "path.page.main";
    public static final String PROPERTY_CHECK_USER                       = "checkUser";
    public static final String PROPERTY_REGISTERED_USER                  = "registeredUser";
    public static final String PROPERTY_GET_ALL_RECORDS                  = "getAllRecords";
    public static final String PROPERTY_GET_QUALITY_RECORDS              = "getQualityRecords";
    public static final String PROPERTY_ADD_RECORD                       = "addRecord";
    public static final String PROPERTY_CHANGE_RECORD                    = "changeRecord";
    public static final String PROPERTY_DELETE_RECORD                    = "deleteRecord";
    public static final String PROPERTY_GET_USERS                        = "getUsers";
    public static final String PROPERTY_DELETE_USER                      = "deleteUser";
    public static final String PROPERTY_SEARCH_ALL_COLUMNS               = "searchAllColumns";
    public static final String PROPERTY_SEARCH_CERTAIN_COLUMN            = "searchCertainColumn";

    public static final String NAMING_EXCEPTION                          = "NamingException";
    public static final String EXCEPTION_SQL                             = "SQLException";
    public static final String IO_EXCEPTION                              = "IOException";
    public static final String FILE_NOT_FOUND_EXCEPTION                  = "FileNotFoundException";

    public static final String LOGIC_NAME_DB                             = "java:comp/env/jdbc/JSP";
    public static final String USER_DB2                                  = "Vlady";

    public static final String RESULT_SUCCESS                            = "success";
    public static final String RESULT_SUCCESS_PROFILE                    = "successProfile";
    public static final String RESULT_FAIL                               = "fail";

    public static final String MESSAGE_LOGIN_ERROR                       = "message.loginerror";
    public static final String MESSAGE_REGISTRATION_PASSWORDS_ERROR      = "message.registrationpasswordserror";
    public static final String MESSAGE_REGISTRATION_LOGIN_ERROR          = "message.registrationloginerror";
    public static final String MESSAGE_WRONG_VIEW                        = "message.wrongview";
    public static final String MESSAGE_MENU_VIEWING                      = "main_menu_viewing";
    public static final String MESSAGE_MENU_ADDING                       = "main_menu_adding";
    public static final String MESSAGE_MENU_EDITING                      = "main_menu_editing";
    public static final String MESSAGE_MENU_SEARCHING                    = "main_menu_searching";
    public static final String MESSAGE_MENU_CONTROL                      = "main_menu_control_users";
    public static final String MESSAGE_SEARCH_NOTHING                    = "search_message_nothing";

    public static final String TYPE_USER                                 = "user";
    public static final String TYPE_GUEST                                = "guest";
    public static final String TYPE_ADMIN                                = "admin";
    public static final String TYPE_EMPTY                                = "";

    public static final String PAGE_VIEW                                 = "view.xhtml";
    public static final String PAGE_ADD                                  = "add.xhtml";
    public static final String PAGE_CONTROL                              = "control.xhtml";
    public static final String PAGE_EDIT                                 = "edit.xhtml";
    public static final String PAGE_SEARCH                               = "search.xhtml";

}
