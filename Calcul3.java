package Calculator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Calcul3 extends JFrame{

    private JButton button_Plus, button_Minus, button_Mult, button_Div, button_Exp, button_C, button_Help, button_Point;
    private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    private JPanel panelSimbols, panelField, panelClean, panelAll;
    private  JTextField fieldInput1, fieldInput2, fieldOut;
    private JLabel label, label2, labelResult;
    private String textFild1 = "";
    private String textFild2 = "";
    private boolean flag = false;  //Позволяет выбрать поле, в которое производится ввод числа
    private StringBuilder bildNumber = new StringBuilder("");

    private double n1 = 0; //хранит значение 1 поля
    private double n2 = 0;  //хранит значение 2 поля
    private int countPoint = 0; // для ввода дробного числа при помощи числовых кнопок

    public Calcul3(){
        super("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocation(150, 150);
        setResizable(false);//Запрет на изменения размеров окна калькулятора

        label = new JLabel("Введите 1 число:");
        label2 = new JLabel("Введите 2 число:");
        labelResult = new JLabel("Результат:");

        panelField = new JPanel(); //Панель для текстового поля
        panelField.setLayout(new GridLayout(3, 2, 0,5));

        //Рамки вокруг fieldInput1, чтобы улучшить внешний вид
        EmptyBorder border = new EmptyBorder(4, 0, 4, 0);
        LineBorder line = new LineBorder(Color.gray, 1, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        // Создаем текстое поле fieldInput1 и размещаем его на панель panelField
        panelField.add(label);
        fieldInput1 = new JTextField(10);
        panelField.add(fieldInput1);

        panelField.add(label2);
        fieldInput2 = new JTextField(10);
        panelField.add(fieldInput2);

        panelField.add(labelResult);
        fieldOut = new JTextField(10);
        panelField.add(fieldOut);

        fieldInput1.setFont(new Font("Dialog", Font.PLAIN, 14)); //public Font(String name, int style, int size)
        fieldInput1.setBorder(compound);
        fieldInput2.setFont(new Font("Dialog", Font.PLAIN, 14));
        fieldInput2.setBorder(compound);
        fieldOut.setFont(new Font("Dialog", Font.PLAIN, 14));
        fieldOut.setBorder(compound);
        fieldInput1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {//Слушатель поля; код выполняется, когда поле получает фокус
                if (fieldInput1.isFocusOwner()){flag = true;
                forField();
                label.setForeground(Color.BLACK);
                label.setText("Введите 1 число:");}

            }
        });
        fieldInput2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fieldInput2.isFocusOwner()){flag = false;
                forField();
                label2.setForeground(Color.BLACK);
                label2.setText("Введите 2 число:");}
            }
        });

        EmptyBorder borderPan = new EmptyBorder(15, 7, 15, 7);
        panelField.setBorder(borderPan);


        EmptyBorder borderPanelSimbol = new EmptyBorder(15, 7, 0, 7);
        panelSimbols = new JPanel();
        panelSimbols.setLayout(new GridLayout(4,4));
        panelSimbols.setBorder(borderPanelSimbol);
//Слушатель для кнопок арифметических действий - buttonListener
        ButtonListener buttonListener = new ButtonListener();
        button_Plus = new JButton("+");
        button_Plus.addActionListener(buttonListener);
        button_Minus = new JButton("-");
        button_Minus.addActionListener(buttonListener);
        button_Mult = new JButton("*");
        button_Mult.addActionListener(buttonListener);
        button_Div = new JButton("/");
        button_Div.addActionListener(buttonListener);
        button_Exp = new JButton("n1^n2");
        button_Exp.addActionListener(buttonListener);
        button_C = new JButton("Сlean");
        button_C.addActionListener(new CleanListener());
        button_Help = new JButton("Help");
        button_Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Help")){
                    JOptionPane.showMessageDialog(
                            null, "<html>Чтобы сложить два числа,<br>" +
                                    "введите их в соответствующие поля и нажмите кнопку +<br>" +
                                    "Аналогично с остальными операциями.</html>"
                    );
                }
            }
        });

