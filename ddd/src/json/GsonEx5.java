package json;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

public class GsonEx5 {
	public static void main(String[] args) throws Exception{
			String uriStr = "https://jsonplaceholder.typicode.com/posts/1";
			URL url = new URI(uriStr).toURL();
			URLConnection conn = url.openConnection();
			try {
				conn = url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(conn!=null) {
				System.out.println("연결됨!");
			}
			
			BufferedReader br
				= new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String jsonStr = "";
					String line = "";
				
				while ((line = br.readLine())!=null) {
					jsonStr += line;
				}
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				List<LinkedTreeMap>HumanList = gson.fromJson(jsonStr, List.class);
				System.out.println(HumanList);
				
				for(int i = 0; i<HumanList.size(); i++) {
					LinkedTreeMap<Integer, Human> human1 = HumanList.get(i);
						for(Map.Entry<Integer, Human> entry:human1.entrySet()) {
							Integer key = entry.getKey();
							Object value = entry.getValue();
							System.out.println(key);
							System.out.println(value);
						}
				}
	}//main
}//class