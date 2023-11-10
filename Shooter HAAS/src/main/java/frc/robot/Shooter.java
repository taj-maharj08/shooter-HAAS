package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private CANSparkMax m_leftRoller, m_rightRoller, m_flywheelMotorPrimary, m_flywheelMotorSecondary, m_flywheelTertiary;

    public Shooter() {
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
        return this.runOnce(() -> {
            m_flywheelMotorPrimary.set(0.1);
            m_rightRoller.set(0.5);
        }).withName("shoot");
    }

    public Command idle() {
        return this.runOnce(() -> {
            m_flywheelMotorPrimary.set(0);
            m_rightRoller.set(0);
        }).withName("Idle");
    }
}
