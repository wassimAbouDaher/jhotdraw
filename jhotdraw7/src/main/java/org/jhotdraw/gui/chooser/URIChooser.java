/*
 * @(#)URIChooser.java
 * 
 * Copyright (c) 2009 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 * 
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */

package org.jhotdraw.gui.chooser;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.JComponent;

/**
 *{@code URIChooser} provides a mechanism for the user to choose a URI.
 *
 * @author Werner Randelshofer
 * @version 1.0 2009-12-16 Created.
 */
public interface URIChooser {
    // ************************
    // ***** Dialog Types *****
    // ************************

    /**
     * Type value indicating that the <code>URIChooser</code> supports an
     * "Open" file operation.
     */
    public static final int OPEN_DIALOG = URIChooser.OPEN_DIALOG;

    /**
     * Type value indicating that the <code>URIChooser</code> supports a
     * "Save" file operation.
     */
    public static final int SAVE_DIALOG = URIChooser.SAVE_DIALOG;

    /**
     * Type value indicating that the <code>URIChooser</code> supports a
     * developer-specified file operation.
     */
    public static final int CUSTOM_DIALOG = URIChooser.CUSTOM_DIALOG;


    // ********************************
    // ***** Dialog Return Values *****
    // ********************************

    /**
     * Return value if cancel is chosen.
     */
    public static final int CANCEL_OPTION = URIChooser.CANCEL_OPTION;

    /**
     * Return value if approve (yes, ok) is chosen.
     */
    public static final int APPROVE_OPTION = URIChooser.APPROVE_OPTION;

    /**
     * Return value if an error occured.
     */
    public static final int ERROR_OPTION = URIChooser.ERROR_OPTION;


    /**
     * Returns the selected URI.
     *
     * @see #setSelectedFile
     * @return the selected file
     */
    public URI getSelectedURI();
    /**
     * Sets the selected URI.
     *
     * @param uri the selected file
     */
    public void setSelectedURI(URI uri);

    /**
     * Returns the type of this dialog.  The default is
     * {@code URIChooser.OPEN_DIALOG}.
     *
     * @return   the type of dialog to be displayed:
     * <ul>
     * <li>URIChooser.OPEN_DIALOG
     * <li>URIChooser.SAVE_DIALOG
     * <li>URIChooser.CUSTOM_DIALOG
     * </ul>
     *
     * @see #setDialogType
     */
    public int getDialogType();

    /**
     * Sets the type of this dialog. Use {@code OPEN_DIALOG} when you
     * want to bring up a chooser that the user can use to open an URI.
     * Likewise, use {@code SAVE_DIALOG} for letting the user choose
     * an URI for saving.
     * Use {@code CUSTOM_DIALOG} when you want to use the
     * chooser in a context other than "Open" or "Save".
     * For instance, you might want to bring up a chooser that allows
     * the user to choose an URI to execute. Note that you normally would not
     * need to set the {@code URIChooser} to use
     * {@code CUSTOM_DIALOG}
     * since a call to {@code setApproveButtonText} does this for you.
     * The default dialog type is {@code URIChooser.OPEN_DIALOG}.
     *
     * @param dialogType the type of dialog to be displayed:
     * <ul>
     * <li>URIChooser.OPEN_DIALOG
     * <li>URIChooser.SAVE_DIALOG
     * <li>URIChooser.CUSTOM_DIALOG
     * </ul>
     *
     * @exception IllegalArgumentException if {@code dialogType} is
     *				not legal
     * @beaninfo
     *   preferred: true
     *       bound: true
     * description: The type (open, save, custom) of the URIChooser.
     *        enum:
     *              OPEN_DIALOG URIChooser.OPEN_DIALOG
     *              SAVE_DIALOG URIChooser.SAVE_DIALOG
     *              CUSTOM_DIALOG URIChooser.CUSTOM_DIALOG
     *
     * @see #getDialogType
     * @see #setApproveButtonText
     */
    public void setDialogType(int type);
    /**
     * Returns the text used in the <code>ApproveButton</code>.
     * If <code>null</code>, the UI object will determine the button's text.
     *
     * Typically, this would be "Open" or "Save".
     *
     * @return the text used in the <code>ApproveButton</code>
     *
     * @see #setApproveButtonText
     * @see #setDialogType
     * @see #showDialog
     */
    public String getApproveButtonText();
    /**
     * Sets the text used in the <code>ApproveButton</code> in the
     * <code>FileChooserUI</code>.
     *
     * @beaninfo
     *   preferred: true
     *       bound: true
     * description: The text that goes in the ApproveButton.
     *
     * @param approveButtonText the text used in the <code>ApproveButton</code>
     *
     * @see #getApproveButtonText
     * @see #setDialogType
     */
    public void setApproveButtonText(String approveButtonText);
    /**
     * Returns the approve button's mnemonic.
     * @return an integer value for the mnemonic key
     *
     * @see #setApproveButtonMnemonic
     */
    public int getApproveButtonMnemonic();

