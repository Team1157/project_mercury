
long lastTime;


long time = System.currentTimeMillis();
SmartDashboard.putNumber("UPS", 1000/(time - lastTime));
lastTime = time;




        http://first.wpi.edu/FRC/roborio/beta/docs/java/index.html?


//TODO: Gyro


        SmartDashboard.putNumber("Accel X",     Accelerometer.getX());
        SmartDashboard.putNumber("Accel Y",     Accelerometer.getY());
        SmartDashboard.putNumber("Accel Z",     Accelerometer.getZ());