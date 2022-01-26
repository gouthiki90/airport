package wheather;

import java.util.List;
import java.util.Scanner;

import wheather.WheatherDto.Response.Body.Items.Item;

public class WheatherApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("------ ✧ 날짜를 입력해주세요 ✧ ------");
        System.out.println("ex) 20220126");
        String baseTime = sc.nextLine();

        List<Item> wheatherList = DownloadWheather.getWheatherList(baseTime);
        for (int i = 0; i < wheatherList.size(); i++) {
            System.out.println("------ ✧ 거제시 능포동의 날씨 정보 ✧ ------");
            System.out.println("예측 날짜 : " + wheatherList.get(i).getFcstDate());
            System.out.println("예측 시간 : " + wheatherList.get(i).getFcstTime());
            System.out.println("온도/강수 확률 : " + wheatherList.get(i).getFcstValue());
        }

    }
}
