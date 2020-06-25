package com.example.linkshortener.controller;


import com.example.linkshortener.dao.UrlRepositary;
import com.example.linkshortener.model.Url;
import com.example.linkshortener.service.UrlConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    UrlRepositary urlRepositary;

    @Autowired
    UrlConversion urlConversion;
    
    @GetMapping("all")
    public List<Url> getAll(){
        return urlRepositary.findAll();
    }

    @PostMapping("/addurl")
    public Url addUrl(Url url) {
        urlRepositary.save(url);
        return url;
    }

    @GetMapping("/encode/{uId}")
    public String newLink(@PathVariable("uId") int uId) {
        String encodedUrl = urlConversion.encode(uId);
        return encodedUrl;
    }

    @GetMapping("decode/{encoded}")
    public Optional<Url> oldLink(@PathVariable("encoded") String encoded) {
        int pkey = urlConversion.decode(encoded);
        return urlRepositary.findById(pkey);
    }

    @DeleteMapping("delete/{uId}")
    public List<Url> delete(@PathVariable("uId") int uId) {
        urlRepositary.deleteById(uId);
        return urlRepositary.findAll();
    }



}




