package student;

import javax.swing.JOptionPane;

/** Util class for GUI */
public class GUIUtil {

    /**
    * Display message.
    * 
    * @param content message content
    * @param title message dialog title
    */
    public static void showMessage(String content, String title) {
       JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
   }
}
