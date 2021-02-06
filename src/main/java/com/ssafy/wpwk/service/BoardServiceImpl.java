package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.Contents;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    /*
     * 게시글 생성
     */
    @Override
    public void create(Board board) throws Exception {
        boardMapper.create(board);
    }

    /*
     * 게시글 전체 조회
     */
    @Override
    public List<Board> findAll() throws Exception {
        return boardMapper.findAll();
    }

    /*
     * 페이지별 게시글 조회
     */
    @Override
    public List<Board> findAllByOffset(int offset) throws Exception {
        return boardMapper.findAllByOffset(offset);
    }

    /*
     * ID를 이용한 컨텐츠 상세조회
     */
    @Override
    public Board findById(Long id) throws Exception {
        return boardMapper.findById(id);
    }

    /*
     * 카테고리를 이용한 게시글 상세조회
     */
    @Override
    public Board findByCategory(String category) throws Exception {
        return boardMapper.findByCategory(category);
    }

    /*
     * 게시글 수정
     */
    @Override
    public void update(Board board) throws Exception {
        boardMapper.update(board);
    }

    /*
     * 게시글 삭제
     */
    @Override
    public void delete(Long id) throws Exception {
        boardMapper.delete(id);
    }

    /*
     * 게시글 좋아요 증가&감소
     */
    @Override
    public void updateLikes(Long id, int likes) throws Exception {
        boardMapper.updateLikes(id, likes);
        //Todo
        // id유저가 어떤 게시글에 대해서 좋아요를 했는지 기록이 필요함...
    }


}
