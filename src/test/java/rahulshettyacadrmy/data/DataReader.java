package rahulshettyacadrmy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
  
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read Json to String
		File jsonFile = new File(filePath);
		String jsonContent = FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8);
	    //Convert String to HashMap (Jackson Databind Deoendency) (conver String sang Java object)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		//lúc này data là một List gồm map1 và map2 được define tại json file
		return data;		
		
	}
	
}
