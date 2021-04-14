
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
    
    public int optionMessage(String missatge) {
        String[] options = {"Editar usuari", "Deshabilitar usuari"};
        return JOptionPane.showOptionDialog(
            null,
            "Quina acció vols realitzar", 
            "FEM Coworking", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]
        );
    }
}
