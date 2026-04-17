package roomBooking;

import common.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class RoomBooking {

  // list of meetings against a room
  TreeMap<String, List<String> > roomMeetings = new TreeMap<>();

  // room, timing against a meeting
  Map<String, Pair<String, Pair<Integer, Integer>>> meetingDetails = new HashMap<>();

  public RoomBooking(List<String> roomIds){
    for(String id: roomIds){
      roomMeetings.put(id, new ArrayList<>());
    }
  }

  private boolean isRoomAvailable(String roomId, int startTime, int endTime){
    for(String meeting: roomMeetings.get(roomId)){
      int meetingStart = meetingDetails.get(meeting).second.first;
      int meetingEnd = meetingDetails.get(meeting).second.second;
      if(!(meetingEnd < startTime || meetingStart > endTime)){
        return false;
      }
    }
    return true;
  }

  public String bookMeeting(String meetingId, int startTime, int endTime){
    if(meetingDetails.containsKey(meetingId)){
      return "@@@";
    }
    for(String roomId: roomMeetings.keySet()){
      if(isRoomAvailable(roomId, startTime, endTime)){
        List<String> currentRoomMeetings = roomMeetings.get(roomId);
        currentRoomMeetings.add(meetingId);
        roomMeetings.put(roomId, currentRoomMeetings);
        meetingDetails.put(meetingId, new Pair<>(roomId, new Pair<>(startTime, endTime)));
        return roomId;
      }
    }
    return "@@@";
  }

  public boolean cancelMeeting(String meetingId){
    if(!meetingDetails.containsKey(meetingId)){
      return false;
    }
    roomMeetings.get(meetingDetails.get(meetingId).first).remove(meetingId);
    meetingDetails.remove(meetingId);
    return true;
  }

}
