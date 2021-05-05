package me.pimpao.springboot2.service;

import lombok.RequiredArgsConstructor;
import me.pimpao.springboot2.domain.Anime;
import me.pimpao.springboot2.mapper.AnimeMapper;
import me.pimpao.springboot2.repository.AnimeRepository;
import me.pimpao.springboot2.request.AnimePostRequestBody;
import me.pimpao.springboot2.request.AnimePutResquetBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    private static List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(new Anime(1L, "DBZ"), new Anime(2L, "Samurai X"), new Anime(3L, "Naruto")));
    }

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody);
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        animeRepository.deleteById(id);
    }

    public void replace(AnimePutResquetBody animePutResquetBody) {
        findByIdOrThrowBadRequestException(animePutResquetBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutResquetBody);
        animeRepository.save(anime);
    }
}
