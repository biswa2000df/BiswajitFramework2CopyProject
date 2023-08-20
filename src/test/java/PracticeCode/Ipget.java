package PracticeCode;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.TimeZone;

public class Ipget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String IP = null;
		String HostName = "";
		String name = null;
		String IP_HOST_ZONE = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			IP = address.getHostAddress(); // 172.18.32.1
			HostName = address.getHostName(); // BISWAJIT2000
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		// TIME ZONE
		try {
			Calendar cal = Calendar.getInstance();
			long milliDiff = cal.get(Calendar.ZONE_OFFSET); // offset=19800000
			// Got local offset, now loop through available timezone id(s).
			String[] zoneName = TimeZone.getAvailableIDs(); // get all the zone name using the array

			for (String id : zoneName) {
				TimeZone tz = TimeZone.getTimeZone(id); // Example =
														// sun.util.calendar.ZoneInfo[id="Africa/Addis_Ababa",offset=10800000,dstSavings=0,useDaylight=false,transitions=7,lastRule=null]
				if (tz.getRawOffset() == milliDiff) { // check the offset is matched
					// Found a match.
					name = id;
					break;
				}
			}
			IP_HOST_ZONE = IP + "," + HostName + "," + name;
			/// System.out.println("IP="+ip+"--------HOSTNAME= "+hostname+"--------ZoneName=
			/// "+name);

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(IP_HOST_ZONE);

	}

}
