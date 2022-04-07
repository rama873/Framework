package WeatherShopperTestScenarios;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

public class json_to_map {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "{\r\n"
				+ "    \"tc_description\": \"Tc_decription\",\r\n"
				+ "    \"Assignment\": {\r\n"
				+ "        \"insco\": \"Seller\",\r\n"
				+ "        \"insure_name\": \"Member Automation\",\r\n"
				+ "        \"adjuster_key\": \"3687177825\",\r\n"
				+ "        \"vin_number\": \"\",\r\n"
				+ "        \"lot_nums\": 0,\r\n"
				+ "        \"officecode\": \"Seller\",\r\n"
				+ "        \"vehicle_type\": \"V\",\r\n"
				+ "        \"yard_number\": 202,\r\n"
				+ "        \"loss_type\": \"C\",\r\n"
				+ "        \"vrn_number\": \"\"\r\n"
				+ "    },\r\n"
				+ "    \"Runner\": {\r\n"
				+ "        \"execute\": 3\r\n"
				+ "    }\r\n"
				+ "}";
		JSONObject js = new JSONObject(s.toString());
		HashMap<String,String> map = new HashMap<String,String>();
		
		Iterator it = js.keys();
		
		while(it.hasNext()) {
			String key = it.next().toString();
			map.put(key,js.get(key).toString());
		}
 System.out.println(map.toString());
	}

}
