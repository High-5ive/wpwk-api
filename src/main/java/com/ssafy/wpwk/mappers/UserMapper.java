package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Ability;
import com.ssafy.wpwk.model.ContentsAbilityDTO;
import com.ssafy.wpwk.model.User;

import com.ssafy.wpwk.model.UserAbilityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    /*사용자 로그인 */
    User login(@Param("email") String email, @Param("password") String password);

    /*새로 가입한 유저 저장  */
    void insertUser(User user);

    /*유저 Id로 User정보 조회  */
    User findUserById(Long userId);

    /*유저의 Email로 User정보 조회  */
    User findUserByEmail(String email);

    /*모든 유저 정보 조회 */
    List<User> findAll();

    /*유저의 정보 업데이트 */
    void updateUserAbilities(@Param("id") Long id, @Param("contentsAbilityDTO") ContentsAbilityDTO contentsAbilityDTO);

    /*탈퇴를 요청한 유저의 상태값을 변경 */
    void deactivateUser(Long userId);

    /* 유저의 비밀번호 변경 */
    void changePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    UserAbilityDTO userAbilities(Long id);

    ContentsAbilityDTO findUserAbilitiesById(Long id);
}
