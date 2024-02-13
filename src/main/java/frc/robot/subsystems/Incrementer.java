package frc.robot.subsystems;
import static frc.robot.Constants.IncrementerConstants.*;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Incrementer extends SubsystemBase {
    CANSparkMax incrementerLeft;
    CANSparkMax incrementerRight;
    double incrementerSpeed;

    public Incrementer() {
        incrementerLeft = new CANSparkMax(kIncrementerLeft, MotorType.kBrushless);
        incrementerRight = new CANSparkMax(kIncrementerRight, MotorType.kBrushless);
        incrementerLeft.setSmartCurrentLimit(kIncrementerCurrentLimit);
        incrementerRight.setSmartCurrentLimit(kIncrementerCurrentLimit);
        incrementerRight.setInverted(true);
        incrementerRight.follow(incrementerLeft);
        incrementerSpeed = kIncrementerSpeed;
        System.out.println("Incrementer Constructed!!");
    }
    //Sets the speed of the lead motor
    public void setIncrementerSpeed(double speed) {
        incrementerLeft.set(speed);
    }
    //Sets the speed of the lead motor to 0
    public void stop() {
        incrementerLeft.set(0);
    }
    //Use this command to pull a note off the floor
    public Command runIncrementer() {
        return this.startEnd(
            () -> {setIncrementerSpeed(kIncrementerSpeed);}
            ,() -> {stop();}
            );
    }
    //Use this command to "eject" a note back onto the floor
    public Command ReverseIncrementer() {
        return this.startEnd(
            () -> {setIncrementerSpeed(kIncrementerSpeed * -0.5);}
            ,() -> {stop();}
            );
    }



}
