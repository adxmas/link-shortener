package com.example.linkshortener.dao;

import com.example.linkshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UrlRepositary extends JpaRepository<Url, Integer> {

    @Query("update Url url set url.longUrl = :longUrl WHERE url.uId = :pkey")
    void setNewLink(@Param("pkey") Long id, @Param("longUrl") String longUrl);
}
