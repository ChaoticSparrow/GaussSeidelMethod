import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ResultDialog extends JDialog {
    private JPanel contentPane;
    private JTable tblResult;

    public ResultDialog(Stack<double[][]> result, int size) {
        setTitle("Gauss Seidel Method Result");
        setSize(new Dimension(800, 400));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setModal(true);
        DefaultTableModel data = new DefaultTableModel();

        data.addColumn("n");
        for (int z = 0; z < size; z++) {
            data.addColumn(String.format("<html>x<sub>%d</sub></html>", z));
        }

        System.out.println("Result size: " + result.size());
        for (int i = 0; i < result.size(); i++) {
            double[][] array = result.get(i);
            String[] d = new String[array.length + 1];
            d[0] = String.valueOf(i);
            System.out.println("Array length: " + array.length);

            for (int j = 0; j < array.length; j++) {
                System.out.println("DISZ: " + String.valueOf(array[j][0]));
                d[j + 1] = String.valueOf(array[j][0]);
            }
            data.addRow(d);
        }

        tblResult.setModel(data);
        tblResult.setDragEnabled(false);
        tblResult.setRowHeight(30);
        tblResult.setFont(new Font("Times New Roman", Font.PLAIN, 14));

    }
}
