public class Calculator implements CalculatorInterface {
    private double firstNumber;
    private double secondNumber;
    public OperationList operationList = new OperationList();

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    @Override
    public double action(int operationID) {
        double result = 0;
        switch (operationID) {
            case 1:
                result = firstNumber + secondNumber;
                operationList.addResult(result);
                break;
            case 2:
                result = firstNumber - secondNumber;
                operationList.addResult(result);
                break;
            case 3:
                result = firstNumber * secondNumber;
                operationList.addResult(result);
                break;
            case 4:
                result = firstNumber / secondNumber;
                operationList.addResult(result);
                break;
        }
        return result;
    }
}
