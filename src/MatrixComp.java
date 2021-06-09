import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MatrixComp extends JPanel {

    private ArrayList<JTextField> tfList = new ArrayList<>();

    private int rows, cols;

    public MatrixComp(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(rows, cols));
        generateRowsAndCols();
    }

    private void generateRowsAndCols() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                var tf = createTextField();
                this.add(tf);
                tfList.add(tf);
            }
        }
    }

    private JTextField createTextField() {
        final JTextField tf = new JTextField();
        return tf;
    }

    public double[][] getData() {
        final double[][] data = new double[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Double.parseDouble(tfList.get(index).getText());
                index++;
            }
        }
        return data;
    }
}
