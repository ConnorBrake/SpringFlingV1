package com.mycompany.springflingv1;
import java.util.Scanner;
import java.lang.Math;

public class SpringFling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Working Velocities
        double possibleVelocity1 = 0;
        double possibleVelocity2 = 0;
        double possibleVelocity3 = 0;
        double possibleVelocity4 = 0;
        double possibleVelocity5 = 0;
        int solutions = 0;
        //Starting Launch Speed [FWD]
        double launchSpeed = 0;
        boolean launchSpeedFound = false;
        //The Unknown-Known Varibles [FWD]
        double horizontalVelocity = 0;
        double horizontalDistance = 0;
        double horizontalAcceleration = 0;
        double verticalVelocity = 0;
        double verticalDistance = 0;
        double verticalAcceleration = 0;
        //Forces of Air Resistance
        double horizontalAirResistance = 0;
        double verticalAirResistance = 0;
        //Gets Required Values 
        Scanner input = new Scanner(System.in); 
        System.out.println("Input The Launch Angle");
        double launchAngle = input.nextDouble();
        System.out.println("Input Bumper Height (m)");
        double bumperHeight = input.nextDouble();
        System.out.println("Input The X Distance To The Bucket (m)");
        double distanceToBucket = input.nextDouble();
        System.out.println("Input The Cross Sectional Area of Your Spring (m^2)");
        double springCrossSectionalArea = input.nextDouble();
        System.out.println("Input The Mass of Your Spring (kg)");
        double springMass = input.nextDouble();
        
        //Calulates The Springs Starting Height
        double rampHeight = (Math.sin(Math.toRadians(launchAngle)))*0.612;
        double springInitialHeight = rampHeight + 1.081 + bumperHeight;
        System.out.println("The Springs Intital Height (m) = " + springInitialHeight);
        
        //Calculates The Trajectory of The Spring on Both Axis
        System.out.println("Trajectory Being Calculated");
        while(launchSpeedFound == false)
        {
            launchSpeed += 0.01;
            //Resets The Components of The Launch Speed
            horizontalVelocity = (Math.cos(Math.toRadians(launchAngle)))*launchSpeed;
            horizontalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*(Math.pow(horizontalVelocity, 2));
            horizontalDistance = 0;
            horizontalAcceleration = horizontalAirResistance/springMass;
            
            verticalVelocity = (Math.sin(Math.toRadians(launchAngle)))*launchSpeed;
            verticalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*(Math.pow(verticalVelocity, 2));
            //System.out.println("Changed Force! " + verticalAirResistance);
            verticalDistance = springInitialHeight;
            verticalAcceleration = (verticalAirResistance/springMass) + -9.807;
            
            //System.out.println(horizontalDistance + " -" + verticalDistance + " ");
            
            for(double time = 0.01; time < 3.50; time += 0.01)
            {
                //double degree = Math.toDegrees(Math.atan(verticalVelocity/horizontalVelocity));
                horizontalDistance = horizontalDistance + (horizontalVelocity*(0.01)) + (0.5*horizontalAcceleration*(Math.pow((0.01), 2)));
                horizontalVelocity = horizontalVelocity + (horizontalAcceleration * (0.01));
                horizontalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*(Math.pow(horizontalVelocity, 2));
                horizontalAcceleration = (horizontalAirResistance/springMass);
                
                verticalDistance = verticalDistance + (verticalVelocity*(0.01)) + (0.5*verticalAcceleration*(Math.pow((0.01), 2)));
                verticalVelocity = (verticalAcceleration *(0.01)) + verticalVelocity;
                //Changing Air Resistance Direction based on if the spring is falling or not
                if((Math.toDegrees(Math.atan(verticalVelocity/horizontalVelocity))) <= 0)
                {
                    verticalAirResistance = 0.5*0.85*1.23*springCrossSectionalArea*(Math.pow(verticalVelocity, 2));
                    //System.out.println("Changed Force! " + verticalAirResistance);
                }
                else
                {
                    verticalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*(Math.pow(verticalVelocity, 2));
                    //System.out.println("Not Changed Force! " + verticalAirResistance);
                }
                verticalAcceleration = (verticalAirResistance/springMass) + -9.807;
                //System.out.println(verticalAcceleration);
                //System.out.println(horizontalAcceleration);
                //For Testing Purposes
                //System.out.println(horizontalDistance + " -" + verticalDistance + " ");
                //System.out.println(launchSpeed);
                //Finds Working Velocities
                if((horizontalDistance >= distanceToBucket - 0.05 && horizontalDistance <= distanceToBucket + 0.05) && (verticalDistance >= 0 - 0.05 && verticalDistance <= 0 + 0.05))
                {
                    ++solutions;
                    switch (solutions) {
                        case 1:
                            possibleVelocity1 = launchSpeed;
                            break;
                        case 2:
                            possibleVelocity2 = launchSpeed;
                            break;
                        case 7:
                            possibleVelocity3 = launchSpeed;
                            break;
                        case 9:
                            possibleVelocity4 = launchSpeed;
                            break;
                        case 10:
                            possibleVelocity5 = launchSpeed;
                            launchSpeedFound = true;
                            break;
                        default:
                            break;
                    }
                    time = 500;
                }
                //Limits Runs
//                if(verticalDistance < - 1 || horizontalDistance > distanceToBucket + 1)
//                {
//                    time = 500;
//                }
//                else if(launchSpeed >= 25)
//                {
//                    launchSpeedFound = true;
//                }
            }
            //System.out.println("\n\n\n\n");
        }
        //Give The Good Velocity To Start With
            //*Look at frictional energy (the frictional coifficent of smooth pine wood is 0.3 to 0.5)
        System.out.println("Working Starting Launch Speeds (m/s) " + possibleVelocity1 + ", " + possibleVelocity2 + ", " + possibleVelocity3 + ", " + possibleVelocity4 + ", " + possibleVelocity5);
        System.out.println("\nFinal Working Distance (m) Horizontal: " + horizontalDistance + " Vertical: " + verticalDistance);
        System.out.println("\n\n\n\n");
        //Extension of Spring
        System.out.println("Input The Spring Constant of Your Spring (N/Kg)");
        double springConstant = input.nextDouble();
        System.out.println("Input The Spring Efficiency of Your Spring (In Decimal (eg. 96% = 0.96))");
        double springEfficiency = input.nextDouble();
        System.out.println("Outputting Average Needed Spring Extension (m)");
        //Calculating The Extension of The Spring
        double averageWorkingLaunchSpeeds = (possibleVelocity1 + possibleVelocity2 + possibleVelocity3 + possibleVelocity4 + possibleVelocity5) / 5;
        double avgSpringExstension = Math.sqrt((springMass * (Math.pow(averageWorkingLaunchSpeeds, 2))) / (springConstant * springEfficiency * 2));
        System.out.println("The Average Needed Spring Extension (m) = " + avgSpringExstension);
    }
}
