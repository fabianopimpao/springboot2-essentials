package me.pimpao.springboot2.mapper;

import me.pimpao.springboot2.domain.Anime;
import me.pimpao.springboot2.request.AnimePostResquestBody;
import me.pimpao.springboot2.request.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    Anime toAnime(AnimePostResquestBody animePostResquestBody);
    Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
