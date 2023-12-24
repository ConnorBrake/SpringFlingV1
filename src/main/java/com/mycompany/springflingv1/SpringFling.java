package com.mycompany.springflingv1;
import java.util.Scanner;
import java.lang.Math;

public class SpringFling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Starting Launch Speed [FWD]
        double launchSpeed = 3;
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
        System.out.println("Input The X Distance To The Bucket");
        double distanceToBucket = input.nextDouble();
        System.out.println("Input The Spring Constant of Your Spring");
        double springConstant = input.nextDouble();
        System.out.println("Input The Cross Sectional Area of Your Spring");
        double springCrossSectionalArea = input.nextDouble();
        System.out.println("Input The Mass of Your Spring");
        double springMass = input.nextDouble();
        
        //Calulates The Springs Starting Height
        double rampHeight = Math.sin(Math.toRadians(launchAngle))*0.612;
        double springInitialHeight = rampHeight + 1.081 + 0.087;
        System.out.println(springInitialHeight);
        
        //Calculates The Trajectory of The Spring on Both Axis
        System.out.println("TrajectoryBeingCalculated");
        while(launchSpeedFound == false)
        {
            launchSpeed += 0.01;
            //Resets The Components of The Launch Speed
            horizontalVelocity = Math.cos(Math.toRadians(launchAngle))*launchSpeed;
            horizontalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*Math.pow(horizontalVelocity, 2);
            horizontalDistance = 0;
            horizontalAcceleration = horizontalAirResistance/springMass;
            
            verticalVelocity = Math.sin(Math.toRadians(launchAngle))*launchSpeed;
            verticalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*Math.pow(verticalVelocity, 2);
            verticalDistance = springInitialHeight;
            verticalAcceleration = (verticalAirResistance/springMass) - 9.807;
            
            System.out.println(horizontalDistance + " " + verticalDistance);
            
            for(double time = 0; time < 3.50; time += 0.01)
            {
                horizontalDistance = horizontalDistance + (horizontalVelocity*(0.01)) + (0.5*horizontalAcceleration*(Math.pow((0.01), 2)));
                horizontalVelocity = horizontalVelocity + horizontalAcceleration * (0.01);
                horizontalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*Math.pow(horizontalVelocity, 2);
                horizontalAcceleration = horizontalAirResistance/springMass;
                
                verticalDistance = verticalDistance + (verticalVelocity*(0.01)) + (0.5*verticalAcceleration*(Math.pow((0.01), 2)));
                verticalVelocity = (verticalAcceleration*(0.01)) + verticalVelocity;
                //Changing Air Resistance Direction
                if(((verticalDistance + (verticalVelocity*(0.01)) + (0.5*verticalAcceleration*(Math.pow((0.01), 2)))) - verticalDistance) > verticalDistance)
                {
                    verticalAirResistance = 1*0.5*0.85*1.23*springCrossSectionalArea*Math.pow(verticalVelocity, 2);
                }
                else
                {
                    verticalAirResistance = -1*0.5*0.85*1.23*springCrossSectionalArea*Math.pow(verticalVelocity, 2);
                }
                verticalAcceleration = (verticalAirResistance/springMass) - 9.807;
                //For Testing Purposes
                System.out.println(horizontalDistance + " " + verticalDistance);
                //System.out.println(launchSpeed);
                
                if((horizontalDistance >= distanceToBucket - 0.05 && horizontalDistance <= distanceToBucket + 0.05) && (verticalDistance >= 0 - 0.05 && verticalDistance <= 0 + 0.05))
                {
                    launchSpeedFound = true;
                    time = 500;
                }
                //Limits Runs
//                if(verticalDistance < - 25 || horizontalDistance < -25)
//                {
//                    time = 500;
//                    System.out.println(pastTime);
//                }
            }
            System.out.println("\n\n\n\n");
        }
        //Give The Good Velocity To Start With
        System.out.println("Working Starting Launch Speed " + launchSpeed);
        System.out.println("\n" + horizontalDistance + " " + verticalDistance);
    }
    
}
