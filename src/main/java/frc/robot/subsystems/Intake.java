package frc.robot.subsystems;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

//import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.Robot;

public class Intake extends SubsystemBase {
    CANSparkMax intakeLeft;
    CANSparkMax intakeRight;
    double intakeSpeed;

    public Intake(){
        intakeLeft = new CANSparkMax(kIntakeLeft,MotorType.kBrushless);
        intakeRight = new CANSparkMax(kIntakeRight,MotorType.kBrushless);
        intakeLeft.setSmartCurrentLimit(kIntakeCurrentLimit);
        intakeRight.setSmartCurrentLimit(kIntakeCurrentLimit);
        intakeRight.setInverted(true);
        intakeRight.follow(intakeLeft);
        intakeSpeed = kIntakeSpeed;
        System.out.println("Intake Constructed!!");
    }
    //Sets the speed of the lead motor
    public void setIntakeSpeed(double speed) {
        intakeLeft.set(speed);
    }
    //Sets the speed of the lead motor to 0
    public void stop() {
        intakeLeft.set(0);
    }
    //Use this command to pull a note off the floor
    public Command runIntake() {
        return this.startEnd(
            () -> {setIntakeSpeed(kIntakeSpeed);}
            ,() -> {stop();}
            );
    }
    //Use this command to "eject" a note back onto the floor
    public Command reverseIntake() {
        return this.startEnd(
            () -> {setIntakeSpeed(kIntakeSpeed * -0.5);}
            ,() -> {stop();}
            );
    }

    //END OF INTAKE CLASS
}
