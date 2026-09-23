package com.wafflestudio.spring2026.meeting.service

import com.wafflestudio.spring2026.meeting.MeetingNotFoundException
import com.wafflestudio.spring2026.meeting.model.Meeting
import com.wafflestudio.spring2026.meeting.repository.MeetingRepository
import org.springframework.stereotype.Service

@Service
class MeetingService(
    private val meetingRepository: MeetingRepository,
) {
    fun createMeeting(
        title: String,
        capacity: Int,
    ): Meeting = meetingRepository.save(title, capacity)

    fun getMeeting(id: Long): Meeting =
        meetingRepository.findById(id) ?: throw MeetingNotFoundException(id)

    fun getAllMeetings(): List<Meeting> = meetingRepository.findAll()

    fun updateMeeting(
        id: Long,
        title: String?,
        capacity: Int?,
    ): Meeting {
        val meeting = getMeeting(id)

        title?.let {
            if (it.isBlank()) {
                throw IllegalArgumentException("모임 제목은 비어 있을 수 없습니다.")
            }
            meeting.title = it
        }

        capacity?.let {
            meeting.capacity = it
        }

        return meeting
    }

    fun deleteMeeting(id: Long) {
        val isDeleted = meetingRepository.deleteById(id)
        if (!isDeleted) {
            throw MeetingNotFoundException(id)
        }
    }
}