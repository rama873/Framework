package WeatherShopperTestScenarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.juneau.html.HtmlSerializer;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.marshall.Xml;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.apache.juneau.xml.XmlSerializer;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Helpers.payloadPOJO;

public class PojoTest {

	public static void main(String[] args) throws SerializeException, ParseException, IOException {
		// TODO Auto-generated method stub
		
		String[]  stringarray = {"Ongole enterpries","Hp goods","kalavathi services"};
		
		//Normal way to create a Seriazible Java object to json.
        payloadPOJO payload = new payloadPOJO("Dell", 550, "black", stringarray);
        
//		payload.setName("Dell");
//		payload.setCost(450);
//		payload.setColor("black");
//		payload.setVendors(stringarray);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String body =objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);

		System.out.println(payload.toString());
		JSONObject jsobj = new JSONObject(payload.toString());
		System.out.println(body.toString());
		
		
		//Using Apache Juneau Library
		JsonSerializer json = JsonSerializer.DEFAULT_READABLE;
		XmlSerializer xml = XmlSerializer.DEFAULT_NS_SQ_READABLE;
        HtmlSerializer html = HtmlSerializer.DEFAULT_SQ_READABLE;
		
        String decr = json.serialize(payload);
		System.out.println(json.serialize(payload));
		System.out.println(xml.serialize(payload));
		System.out.println(html.serialize(payload));
		
//		JsonParser jsonparser = JsonParser.DEFAULT;
//		payloadPOJO getpayload = jsonparser.parse(decr, payloadPOJO.class);
//		System.out.println(getpayload.getColor());
	}

}
