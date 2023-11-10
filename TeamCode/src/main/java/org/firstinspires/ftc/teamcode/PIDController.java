package org.firstinspires.ftc.teamcode;

public class PIDController {
    double p;

    public PIDController (double pValue) {
        p = pValue;
    }

    public double PIDOutput (double currentInput, double targetInput, double minLimit, double maxLimit) {
        double output;
        double error =  currentInput - targetInput;

        output = p * error;

        if (output < minLimit) {
            output = minLimit;
            return output;
        }
        else if (output > maxLimit) {
            output = maxLimit;
            return output;
        }
        else {
            return output;
        }
    }
}
