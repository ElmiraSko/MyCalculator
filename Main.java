package Calculator;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calcul3();
				System.out.print("Test");
            }
        });

    }
}
