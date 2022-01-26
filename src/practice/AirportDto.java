package practice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor // 리플렉션, 풀생성자 생성
@Data // 리플렉션, Setter Getter
public class AirportDto {
    private Response response;

    @AllArgsConstructor
    @Data
    class Response {
        private Header header; // 컴포지션
        private Body body;

        @AllArgsConstructor
        @Data
        class Header {
            private String resultCode;
            private String resultMsg;
        }

        @AllArgsConstructor
        @Data
        class Body {
            private Items items;
            private int numOfRows;
            private int pageNo;
            private int totalCount;

            @AllArgsConstructor
            @Data
            class Items {
                private List<Item> item;

                @AllArgsConstructor
                @Data
                class Item {
                    private String airportId;
                    private String airportNm;
                }
            }

        }
    }
}
