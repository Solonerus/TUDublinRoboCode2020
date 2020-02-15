package Oberon;
import robocode.*;
import robocode.util.Utils;
import java.util.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;


/**
 * Oberon - a robot by (Alan Byju, Paul Geoghegan and Saul Burgess)
 */


public class Oberon extends AdvancedRobot
{
	Random rand = new Random();
	double a, b, xRef, yRef, h, rangeX, rangeY;
	byte scanDirection = -1;

	double[] goToX = new double[5];
	double[] goToY = new double[5];

	//main
	public void run(){
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		rangeX = getBattleFieldWidth() - (getSentryBorderSize() * 2);
		rangeY = getBattleFieldHeight() - (getSentryBorderSize() * 2);

		rangeX = rangeX / 5;
		rangeY = rangeY / 5;

		for(int i = 0 ; i < 5 ; i++)
		{

			goToX[i] = rangeX / 2 + (rangeX * i) + getSentryBorderSize();
			goToY[i] = rangeY / 2 + (rangeY * i) + getSentryBorderSize();

		} //end for

		//main loop
		while(true){

			movementStrategyController();

			//makes sure everything excicutes
			execute();

		}//End While
	}//End Run


	//This function is the key component of Oberon, the basis of it's decision-tree. This function decides the best strategy for each scenario and executes them
	void movementStrategyController(){

		//generates random numbers to choose what point Oberon will go to
		a = rand.nextInt(5);
		b = rand.nextInt(5);

		xRef = a;
		yRef = b;

		//passes x and y coardinates for Oberon to go to
		goTo();

	}//End movementStrategyController

	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent e)
	{

		/*
		if(isSentryRobot == false)
		{
		*/

		scanDirection *= -1; // changes value from 1 to -1
		setTurnRadarRight(360 * scanDirection);

		//turns gun towards enemy
		setTurnGunRight(getRadarHeading() - getGunHeading() + e.getBearing());

		//attacks enemy
		attack();

		/*
		} //end sentry check
		else
		{

		} //end else
		*/

	} // end scanned robot event/method

	//shoots at the enemy
	public void attack()
	{


		//checks if the gun is ready to fire
		if(getGunHeat() == 0)
		{

			fire(1);

		} //end if

	} //end attack

	//when Oberon is hit by a bullit this method will run
	public void onHitByBullet(HitByBulletEvent e)
	{

		//TEMP

	} //end when hit by bullit event/method

	//when robot hits wall
	public void onHitWall(HitWallEvent e)
	{

		//TEMP

	} //end on hit wall event/method

	//A function that controls the goTo strategy of Oberon using passed cordinates from the movement controller in main
	public void goTo (){
		double x = goToX[3];
		double y = goToY[3];

		double goAngle = Utils.normalRelativeAngleDegrees(Math.atan2(x, y) - getHeadingRadians());

		setTurnRightRadians(Math.atan(Math.tan(goAngle)));
		setAhead(Math.cos(goAngle) * Math.hypot(x, y));
	}//End goTo

}//End Oberon
