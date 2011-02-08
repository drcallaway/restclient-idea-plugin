package com.sourcestream.plugin.idea.restclient;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.peer.PeerFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Base class for RESTClient IDEA plugin.
 */
public abstract class RESTClientPlugin implements ProjectComponent
{
    public static final String METHOD_OPEN_REQUEST = "jmi_open_reqAction";
    public static final String METHOD_OPEN_RESPONSE = "jmi_open_resAction";

    protected static final String TOOL_WINDOW_ID = "RESTClient";
    protected static final Icon toolWindowIcon = IconLoader.getIcon("/icons/remote.png");

    protected Project project;
    protected JPanel pluginPanel;

    /**
     * Constructs a new RESTClient plugin.
     *
     * @param project IDEA project
     */
    public RESTClientPlugin(Project project)
    {
        this.project = project;
    }

    /**
     * Gets a reference to the RESTMain object.
     *
     * @return RESTMain object
     */
    public abstract Object getRESTMain();

    /**
     * Gets a reference to the plugin's UI panel.
     *
     * @return UI panel
     */
    public JPanel getPluginPanel()
    {
        return pluginPanel;
    }

    /**
     * Initializes the plugin.
     */
    public void initComponent()
    {
    }

    /**
     * Deletes the plugin.
     */
    public void disposeComponent()
    {
    }

    /**
     * Gets the plugin name.
     *
     * @return Plugin name
     */
    @NotNull
    public String getComponentName()
    {
        return TOOL_WINDOW_ID;
    }

    /**
     * Performs project initialization. Invoked by IDEA when the project is opened.
     */
    public void projectOpened()
    {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.registerToolWindow(TOOL_WINDOW_ID, false, ToolWindowAnchor.BOTTOM);

        pluginPanel = createPluginPanel();
        ContentFactory contentFactory = PeerFactory.getInstance().getContentFactory();
        Content content = contentFactory.createContent(pluginPanel, "", false);

        toolWindow.getContentManager().addContent(content);
        toolWindow.setIcon(toolWindowIcon);
    }

    /**
     * Performs project cleanup tasks. Invoked by IDEA when the project is closed.
     */
    public void projectClosed()
    {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(this.project);
        toolWindowManager.unregisterToolWindow(TOOL_WINDOW_ID);
    }

    /**
     * Creates the plugin UI within its tool window.
     *
     * @return Panel containing plugin UI
     */
    private JPanel createPluginPanel()
    {
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());

        Box toolbox = Box.createVerticalBox();
        Component comp = ActionManager.getInstance().createActionToolbar("REST Test Client Menu Bar", (ActionGroup) ActionManager.getInstance().getAction("RESTTestClient.MenuToolbar"), true).getComponent();
        comp.setMaximumSize(new Dimension(25, 25));
        toolbox.add(comp);
        comp = ActionManager.getInstance().createActionToolbar("REST Test Client Menu Bar", (ActionGroup) ActionManager.getInstance().getAction("RESTTestClient.MenuToolbar1"), true).getComponent();
        comp.setMaximumSize(new Dimension(25, 25));
        toolbox.add(comp);
        comp = ActionManager.getInstance().createActionToolbar("REST Test Client Menu Bar", (ActionGroup) ActionManager.getInstance().getAction("RESTTestClient.MenuToolbar2"), true).getComponent();
        comp.setMaximumSize(new Dimension(25, 25));
        toolbox.add(comp);
        comp = ActionManager.getInstance().createActionToolbar("REST Test Client Menu Bar", (ActionGroup) ActionManager.getInstance().getAction("RESTTestClient.MenuToolbar3"), true).getComponent();
        comp.setMaximumSize(new Dimension(25, 25));
        toolbox.add(comp);

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BorderLayout());
        toolbarPanel.add(toolbox, BorderLayout.NORTH); //add to north region to keep toolbox from resizing

        outerPanel.add(toolbarPanel, BorderLayout.WEST);
        outerPanel.add(pluginPanel, BorderLayout.CENTER);

        return outerPanel;
    }

}
