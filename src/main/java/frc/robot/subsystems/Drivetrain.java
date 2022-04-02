// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  public static final class Config {
    public static final int kRightMotorMaster = 6;
    public static final int kLeftMotorMaster = 3;
    public static final int kRightMotorSlave = 8;
    public static final int kLeftMotorSlave = 7;
  }

  private WPI_TalonSRX m_rightMotorMaster = new WPI_TalonSRX(Config.kRightMotorMaster);
  private WPI_TalonSRX m_leftMotorMaster = new WPI_TalonSRX(Config.kLeftMotorMaster);
  private WPI_VictorSPX m_rightMotorSlave = new WPI_VictorSPX(Config.kRightMotorSlave);
  private WPI_VictorSPX m_leftMotorSlave = new WPI_VictorSPX(Config.kLeftMotorSlave);
 
  private DifferentialDrive m_drive = new DifferentialDrive(m_rightMotorMaster, m_leftMotorMaster);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    m_rightMotorMaster.setInverted(true);
    m_rightMotorSlave.setInverted(true);

    m_rightMotorSlave.follow(m_rightMotorMaster);
    m_leftMotorSlave.follow(m_leftMotorMaster);

    m_rightMotorMaster.setSelectedSensorPosition(0.0);
    m_leftMotorMaster.setSelectedSensorPosition(0.0);

    SmartDashboard.putData("Practice/resetDrivetrain", new InstantCommand(this::reset, this));
    m_rightMotorMaster.setNeutralMode(NeutralMode.Brake);
    m_leftMotorMaster.setNeutralMode(NeutralMode.Brake);
    m_rightMotorSlave.setNeutralMode(NeutralMode.Brake);
    m_rightMotorSlave.setNeutralMode(NeutralMode.Brake);
  }

  public DifferentialDrive getDrive() {
    return m_drive;
  }

  public void test() {
    m_rightMotorSlave.set(1.0);
    m_leftMotorSlave.set(1.0);
  }

  public void off() {
    m_rightMotorSlave.set(0.0);
    m_leftMotorSlave.set(0.0);
  }

  public void reset() {
    m_rightMotorMaster.setSelectedSensorPosition(0.0);
    m_leftMotorMaster.setSelectedSensorPosition(0.0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Practice/rightEnc", m_rightMotorMaster.getSelectedSensorPosition());
    SmartDashboard.putNumber("Practice/leftEnc", m_leftMotorMaster.getSelectedSensorPosition());
    SmartDashboard.putNumber("Practice/rightVolt", m_rightMotorMaster.getBusVoltage());
    SmartDashboard.putNumber("Practice/leftVolt", m_leftMotorMaster.getBusVoltage());
    SmartDashboard.putNumber("Practice/rightVel", m_rightMotorMaster.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Practice/leftVel", m_leftMotorMaster.getSelectedSensorVelocity());
  }
}
