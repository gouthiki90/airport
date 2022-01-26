
package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import practice.AirportDto.Response.Body.Items.Item;

// 공항목록 조회, 공항 이름 입력해서 공항 목록 조회
public class DownloadAirport {
    static Map<String, String> getAirportList() { // 공항목록 불러오는 메소드, airportMap을 리턴해줌.
        Map<String, String> airportMap = new HashMap<>();
        try {
            URL url = new URL(
                    "http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getArprtList?serviceKey=wJmmW29e3AEUjwLioQR22CpmqS645ep4S8TSlqtSbEsxvnkZFoNe7YG1weEWQHYZ229eNLidnI2Yt5EZ3Stv7g%3D%3D&_type=json");
            // Input할 경로

            // Stream 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 버퍼 연결, Input이기 때문에 reader, 자동 캐스팅(문자열 형변환)
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));

            String reponseJson = br.readLine(); // 불러올 데이터 읽기
            Gson gson = new Gson(); // gson으로 자바언어 Json으로 변환
            AirportDto dto = gson.fromJson(reponseJson, AirportDto.class);

            List<Item> result = dto.getResponse().getBody().getItems().getItem();

            for (int i = 0; i < result.size(); i++) {
                airportMap.put(result.get(i).getAirportNm(), result.get(i).getAirportId());
                // Map으로 정렬할 key(공항 이름)와 value(공항 아이디)를 result(dto의 List) 크기만큼 반복
                // 즉 공항 이름을 입력하면 공항 아이디를 통해 공항 정보 불러옴.
            }

        } catch (Exception e) {
            System.out.println("공항목록 조회 중 오류가 발생했습니다.");
        }
        return airportMap;
    }

}