//Слушатель для числовых кнопок - numbListener
        NumberListener numbListener = new NumberListener();
        b0 = new JButton("0");
        b0.addActionListener(numbListener);
        b1 = new JButton("1");
        b1.addActionListener(numbListener);
        b2 = new JButton("2");
        b2.addActionListener(numbListener);
        b3 = new JButton("3");
        b3.addActionListener(numbListener);
        b4 = new JButton("4");
        b4.addActionListener(numbListener);
        b5 = new JButton("5");
        b5.addActionListener(numbListener);
        b6 = new JButton("6");
        b6.addActionListener(numbListener);
        b7 = new JButton("7");
        b7.addActionListener(numbListener);
        b8 = new JButton("8");
        b8.addActionListener(numbListener);
        b9 = new JButton("9");
        b9.addActionListener(numbListener);
        button_Point = new JButton(",");
        button_Point.addActionListener(numbListener);

        panelSimbols.add(b1);
        panelSimbols.add(b2);
        panelSimbols.add(b3);
        panelSimbols.add(button_Plus);

        panelSimbols.add(b4);
        panelSimbols.add(b5);
        panelSimbols.add(b6);
        panelSimbols.add(button_Minus);

        panelSimbols.add(b7);
        panelSimbols.add(b8);
        panelSimbols.add(b9);
        panelSimbols.add(button_Mult);

        panelSimbols.add(b0);
        panelSimbols.add(button_Point);
        panelSimbols.add(button_Exp);
        panelSimbols.add(button_Div);

        panelClean = new JPanel();
        panelClean.setLayout(new GridLayout(1, 2));
        panelClean.setBorder(borderPan);
        panelClean.add(button_Help);
        panelClean.add(button_C);

        panelAll = new JPanel();
        panelAll.setLayout(new BorderLayout());
        panelAll.add(panelField, "North");
        panelAll.add(panelSimbols, "Center");
        panelAll.add(panelClean, "South");

        Container container = getContentPane();
        container.add(panelAll);
        setVisible(true);
    }

    //Вспомогательный метод с инструкцими, используется в слушателе для полей ввода
    public void forField(){
        bildNumber.setLength(0);
        countPoint = 1;
        fieldOut.setText("");
    }
    //Создадим класс-слушатель кнопок ButtonListener
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            String result = "";
            if (ae.getActionCommand().equals("+")){
                getTextFields();
                result = String.valueOf(n1 + n2);
            }
            if (ae.getActionCommand().equals("-")){
                getTextFields();
                result = String.valueOf((n1 - n2));
            }
            if (ae.getActionCommand().equals("*")){
                getTextFields();
                result = String.valueOf(n1 * n2);
            }
            if (ae.getActionCommand().equals("/")){
                getTextFields();
                if (n2 != 0) {
                    result = String.valueOf(n1 / n2);
                } else {
                    label2.setForeground(Color.RED);
                    label2.setText("На ноль делить нельзя!");
                }
            }
            if (ae.getActionCommand().equals("n1^n2")){
                getTextFields();
                double m = 1;
                if ((n2-Math.round(n2))==0 && n2>0){ //n2 - показатель степени, должен быть целым
                    for (int i = 1; i<=n2; i++){
                        m = m*n1;
                    }
                    result = String.valueOf(m);
                }else {
                    label2.setForeground(Color.RED);
                    label2.setText("<html>Введите целое число!<br>(положительное)</html>");
                }
            }
            fieldOut.setText(result);
            n1=0; n2=0;
        }
    }
    //Создаем слушателя для кнопки clean. По нажатию на нее все текстовые поля очищаются
    class CleanListener implements ActionListener{
        public void actionPerformed(ActionEvent ex){
            if(ex.getActionCommand().equals("Сlean")) {
                fieldInput1.setText(""); //стераем содержимое поля field
                fieldInput2.setText("");
                fieldOut.setText("");
                fieldInput1.requestFocus(); //возвращаем фокус полю
                label.setForeground(Color.BLACK);
                label.setText("Введите 1 число:");
                label2.setForeground(Color.BLACK);
                label2.setText("Введите 2 число:");
                bildNumber.delete(0, bildNumber.length());//очищаем построитель строк
            }
        }
    }
    //Вспомогательный метод, используется в классе ButtonListener
    public void getTextFields(){ //получаем содержимое полей ввода
        textFild1 = fieldInput1.getText().trim(); //содержимое поля fieldInput1, (методом trim() отбрасываем пробелы)
        textFild2 = fieldInput2.getText().trim();
        try {
            n1 = Double.parseDouble(textFild1); //метод parseDouble - переводит строку в число
        }catch (NumberFormatException e){
            label.setForeground(Color.RED);
            label.setText("<html>Вы ничего не ввели<br>или это не число</html>");
            fieldInput1.setFocusable(true);
        }
        try {
            n2 = Double.parseDouble(textFild2);
        }catch (NumberFormatException e){
            label2.setForeground(Color.RED);
            label2.setText("<html>Вы ничего не ввели<br>или это не число</html>");
        }
            bildNumber.setLength(0);
    }
    //Слушатель для числовых кнопок
    public class NumberListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
                if (ae.getActionCommand().equals("1")){
                    bildNumber.append("1");
                }
                if (ae.getActionCommand().equals("2")){
                    bildNumber.append("2");
                }
                if (ae.getActionCommand().equals("3")){
                    bildNumber.append("3");
                }
                if (ae.getActionCommand().equals("4")){
                    bildNumber.append("4");
                }
                if (ae.getActionCommand().equals("5")){
                    bildNumber.append("5");
                }
                if (ae.getActionCommand().equals("6")){
                    bildNumber.append("6");
                }
                if (ae.getActionCommand().equals("7")){
                    bildNumber.append("7");
                }
                if (ae.getActionCommand().equals("8")){
                    bildNumber.append("8");
                }
                if (ae.getActionCommand().equals("9")){
                    bildNumber.append("9");
                }
                if (ae.getActionCommand().equals("0")){
                    bildNumber.append("0");
                }
                if (ae.getActionCommand().equals(",")){
                    if (countPoint == 1){
                        bildNumber.append(".");
                        countPoint--;
                    }
                }
                if (flag){ fieldInput1.setText(bildNumber.toString());
                } else {fieldInput2.setText(bildNumber.toString());
                }
            }
        }


    }

