package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private CANSparkMax m_leftRoller, m_rightRoller, m_flywheelMotorPrimary, m_flywheelMotorSecondary, m_flywheelTertiary;
    private SimpleMotorFeedforward feedforward;
    private final double goalVel = 5000;
    private final double goalIdleVel = 0;
    public Shooter() {
        feedforward = new SimpleMotorFeedforward(0.1, 0.008, 0);

        m_leftRoller = new CANSparkMax(0, MotorType.kBrushless);
        m_rightRoller = new CANSparkMax(1, MotorType.kBrushless);
        m_flywheelMotorPrimary = new CANSparkMax(2, MotorType.kBrushless);
        m_flywheelMotorSecondary = new CANSparkMax(3, MotorType.kBrushless);
        m_flywheelTertiary = new CANSparkMax(4, MotorType.kBrushless);
        
        m_flywheelMotorSecondary.follow(m_flywheelMotorPrimary);
        m_flywheelTertiary.follow(m_flywheelMotorPrimary);
        m_leftRoller.follow(m_rightRoller);
        setDefaultCommand(idle());
    }

    public Command shoot(){
        return this.run(() -> {
            double ft = feedforward.calculate(goalVel);
            m_flywheelMotorPrimary.getPIDController().setReference(goalVel, ControlType.kVelocity, 0, ft);
            m_rightRoller.set(0.5);
        }).withName("shoot");
    }

    public double getSpeed(){
        return m_flywheelMotorPrimary.getEncoder().getVelocity();
    }

    public Command idle() {
        return this.run(() -> {
            double ft = feedforward.calculate(goalIdleVel);
            m_flywheelMotorPrimary.getPIDController().setReference(goalIdleVel, ControlType.kVelocity, 0, ft);
            m_rightRoller.set(0);
        }).withName("Idle");
    }
}
