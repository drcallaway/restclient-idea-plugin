/**
 * The MIT License
 *
 * Copyright (c) 2011 by Dustin R. Callaway
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sourcestream.plugin.idea.restclient;

import java.awt.*;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactoryImpl;

/**
 * Base class for the RESTClient IDEA plugin. This class implements the functions that do not require direct access to
 * the RESTClient application's RESTMain class.
 */
public abstract class IdeaPluginBase implements ProjectComponent
{
    protected static final String TOOL_WINDOW_ID = "RESTClient";
    protected static final Icon toolWindowIcon = IconLoader.getIcon("/icons/logo.png");

    protected Project project;
    protected JPanel pluginPanel;

    /**
     * Constructs a new RESTClient plugin.
     *
     * @param project IDEA project
     */
    public IdeaPluginBase(Project project)
    {
        this.project = project;
    }

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
     * Performs plugin cleanup tasks.
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
        ContentFactory contentFactory = ContentFactoryImpl.SERVICE.getInstance();
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
     * Creates the plugin UI within the project's tool window.
     *
     * @return Panel containing plugin UI
     */
    private JPanel createPluginPanel()
    {
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());

        Box toolbox = Box.createVerticalBox();
        Component comp = ActionManager.getInstance().createActionToolbar("File Menu",
            (ActionGroup) ActionManager.getInstance().getAction("RESTClient.FileMenu"), true).getComponent();
        comp.setMaximumSize(new Dimension(27, 27));
        toolbox.add(comp);

        comp = ActionManager.getInstance().createActionToolbar("Edit Menu",
            (ActionGroup) ActionManager.getInstance().getAction("RESTClient.EditMenu"), true).getComponent();
        comp.setMaximumSize(new Dimension(27, 27));
        toolbox.add(comp);

        comp = ActionManager.getInstance().createActionToolbar("Tools Menu",
            (ActionGroup) ActionManager.getInstance().getAction("RESTClient.ToolsMenu"), true).getComponent();
        comp.setMaximumSize(new Dimension(27, 27));
        toolbox.add(comp);

        comp = ActionManager.getInstance().createActionToolbar("Help Menu",
            (ActionGroup) ActionManager.getInstance().getAction("RESTClient.HelpMenu"), true).getComponent();
        comp.setMaximumSize(new Dimension(27, 27));
        toolbox.add(comp);

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BorderLayout());
        toolbarPanel.add(toolbox, BorderLayout.NORTH); //add to north region to keep toolbox from resizing

        outerPanel.add(toolbarPanel, BorderLayout.WEST);
        outerPanel.add(pluginPanel, BorderLayout.CENTER);

        return outerPanel;
    }
}
