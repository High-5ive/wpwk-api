package com.ssafy.wpwk.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.UserService;
import com.ssafy.wpwk.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@CrossOrigin({"*"})
public class NaverLoginController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    private final static String INIT_PASSWORD = "wpwk";

    @GetMapping("/login/process/naver/{token}")
    public ResponseEntity<?> test(
        @PathVariable("token") String token
    ) throws Exception {

        JsonParser parser = new JsonParser();

        String tmp = getUserInfo(token);
        JsonElement userInfoElement = parser.parse(tmp);

        // 네이버 토큰 값이 유효하지 않은 경우
        if(!userInfoElement.getAsJsonObject().get("message").getAsString().equals("success")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // JSON 파싱해서 id, nickname, email 가져오기
        // Long id = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsLong();
        String nickName = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
        String email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();

        System.out.println(email);
        System.out.println(nickName);

        // 현재 DB에 이메일이 있는 지 확인
        User user = userService.findUserByEmail(email);

        if(user == null) {
            user = User.builder()
                    .email(email)
                    .password(INIT_PASSWORD)
                    .nickname(nickName)
                    .status(1)
                    .provider("naver")
                    .build();

            userService.insertUser(user, false);
        }

        System.out.println(user.getId());

        String accessToken = jwtUtil.createToken(user.getId(), user.getEmail(), user.getNickname());

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

    private String getUserInfo(String access_token) {
        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                System.out.println("error");
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            return res.toString();
        } catch (Exception e) {
            System.err.println(e);
            return "Err";
        }
    }
}