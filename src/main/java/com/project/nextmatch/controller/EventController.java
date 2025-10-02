package com.project.nextmatch.controller;

import com.project.nextmatch.service.EventService;
import com.project.nextmatch.service.EventService.EventListResponse;
import com.project.nextmatch.dto.EventCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // POST /api/event (대회 생성)
    @PostMapping
    public ResponseEntity<Long> createEvent(@RequestBody EventCreateRequest request) {
        Long eventId = eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventId);
    }

    // GET /api/event/list (대회 목록 조회)
    // JS 코드가 요청하는 URL '/list'에 매핑
    @GetMapping("/list")
    // JS 코드가 'search' 파라미터를 넘기고 있으므로 @RequestParam으로 받습니다.
    public ResponseEntity<List<EventListResponse>> getEventList(
            @RequestParam(value = "search", required = false) String searchKeyword) {

        // 현재는 검색 로직이 없으므로, 모든 목록을 가져오는 서비스 메서드를 호출합니다.
        // 나중에 검색 기능을 구현할 때 eventService.getEventList(searchKeyword);를 사용합니다.
        List<EventListResponse> events = eventService.getEventList();

        return ResponseEntity.ok(events);
    }
}