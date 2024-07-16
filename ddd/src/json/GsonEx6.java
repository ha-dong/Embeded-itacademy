package json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonEx6 {
	public static void main(String[] args) throws Exception{
		String uriStr = "https://apis.data.go.kr/B490007/hrdkapi/NCS001?serviceKey=zdcLWo1S7NHYWqvcfEoiha9REwwXCbFApvxzGS7jvyb72D3x4UASxWn3uKv%2FcVa2aEkk5Yc8X2mwsBAlN2GvKQ%3D%3D&pageNo=1&numOfRows=10&USG_YN=N&NCS_DEGR=22&NCS_LCLAS_CD=04";
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
			
			System.out.println(jsonStr);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			Map<String, Object> jsonMap = gson.fromJson(jsonStr, Map.class);
			System.out.println(jsonMap);
			
			Map<String, Object> responceMap = (Map<String, Object>)jsonMap.get("response");
			System.out.println(responceMap);
			
			Map<String, Object> bodyMap = (Map<String, Object>)responceMap.get("body");
			System.out.println(bodyMap);
			
			Map<String, Object> itemsMap = (Map<String, Object>)bodyMap.get("items");
			System.out.println(itemsMap);
			
			List<Object>itemList = (List<Object>)itemsMap.get("item");
			System.out.println(itemList);
			
			Map<String, Object> itemMap = (Map<String, Object>)itemList.get(0);
			
			System.out.println(itemMap.get("NCS_LCLAS_CD"));
			System.out.println(itemMap.get("NCS_LCLAS_CDMM"));
			System.out.println(itemMap.get("NCS_DEGR"));
			System.out.println(itemMap.get("USG_YN"));
	}//main
}//class
