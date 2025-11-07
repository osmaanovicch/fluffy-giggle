import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JPanel CalculatorPanel;

    private JButton ModuloButton;
    private JButton AbsButton;
    private JButton PercentButton;
    private JButton DivisionButton;
    private JButton MultiplicationButton;
    private JButton EqualsButton;
    private JButton CommaButton;
    private JButton ZeroButton;
    private JButton ACButton;
    private JButton SubtractionButton;
    private JButton SumButton;


    private JButton OneButton;
    private JButton TwoButton;
    private JButton ThreeButton;
    private JButton FourButton;
    private JButton FiveButton;
    private JButton SixButton;
    private JButton SevenButton;
    private JButton EightButton;
    private JButton NineButton;

    private JTextField textField1;

    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean isNewOperation = true;




    public Calculator(){
        ActionListener numberListener = new NumberListener();
        ZeroButton.addActionListener(numberListener);
        OneButton.addActionListener(numberListener);
        TwoButton.addActionListener(numberListener);
        ThreeButton.addActionListener(numberListener);
        FourButton.addActionListener(numberListener);
        FiveButton.addActionListener(numberListener);
        SixButton.addActionListener(numberListener);
        SevenButton.addActionListener(numberListener);
        EightButton.addActionListener(numberListener);
        NineButton.addActionListener(numberListener);

        CommaButton.addActionListener(e-> addDecimalPoint());

        SumButton.addActionListener(e -> setOperator("+"));
        SubtractionButton.addActionListener(e -> setOperator("-"));
        MultiplicationButton.addActionListener(e -> setOperator("*"));
        DivisionButton.addActionListener(e -> setOperator("/"));
        ModuloButton.addActionListener(e -> setOperator("%"));
        PercentButton.addActionListener(e ->applyPercent());
        AbsButton.addActionListener(e -> applyAbs());

        EqualsButton.addActionListener(e -> CalculateResult());
        ACButton.addActionListener(e -> clear());
        textField1.setHorizontalAlignment(SwingConstants.RIGHT);

    }
    private class NumberListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String value = ((JButton)e.getSource()).getText();
            if(isNewOperation){
                textField1.setText(value);
                isNewOperation=false;
            }else {
                textField1.setText(textField1.getText()+value);
            }
        }
    }
    private void setOperator(String op){
        if(!operator.isEmpty()){
            CalculateResult();
        }
        firstNumber=Double.parseDouble(textField1.getText());
        operator=op;
        isNewOperation=true;
    }

    private void CalculateResult() {
        secondNumber = Double.parseDouble(textField1.getText());
        double result;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = (secondNumber != 0) ? (firstNumber / secondNumber) : 0;
                break;
            case "%":
                result = firstNumber % secondNumber;
                break;
            default:
                result = secondNumber; // ako nema operatora, samo prikaži uneseni broj
        }

        textField1.setText(String.valueOf(result));
        firstNumber = result;
        operator = "";
        isNewOperation = true;
    }
    private void clear(){
        textField1.setText("0");
        firstNumber=0;
        secondNumber=0;
        operator="";
        isNewOperation=true;
    }
    private void applyPercent() {
        double value = Double.parseDouble(textField1.getText());
        value = value / 100;
        textField1.setText(String.valueOf(value));
        isNewOperation = true;
    }
    private void applyAbs() {
        // Pretvara trenutni broj u apsolutnu vrijednost
        double value = Double.parseDouble(textField1.getText());
        value = Math.abs(value);
        textField1.setText(String.valueOf(value));
        isNewOperation = true;
    }

    private void addDecimalPoint() {
        // Dodaje decimalnu tačku ako već ne postoji
        if (isNewOperation) {
            textField1.setText("0.");
            isNewOperation = false;
        } else if (!textField1.getText().contains(".")) {
            textField1.setText(textField1.getText() + ".");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().CalculatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
