/**
 * 
 */
package fr.norsys.formation.ihm.main;

import fr.norsys.formation.ihm.UtilisateurUI;

/**
 * @author technomaker09
 *
 */
public class Main {

	/**
	 * @param args
	 */
    private static void createAndShowGUI() {
    	
        //Create and set up the content pane.
        UtilisateurUI ui = new UtilisateurUI();
        ui.setVisible(true); //content panes must be opaque
    }
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}
