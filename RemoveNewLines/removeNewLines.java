import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class removeNewLines {
    public static void main(String[] args) {
        System.out.println("Program is working... Im watching board.");
        System.out.println("When you copy something, I will combine it.");
        System.out.println("You can close program to stop it. (Ctrl+C)\n");

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String lastContent = "";

        while (true) {
            try {
                Transferable contents = clipboard.getContents(null);

                if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String currentText = (String) contents.getTransferData(DataFlavor.stringFlavor);

                    if (!currentText.equals(lastContent) && (currentText.contains("\n") || currentText.contains("\r"))) {
                        String fixedText = currentText.replaceAll("\\s+", " ").trim();

                        StringSelection selection = new StringSelection(fixedText);
                        clipboard.setContents(selection, selection);
                        
                        lastContent = fixedText;
                        
                        System.out.println("EDITED: " + fixedText);
                        System.out.println("------------------------------------------------");
                    } else lastContent = currentText;
                }
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }
}
