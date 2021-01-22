package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    /** 사용자 로그인 */
    User login(@Param("email") String email, @Param("password") String password);

    /** 새로 가입한 유저 저장  */
    void insertUser(User user);

    /** 사용자 id로 정보 조회  */
    User findUserById(Long userId);

    /** 사용자 이메일로 정보 조회 */
    User findUserByEmail(String email);

    /** 모든 사용자 정보 조회 */
    List<User> findAll();

    /** 사용자의 정보 업데이트 */
    void updateUser(User user);

    /** 탈퇴를 요청한 유저의 상태값을 변경 */
    void deactivateUser(Long userId);

    /** 사용자 비밀번호 변경 */
    void changePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    /** 사용자 이메일 인증 */
    void verification(@Param("id") Long id, @Param("key") String key);
}
