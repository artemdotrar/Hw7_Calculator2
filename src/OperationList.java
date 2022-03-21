public class OperationList {
    private double[] history = new double[5];

    public String getHistory() {
        String historyLine = "";
        for (int i = 0; i < history.length; i++) {
            historyLine += history[i] + "\t";
        }
        return historyLine;
    }


    public void addResult(double result) {
        for (int i = history.length - 1; i > 0; i--) {
            history[i] = history[i - 1];
        }
        history[0] = result;

    }
}