    /**
     * Sets the approve button's mnemonic using a numeric keycode.
     *
     * @param mnemonic  an integer value for the mnemonic key
     *
     * @beaninfo
     *   preferred: true
     *       bound: true
     * description: The mnemonic key accelerator for the ApproveButton.
     *
     * @see #getApproveButtonMnemonic
     */
    public void setApproveButtonMnemonic(int mnemonic);

    /**
     * Returns the component of the URIChooser.
     * <p>
     * Typically, this would return {@code this}.
     *
     * @return The component.
     */
    public JComponent getComponent();

    /**
     * Adds an <code>ActionListener</code> to the chooser.
     *
     * @param l  the listener to be added
     *
     * @see #approveSelection
     * @see #cancelSelection
     */
    public void addActionListener(ActionListener l);

    /**
     * Removes an <code>ActionListener</code> from the chooser.
     *
     * @param l  the listener to be removed
     *
     * @see #addActionListener
     */
    public void removeActionListener(ActionListener l);

    /**
     * Sets the string that goes in the <code>URIChooser</code> window's
     * title bar.
     *
     * @param dialogTitle the new <code>String</code> for the title bar
     *
     * @beaninfo
     *   preferred: true
     *       bound: true
     * description: The title of the URIChooser dialog window.
     *
     * @see #getDialogTitle
     *
     */
    public void setDialogTitle(String dialogTitle);

    /**
     * Gets the string that goes in the <code>URIChooser</code>'s titlebar.
     *
     * @see #setDialogTitle
     */
    public String getDialogTitle();


    /**
     * Tells the UI to rescan its files list from the current directory.
     */
    public void rescanCurrentDirectory();


   // **************************************
    // ***** URIChooser Dialog methods *****
    // **************************************

    /**
      * Pops up an "Open" chooser dialog. Note that the
     * text that appears in the approve button is determined by
     * the L&F.
     *
     * @param    parent  the parent component of the dialog,
     *			can be <code>null</code>;
     *                  see <code>showDialog</code> for details
     * @return   the return state of the file chooser on popdown:
     * <ul>
     * <li>URIChooser.CANCEL_OPTION
     * <li>URIChooser.APPROVE_OPTION
     * <li>URIChooser.ERROR_OPTION if an error occurs or the
     *			dialog is dismissed
     * </ul>
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see #showDialog
     */
    public int showOpenDialog(Component parent) throws HeadlessException;
    /**
     * Pops up a "Save File" file chooser dialog. Note that the
     * text that appears in the approve button is determined by
     * the L&F.
     *
     * @param    parent  the parent component of the dialog,
     *			can be <code>null</code>;
     *                  see <code>showDialog</code> for details
     * @return   the return state of the file chooser on popdown:
     * <ul>
     * <li>URIChooser.CANCEL_OPTION
     * <li>URIChooser.APPROVE_OPTION
     * <li>URIChooser.ERROR_OPTION if an error occurs or the
     *			dialog is dismissed
     * </ul>
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see #showDialog
     */
    public int showSaveDialog(Component parent) throws HeadlessException;

    /**
     * Pops a custom file chooser dialog with a custom approve button.
     * For example, the following code
     * pops up a file chooser with a "Run Application" button
     * (instead of the normal "Save" or "Open" button):
     * <pre>
     * filechooser.showDialog(parentFrame, "Run Application");
     * </pre>
     *
     * Alternatively, the following code does the same thing:
     * <pre>
     *    URIChooser chooser = new URIChooser(null);
     *    chooser.setApproveButtonText("Run Application");
     *    chooser.showDialog(parentFrame, null);
     * </pre>
     *
     * <!--PENDING(jeff) - the following method should be added to the api:
     *      showDialog(Component parent);-->
     * <!--PENDING(kwalrath) - should specify modality and what
     *      "depends" means.-->
     *
     * <p>
     *
     * The <code>parent</code> argument determines two things:
     * the frame on which the open dialog depends and
     * the component whose position the look and feel
     * should consider when placing the dialog.  If the parent
     * is a <code>Frame</code> object (such as a <code>JFrame</code>)
     * then the dialog depends on the frame and
     * the look and feel positions the dialog
     * relative to the frame (for example, centered over the frame).
     * If the parent is a component, then the dialog
     * depends on the frame containing the component,
     * and is positioned relative to the component
     * (for example, centered over the component).
     * If the parent is <code>null</code>, then the dialog depends on
     * no visible window, and it's placed in a
     * look-and-feel-dependent position
     * such as the center of the screen.
     *
     * @param   parent  the parent component of the dialog;
     *			can be <code>null</code>
     * @param   approveButtonText the text of the <code>ApproveButton</code>
     * @return  the return state of the file chooser on popdown:
     * <ul>
     * <li>URIChooser.CANCEL_OPTION
     * <li>URIChooser.APPROVE_OPTION
     * <li>JFileCHooser.ERROR_OPTION if an error occurs or the
     *			dialog is dismissed
     * </ul>
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     */
    public int showDialog(final Component parent, final String approveButtonText) throws HeadlessException;

}