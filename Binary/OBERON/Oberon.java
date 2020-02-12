
package Oberon;
import robocode.*;
import java.util.Random;
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
double move;
double x;
double y;

// Declairs variables to keep track of enemy energy so that we can check if they have fired
double enemyE = 100;
double enemyCE = 0;

////enemy info
double enemyD;

//main
public void run()
{
// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

//gets max height and with so that Oberon won't leave valid playspace and get shot at by century bot
double maxPlayHeight = getBattleFieldHeight();
double maxPlayWidth = getBattleFieldWidth();

//gets century area
double century = getSentryBorderSize();

//main loop
while(true)
{

//Stuff to make the radar do stuff and things
setAdjustRadarForGunTurn(true);
setTurnRadarRight(36000);

//generates move
move = rand.nextInt(80)+20;

//gets coardinates
x = getX();
y = getY();


//checks if movement is within bounds
if((y + move) > (maxPlayHeight - (century*2)) || (x + move) > (maxPlayWidth - (century*2)))
{

//turns right 60 degrees
turnRight(60);

} //end if
else if((y - move) < ((century*2)) || (x - move) < ((century*2)))
{

//turns right 60 degrees
turnRight(60);

} // end if

//moves Oberon
ahead(move);

//makes sure everything excicutes
execute();
} //end while

} //end main


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

		setTurnRadarRight(360 * scanDirection);

	} //end else
*/

//get enemy energy
enemyCE = getEnergy();

if(enemyCE != enemyE)
{

enemyE = enemyCE;

} //end if

//gets enemy info
//enemyD = e.getDistance;

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

} //end oberon
