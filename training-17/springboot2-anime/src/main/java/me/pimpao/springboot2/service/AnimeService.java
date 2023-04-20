package me.pimpao.springboot2.service;

import lombok.RequiredArgsConstructor;
import me.pimpao.springboot2.domain.Anime;
import me.pimpao.springboot2.exception.BadRequestException;
import me.pimpao.springboot2.mapper.AnimeMapper;
import me.pimpao.springboot2.repository.AnimeRepository;
import me.pimpao.springboot2.request.AnimePostRequestBody;
import me.pimpao.springboot2.request.AnimePutRequestBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByNameOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody);
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        animeRepository.delete(findByNameOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        findByNameOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        animeRepository.save(anime);
    }

}
