package org.shark.appboard.board.controller;

import org.shark.appboard.board.dto.BoardDTO;
import org.shark.appboard.board.dto.response.ResponseDTO;
import org.shark.appboard.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;
  
  @GetMapping
  public ResponseEntity<ResponseDTO<Page<BoardDTO>>> list(
      @PageableDefault(page = 1, size = 5, sort = "createdAt", direction = Direction.DESC) Pageable pageable
  ) {
    Page<BoardDTO> boards = boardService.getAllBoards(pageable);
    
    ResponseDTO<Page<BoardDTO>> response = ResponseDTO.<Page<BoardDTO>>builder()
        .status(HttpStatus.OK.value())
        .message("게시글 목록 조회 성공")
        .data(boards)
        .build();
    
    return ResponseEntity.ok(response);
  }
  
  @GetMapping("/{bid}")
  public ResponseEntity<ResponseDTO<BoardDTO>> detail(@PathVariable(value = "bid") Long bid) {
    BoardDTO board = boardService.getBoardById(bid);
    
    ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder()
        .status(HttpStatus.OK.value())
        .message("게시글 조회 성공")
        .data(board)
        .build();
    
    return ResponseEntity.ok(response);
  }
  
  @PostMapping
  public ResponseEntity<ResponseDTO<BoardDTO>> create(@RequestBody BoardDTO boardDTO) {
    BoardDTO createdBoard = boardService.createBoard(boardDTO);
    
    ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder()
        .status(HttpStatus.CREATED.value())  // 201
        .message("게시글 생성 성공")
        .data(createdBoard)
        .build();
    
    return ResponseEntity.status(HttpStatus.CREATED).body(response);  // 201
  }
  
  @PutMapping("/{bid}")
  public ResponseEntity<ResponseDTO<BoardDTO>> update(
      @PathVariable(value = "bid") Long bid,
      @RequestBody BoardDTO boardDTO
  ) {
    BoardDTO updatedBoard = boardService.updateBoard(bid, boardDTO);
    
    ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder()
        .status(HttpStatus.OK.value())
        .message("게시글 수정 성공")
        .data(updatedBoard)
        .build();
    
    return ResponseEntity.ok(response);
  }
  
  @DeleteMapping("/{bid}")
  public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable(value = "bid") Long bid) {
    boardService.deleteBoard(bid);
    
    ResponseDTO<Void> response = ResponseDTO.<Void>builder()
        .status(HttpStatus.NO_CONTENT.value())  // 204
        .message("게시글 삭제 성공")
        .build();
    
    // return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);  // 204 (NO_CONTENT에 의해 부트가 body 삭제)
    return ResponseEntity.ok(response);  // 200
  }
  
}
