package ua.ostrometskiy.diplomaBootJPA.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genres {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Books> bookList;

    public Genres() {
    }

    public Genres(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Books> getBookList() {
        return bookList;
    }

    public void setBookList(List<Books> booksList) {
        this.bookList = booksList;
    }

    @Override
    public String toString() {
        return "Genres{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
