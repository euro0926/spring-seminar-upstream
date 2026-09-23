package com.wafflestudio.spring2026.meeting

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class MeetingNotFoundException(
    val meetingId: Long,
) : RuntimeException(
    "ID가 ${meetingId}인 모임을 찾을 수 없습니다.",
)