import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.event.*;
import java.text.MessageFormat;

public class Form extends JFrame implements ActionListener, KeyListener {
    private JPanel mainPanel;
    private JScrollPane scrollContainerPana;
    private JTextField formulalnput;
    private JList functionsList;
    private JButton evalButton;
    private JTextArea textArea1;
    String mystring;
    double result;

    public Form() {
        this.setTitle("SciCalcu");
        setContentPane(mainPanel);
        pack();
        this.setBounds(0, 0, 900, 600);

        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("Options");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        final JMenuItem reset = new JMenuItem("Reset");
        JMenuItem exit = new JMenuItem("Exit");
        textArea1.setEditable(false);

        fileMenu.add(reset);
        evt ev = new evt();
        reset.addActionListener(ev);

        fileMenu.add(exit);
        event e = new event();
        exit.addActionListener(e);

        menubar.add(fileMenu);
        setJMenuBar(menubar);

        MyList ad = new MyList("addition", "+");
        MyList sub = new MyList("subtraction", "-");
        MyList mp = new MyList("multiplication", "*");
        MyList sin = new MyList("sine", "sin()");
        MyList cos = new MyList("cosine", "cos()");
        MyList tn = new MyList("tangent", "tg()");
        MyList ctg = new MyList("cotangent", "ctg()");
        MyList arcs = new MyList("arcsine", "arcsin()");
        MyList ppi = new MyList("PI", "pi");
        MyList ee = new MyList("e", "e");
        MyList gr = new MyList("golden ratio", "[phi]");
        final MyList last = new MyList("Last result", "");

        final DefaultListModel<MyList> listModel = new DefaultListModel();
        listModel.addElement(ad);
        listModel.addElement(sub);
        listModel.addElement(mp);
        listModel.addElement(sin);
        listModel.addElement(cos);
        listModel.addElement(tn);
        listModel.addElement(ctg);
        listModel.addElement(arcs);
        listModel.addElement(ppi);
        listModel.addElement(ee);
        listModel.addElement(gr);
        listModel.addElement(last);

        functionsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = functionsList.getSelectedIndex();
                    switch (index) {
                        case 0:
                            formulalnput.setText(formulalnput.getText() + "+");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition());
                            break;
                        case 1:
                            formulalnput.setText(formulalnput.getText() + "-");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition());
                            break;
                        case 2:
                            formulalnput.setText(formulalnput.getText() + "*");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition());
                            break;
                        case 3:
                            formulalnput.setText(formulalnput.getText() + "sin()");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition() - 1);
                            break;
                        case 4:
                            formulalnput.setText(formulalnput.getText() + "cos()");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition() - 1);
                            break;
                        case 5:
                            formulalnput.setText(formulalnput.getText() + "tg()");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition() - 1);
                            break;
                        case 6:
                            formulalnput.setText(formulalnput.getText() + "ctg()");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition() - 1);
                            break;
                        case 7:
                            formulalnput.setText(formulalnput.getText() + "arcsin()");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition() - 1);
                            break;
                        case 8:
                            formulalnput.setText(formulalnput.getText() + "pi");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition());
                            break;
                        case 9:
                            formulalnput.setText(formulalnput.getText() + "e");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition());
                            break;
                        case 10:
                            formulalnput.setText(formulalnput.getText() + "[phi]");
                            formulalnput.requestFocus();
                            formulalnput.setCaretPosition(formulalnput.getCaretPosition());
                            break;
                        case 11:
                            formulalnput.setText(formulalnput.getText() + "");
                            MyList function = (MyList) functionsList.getSelectedValue();
                            double value = 0.0;
                            if(function.getName().equals("Last result")) {
                                value = result;
                            }
                            formulalnput.setText(formulalnput.getText() + value);
                            formulalnput.requestFocus();
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        functionsList.setModel(listModel);

        evalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               mystring = formulalnput.getText();
                org.mariuszgromada.math.mxparser.Expression expression = new Expression(mystring);
                if (expression.checkSyntax()) {
                    result = expression.calculate();
                    String res = String.valueOf(result);
                    MessageFormat form = new MessageFormat(mystring + " = " + res + "\n");
                    textArea1.append(form.toPattern());
                    formulalnput.setText(" ");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong formula", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    formulalnput.setText(" ");
                }
            }
        });

        formulalnput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    mystring = formulalnput.getText();
                    org.mariuszgromada.math.mxparser.Expression expression = new Expression(mystring);
                    if (expression.checkSyntax()) {
                        result = expression.calculate();
                        String res = String.valueOf(result);
                        MessageFormat form = new MessageFormat(mystring + " = " + res + "\n");
                        textArea1.append(form.toPattern());
                        formulalnput.setText(" ");
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong formula", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        formulalnput.setText(" ");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    formulalnput.setText(mystring);
                }
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(Form.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public class event implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public class evt implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textArea1.setText("");
            formulalnput.setText("");
        }
    }
}