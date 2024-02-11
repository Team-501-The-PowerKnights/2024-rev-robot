package frc.robot.subsystems;
import static frc.robot.Constants.IntakeConstants.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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
        intakeLeft = new CANSparkMax(kFeederLeft,MotorType.kBrushless);
        intakeRight = new CANSparkMax(kFeederRight,MotorType.kBrushless);
        intakeRight.setInverted(true);
        intakeRight.follow(intakeLeft);
        intakeSpeed = kIntakeSpeed;
        System.out.println("Intake Constructed!!");
    }
    //Sets the speed of the lead motor
    public void setIntakeSpeed(double speed) {
        intakeLeft.set(speed);
        //System.out.println("Setting Intake Speed. WHEEEEEEEEEE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    //Sets the speed of the lead motor to 0
    public void stop() {
        intakeLeft.set(0);
        //System.out.println("STOPPING THE INTAKE!!! Hope we have a note.#################################################");
    }
    //Use this command to pull a note off the floor
    public Command runIntake() {
        return this.startEnd(
            () -> {
                //System.out.println("Starting RunIntake.>>>>>>>>>>>>>>");
                setIntakeSpeed(kIntakeSpeed);
            }
            ,
            () -> {
                //System.out.println("Stopping RunIntake.##############");
                stop();}
            );
    }
    //Use this command to "eject" a note back onto the floor
    public Command ReverseIntake() {
        return this.startEnd(
            () -> {setIntakeSpeed(kIntakeSpeed * -0.5);}
            ,
            () -> {stop();}
            );
    }

    //END OF INTAKE CLASS
}
