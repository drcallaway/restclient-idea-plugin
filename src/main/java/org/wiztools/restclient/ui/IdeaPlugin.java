package org.wiztools.restclient.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.sourcestream.plugin.idea.restclient.RESTClientPlugin;
import com.sourcestream.plugin.idea.restclient.ReflectionUtil;
import org.wiztools.restclient.RequestBean;
import org.wiztools.restclient.Util;
import org.wiztools.restclient.server.TraceServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * IDEA plugin for RESTClient.
 */
public class IdeaPlugin extends RESTClientPlugin
{
    public static final String METHOD_OPEN_REQUEST = "jmi_open_reqAction";
    public static final String METHOD_OPEN_RESPONSE = "jmi_open_resAction";
    public static final String METHOD_OPEN_ARCHIVE = "jmi_open_archiveAction";
    public static final String METHOD_SAVE = "actionSave";
    public static final String METHOD_OPTIONS = "actionOpenOptionsDialog";
    public static final String ACTION_SAVE_REQUEST = "save_request";
    public static final String ACTION_SAVE_RESPONSE = "save_response";
    public static final String ACTION_SAVE_RESPONSE_BODY = "save_response_body";
    public static final String ACTION_SAVE_REQ_RES_ARCHIVE = "save_req_res_archive";
    public static final String ACTION_CLEAR_RESPONSE = "clear_response";
    public static final String ACTION_RESET_ALL = "reset_all";
    public static final String ACTION_RESET_TO_LAST = "reset_to_last";
    public static final String ACTION_PASSWORD_ENCODER_DECODER = "password_encoder_decoder";
    public static final String ACTION_START_TRACE_SERVER = "start_trace_server";
    public static final String ACTION_STOP_TRACE_SERVER = "stop_trace_server";
    public static final String ACTION_INSERT_TRACE_URL = "insert_trace_url";

    private RESTMain restMain;
    private PasswordGenDialog passwordGenDialog;

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

    /**
     * Clears the response.
     */
    public void clearResponse()
    {
        restMain.getView().clearUIResponse();
    }

    /**
     * Clears the request and response.
     */
    public void resetAll()
    {
        restMain.getView().clearUIRequest();
        restMain.getView().clearUIResponse();
    }

    /**
     * Resets to the last request/response.
     */
    public void resetToLast()
    {
        restMain.getView().setUIToLastRequestResponse();
    }

    /**
     * Shows the password encode/decode dialog box.
     */
    public void passwordEncodeDecode()
    {
        if (passwordGenDialog == null)
        {
            passwordGenDialog = new PasswordGenDialog(restMain.getFrame());
        }

        passwordGenDialog.setVisible(true);
    }

    /**
     * Starts the trace server.
     */
    public void startTraceServer()
    {
        try
        {
            TraceServer.start();
            restMain.getView().setStatusMessage("Trace Server started.");
        }
        catch (Exception ex)
        {
            restMain.getView().showError(Util.getStackTrace(ex));
        }
    }

    /**
     * Stops the trace server.
     */
    public void stopTraceServer()
    {
        try
        {
            if (TraceServer.isRunning())
            {
                TraceServer.stop();
                restMain.getView().setStatusMessage("Trace Server stopped.");
            }
        }
        catch (Exception ex)
        {
            restMain.getView().showError(Util.getStackTrace(ex));
        }
    }

    /**
     * Inserts the trace server URL.
     */
    public void insertTraceServerUrl()
    {
        RequestBean request = (RequestBean) restMain.getView().getRequestFromUI();

        if (request.getUrl() != null)
        {
            int ret = JOptionPane.showConfirmDialog(restMain.getFrame(), "URL field not empty. Overwrite?",
                "Request URL not empty", JOptionPane.YES_NO_OPTION);

            if (ret == JOptionPane.NO_OPTION)
            {
                return;
            }
        }

        try
        {
            request.setUrl(new URL("http://localhost:" + TraceServer.PORT + "/"));
        }
        catch (MalformedURLException ex)
        {
            assert true : ex;
        }

        restMain.getView().setUIFromRequest(request);
    }

    /**
     * Displays options.
     *
     * @param event Action event
     */
    public void options(ActionEvent event)
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_OPTIONS, event);
    }
}
