package com.ssafy.wpwk.utils;

import com.ssafy.wpwk.enums.MessageType;

public class MessageUtil {

    /**
     * @Param(from) : 송신자 닉네임
     * @Param(title) : 타겟 게시물(콘텐츠 및 게시글)
     * @Param(message) : 메시지 내용
     * @Param(type) : 메시지 타입
     * */
    public static String makeMessage(String from, String title, String message, MessageType type) {
        StringBuffer sb = new StringBuffer();

        switch (type){
            case WARN: // 경고처리
                System.out.println("WARN");
                sb.append(title);
                sb.append("에 부적절한 내용이 포함되어 있어 확인 바랍니다");
                break;
            case DELETE: // 삭제처리
                System.out.println("DELETE");
                sb.append(title);
                sb.append("에 부적절한 내용이 포함되어 있어 삭제되었습니다");
                break;
            case LIKE: // 좋아요 처리
                sb.append(from)
                  .append("님이 회원님의 ");
                   if(title.equals("")) {
                       sb.append("게시물");
                   } else {
                       sb.append(title);
                   }
                   sb.append("을 좋아합니다");
                break;
            case COMMENT: // 좋아요 처리
                sb.append(from)
                  .append("님이 회원님의 ");
                    if(title.equals("")) {
                        sb.append("게시물");
                    } else {
                        sb.append(title);
                    }
                   sb.append("에 댓글을 달았습니다");
                break;
            case FOLLOW: // 팔로우 처리
                sb.append(from)
                  .append("님이 회원님을 팔로우하기 시작했습니다");
                break;
        }

        return sb.toString();
    }
}
