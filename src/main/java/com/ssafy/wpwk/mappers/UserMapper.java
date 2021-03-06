package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.AbilityRequestDTO;
import com.ssafy.wpwk.model.ContentsEndRequestDTO;
import com.ssafy.wpwk.model.AbilityResponseDTO;
import com.ssafy.wpwk.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 사용자 로그인
     */
    User login(@Param("email") String email, @Param("password") String password);

    /**
     * 새로 가입한 유저 저장
     */
    void insertUser(User user);

    /**
     * 사용자 id로 정보 조회
     */
    User findUserById(Long userId);

    /**
     * 사용자 이메일로 정보 조회
     */
    User findUserByEmail(String email);

    /**
     * 모든 사용자 정보 조회
     */
    List<User> findAll();

    /**
     * 활성화된 사용자 정보 조회(관리자 제외)
     */
    List<User> findAllByNormalUser();

    /**
     * 탈퇴를 요청한 유저의 상태값을 변경
     */
    void deactivateUser(Long userId);

    /**
     * 사용자 비밀번호 변경
     */
    void changePassword(User user);

    /**
     * 사용자 이메일 인증
     */
    void verification(@Param("id") Long id, @Param("key") String key);

    /**
     * 사용자의 역량 정보 업데이트
     */
    void updateUserAbilities(@Param("id") Long id, @Param("userAbility") AbilityRequestDTO abilityDTO);

    /**
     * ID를 이용한 사용자 역량 정보 조회
     */
    AbilityResponseDTO findUserAbilitiesById(Long id);

    /**
     * 사용자 인증키 언데이트
     */
    void updateKey(User user);

    /**
     * 사용자 팔로우 요청
     */
    void updateFollowed(@Param("id") Long id);

    /**
     * 사용자 팔로잉 요청
     */
    void updateFollowing(@Param("id") Long id);

    /**
     * 사용자 팔로잉 팔로우 정보 생성
     */
    void insertFollow(@Param("toUserId") Long toUserId, @Param("fromUserId") Long fromUserId);

    /**
     * 사용자 팔로워 or 팔로잉 리스트 정보 조회
     */
    List<User> findFollowListById(@Param("userId") Long id, @Param("option") String option);

    /**
     * 사용자 언팔로우 요청
     */
    void unFollowed(@Param("id") Long toUserId);

    /**
     * 사용자 언팔로잉 요청
     */
    void unFollowing(@Param("id") Long fromUserId);

    /**
     * 사용자 팔로잉 정보 삭제
     */
    void deleteFollow(@Param("toUserId") Long toUserId,@Param("fromUserId") Long fromUserId);

    /**
     * 사용자 팔로워 or 팔로잉유저 조회
     */
    User findFollowById(@Param("toUserId") Long toUserId,@Param("fromUserId") Long fromUserId);
}
