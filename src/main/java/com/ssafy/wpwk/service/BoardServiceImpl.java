package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
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
}
