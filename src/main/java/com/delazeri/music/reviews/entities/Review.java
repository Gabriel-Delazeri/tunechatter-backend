package com.delazeri.music.reviews.entities;

import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.users.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String comment;

    @OneToMany(mappedBy = "review")
    private List<Like> likes;

    @Column(name = "likes")
    private int likeCount;

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    private double rating;
}
