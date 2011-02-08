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

    public void openRequest()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_OPEN_REQUEST);
    }

    public void openResponse()
    {
        ReflectionUtil.invokeMethod(restMain, IdeaPlugin.METHOD_OPEN_RESPONSE);
    }
}
