package com.delazeri.music.reviews.entities;

import com.delazeri.music.users.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "likes")
@AllArgsConstructor
@NoArgsConstructor
public class Like implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like(User user, Review review) {
        this.user = user;
        this.review = review;
    }
}
