import java.util.StringTokenizer;
/*
 * Name: Avinash
 * Date: April 29th, 2018
 * Description: This class is made for the sole purpose of taking in different types of time input and converting it so that different formats can be compared
 */
public class Time implements Comparable<Time>{
	int timeInMinutes = 0;
	int timeInSeconds = 0;
	
	/*
	 * Parameters: String time which is the time in string format
	 * Return Type: none
	 * Description: Creates a time variable with a string input
	 */
	public Time(String time)
	{
		StringTokenizer s = new StringTokenizer(time, ":");
		timeInMinutes = Integer.parseInt(s.nextToken());
		timeInSeconds = Integer.parseInt(s.nextToken());
	}
	/*
	 * Parameters: int seconds which is the time in seconds
	 * Return Type: none
	 * Description: creates time instance with time in seconds input
	 */
	public Time(int seconds)
	{
		timeInMinutes = seconds / 60;
		seconds -= timeInMinutes * 60;
		timeInSeconds = seconds;
	}
	/*
	 * Parameters: Time time, which is another time class
	 * Return Type: none
	 * Description: Creates a time variable from another time class
	 */
	public Time(Time time)
	{
		this.timeInMinutes = time.timeInMinutes;
		this.timeInSeconds = time.timeInSeconds;
	}
	public boolean equals(Object o)
	{
		Time secondTime = (Time)o;
		if(this.timeInMinutes == secondTime.timeInMinutes && this.timeInSeconds == secondTime.timeInSeconds)
			return true;
		else 
			return false;
	}
	public String toString()
	{
		StringBuilder time = new StringBuilder();
		if(timeInMinutes <= 9)
			time.append("0" + timeInMinutes);
		else
			time.append(timeInMinutes);
		if(timeInSeconds <= 9)
			time.append(":0" + timeInSeconds);
		else
			time.append(":" + timeInSeconds);
		
		return time.toString();
	}
	/*
	 * Parameters: Time time
	 * Return Type: void
	 * Description: subtracts one time class from another time class 
	 */
	public void subtract(Time time)
	{
		StringTokenizer s = new StringTokenizer(time.toString(), ":");
		int minutes = Integer.parseInt(s.nextToken().trim());
		int seconds = Integer.parseInt(s.nextToken().trim());
		timeInMinutes -= minutes;
		timeInSeconds  -=seconds;
		if(timeInSeconds / 60 >= 0)
		{
			int minutesToConvert = timeInSeconds / 60;
			timeInMinutes += minutesToConvert;
			timeInSeconds-= minutesToConvert*60;
		}
	}
	/*
	 * Parameters: Time time
	 * Return Type: void 
	 * Description: adds two time classes
	 */
	public void add(Time time)
	{
		StringTokenizer s = new StringTokenizer(time.toString(), ":");
		int minutes = Integer.parseInt(s.nextToken().trim());
		int seconds = Integer.parseInt(s.nextToken().trim());
		timeInMinutes += minutes;
		timeInSeconds  +=seconds;
		if(timeInSeconds / 60 >= 0)
		{
			int minutesToConvert = timeInSeconds / 60;
			timeInMinutes += minutesToConvert;
			timeInSeconds-= minutesToConvert*60;
		}
	}

	@Override
	public int compareTo(Time o) {
		// TODO Auto-generated method stub
		int totalSeconds = (timeInMinutes * 60) + timeInSeconds; 
		int totalSeconds2 = (o.timeInMinutes * 60) + o.timeInSeconds;
		return totalSeconds-totalSeconds2;
	}
}
