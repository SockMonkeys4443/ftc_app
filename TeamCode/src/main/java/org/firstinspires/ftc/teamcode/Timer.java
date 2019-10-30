package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Timer {
    private ElapsedTime runtime;
    //a bookmark set when restart() is done
    private double timeMark;

    Timer(ElapsedTime runtime) {
        this.runtime = runtime;
    }

    public void restart() {
        timeMark = runtime.time();

    }

    /**
     * Tells you how much time has passed since restart() was called
     * @return the amount of seconds that have passed
     */
    public double check() {
        return runtime.time() - timeMark;
    }
}
