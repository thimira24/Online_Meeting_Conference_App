package lanka.meeting.app.db

import lanka.meeting.app.model.Meeting
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Meeting::class], version = 1, exportSchema = false)
abstract class MeetlyDB : RoomDatabase() {

    abstract fun meetingDao(): MeetingDao

}