package me.pimpao.springboot2.controller;

import me.pimpao.springboot2.domain.Anime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "anime")
public class AnimeController {

    @GetMapping(path = "list")
    public List<Anime> list() {
        return List.of(new Anime("DBZ"), new Anime("Samurai X"));
    }
}
