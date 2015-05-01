package us.nxgencity.map.events;

import com.synload.eventsystem.EventClass;
import com.synload.eventsystem.Handler;
import com.synload.framework.modules.annotations.Event.Type;

public class LoadSlideEvent extends EventClass {
	public String entryId;
	public LoadSlideEvent(String entryId){
		this.entryId = entryId;
		this.setHandler(Handler.EVENT);
		this.setType(Type.OTHER);
	}
	public LoadSlideEvent(long entryId){
		this.entryId = String.valueOf(entryId);
		this.setHandler(Handler.EVENT);
		this.setType(Type.OTHER);
	}
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
}
