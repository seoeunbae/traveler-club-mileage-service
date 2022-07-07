# traveler-club-mileage-service
Mileage service for Triple Traveler Club

# Stack
**Project:**  SpringBoot2.x, Java 11


**ORM:**  Querydsl, JPA


**Database:**  H2


**TestCode:**  Mockito, Junit 4


**Logging:**  Log4j

# ERD
<img width="739" alt="Screen Shot 2022-07-07 at 4 54 46 PM" src="https://user-images.githubusercontent.com/71380240/177721761-f3d3188e-e052-4fca-87c9-202ecd762c9e.png">

## Install H2 database before Run application

1. install H2 database <br/>
   http://www.h2database.com/html/main.html
2. run h2.sh
3. create a 'triple' database in h2 console
<img width="450" alt="Screen Shot 2022-07-07 at 3 54 57 PM" src="https://user-images.githubusercontent.com/71380240/177711401-1b5cba52-ab05-4e7d-8613-918d0156b8d6.png">
4. click 'Connect'

# How to Run
```
git clone https://github.com/seoeunbae/traveler-club-mileage-service.git
cd traveler-club-mileage-service
./gradlew clean build -x test
java -jar /build/libs/traveler-club-mileage-0.0.1-SNAPSHOT.jar
```
# API Specifications

**1️⃣ 리뷰 생성/수정/삭제**

**Request**

  ```
  POST localhost:8081/events

  {
    "type": "REVIEW",
    "action": "ADD", /* "MOD", "DELETE" */
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": " 좋아요!",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
  }
  ```

**content, photos가 널인 경우**

해당 필드를 제거하고 요청을 보냅니다.

  ```
  ex. content가 없는 경우
  {
    "type": "REVIEW",
    "action": "ADD", /* "MOD", "DELETE" */
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
  }
  ```
**Response**

  ```
  {
      "code": 200,
      "result": {
          "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
          "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
          "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f",
          "mileage": 3
      }
  }
  ```

**주의사항**

user_id, place_id, photo_id가 존재하지 않는 id인 경우, **NotExistException**이 발생합니다.

해당 USER, PLACE, PHOTO 클래스에 더미데이터를 넣고 API Request를 send해주세요.

**2️⃣ 해당 유저의 포인트 조회**

**Request**

  ```
  GET localhost:8081/{user_id(UUID)}/mileage
  ```

**Response**

  ```
  {
      "code": 200,
      "result": {
          "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
          "mileage": 3
      }
  }
  ```
