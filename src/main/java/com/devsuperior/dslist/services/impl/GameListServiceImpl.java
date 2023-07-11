package com.devsuperior.dslist.services.impl;

import java.util.List;

import com.devsuperior.dslist.services.GameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameListServiceImpl implements GameListService {

	private final GameListRepository gameListRepository;

	private final GameRepository gameRepository;

	public GameListServiceImpl(GameListRepository gameListRepository, GameRepository gameRepository){
		this.gameListRepository = gameListRepository;
		this.gameRepository = gameRepository;
	}
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(GameListDTO::new).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {

		List<GameMinProjection> list = gameRepository.searchByList(listId);

		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);

		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
