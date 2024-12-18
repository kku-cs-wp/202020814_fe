package kr.ac.kku.cs.wp.wsd.model;


import jakarta.persistence.*;

@Entity
@Table(name = "boards") // 매핑될 테이블 이름
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;


    public Board() {}

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter와 Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Board{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
