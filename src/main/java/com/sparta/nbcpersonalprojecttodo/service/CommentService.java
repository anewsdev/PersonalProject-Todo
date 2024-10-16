package com.sparta.nbcpersonalprojecttodo.service;

import com.sparta.nbcpersonalprojecttodo.dto.CommentRequestDto;
import com.sparta.nbcpersonalprojecttodo.dto.CommentResponseDto;
import com.sparta.nbcpersonalprojecttodo.entity.Comment;
import com.sparta.nbcpersonalprojecttodo.entity.Todo;
import com.sparta.nbcpersonalprojecttodo.repository.CommentRepository;
import com.sparta.nbcpersonalprojecttodo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long todoId) {
        //todoId로 일정 있는지 확인
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()-> new IllegalArgumentException("해당 일정이 없습니다."));

        //comment 생성 시 연관관계 설정
        Comment comment = new Comment(requestDto);
        comment.setTodo(todo);

        //comment 저장
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public List<CommentResponseDto> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
