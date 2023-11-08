package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private CANSparkMax motor1, motor2, motor3, motor4, motor5;

    public Shooter(int motor1ID, int motor2ID, int motor3ID, int motor4ID, int motor5ID) {
        motor1 = new CANSparkMax(motor1ID, MotorType.kBrushless);
        motor2 = new CANSparkMax(motor2ID, MotorType.kBrushless);
        motor3 = new CANSparkMax(motor3ID, MotorType.kBrushless);
        motor4 = new CANSparkMax(motor4ID, MotorType.kBrushless);
        motor5 = new CANSparkMax(motor5ID, MotorType.kBrushless);
    }

    public void shoot() {
        motor1.set(.1);
        motor2.set(.1);
        motor3.set(.1);
        motor4.set(.1);
        motor5.set(.1);
    }

}
