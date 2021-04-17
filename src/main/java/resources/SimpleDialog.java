
package resources;

import javax.swing.JOptionPane;

/**
 * Classe per gestionar de manera més àgil com mostrar Dialogs window
 * 
 * @author marc
 */
public class SimpleDialog {
    public void warningMessage(String missatge) {
        JOptionPane.showMessageDialog(
            null, 
            missatge, 
            "FEM Coworking", 
            JOptionPane.WARNING_MESSAGE
        );
    }
    
    public void errorMessage(String missatge) {
        JOptionPane.showMessageDialog(
            null, 
            missatge, 
            "FEM Coworking", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    public void infoMessage(String missatge) {
        JOptionPane.showMessageDialog(
            null, 
            missatge, 
            "FEM Coworking", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public void questionMessage(String missatge) {
        JOptionPane.showMessageDialog(
            null, 
            missatge, 
            "FEM Coworking", 
            JOptionPane.QUESTION_MESSAGE
        );
    }
    
    public int optionMessage(String missatge, String option0, String option1) {
        String[] options = {option0, option1};
        return JOptionPane.showOptionDialog(
            null,
            missatge, 
            "FEM Coworking", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]
        );
    }
    
    public int confirmDialog(String missatge, String option0, String option1) {
        return JOptionPane.showConfirmDialog(
            null, 
            missatge, 
            "FEM Coworking", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
    }
}
