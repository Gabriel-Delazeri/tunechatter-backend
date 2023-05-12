package com.delazeri.music.tracks.repositories;

import com.delazeri.music.tracks.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, UUID> {
}
