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

	//declairs rand for use in locate
	Random rand = new Random();
	double enemyV, enemyB;
	byte scanDirection = 1;

	// Oberon info
	int TurnCounter = 0;
	double move;
	double x;
	double y;
	double h;
	double Px;
	double Py;
	double centerX;
	double centerY;
	double BorderX;
	double BorderY;
	double Sentry;

	// Declairs variables to keep track of enemy energy so that we can check if they have fired
	double enemyE = 100;
	double enemyCE = 0;

	////enemy info
	double enemyD;


	//main
	public void run()
	{
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);

		centerX = getBattleFieldWidth() / 2;
		centerY = getBattleFieldHeight() / 2;
		BorderX = getBattleFieldWidth();
		BorderY = getBattleFieldHeight();
		Sentry = getSentryBorderSize();


		//main loop
		while(true)
		{

			//gets coardinates and defines future coordinates
			x = getX();
			y = getY();
			h = getHeading();


			//makes sure everything excicutes
			execute();

		}//End While
	}



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
	public void goTo (double x, double y){
		x = x - getX();
		y = y - getY();

		double goAngle = Utils.normalRelativeAngleDegrees(Math.atan2(x, y) - getHeadingRadians());

		setTurnRightRadians(Math.atan(Math.tan(goAngle)));
		setAhead(Math.cos(goAngle) * Math.hypot(x, y));
	}//End goTo

	//Moves based on testing it's position in the future: currently deppreciated due to currently unresolved issues
	public void MoveRobot()
	{

		//generates and distance from centre
		double fromcentre = (Math.sqrt(Math.pow(2, (getX() - centerX)) + Math.pow(2, (getY() - centerY))));
		int pass = (int)fromcentre;
		move = rand.nextInt(400)+100;


		//Generates projected coordinates
		Px = x+(Math.cos(h) + move);
		Py = y+(Math.sin(h) + move);


		//checks if movement is within bounds
		if ( (Sentry < Px && Px < (BorderX - Sentry)) && (Sentry < Py && Py < (BorderY - Sentry)) )
		{
			setAhead(move);
			} else {
			turnRight(45);
		}//End Else

	}//End moverobot

}//End Oberon
