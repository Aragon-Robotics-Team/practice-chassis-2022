// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
  private Drivetrain m_drivetrain;
  private Joystick m_joystick;
  private SlewRateLimiter m_rateLimiter = new SlewRateLimiter(0.9);
  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(Joystick joystick, Drivetrain drivetrain) {
    m_joystick = joystick;
    m_drivetrain = drivetrain;

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = -m_joystick.getRawAxis(1);
    double turn = -m_joystick.getRawAxis(2);

    m_drivetrain.getDrive().arcadeDrive(m_rateLimiter.calculate(speed), turn, true);
    // if (speed > 0.5) {
    //   m_drivetrain.test();
    // } else {
    //   m_drivetrain.off();
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
