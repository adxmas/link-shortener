package com.example.linkshortener.controller;


import com.example.linkshortener.dao.UrlRepositary;
import com.example.linkshortener.model.Url;
import com.example.linkshortener.service.UrlConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    UrlRepositary urlRepositary;

    @Autowired
    UrlConversion urlConversion;

    // starts webpage
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(Model model){
        Url startUrl = urlRepositary.findById(0).orElse(null);
        model.addAttribute("Url", startUrl);
        return "start";
    }

    // adds url to database and encodes it
    @RequestMapping(value = "/saveurl")
    public String saveUrl(Url url, Model model){
        urlRepositary.save(url);
        //model.addAttribute("allLinks", urlRepositary.findAll());
        //return "all-links";
        model.addAttribute("encodedUrl", "shortcut.co/"+urlConversion.encode(url.getuId()));
        return "encode";
    }

    // returns encoded url to its previous form
    @RequestMapping(value = "/decode")
    //public String decodeUrl(Url url, Model model){
    public String decodeUrl(Url url, Model model){
        String[] linkParts = url.getLongUrl().split("/", 2);
        String url24 = urlRepositary.findById(urlConversion.decode(linkParts[1])).orElse(null).getLongUrl();
        model.addAttribute("decodedUrl", url24);
        return "decode";
    }


    //random operations
    /*
    @GetMapping("/encode/{uId}")
    public String newLink(@PathVariable("uId") int uId, Model model) {
        String encodedUrl = urlConversion.encode(uId);
        model.addAttribute("encodedUrl", encodedUrl);
        return "encode";
    }


    @PostMapping("/add")
    public String postUrl(Model model){
        model.addAttribute("allLinks", urlRepositary.findAll());
        return "all-links";
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Url> getAll(){
        return urlRepositary.findAll();
    }

    //--
    @GetMapping("/links")
    public String returnAll(Model model){
        System.out.println("returnAll worked");
        model.addAttribute("allLinks", urlRepositary.findAll());
        return "all-links";
    }


    /*@PostMapping("/addurl")
    public Url addUrl(Url url) {
        urlRepositary.save(url);
        return url;
    }

    /*@GetMapping("/encode/{uId}")
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

    @PutMapping("/update")
    public Url updateUrl(@RequestBody Url url){
        Url url2 = urlRepositary.findById(url.getuId()).orElse(null);
        url2.setuId(url.getuId());
        url2.setLongUrl(url.getLongUrl());
        return urlRepositary.save(url2);
    }*/


}




