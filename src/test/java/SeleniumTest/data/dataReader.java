package SeleniumTest.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//reading Json to String
		 String jsoncontent = FileUtils.readFileToString(new File("C:\\Users\\Rajath\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\SeleniumTest\\data\\PurchaseOrder.json"),
				 StandardCharsets.UTF_8);
		 
		 //Converting String to hashmap
		 ObjectMapper mapper = new ObjectMapper();
		 List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){
		 });
		 
		 return data;
		 }
		 
	
	}
