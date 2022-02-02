package weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import weather.weatherDto.Response.Body.Items.Item;

public class Downloadweather {
    public static List<Item> getweatherList(String baseDate) {
        Map<String, String> weatherMap = new HashMap<>();
        try {
            // StringBuffer sb = new StringBuffer();
            // sb.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?");
            // sb.append(
            // "serviceKey=eCazEq%2FCP4iBdrQDcQQrLr3rgUrV%2ByZOFRdwxGTrcfeZbe3FqDvkQ6iMAWgeXRDOa%2FABNLYI3Dhz7hzxyUuI4A%3D%3D&");
            // sb.append("pageNo=1&");
            // sb.append("numOfRows=1000&");
            // sb.append("dataType=JSON&");
            // sb.append(baseDate);
            // sb.append("base_time=0600&");
            // sb.append("nx=92&");
            // sb.append("ny=69");
            // sb.append("category")
            URL url = new URL(
                    "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=eCazEq%2FCP4iBdrQDcQQrLr3rgUrV%2ByZOFRdwxGTrcfeZbe3FqDvkQ6iMAWgeXRDOa%2FABNLYI3Dhz7hzxyUuI4A%3D%3D&pageNo=1&numOfRows=1000&dataType=JSON&"
                            + baseDate
                            + "base_time=0600&nx=92&ny=69&category=RN1&resultMsg=NORMALSERVICE&resultCode=00");

            // 스트림 연결
            HttpURLConnection stream = (HttpURLConnection) url.openConnection();
            // BR, Input 통신 연결
            BufferedReader br = new BufferedReader(new InputStreamReader(stream.getInputStream(), "utf-8"));
            // JSON을 읽는다.
            String responseJson = br.readLine();
            // 자바를 JSON으로 파싱한다.
            Gson gson = new Gson();
            weatherDto dto = gson.fromJson(responseJson, weatherDto.class);
            System.out.println(responseJson);

            // 리스트 정렬 후, 해쉬맵으로 키값 정하기
            List<Item> result = dto.getResponse().getBody().getItems().getItem();
            for (int i = 0; i < result.size(); i++) {
                // weatherMap.put(result.get(i).getBaseDate(), result.get(i).getCategory());
                weatherMap.put(result.get(i).getBaseDate(), result.get(i).getObsrValue());
            }
            // result를 i만큼 정렬하고, put에 정렬한다.

            return result;
            // HashMap에 result 리턴

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        // 매개변수만 출력하면 되어서 리턴값 null
    }

}
