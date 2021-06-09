import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow {

    public static void main(String[] args) {
        final JFrame frame1 = new JFrame("Hello, world!");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(new Dimension(300, 125));
        frame1.setResizable(false);

        JPanel systemSizePnl = new JPanel();
        systemSizePnl.setLayout(new BorderLayout(5, 5));

        JLabel sizeLbl = new JLabel("Enter the size of the linear system");
        systemSizePnl.add(sizeLbl, BorderLayout.PAGE_START);

        JTextField sizeTxt = new JTextField();
        systemSizePnl.add(sizeTxt, BorderLayout.CENTER);

        JButton generateMatrixBtn = new JButton("Generate Matrix");
        generateMatrixBtn.addActionListener(click -> {
            try {
                int i = Integer.parseInt(sizeTxt.getText());
                System.out.println(i);

                JFrame frame2 = new JFrame();
                frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame2.setSize(new Dimension(500, 500));
                frame2.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame1.setVisible(true);
                        frame2.dispose();
                    }
                });

                MatrixComp mc = new MatrixComp(i, i);
                MatrixComp mc2 = new MatrixComp(i, 1);
                MatrixComp mc3 = new MatrixComp(i, 1);

                frame2.setLayout(new BorderLayout());

                JPanel titleBar = new JPanel();
                titleBar.setLayout(new GridLayout(1, 2, 5, 5));

                JPanel secondaryTitleBar = new JPanel();
                secondaryTitleBar.setLayout(new GridLayout(1, 2, 5, 5));

                titleBar.add(new JLabel("MATRIX A"));
                secondaryTitleBar.add(new JLabel("MATRIX b"));
                secondaryTitleBar.add(new JLabel("Initial Values"));
                titleBar.add(secondaryTitleBar);

                JPanel contentBar = new JPanel();
                contentBar.setLayout(new GridLayout(1, 2, 5, 5));

                JPanel secondaryContentBar = new JPanel();
                secondaryContentBar.setLayout(new GridLayout(1, 2, 5, 5));

                contentBar.add(mc);
                secondaryContentBar.add(mc2);
                secondaryContentBar.add(mc3);
                contentBar.add(secondaryContentBar);

                JPanel solvePanel = new JPanel();
                solvePanel.setLayout(new BorderLayout());

                solvePanel.add(new JLabel("Iterations: "), BorderLayout.LINE_START);

                JTextField txtIteration = new JTextField();
                solvePanel.add(txtIteration, BorderLayout.CENTER);

                JButton btnSolve = new JButton("Solve");
                btnSolve.addActionListener(solve -> {
                    try {
                        if (!GaussSeidelMethod.isDiagonallyDominant(mc.getData())) {
                            JOptionPane.showMessageDialog(frame1, "Matrix is not Diagonally Dominant", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        }else{
                            int iteration = Integer.parseInt(txtIteration.getText());
                            var result = GaussSeidelMethod.solveFor(mc.getData(), mc2.getData(), mc3.getData(), iteration);

                            new ResultDialog(result, result.get(0).length).setVisible(true);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame2, "Please double check entered values", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        System.out.println(e);
                    }
                });

                solvePanel.add(btnSolve, BorderLayout.PAGE_END);

                frame1.setVisible(false);
                frame2.add(titleBar, BorderLayout.PAGE_START);
                frame2.add(contentBar, BorderLayout.CENTER);
                frame2.add(solvePanel, BorderLayout.PAGE_END);

                frame2.setLocationRelativeTo(null);
                frame2.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame1, "Please enter a number", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        });
        systemSizePnl.add(generateMatrixBtn, BorderLayout.PAGE_END);

        frame1.add(systemSizePnl);

        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
    }
}

//    MatrixComp mc = new MatrixComp(4, 4);
//    MatrixComp mc2 = new MatrixComp(4, 1);
//    MatrixComp mc3 = new MatrixComp(4, 1);
//
//    JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(1, 3));
//                panel.add(mc);
//                panel.add(mc2);
//                panel.add(mc3);
//
//                frame.add(panel, BorderLayout.CENTER);
//
//                JButton btnGood = new JButton("Goods na!");
//                btnGood.addActionListener(click -> {
//                System.out.println(Arrays.deepToString(mc.getData()));
//                });
//                frame.add(btnGood, BorderLayout.SOUTH);
