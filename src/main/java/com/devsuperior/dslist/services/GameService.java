package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GameService {

    GameDTO findById(@PathVariable Long listId);

    List<GameMinDTO> findAll();

    List<GameMinDTO> findByGameList(Long listId);

}
