package com.ssafy.wpwk.controller;

import com.google.gson.Gson;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class KakaoLoginController {

    @Autowired
    private UserService userService;


    private final static String INIT_PASSWORD = "wpwk";

    @GetMapping(value = "/kakao/{code}")
    public ResponseEntity<?> login(@PathVariable("code") String authorize_code) {
        String accsesToken = "",email="",nickname="";

        try {
            RestTemplate rt = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
            param.add("grant_type", "authorization_code");
            param.add("client_id", "f871952e3d6294ff03c50c4e129fa152");
            param.add("redirect_uri", "http://localhost:8080/kakao");
            param.add("code", authorize_code);

            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                    new HttpEntity<>(param, httpHeaders);

            ResponseEntity<String> response = rt.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoTokenRequest,
                    String.class
            );

            String data = response.getBody();
            Gson gsonObj = new Gson();
            Map map = gsonObj.fromJson(data, Map.class);
            accsesToken = map.get("access_token").toString();
            System.out.println(response.getBody());

            //  user 정보 가져오기
            RestTemplate rt2 = new RestTemplate();
            httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + accsesToken);
            httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                    new HttpEntity<>(httpHeaders);

            ResponseEntity<String> profileResponse = rt2.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.POST,
                    kakaoProfileRequest,
                    String.class
            );
            System.out.println(profileResponse.getBody());
            data = response.getBody();
            gsonObj = new Gson();
            map = gsonObj.fromJson(data, Map.class);
             email = map.get("email").toString();
             nickname = map.get("nickname").toString();
            User user = userService.findUserByEmail(email);

            if (user == null) {
                user = User.builder()
                        .email(email)
                        .password(INIT_PASSWORD)
                        .nickname(nickname)
                        .status(1)
                        .provider("naver")
                        .build();

                userService.insertUser(user);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, String> res = new HashMap<>();
        res.put("accessToken", accsesToken);
        res.put("email", email);
        res.put("nickname", nickname);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
