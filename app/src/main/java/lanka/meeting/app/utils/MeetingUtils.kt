package lanka.meeting.app.utils

import lanka.core.extensions.toast
import lanka.meeting.app.R
import android.content.Context
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import com.google.firebase.auth.FirebaseAuth
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetUserInfo
import java.net.URL

object MeetingUtils {

    fun startMeeting(context: Context, meetingCode: String, @StringRes initialToastMessage: Int) {
        context.toast(context.getString(initialToastMessage))

        val serverUrl = URL(context.getString(R.string.app_server_url))
        val defaultOptions = JitsiMeetConferenceOptions.Builder()
            .setServerURL(serverUrl)
            .setWelcomePageEnabled(false)
            .setFeatureFlag("invite.enabled", false)
            .setFeatureFlag("live-streaming.enabled", true)
            .setFeatureFlag("meeting-name.enabled", false)
            .setFeatureFlag("call-integration.enabled", false)
            .setFeatureFlag("recording.enabled", false)
            .build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)

        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom(meetingCode)
            .setUserInfo(null)

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userInfoBundle = if (currentUser != null) {
            bundleOf(
                "displayName" to currentUser.displayName,
                "email" to currentUser.email,
                "avatarURL" to currentUser.photoUrl
            )
        } else {
            bundleOf(
                "displayName" to context.getString(
                    R.string.all_unauthenticated_user_name,
                    context.getString(R.string.app_name)
                )
            )
        }

        options.setUserInfo(JitsiMeetUserInfo(userInfoBundle))
        JitsiMeetActivity.launch(context, options.build())
    }
}