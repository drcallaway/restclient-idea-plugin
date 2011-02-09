package org.wiztools.restclient.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.sourcestream.plugin.idea.restclient.RESTClientPlugin;
import com.sourcestream.plugin.idea.restclient.ReflectionUtil;

import javax.swing.*;

/**
 * IDEA plugin for RESTClient.
 */
public class IdeaPlugin extends RESTClientPlugin
{
    public static final String METHOD_OPEN_REQUEST = "jmi_open_reqAction";
    public static final String METHOD_OPEN_RESPONSE = "jmi_open_resAction";
    public static final String METHOD_OPEN_ARCHIVE = "jmi_open_archiveAction";
    public static final String METHOD_SAVE = "actionSave";
    public static final String ACTION_SAVE_REQUEST = "save_request";
    public static final String ACTION_SAVE_RESPONSE = "save_response";
    public static final String ACTION_SAVE_RESPONSE_BODY = "save_response_body";
    public static final String ACTION_SAVE_REQ_RES_ARCHIVE = "save_req_res_archive";

    private RESTMain restMain;

    /**
     * Constructs a new plugin object.
     *
     * @param project IDEA project
     */
    public IdeaPlugin(Project project)
    {
        super(project);
    }

    /**
     * Gets an instance of this plugin.
     *
     * @param project IDEA project
     * @return Instance of this plugin
     */
    public static IdeaPlugin getInstance(Project project)
    {
        return project.getComponent(IdeaPlugin.class);
    }

    /**
     * Performs project initialization. Invoked by IDEA when the project is opened.
     */
    public void projectOpened()
    {
        JFrame jFrame = WindowManager.getInstance().getFrame(project);
        restMain = new RESTMain(jFrame, null, null);
        pluginPanel = restMain.getView();
        super.projectOpened();
    }

    /**
     * Opens a saved request.
     */
    public void openRequest()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_OPEN_REQUEST);
    }

    /**
     * Opens a saved response.
     */
    public void openResponse()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_OPEN_RESPONSE);
    }

    /**
     * Opens a saved archive.
     */
    public void openArchive()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_OPEN_ARCHIVE);
    }

    /**
     * Saves the request.
     */
    public void saveRequest()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_SAVE, FileChooserType.SAVE_REQUEST);
    }

    /**
     * Saves the response.
     */
    public void saveResponse()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_SAVE, FileChooserType.SAVE_RESPONSE);
    }

    /**
     * Saves the response body.
     */
    public void saveResponseBody()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_SAVE, FileChooserType.SAVE_RESPONSE_BODY);
    }

    /**
     * Saves the request/response archive.
     */
    public void saveReqResArchive()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_SAVE, FileChooserType.SAVE_ARCHIVE);
    }
}
