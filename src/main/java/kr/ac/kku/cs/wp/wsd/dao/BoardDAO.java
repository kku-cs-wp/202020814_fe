package kr.ac.kku.cs.wp.wsd.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kku.cs.wp.wsd.model.Board;

import java.util.List;

@Repository
public class BoardDAO {
    private final SessionFactory sessionFactory;

    public BoardDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 데이터베이스 연결 테스트 메서드
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public void testConnection() {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    // 게시글 저장
    @Transactional // 쓰기 트랜잭션
    public void saveBoard(Board board) {
        System.out.println("Saving board: " + board.getTitle() + ", " + board.getContent());
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(board);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 모든 게시글 조회
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public List<Board> getAllBoards() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Board", Board.class).list();
        }
    }

    // ID로 게시글 조회
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public Board getBoardById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Board.class, id);
        }
    }

    // 게시글 업데이트
    @Transactional // 쓰기 트랜잭션
    public void updateBoard(Board board) {
        System.out.println("Updating board: " + board.getTitle() + ", " + board.getContent());
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(board); // 데이터 업데이트
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    // 게시글 삭제
    @Transactional // 쓰기 트랜잭션
    public void deleteBoardById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Board board = session.get(Board.class, id); // ID로 게시글 조회
            if (board != null) {
                session.remove(board); // 게시글 삭제
            } else {
                throw new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("게시글 삭제 중 오류 발생: " + id);
        }
    }
}

